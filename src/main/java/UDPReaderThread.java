import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReaderThread implements Runnable {

    private DatagramSocket mailbox;

    private ConnectionManager manager;

    public UDPReaderThread(DatagramSocket mailbox, ConnectionManager manager) {
        this.mailbox = mailbox;
        this.manager = manager;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[128];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        ByteArrayInputStream bais;
        DataInputStream in;
        byte b;
        try {
            forever: for (;;) {
                mailbox.receive(packet);
                bais = new ByteArrayInputStream(buffer, 0, packet.getLength());
                in = new DataInputStream(bais);
                b = in.readByte();
                switch (b) {
                    case 'H':
                        manager.setPartnerHost(in.readUTF());
                        break;
                    case 'P':
                        manager.setPartnerPort(in.readInt());
                        break forever;
                    default:
                        System.err.println("Bad Message");
                        break;
                }
            }
        }
        catch (IOException ioe) {
            // squash
        }
        finally {
            mailbox.close();
            manager.youGotMeFirst();
        }
    }
}
