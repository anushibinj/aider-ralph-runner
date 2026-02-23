import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testCellRetrieval() {
        Board board = new Board();
        assertEquals(Player.EMPTY, board.getCell(0, 0));
    }

    @Test
    public void testCellUpdate() {
        Board board = new Board();
        board.setCell(0, 0, Player.X);
        assertEquals(Player.X, board.getCell(0, 0));
    }

    @Test
    public void testOutOfBounds() {
        Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> board.getCell(3, 0));
        assertThrows(IllegalArgumentException.class, () -> board.setCell(3, 0, Player.X));
    }

    @Test
    public void testIsFull() {
        Board board = new Board();
        assertFalse(board.isFull());
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.setCell(row, col, Player.X);
            }
        }
        assertTrue(board.isFull());
    }
}
