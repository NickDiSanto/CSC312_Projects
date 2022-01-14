import java.util.Vector;

public class BubbleSort {
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
        for (int i = 0; i < numbers.size(); i++)
            System.out.print(numbers.elementAt(i) + ", ");
    }

    public static Vector<Integer> sort (Vector<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = numbers.size() - 1; j > i; j--) {
                if (numbers.elementAt(j) < numbers.elementAt(j - 1)) {
                    int temp = numbers.elementAt(j);
                    numbers.set(j, numbers.elementAt(j - 1));
                    numbers.set(j - 1, temp);
                }
            }
        }
        return numbers;
    }

}

// mergeSort