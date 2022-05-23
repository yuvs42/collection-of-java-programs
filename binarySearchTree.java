
import java.util.Scanner;
import java.io.IOException;
class Node { //Node class representing each Node of the tree.
    int value;
    Node left;
    Node right;

    public Node(int value) {

        this.value = value;
    }
}
public class binarySearchTree {

    Node root;//beginning of the tree

    //constructor
    binarySearchTree(){
        root = null;
    }

    void insertnew(int key){ // Method calling the insert method
        root = insert(root, key);
    }

    Node insert (Node root, int key){ // insertion method
        if (root == null) {
            root = new Node(key);
        }
        else {
            if (key < root.value){
                root.left = insert (root.left, key);
            }
            else if (key > root.value){
                root.right = insert(root.right, key);
            }
        }
        return root;
    }
    int startTravel(int findKey){ // Method calling the search method
        return travel(root, findKey);
    }
    int count = 0;//counting the number of steps while going over the tree.
    int travel (Node root, int findKey) { //search method

        if (root == null) {
            return 0;
        } else if (root.value == findKey) {
            count++;
            return count;
        } else if (findKey > root.value) {
            count++;
            return travel(root.right, findKey);
        } else if (findKey < root.value) {
            count++;
            return travel(root.left, findKey);
        }
        else {
            count = 0;
            return count;
        }
    }




    public static void main (String [] args) {
        binarySearchTree tree = new binarySearchTree();
        Scanner sc = new Scanner(System.in);
        int enteredValue = 0;
        boolean stop = true;

        System.out.println("Enter numbers to the tree, one at a time, and when you are done, enter a letter:");
        while (stop) { // a loop for entering elements to the tree

            try { // try..catch clause to check the entered value.

                enteredValue = Integer.parseInt(sc.next());
                tree.insertnew(enteredValue);

            }
            catch (Exception e){
                System.out.println("all numbers were entered");
                stop = false;
            }

        }
        System.out.println("Please enter the number you wish to find:");
        enteredValue = sc.nextInt();
        int travelTime = tree.startTravel(enteredValue);
        if (travelTime == 0){
            System.out.println("number was not found");//a massage in case the number is not present in the tree.
        }
        else {
            System.out.println("the number of iterations in order to find the desired value was:" + travelTime);
        }

    }


}




