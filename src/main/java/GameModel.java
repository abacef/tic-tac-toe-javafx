public class GameModel {

    private String player1;

    private String player2;

    private boolean player1IsX;

    public GameModel(String player1, String player2, boolean player1IsX) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1IsX = player1IsX;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}