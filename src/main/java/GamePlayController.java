import javafx.fxml.FXML;


// view
public class GamePlayController implements ModelListener {

    private GameModel model;

    private String myName;

    private String theirName;

    private ViewListener listener;

    @FXML
    private void initialize() {
        // cosas
    }

    public void setMyName(String name) {
        this.myName = name;
    }

    public void setTheirName(String name) {
        this.theirName = name;
    }

    public void setListener(ViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void newGame() {
        
    }

    @Override
    public void setMark(int i, Mark mark) {

    }

    @Override
    public void setWin(int i) {

    }

    /*
    @Override
    public void waitingForPartner() {

    }
    */

    @Override
    public void yourTurn() {

    }

    @Override
    public void otherTurn(String name) {

    }

    @Override
    public void youWin() {

    }

    @Override
    public void otherWin(String name) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void quit() {
        System.exit(0);
    }
}