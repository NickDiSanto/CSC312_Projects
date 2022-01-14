import java.util.Random;
import java.util.Vector;

public class QuickSort {
    public static void main(String[] args) {
        Vector<Integer> numbers = new Vector<>();
        numbers.addElement(12);
        numbers.addElement(4);
        numbers.addElement(2);
        numbers.addElement(9);
        numbers.addElement(11);
        numbers.addElement(16);
        numbers.addElement(1);
        numbers.addElement(5);
        numbers.addElement(3);
        numbers.addElement(8);

        sort(numbers, 0, numbers.size() - 1);
        for (int i = 0; i < numbers.size() - 1; i++)
            System.out.print(numbers.elementAt(i) + ", ");
        System.out.print(numbers.elementAt(numbers.size() - 1));
    }

    public static Vector<Integer> sort(Vector<Integer> numbers, int first, int last) {
        while (first < last) {
            int pivot = randomizedPartition(numbers, first, last);
            sort(numbers, first, pivot - 1);
            first = pivot + 1;
        }
        return numbers;
    }

    private static int randomizedPartition(Vector<Integer> numbers, int left, int right) {
        Random rand = new Random();
        int r = rand.nextInt(right - left + 1) + left;
        int temp = numbers.elementAt(r);
        numbers.set(r, numbers.elementAt(right));
        numbers.set(right, temp);
        return partition(numbers, left, right);
    }

    private static int partition(Vector<Integer> numbers, int first, int last) {
        int pivot = numbers.elementAt(last);
        int i = first - 1;

        for (int j = first; j <= last - 1; j++) {
            if (numbers.elementAt(j) < pivot) {
                i++;
                int temp = numbers.elementAt(i);
                numbers.set(i, numbers.elementAt(j));
                numbers.set(j, temp);
            }
        }
        int temp = numbers.elementAt(i + 1);
        numbers.set(i + 1, numbers.elementAt(last));
        numbers.set(last, temp);

        return i + 1;
    }
}