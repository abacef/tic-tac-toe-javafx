public class GameModel implements ViewListener {

    private String player1;

    private String player2;

    private ModelListener view1;

    private ModelListener view2;

    private boolean player1IsX;

    private String partnerHost;

    @Override
    public synchronized void join(ModelListener view, String name) {

    }

    @Override
    public synchronized void squareChosen(ModelListener view, int i) {

    }

    @Override
    public synchronized void newGame(ModelListener view) {

    }

    @Override
    public synchronized void quit(ModelListener view) {

    }

    public void connect(String host) {
        this.partnerHost = host;
        notifyAll();
    }

    public String getPartnerHost() {
        return partnerHost;
    }

    public void setPlayer1IsX(boolean player1IsX) {
        this.player1IsX = player1IsX;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}