package TCPlogic;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class TicTacToeP2P {

    private static final int PORT_80 = 80;

    public TicTacToeP2P() {
        String host = getMyHost();
        if (host == null) {
            // catch not connected to the internet
        }
        try {
            boolean iAmClient;
            Socket socket = new Socket();
            try {
                socket.connect(new InetSocketAddress(host, PORT_80));
                iAmClient = true;
            }
            catch (IOException ioe) {
                iAmClient = false;
            }

            // Connection succeeded; I am the "client" peer.
            if (iamClient) {
                // Set up view and model proxy.
                TicTacToeView view = TicTacToeView.create (name);
                ModelProxy proxy = new ModelProxy(socket);
                view.setListener(proxy);
                proxy.setListener(view);

                // Tell server I joined the game.
                proxy.join(view, name);
            }

            // Connection failed; I am the "server" peer.
            else {
                // Listen for a connection from the "client" peer.
                ServerSocket serversocket = new ServerSocket();
                serversocket.bind(new InetSocketAddress (host, port));

                // Set up model and view.
                TicTacToeModel model = new TicTacToeModel();
                TicTacToeView view = TicTacToeView.create (name);
                view.setListener (model);

                // Tell model I joined the game.
                model.join (view, name);

                // Accept a connection from the "client" peer.
                socket = serversocket.accept();
                serversocket.close();

                // Set up view proxy object.
                ViewProxy proxy = new ViewProxy (socket);
                proxy.setListener (model);
            }
        }

        catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    private String getMyHost() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                InetAddress addr = addresses.nextElement();
                return addr.getHostAddress();
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Huh! It seems you are not connected to the " +
                "internet");
        return null;
    }
}
