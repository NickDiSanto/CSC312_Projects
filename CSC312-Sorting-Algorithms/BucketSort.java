import java.util.ArrayList;
import java.util.Vector;

public class BucketSort {

    public static void main(String[] args) {
        Vector<String> strings = new Vector<>();
        strings.addElement("Hello this is a string");
        strings.addElement("Adam and Eve");
        strings.addElement("My name is Nicolas DiSanto");
        strings.addElement("My name is Nick DiSanto");
        strings.addElement("Abc");
        strings.addElement("Test");
        strings.addElement("Jingle bells jingle bells");
        strings.addElement("Zoinks");
        strings.addElement("CSC is a cool class");
        strings.addElement("I go to CBU");

        bucketSort(strings);
        for (int i = 0; i < strings.size() - 1; i++)
            System.out.print(strings.elementAt(i) + ", ");
        System.out.print(strings.elementAt(strings.size() - 1));
    }

    public static Vector<String> bucketSort(Vector<String> strings) {
        int size = 26;
        ArrayList<String>[] buckets = new ArrayList[size];
        for (int i = 0; i < size; i++)
            buckets[i] = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++)
            buckets[strings.elementAt(i).toUpperCase().charAt(0) - 65].add(strings.elementAt(i));

        for (int i = 0; i < size; i++)
            buckets[i] = insertionSort(buckets[i]);

        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                strings.set(count, buckets[i].get(j));
                count++;
            }
        }
        return strings;
    }

    public static ArrayList<String> insertionSort(ArrayList<String> bucket) {
        for (int j = 1; j < bucket.size(); j++) {
            String key = bucket.get(j);
            int i = j - 1;
            while (i >= 0 && isGreater(bucket.get(i), key)) {
                bucket.set(i + 1, bucket.get(i));
                i--;
            }
            bucket.set(i + 1, key);
        }
        return bucket;
    }

    public static boolean isGreater(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) > str2.charAt(i))
                return true;
            else if (str1.charAt(i) < str2.charAt(i))
                return false;
        }
        return false;
    }
}