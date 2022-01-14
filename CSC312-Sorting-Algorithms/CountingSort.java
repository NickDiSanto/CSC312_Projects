import java.util.Vector;

public class CountingSort {
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

        int largest = findLargest(numbers);

        Vector<Integer> output = sort(numbers, largest);
        for (int i = 0; i < output.size() - 1; i++)
            System.out.print(output.elementAt(i) + ", ");
        System.out.println(output.elementAt(output.size() - 1));
    }

    private static int findLargest(Vector<Integer> numbers) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.elementAt(i) > largest)
                largest = numbers.elementAt(i);
        }
        return largest;
    }

    public static Vector<Integer> sort(Vector<Integer> numbers, int largest) {
        Vector<Integer> count = new Vector<>();
        for (int i = 0; i <= largest; i++)
            count.addElement(0);

        for (int i = 0; i < numbers.size(); i++)
            count.set(numbers.elementAt(i), count.elementAt(numbers.elementAt(i)) + 1);
        for (int i = 1; i <= largest; i++)
            count.set(i, count.elementAt(i) + count.elementAt(i - 1));

        Vector<Integer> output = new Vector<>();

        for (int i = 0; i < numbers.size(); i++)
            output.addElement(0);

        for (int i = numbers.size() - 1; i >= 0; i--) {
            output.set(count.elementAt(numbers.elementAt(i)) - 1, numbers.elementAt(i));
            count.set(numbers.elementAt(i), count.elementAt(numbers.elementAt(i)) - 1);
        }

        return output;
    }
}