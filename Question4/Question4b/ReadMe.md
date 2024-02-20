# Nearest Values Finder

The Nearest Values Finder is a Java class designed to find the nearest values to a given target value in a binary tree. It utilizes an in-order traversal algorithm and a priority queue to efficiently identify and store the closest values.

## Method Explanation

**findNearestValues(Node root, double target, int numVals)**

This method takes the root node of a binary tree, a target value, and the number of nearest values to find. It performs an in-order traversal of the binary tree and returns a list containing the nearest values to the target value.

``` java

public static void main(String[] args) {
        NearestValuesFinder finder = new NearestValuesFinder();

        // Create a binary tree
        Node root = new Node(8);
        root.leftChild = new Node(4);
        root.rightChild = new Node(9);
        root.leftChild.leftChild = new Node(3);
        root.leftChild.rightChild = new Node(7);
        root.rightChild.leftChild = new Node(2);
        root.rightChild.rightChild = new Node(5);

        // Define the target value and the number of closest values to find
        double target = 3.8;
        int numVals = 2;
        // Find the nearest values to the target and print the result
        List<Integer> closest = finder.findNearestValues(root, target, numVals);
        System.out.println(closest);
    }


// Output - [3, 4]