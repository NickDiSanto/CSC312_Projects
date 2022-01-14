import java.util.Random;
import java.util.Vector;

public class MatrixMultiplyRecursive {

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

        Vector<Vector<Integer>> data3 = matrixMultiplyRecursive(data1, data2);

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

    public static Vector<Vector<Integer>> matrixMultiplyRecursive(Vector<Vector<Integer>> data1, Vector<Vector<Integer>> data2) {

        int numRows = data1.size();
        Vector<Vector<Integer>> ret = new Vector<>();

        for (int i = 0; i < numRows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < numRows; j++)
                tempRow.addElement(0);
            ret.addElement(tempRow);
        }

        if (numRows == 1)
            ret.elementAt(0).set(0, data1.elementAt(0).elementAt(0) * data2.elementAt(0).elementAt(0));
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

            Vector<Vector<Integer>> C11 = matrixAdd(matrixMultiplyRecursive(A11, B11), matrixMultiplyRecursive(A12, B21));
            Vector<Vector<Integer>> C12 = matrixAdd(matrixMultiplyRecursive(A11, B12), matrixMultiplyRecursive(A12, B22));
            Vector<Vector<Integer>> C21 = matrixAdd(matrixMultiplyRecursive(A21, B11), matrixMultiplyRecursive(A22, B21));
            Vector<Vector<Integer>> C22 = matrixAdd(matrixMultiplyRecursive(A21, B12), matrixMultiplyRecursive(A22, B22));

            for (int i = 0; i < numRows / 2; i++) {
                for (int j = 0; j < numRows / 2; j++)
                    ret.elementAt(i).set(j, C11.elementAt(i).elementAt(j));
            }
            for (int i = 0; i < numRows / 2; i++) {
                for (int j = numRows / 2; j < numRows; j++)
                    ret.elementAt(i).set(j, C12.elementAt(i).elementAt(j - numRows / 2));
            }
            for (int i = numRows / 2; i < numRows; i++) {
                for (int j = 0; j < numRows / 2; j++)
                    ret.elementAt(i).set(j, C21.elementAt(i - numRows / 2).elementAt(j));
            }
            for (int i = numRows / 2; i < numRows; i++) {
                for (int j = numRows / 2; j < numRows; j++)
                    ret.elementAt(i).set(j, C22.elementAt(i - numRows / 2).elementAt(j - numRows / 2));
            }
        }

        return ret;
    }

    private static Vector<Vector<Integer>> matrixAdd(Vector<Vector<Integer>> data1, Vector<Vector<Integer>> data2) {

        int numRows = data1.size();
        Vector<Vector<Integer>> ret = new Vector<>();

        for (int i = 0; i < numRows; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < numRows; j++)
                tempRow.addElement(data1.elementAt(i).elementAt(j) + data2.elementAt(i).elementAt(j));
            ret.addElement(tempRow);
        }

        return ret;
    }
}