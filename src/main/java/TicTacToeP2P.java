import java.io.IOException;
import java.net.*;

public class TicTacToeP2P implements Runnable {

    private String host;

    private String name;

    private GamePlayController view;

    public static final int PORT_80 = 80;

    public static final String LOCALHOST = "localhost";

    public TicTacToeP2P(String name) {
        this.host = "Bogous Host";
        this.name = name;
    }

    /**
     * Called when a partners host has been input. on the main UI
     * @param partnerHost
     */
    public TicTacToeP2P(String partnerHost, String name) {
        this.host = partnerHost;
        this.name = name;
    }

    public void setView(GamePlayController view) {
        this.view = view;
    }

    @Override
    public void run() {
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
            if (iAmClient) {
                // Set up view and model proxy.
                view.setMyName(name);
                ModelProxy proxy = new ModelProxy(socket);
                view.setListener(proxy);
                proxy.setListener(view);

                // Tell server I joined the game.
                proxy.join(view, name);
            }

            // Connection failed; I am the "server" peer.
            else {
                // Listen for a connection from myself for the sole purpose
                // of either trying to connect to another host, or the app
                // has quit
                ServerSocket serversocket = new ServerSocket();
                serversocket.bind(new InetSocketAddress(LOCALHOST, PORT_80));

                // Set up model and view.
                GameModel model = new GameModel();
                view.setListener(model);

                // Tell model I joined the game.
                model.join(view, name);

                // Accept a connection from the "client" peer.
                socket = serversocket.accept();
                serversocket.close();

                // Set up view proxy object.
                ViewProxy proxy = new ViewProxy(socket);
                proxy.setListener(model);

                synchronized (TicTacToeP2P.this) {
                    try {
                        System.out.println("waiting");
                        wait(1000);
                    } catch (InterruptedException ie) {

                    }
                }
                System.out.println("doneWaiting");
                if (model.getPartnerHost() == null) {
                    return;
                }
                socket = new Socket();
                socket.connect(new InetSocketAddress(model.getPartnerHost(),
                        PORT_80));
                System.out.println("\tconnected");
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
