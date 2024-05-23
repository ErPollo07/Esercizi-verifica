import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {6, 2, 4, 8, 12, 14, 16, 10};

        order(arr);
        System.out.println("Args = " + Arrays.toString(arr));
        System.out.println("Args = [2, 4, 6, 8, 10, 12, 14, 10]");
    }

    private static void order(int[]arr) {
        int posCurrent, posMin = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] % 2 == 0) {
                posCurrent = posMin = i;

                // Find the minimum number from i to the max number
                for (int j = posCurrent; j < arr.length - 1; j++) {
                    if (arr[j] % 2 == 0 && arr[j] < arr[posMin]){
                        posMin = j;
                    }
                }

                if (posMin != posCurrent) {
                    swap(arr, posMin, posCurrent);
                }
            }
        }
    }

    private static void swap (int[]arr,int pos1, int pos2) {
        int temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }
}


