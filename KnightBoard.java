public class KnightBoard {
  private boolean[][] board;
  private int r;
  private int c;
  private int[][] outgoing;

  public KnightBoard(int r, int c) {
    if (r <= 0 || c <= 0) throw new IllegalArgumentException("Board dimensions must be positive!");
    board = new boolean[r][c];
    outgoing = new int[r][c];
  }

  private int[] moveKnight(int direction) {
    if (direction < 0 || direction > 7) throw new IllegalArgumentException("Direction must be from 0 to 7");
    if (!board[r][c]) throw new IllegalStateException("No knight has reached these coordinates");
    r = r + (int)Math.pow(-1, (direction / 2) % 2) * (direction % 2 + 1);
    c = c + ((int)Math.pow(-1, (direction / 2 + 1) % 2) * ((direction + 1) % 2 + 1) * (int)(Math.pow(-1, direction / 4)));
    if (board[r][c]) throw new IllegalArgumentException("Point has already been reached");
    board[r][c] = true;
    int[] ans = new int[2];
    ans[0] = r;
    ans[1] = c;
    return ans;
  }

  public boolean solve(int startR, int startC) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j]) throw new IllegalStateException("Board must be empty to solve!");
      }
    }
    r = startR;
    c = startC;
    board[r][c] = true;
    return false;
  }

  public String toString() {
    String ans = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j]) {
          if (r == i && c == j) {
            ans += "N ";
          } else {
            ans += "X ";
          }
        } else {
          ans += "_ ";
        }
      }
      ans += "\n";
    }
    return ans;
  }

  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(5, 5);
    System.out.println(test);
    test.moveKnight(4);
    System.out.println(test);
    test.moveKnight(2);
    System.out.println(test);
    test.moveKnight(1);
    System.out.println(test);
    test.moveKnight(5);
    System.out.println(test);

  }
}

/*
   7 3
 6     2
 0     4
   1 5
*/
