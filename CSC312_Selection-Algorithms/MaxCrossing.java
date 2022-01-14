import java.util.Vector;

public class MaxCrossing {
    public static void main(String[] args) {
        Vector<Integer> numbers = new Vector<>();
        numbers.addElement(6);
        numbers.addElement(-11);
        numbers.addElement(6);
        numbers.addElement(9);
        numbers.addElement(3);
        numbers.addElement(15);
        numbers.addElement(-6);


        int[] array = findMaximumSubarray(numbers, 0, numbers.size() - 1);
        array[1]--;

        String firstDay;
        String secondDay;
        if (array[0] == 0) {
            firstDay = "Sunday";
            secondDay = "Monday";
        } else if (array[0] == 1) {
            firstDay = "Monday";
            secondDay = "Tuesday";
        } else if (array[0] == 2) {
            firstDay = "Tuesday";
            secondDay = "Wednesday";
        } else if (array[0] == 3) {
            firstDay = "Wednesday";
            secondDay = "Thursday";
        } else if (array[0] == 4) {
            firstDay = "Thursday";
            secondDay = "Friday";
        } else if (array[0] == 5) {
            firstDay = "Friday";
            secondDay = "Saturday";
        } else {
            firstDay = "Saturday";
            secondDay = "Sunday";
        }

        System.out.println();
        System.out.println("Suppose the following are the changes in a given stock's price per day:");
        System.out.println("Sunday: " + numbers.elementAt(0));
        System.out.println("Monday: " + numbers.elementAt(1));
        System.out.println("Tuesday: " + numbers.elementAt(2));
        System.out.println("Wednesday: " + numbers.elementAt(3));
        System.out.println("Thursday: " + numbers.elementAt(4));
        System.out.println("Friday: " + numbers.elementAt(5));
        System.out.println("Saturday: " + numbers.elementAt(6));
        System.out.println();

        System.out.println("For this stock, the most growth in a two-day span was between " + firstDay + " and " + secondDay + ": a growth of +" + array[2] + ".");
        System.out.println("Therefore, if you bought the stock before " + firstDay + " and sold it after " + secondDay + ", you would maximize your profit.");

    }

    private static int[] findMaximumSubarray(Vector<Integer> numbers, int low, int high) {
        if (high == low)
            return new int[] { low, high, numbers.elementAt(low) };
        else {
            int mid = (low + high) / 2;
            int[] array1 = findMaximumSubarray(numbers, low, mid);
            int[] array2 = findMaximumSubarray(numbers, mid + 1, high);
            int[] array3 = findMaxCrossingSubarray(numbers, low, mid, high);

            if (array1[2] >= array2[2] && array1[2] >= array3[2])
                return array1;
            else if (array2[2] >= array1[2] && array2[2] >= array3[2])
                return array2;
            else
                return array3;
        }
    }

    private static int[] findMaxCrossingSubarray(Vector<Integer> numbers, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = Integer.MIN_VALUE;
        for (int i = mid; i > low; i--) {
            sum += numbers.elementAt(i);
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = Integer.MIN_VALUE;
        for (int j = mid + 1; j < high; j++) {
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }
        int[] arr = new int[] { maxLeft, maxRight, leftSum + rightSum };
        return arr;
    }
}
