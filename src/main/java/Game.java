public class Game {
    private Board board;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Player.X;
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
}
