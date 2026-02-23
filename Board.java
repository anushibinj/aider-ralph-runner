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

    public boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

    public Player getCell(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return this.grid[row][col];
    }

    public void setCell(int row, int col, Player player) {
        if (!isValidCoordinate(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        this.grid[row][col] = player;
    }

    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.grid[i][j] == Player.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
