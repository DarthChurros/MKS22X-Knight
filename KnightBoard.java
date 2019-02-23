import java.util.Arrays;

public class KnightBoard {
  private int[][] board;
  private int[][] outgoing;

  public KnightBoard(int r, int c) {
    if (r <= 0 || c <= 0) throw new IllegalArgumentException("Board dimensions must be positive!");
    board = new int[r][c];
    outgoing = new int[r][c];
    fillOutgoing();
  }

  private void fillOutgoing() {
    for (int i = 0; i < outgoing.length; i++) {
      for (int j = 0; j < outgoing[i].length; j++) {
        outgoing[i][j] = 8;
        if ((i == 1 || i == outgoing.length - 2) || (j == 1 || j == outgoing[i].length - 2)) outgoing[i][j] = 6;
        if ((i == 1 || i == outgoing.length - 2) && (j == 1 || j == outgoing[i].length - 2)) outgoing[i][j] = 4;
        if (i == 0 || i == outgoing.length - 1 || j == 0 || j == outgoing[i].length - 1) {
          if (outgoing[i][j] == 6) {
            outgoing[i][j] = 3;
          } else {
            outgoing[i][j] = 4;
          }
        }
        if ((i == 0 || i == outgoing.length - 1) && (j == 0 || j == outgoing[i].length - 1)) outgoing[i][j] = 2;
      }
    }
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
    fillOutgoing();
    return solve(1, startR, startC);
  }

  private boolean solve(int moves, int r, int c) {
    if (r < 0 || r >= board.length || c < 0 || c >= board[r].length) return false;
    if (board[r][c] != 0) return false;
    board[r][c] = moves;
    if (moves >= board.length * board[r].length) return true;
    int[] options = getMoves(r, c);
    for (int i = 0; i < options.length; i++) {
      outgoing[moveKnight(r, c, options[i])[0]][moveKnight(r, c, options[i])[1]]--;
    }
    System.out.println(this);
    System.out.println(outgoingStr());
    System.out.println(Arrays.toString(options) + "\n___________________");
    outgoing[r][c]--;
    for (int i = 0; i < 8; i++) {
      if (options.length > 0) {
        if (solve(moves + 1, moveKnight(r, c, options[i])[0], moveKnight(r, c, options[i])[1])) return true;
      }
    }
    outgoing[r][c]++;
    for (int i = 0; i < options.length; i++) {
      outgoing[moveKnight(r, c, options[i])[0]][moveKnight(r, c, options[i])[1]]++;
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
    fillOutgoing();
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

  private String outgoingStr() {
    String ans = "";
    for (int i = 0; i < outgoing.length; i++) {
      for (int j = 0; j < outgoing[i].length; j++) {
        if (outgoing.length * outgoing[i].length > 10 && outgoing[i][j] < 10) ans += " ";
        ans += outgoing[i][j] + " ";
      }
      ans += "\n";
    }
    return ans;
  }

  private int[] getMoves(int r, int c) {
    int[] moves = new int[outgoing[r][c]];
    int size = 0;
    for (int i = 0; i < 8; i++) {
      int[] move = moveKnight(r, c, i);
      if (move[0] >= 0 && move[0] < board.length && move[1] >= 0 && move[1] < board[r].length && board[move[0]][move[1]] == 0) {
        moves[size++] = i;
      }
    }
    int temp, j;
    for (int i = 1; i < moves.length; i++) {
      temp = moves[i];
      j = i - 1;
      while (j >= 0 && outgoing[moveKnight(r, c, moves[j])[0]][moveKnight(r, c, moves[j])[1]] > outgoing[moveKnight(r, c, moves[i])[0]][moveKnight(r, c, moves[i])[1]]) {
        moves[j+1] = moves[j];
        j--;
      }
      moves[j+1] = temp;
    }
    return moves;
  }

  public static void main(String[] args) {
    KnightBoard test = new KnightBoard(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
    System.out.println(test.solve(0, 0));
    //System.out.println(test);
  }
}


/*
   7 3
 6     2
 0     4
   1 5
*/
