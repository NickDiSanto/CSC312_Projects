import java.util.Random;
import java.util.Vector;

public class RandomizedSelect {

    public static void main(String[] args) {
	    Vector<Integer> numbers = new Vector<>();
        numbers.addElement(8);
        numbers.addElement(1);
        numbers.addElement(4);
	    numbers.addElement(11);
        numbers.addElement(2);
        numbers.addElement(16);
        numbers.addElement(3);
        numbers.addElement(22);
        numbers.addElement(10);

        int ithSmallest = 8;

        System.out.println(randomizedSelect(numbers, 0, numbers.size() - 1, ithSmallest));
    }

    private static int randomizedSelect(Vector<Integer> numbers, int left, int right, int ithSmallest) {
        if (left == right)
            return numbers.elementAt(left);
        int pivot = randomizedPartition(numbers, left, right);
        int k = pivot - left + 1;
        if (ithSmallest == k)
            return numbers.elementAt(pivot);
        else if (ithSmallest < k)
            return randomizedSelect(numbers, left, pivot - 1, ithSmallest);
        else
            return randomizedSelect(numbers, pivot + 1, right, ithSmallest - k);
    }

    private static int randomizedPartition(Vector<Integer> numbers, int left, int right) {
        Random rand = new Random();
        int r = rand.nextInt(right - left + 1) + left;
        int temp = numbers.elementAt(r);
        numbers.set(r, numbers.elementAt(right));
        numbers.set(right, temp);
        return partition(numbers, left, right);
    }

    private static int partition(Vector<Integer> numbers, int left, int right) {
        int x = numbers.elementAt(right);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (numbers.elementAt(j) <= x) {
                i++;
                int temp = numbers.elementAt(i);
                numbers.set(i, numbers.elementAt(j));
                numbers.set(j, temp);
            }
        }
        int temp = numbers.elementAt(i + 1);
        numbers.set(i + 1, numbers.elementAt(right));
        numbers.set(right, temp);
        return i + 1;
    }
}