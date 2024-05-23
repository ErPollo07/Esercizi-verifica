import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] tempArr = new int[11];
        int couter = 0;
        String number;

        do {
            System.out.println("Insert a number (q to exit): ");
            number = scanner.next();

            if (number.equalsIgnoreCase("q")) {
                if (couter < 10) {
                    System.out.println("You have to insert at least 10 number maximum 11. You have insert " + couter + " number.");
                } else {
                    break;
                }
            } else if (Integer.parseInt(number) < 0 || Integer.parseInt(number) > 30) {
                System.out.println("You have to insert a positive number between 1 and 30");
            } else if (checkIfDigit(number)) {
                tempArr[couter] = Integer.parseInt(number);
                couter++;
            } else {
                System.out.println("You have to insert a number.");
            }

        } while (couter <= 11);

        int[] arr = new int[couter];

        System.arraycopy(tempArr, 0, arr, 0, arr.length);

        order(arr);

        System.out.println("Args = " + Arrays.toString(arr));
    }

    private static void order(int[]arr) {
        int posMin = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] % 2 == 0) {
                posMin = i;

                // Find the minimum even number from i to the max number
                for (int j = i; j < arr.length - 1; j++) {
                    if (arr[j] % 2 == 0 && arr[j] < arr[posMin]){
                        posMin = j;
                    }
                }

                // Swap the number in posMin with the number in pos i
                if (posMin != i) {
                    swap(arr, posMin, i);
                }
            }
        }
    }

    private static void swap (int[]arr,int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    private static boolean checkIfDigit(String s) {
        // Try to transform the string into long
        // if is possible return true
        // if is not possible return false
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}


