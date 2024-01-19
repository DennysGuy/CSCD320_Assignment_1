import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class OSfinding {
    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 0) {
             fileName = args[0];
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Reading integers from the file '" + fileName + "':");
            ArrayList<Integer> integerList = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    integerList.add(number);
                } catch (NumberFormatException e){
                    System.err.println("Skipping non-integer value: " + line);
                }
            }

            Integer[] integerArray = integerList.toArray(new Integer[integerList.size()]);
            randomizedSelect(integerArray, 0, 0);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


    }

    private static int partition(Integer[] A, int left, int right) {
        int pivot = A[right];
        int index = left;

        for (int i = left; i < right; i++) {
            if (A[i] < pivot) {
                swap(A, index, i);
                index++;
            }
        }

        swap(A, A[index], A[right]);
        return index;
    }

    private static void swap(Integer[] A, int index, int i ) {
        int temp = A[index];
        A[index] = A[i];
        A[i] = temp;
    }

    private static int randomizedPartition(Integer[] A, int left, int right) {
       Random randomNumber = new Random();
       int i = randomNumber.nextInt(right - left +1) + left;
       return partition(A, left, right);
    }

    private static int randomizedSelect(Integer[] A, int p, int r, int i) {
        if (p==r) {
            return A[p];
        }

        int q = randomizedPartition(A, p ,r);
        int k = q - p + 1;
        if (i==k) {
            return A[q];
        }
        else if (i<k) {
            return randomizedSelect(A, p , q-1, i);
        }
        else {
            return randomizedSelect(A, q+1, r, i-k);
        }
    }
}