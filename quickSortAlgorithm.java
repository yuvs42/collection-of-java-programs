public class quickSortAlgorithm {
    private static int count = 0;

    static void qSort(int[] array, int l, int r) {

        int m = partition(array, l-1, r,array[r]);
        if (m!=r) {
            swap(array, m, r);
            count++;
        }
        if ((m-l)>1)
            qSort(array, l, m - 1);
        if ((r-m)>1)
            qSort(array, m + 1, r);
    }
    static int partition (int [] array, int first, int last, int pivot) {

        while (first < last) {
            do {
                first++;
            } while (array[first] < pivot);
            do {
                last--;
            } while (array[last] > pivot && (last != 0));
            if (first < last) {
                swap(array, first, last);
                count++;
            }
        }
        return first;
    }

    static void swap(int[]arr, int a, int b){
        int temp = arr[a];
        arr[a]= arr[b];
        arr[b]=temp;
    }
    public static void main (String[] args){
        int[ ] input = {12,9,4,99,120,1,3,10,23,45,75,69,31,88,101,14,29,91,2,0,77};
        qSort(input, 0, input.length-1);
        for (int i=0; i<input.length; i++){
            System.out.println(input[i]);
        }
        System.out.println("Number of Swaps: " + count);
    }
}


