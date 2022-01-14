import java.util.Random;
import java.util.Vector;

public class StrassenMatrixMultiplyRecursive {

    public static void main(String[] args) {

        Vector<Vector<Integer>> data1 = new Vector<>();
        Vector<Vector<Integer>> data2 = new Vector<>();

        Random r = new Random();
        int rows = 1;
        for (int i = 1; i <= (r.nextInt(4) + 1); i++)
            rows *= 2;

        for (int i = 0; i < rows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < rows; j++) {
                int temp = i + j;
                tempRow.addElement(temp);
            }
            data1.addElement(tempRow);
        }

        for (int i = 0; i < rows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < rows; j++) {
                int temp = i * j;
                tempRow.addElement(temp);
            }
            data2.addElement(tempRow);
        }
        Vector<Vector<Integer>> data3 = strassenMatrixMultiplyRecursive(data1, data2);

        for (int i = 0; i < data1.size(); i++) {
            System.out.println(data1.elementAt(i));
        }
        System.out.println();

        for (int i = 0; i < data2.size(); i++) {
            System.out.println(data2.elementAt(i));
        }
        System.out.println();

        for (int i = 0; i < data3.size(); i++) {
            System.out.println(data3.elementAt(i));
        }
    }

    public static Vector<Vector<Integer>> strassenMatrixMultiplyRecursive(Vector<Vector<Integer>> data1, Vector<Vector<Integer>> data2) {

        int numRows = data1.size();
        Vector<Vector<Integer>> data3 = new Vector<>();

        for (int i = 0; i < numRows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < numRows; j++)
                tempRow.addElement(0);
            data3.addElement(tempRow);
        }

        if (numRows == 1)
            data3.elementAt(0).set(0, data1.elementAt(0).elementAt(0) * data2.elementAt(0).elementAt(0));
        else {
            Vector<Vector<Integer>> A11 = new Vector<>();
            Vector<Vector<Integer>> A12 = new Vector<>();
            Vector<Vector<Integer>> A21 = new Vector<>();
            Vector<Vector<Integer>> A22 = new Vector<>();

            Vector<Vector<Integer>> B11 = new Vector<>();
            Vector<Vector<Integer>> B12 = new Vector<>();
            Vector<Vector<Integer>> B21 = new Vector<>();
            Vector<Vector<Integer>> B22 = new Vector<>();

            for (int i = 0; i < numRows / 2; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = 0; j < numRows / 2; j++)
                    tempRow.addElement(data1.elementAt(i).elementAt(j));
                A11.addElement(tempRow);
            }
            for (int i = 0; i < numRows / 2; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = numRows / 2; j < numRows; j++)
                    tempRow.addElement(data1.elementAt(i).elementAt(j));
                A12.addElement(tempRow);
            }
            for (int i = numRows / 2; i < numRows; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = 0; j < numRows / 2; j++)
                    tempRow.addElement(data1.elementAt(i).elementAt(j));
                A21.addElement(tempRow);
            }
            for (int i = numRows / 2; i < numRows; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = numRows / 2; j < numRows; j++)
                    tempRow.addElement(data1.elementAt(i).elementAt(j));
                A22.addElement(tempRow);
            }

            for (int i = 0; i < numRows / 2; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = 0; j < numRows / 2; j++)
                    tempRow.addElement(data2.elementAt(i).elementAt(j));
                B11.addElement(tempRow);
            }
            for (int i = 0; i < numRows / 2; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = numRows / 2; j < numRows; j++)
                    tempRow.addElement(data2.elementAt(i).elementAt(j));
                B12.addElement(tempRow);
            }
            for (int i = numRows / 2; i < numRows; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = 0; j < numRows / 2; j++)
                    tempRow.addElement(data2.elementAt(i).elementAt(j));
                B21.addElement(tempRow);
            }
            for (int i = numRows / 2; i < numRows; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = numRows / 2; j < numRows; j++)
                    tempRow.addElement(data2.elementAt(i).elementAt(j));
                B22.addElement(tempRow);
            }

            // "create using doubly nested parallel for loops"
            Vector<Vector<Integer>> S1 = matrixSubtract(B12, B22);
            Vector<Vector<Integer>> S2 = matrixAdd(A11, A12);
            Vector<Vector<Integer>> S3 = matrixAdd(A21, A22);
            Vector<Vector<Integer>> S4 = matrixSubtract(B21, B11);
            Vector<Vector<Integer>> S5 = matrixAdd(A11, A22);
            Vector<Vector<Integer>> S6 = matrixAdd(B11, B22);
            Vector<Vector<Integer>> S7 = matrixSubtract(A12, A22);
            Vector<Vector<Integer>> S8 = matrixAdd(B21, B22);
            Vector<Vector<Integer>> S9 = matrixSubtract(A11, A21);
            Vector<Vector<Integer>> S10 = matrixAdd(B11, B12);

            // "recursively spawn the computation of seven n/2 x n/2 matrix products"
            Vector<Vector<Integer>> P1 = strassenMatrixMultiplyRecursive(A11, S1);
            Vector<Vector<Integer>> P2 = strassenMatrixMultiplyRecursive(S2, B22);
            Vector<Vector<Integer>> P3 = strassenMatrixMultiplyRecursive(S3, B11);
            Vector<Vector<Integer>> P4 = strassenMatrixMultiplyRecursive(A22, S4);
            Vector<Vector<Integer>> P5 = strassenMatrixMultiplyRecursive(S5, S6);
            Vector<Vector<Integer>> P6 = strassenMatrixMultiplyRecursive(S7, S8);
            Vector<Vector<Integer>> P7 = strassenMatrixMultiplyRecursive(S9, S10);

            // "compute using doubly nested parallel for loops"
            Vector<Vector<Integer>> C11 = matrixAdd(matrixSubtract(matrixAdd(P5, P4), P2), P6);
            Vector<Vector<Integer>> C12 = matrixAdd(P1, P2);
            Vector<Vector<Integer>> C21 = matrixAdd(P3, P4);
            Vector<Vector<Integer>> C22 = matrixSubtract(matrixSubtract(matrixAdd(P5, P1), P3), P7);

            for (int i = 0; i < numRows / 2; i++) {
                for (int j = 0; j < numRows / 2; j++)
                    data3.elementAt(i).set(j, C11.elementAt(i).elementAt(j));
            }
            for (int i = 0; i < numRows / 2; i++) {
                for (int j = numRows / 2; j < numRows; j++)
                    data3.elementAt(i).set(j, C12.elementAt(i).elementAt(j - numRows / 2));
            }
            for (int i = numRows / 2; i < numRows; i++) {
                for (int j = 0; j < numRows / 2; j++)
                    data3.elementAt(i).set(j, C21.elementAt(i - numRows / 2).elementAt(j));
            }
            for (int i = numRows / 2; i < numRows; i++) {
                for (int j = numRows / 2; j < numRows; j++)
                    data3.elementAt(i).set(j, C22.elementAt(i - numRows / 2).elementAt(j - numRows / 2));
            }
        }

        return data3;
    }

    public static Vector<Vector<Integer>> matrixAdd(Vector<Vector<Integer>> data1, Vector<Vector<Integer>> data2) {

        int numRows = data1.size();
        Vector<Vector<Integer>> data3 = new Vector<>();

        for (int i = 0; i < numRows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < numRows; j++)
                tempRow.addElement(data1.elementAt(i).elementAt(j) + data2.elementAt(i).elementAt(j));
            data3.addElement(tempRow);
        }

        return data3;
    }

    private static Vector<Vector<Integer>> matrixSubtract(Vector<Vector<Integer>> data1, Vector<Vector<Integer>> data2) {

        int numRows = data1.size();
        Vector<Vector<Integer>> data3 = new Vector<>();

        for (int i = 0; i < numRows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < numRows; j++)
                tempRow.addElement(data1.elementAt(i).elementAt(j) - data2.elementAt(i).elementAt(j));
            data3.addElement(tempRow);
        }

        return data3;
    }
}