import java.util.Random;
import java.util.Vector;

public class MatrixMultiply {

    public static Random r = new Random();
    public static int cols1 = r.nextInt(5) + 3;
    public static int rows1 = r.nextInt(5) + 3;
    public static int cols2 = rows1;
    public static int rows2 = cols1;

    public static void main(String[] args) {
        Vector<Vector<Integer>> data1 = new Vector<>();
        Vector<Vector<Integer>> data2 = new Vector<>();

        for (int i = 0; i < rows1; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < cols1; j++) {
                int temp = i + j;
                tempRow.addElement(temp);
            }
            data1.addElement(tempRow);
        }

        for (int i = 0; i < rows2; i++) {
            Vector<Integer> tempRow = new Vector<>();
            for (int j = 0; j < cols2; j++) {
                int temp = i * j;
                tempRow.addElement(temp);
            }
            data2.addElement(tempRow);
        }
        Vector<Vector<Integer>> data3 = matrixMultiply(data1, data2);

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

    public static Vector<Vector<Integer>> matrixMultiply(Vector<Vector<Integer>> data1, Vector<Vector<Integer>> data2) {
        Vector<Vector<Integer>> data3 = new Vector<>();
        if (cols1 != rows2)
            throw new IllegalArgumentException("Incompatible dimensions.");
        else {
            for (int i = 0; i < rows1; i++) {
                Vector<Integer> tempRow = new Vector<>();
                for (int j = 0; j < cols2; j++) {
                    int sum = 0;
                    for (int k = 0; k < cols1; k++)
                        sum += data1.elementAt(i).elementAt(k) * data2.elementAt(k).elementAt(j);
                    tempRow.addElement(sum);
                }
                data3.addElement(tempRow);
            }
        }
        return data3;
    }
}