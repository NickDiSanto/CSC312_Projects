import java.util.Vector;

public class InsertionSort {
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

        sort(numbers);
        for (int i = 0; i < numbers.size() - 1; i++)
            System.out.print(numbers.elementAt(i) + ", ");
        System.out.print(numbers.elementAt(numbers.size() - 1));
    }

    public static Vector<Integer> sort(Vector<Integer> numbers) {
        for (int j = 1; j < numbers.size(); j++) {
            int key = numbers.elementAt(j);
            int i = j - 1;
            while (i >= 0 && numbers.elementAt(i) > key) {
                numbers.set(i + 1, numbers.elementAt(i));
                i--;
            }
            numbers.set(i + 1, key);
        }
        return numbers;
    }
}