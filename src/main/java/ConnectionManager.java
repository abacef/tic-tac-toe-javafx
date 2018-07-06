import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ConnectionManager {

    private static final int STARTING_PORT = 4570;

    private String partnerHost;

    private int partnerPort;

    private String myHost;

    private int myPort;

    private DatagramSocket myMailbox;

    private InetSocketAddress myAddress;

    public ConnectionManager(Stage primaryStage, String player1) {
        try {
            myHost = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
        myPort = STARTING_PORT;

        myAddress = new InetSocketAddress(myHost, myPort);
        myMailbox = null;
        boolean success = false;
        while (!success) {
            try {
                myMailbox = new DatagramSocket(myAddress);
                success = true;
            }
            catch (SocketException se) {
                myPort++;
            }
        }

        Thread reader = new Thread(new UDPReaderThread());
        reader.start();

        // load window
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource
                    ("fxml/hOnlineConnect.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Wait or Enter Partners Information");
            Parent root = loader.load();
            stage.setScene(new Scene(root, 400, 305));
            stage.initOwner(primaryStage);
            stage.setOnCloseRequest(event -> {
                DatagramPacket packet = new DatagramPacket(new byte[]
                        {'Q'}, 1, myAddress);
                try {
                    myMailbox.send(packet);
                }
                catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            });

            HOnlineConnectController controller = loader.getController();
            controller.setInitializingPlayerName(player1);
            controller.setManager(this);

            stage.showAndWait();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private class UDPReaderThread implements Runnable {
        @Override
        public void run() {
            byte[] buffer = new byte[128];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            ByteArrayInputStream bais;
            DataInputStream in;
            byte b;
            try {
                forever: for (;;) {
                    myMailbox.receive(packet);
                    bais = new ByteArrayInputStream(buffer, 0, packet.getLength());
                    in = new DataInputStream(bais);
                    b = in.readByte();
                    switch (b) {
                        case 'H':
                            partnerHost = in.readUTF();
                            System.out.println("\tYour partner's host is " +
                                    partnerHost);
                            break;
                        case 'P':
                            partnerPort = in.readInt();
                            System.out.println("\tYour partner's port is " +
                                    partnerPort);
                        case 'Q':
                            System.out.println("\tClosing connection");
                            break forever;
                        default:
                            System.err.println("Bad Message");
                            break;
                    }
                }
            }
            catch (IOException ioe) {
                System.out.println("Interrupted?!?!?!?!??");
            }
            finally {
                myMailbox.close();
            }
        }
    }

    public InetSocketAddress getMyAddress() {
        return myAddress;
    }

    public DatagramSocket getMyMailbox() {
        return myMailbox;
    }

    public String getMyHost() {
        return myHost;
    }

    public int getMyPort() {
        return myPort;
    }

    public void setPartnerHost(String partnerHost) {
        this.partnerHost = partnerHost;
    }

    public void setPartnerPort(int partnerPort) {
        this.partnerPort = partnerPort;
    }

    @Override
    public String toString() {
        return "myHost: " + myHost + "\nmyPort: " + myPort + "\npartnerHost: " +
                partnerHost + "\npartnerPort: " + partnerPort;
    }
}