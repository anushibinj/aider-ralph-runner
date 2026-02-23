public class Board {
    private Player[][] grid;

    public Board() {
        this.grid = new Player[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.grid[i][j] = Player.EMPTY;
            }
        }
    }
}
