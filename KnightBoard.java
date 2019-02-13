public class KnightBoard {
  private boolean[][] board;
  private int[][] outgoing;

  public KnightBoard(int r, int c) {
    if (r <= 0 || c <= 0) throw new IllegalArgumentException("Board dimensions must be positive!");
    board = new boolean[r][c];
    outgoing = new int[r][c];
  }
}
