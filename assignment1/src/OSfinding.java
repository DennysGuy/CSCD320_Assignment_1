import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.System.exit;

public class OSfinding {
    public static void main(String[] args) {
        String fileName = "";
        int key = 0;
       if (args.length == 2) {
           fileName = args[0];
           key = Integer.parseInt(args[1]);
       } else {
           System.out.println("Please enter a text file and key formatted as: [program filename.txt key]");
           exit(1);
       }


        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
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

            int[] integerArray = new int[integerList.size()];

            for(int i = 0; i < integerArray.length; i++) {
                integerArray[i] = integerList.get(i);
            }

            if (key < 1 || key > integerArray.length) {
                System.out.println("null");
            } else {
                System.out.println(randomizedSelect(integerArray, 0, integerArray.length-1, key));
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }

    private static int partition(int[] A, int left, int right) {
        int pivot = A[right];
        int index = left;

        for (int i = left; i < right; i++) {
            if (A[i] <= pivot) {
                swap(A, index, i);
                index++;
            }
        }

        swap(A, index, right);
        return index;
    }

    private static void swap(int[] A, int index, int i ) {
        int temp = A[index];
        A[index] = A[i];
        A[i] = temp;
    }

    private static int randomizedPartition(int[] A, int left, int right) {
       Random randomNumber = new Random();
       int i = randomNumber.nextInt(right - left +1) + left;
       swap(A, i, right);
       return partition(A, left, right);
    }

    private static int randomizedSelect(int[] A, int p, int r, int i) {

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