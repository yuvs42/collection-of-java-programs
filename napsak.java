
import java.lang.*;

class Knapsack {

    static void DynamicKnapssack(int[] v, int[] w, int n, int W) {
        int counter = 0; // counting the number of items in the back
        int weightcounter = 0; // counting the weight of th bag
        int[][] knop = new int[n + 1][W + 1];// a matrix to hold all the possible values
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    knop[i][j] = 0;// seting the value 0, in case there are no items in the bag, or all the items value is 0.
                } else if (w[i] <= j) {
                    knop[i][j] = Math.max(v[i] + knop[i - 1][j - w[i]], knop[i - 1][j]);// make sure that if another item is added, is it have more value then the previus value
                } else {
                    knop[i][j] = knop[i - 1][j];// place the previus value again in the table, indicating that a new value could not be added.
                }
            }
        }
        int i = n;
        int j = W;
        int c = 0;
        while (i > 0 && j > 0) { // a loop to calculate the number of items and the weight of the items in the bag
            if (knop[i][j] != knop[i - 1][j]) {
                weightcounter += w[i];
                i--;
                j = j - w[i];
                counter++;
            } else {
                i--;
            }
            c ++; // counter to check how long it takes to go throu the array matrix and find the items in the bag.
        }
        System.out.println(c);
        System.out.println("The total weight of the bag in: " + weightcounter);
        System.out.println("The number of items in the bag: " + counter);
        System.out.println("The total value of the bag: " + knop[n][W]);
    }

    public static void main(String[] args) {
        int Weights[] = {0, 1, 4, 6, 2, 5, 10, 8, 3, 9, 1, 4, 2, 5, 8, 9, 1};
        int Values[] = {0, 10, 5, 30, 8, 12, 30, 50, 10, 2, 10, 40, 80, 100, 25, 10, 5};
        DynamicKnapssack(Values, Weights, 16, 20);

    }
}

