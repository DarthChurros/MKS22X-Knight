public class KnightBoard {
  private boolean[][] board;
  private int[][] outgoing;

  public KnightBoard(int r, int c) {
    if (r <= 0 || c <= 0) throw new IllegalArgumentException("Board dimensions must be positive!");
    board = new boolean[r][c];
    outgoing = new int[r][c];
  }

  private int[] moveKnight(int r, int c, int direction) {
    if (direction < 0 || direction > 7) throw new IllegalArgumentException("Direction must be from 0 to 7");
    if (!board[r][c]) throw new IllegalStateException("No knight has reached these coordinates");
    int[] destination = new int[2];
    destination[0] = r + (int)Math.pow(-1, (direction / 2) % 2) * (direction % 2 + 1);
    destination[1] = c + (int)Math.pow(-1, (direction / 2 + 1) % 2) * ((direction + 1) % 2 + 1);
    return destination;
  }
}

/*
r(0) = 1    c(0) = 2
r(1) = 2    c(1) = 1
r(2) = -1   c(2) = -2
r(3) = -2   c(3) = -1
r(4) = 1    c(4) = -2
r(5) = 2    c(5) = -1
r(6) = -1   c(6) = 2
r(7) = -2   c(7) = 1
*/
