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
    r = r + (int)Math.pow(-1, (direction / 2) % 2) * (direction % 2 + 1);
    c = c + ((int)Math.pow(-1, (direction / 2 + 1) % 2) * ((direction + 1) % 2 + 1) * (int)(Math.pow(-1, direction / 4)));
    if (board[r][c]) throw new IllegalArgumentException("Point has already been reached");
    int[] ans = new int[2];
    ans[0] = r;
    ans[1] = c;
    return ans;
  }
}

/*
   3 7
 2     6
 4     0
   5 1
*/
