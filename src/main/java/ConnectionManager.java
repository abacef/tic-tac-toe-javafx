import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionManager {

    public static final int STARTING_PORT = 4570;

    private String partnerHost;

    private int partnerPort;

    private String myHost;

    private int myPort;

    public void setPartnerHost(String host) {
        this.partnerHost = host;
    }

    public void setPartnerPort(int port) {
        this.partnerPort = port;
    }

    public void setMyHost(String host) {
        this.myHost = host;
    }

    public void setMyPort(int port) {
        this.myPort = port;
    }

    public String getMyHost() {
        return myHost;
    }

    public int getMyPort() {
        return myPort;
    }

    public void iGotYouFirst() {
        try {
            System.out.println(InetAddress.getLocalHost().getCanonicalHostName());
        }
        catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        }
    }

    public void youGotMeFirst() {

    }
}