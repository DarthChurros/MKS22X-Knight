public class KnightBoard {
  private int[][] board;
  private int[][] outgoing;

  public KnightBoard(int r, int c) {
    if (r <= 0 || c <= 0) throw new IllegalArgumentException("Board dimensions must be positive!");
    board = new int[r][c];
    outgoing = new int[r][c];
  }

  private int[] moveKnight(int r, int c, int direction) {
    if (direction < 0 || direction > 7) throw new IllegalArgumentException("Direction must be from 0 to 7");
    if (board[r][c] == 0) throw new IllegalStateException("No knight has reached these coordinates");
    r = r + (int)Math.pow(-1, (direction / 2) % 2) * (direction % 2 + 1);
    c = c + ((int)Math.pow(-1, (direction / 2 + 1) % 2) * ((direction + 1) % 2 + 1) * (int)(Math.pow(-1, direction / 4)));
    int[] ans = new int[2];
    ans[0] = r;
    ans[1] = c;
    return ans;
  }

  public boolean solve(int startR, int startC) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != 0) throw new IllegalStateException("Board must be empty to solve!");
      }
    }
    if (startR < 0 || startR >= board.length || startC < 0 || startC >= board[0].length) throw new IllegalArgumentException("Coordinates out of bounds!");
    return solve(1, startR, startC);
  }

  private boolean solve(int moves, int r, int c) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[r].length) return false;
    if (board[r][c] != 0) return false;
    board[r][c] = moves;
    if (moves >= board.length * board[r].length) return true;
    //System.out.println(this);
    for (int i = 0; i < 8; i++) {
      if (solve(moves + 1, moveKnight(r, c, i)[0], moveKnight(r, c, i)[1])) return true;
    }
    board[r][c] = 0;
    return false;
  }

  public int countSolutions(int startR, int startC) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] != 0) throw new IllegalStateException("Board must be empty to count solutions!");
      }
    }
    return count(1, startR, startC);
  }

  private int count(int moves, int r, int c) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[r].length) return 0;
    if (board[r][c] != 0) return 0;
    if (moves >= board.length * board[r].length) return 1;
    int solutions = 0;
    board[r][c] = moves;
    //System.out.println(this);
    for (int i = 0; i < 8; i++) {
      solutions += count(moves + 1, moveKnight(r, c, i)[0], moveKnight(r, c, i)[1]);
    }
    board[r][c] = 0;
    return solutions;
  }

  public String toString() {
    String ans = "";
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board.length * board[i].length > 10 && board[i][j] < 10) ans += " ";
        if (board[i][j] != 0) {
          ans += board[i][j] + " ";
        } else {
          ans += "_ ";
        }
      }
      ans += "\n";
    }
    return ans;
  }

  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println(test.countSolutions(0, 0));
    //System.out.println(test);
  }
}

/*
   7 3
 6     2
 0     4
   1 5
*/
