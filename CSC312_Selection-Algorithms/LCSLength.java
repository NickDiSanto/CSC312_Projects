
public class LCSLength {
    public static int[][] c = new int[8][7];

    public static void main(String[] args) {
        String[] X = new String[] {"A", "B", "C", "B", "D", "A", "B"};
        String[] Y = new String[] {"B", "D", "C", "A", "B", "A"};
        int i = X.length - 1;
        int j = Y.length - 1;
        LCS_Length(X, Y);
        Print_LCS(X, Y, i, j);
    }

    private static void LCS_Length(String[] X, String[] Y) {
        int m = X.length;
        int n = Y.length;
        for (int i = 1; i <= m; i++)
            c[i][0] = 0;
        for (int j = 0; j <= n; j++)
            c[0][j] = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (X[i].equals(Y[j])) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                }
                else if (c[i - 1][j] >= c[i][j - 1])
                    c[i][j] = c[i - 1][j];
                else
                    c[i][j] = c[i][j - 1];
            }
        }
    }

    public static void Print_LCS(String[] X, String[] Y, int i, int j) {
        if (i == 0 || j == 0)
            return;
        if (X[i].equals(Y[j])) {
            Print_LCS(X, Y, i - 1, j - 1);
            System.out.println(X[i]);
        }
        else if (c[i - 1][j] >= c[i][j - 1])
            Print_LCS(X, Y, i - 1, j);
        else
            Print_LCS(X, Y, i, j - 1);
    }
}