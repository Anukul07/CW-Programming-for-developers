package Question4.Question4b;

import java.util.*;

// Define a class representing a Node in a binary tree
class Node {
    int parentValue; // The value stored in the parent node
    Node leftChild, rightChild; // References to the left and right child nodes

    // Constructor to initialize a Node with a given parent value
    public Node(int parentValue) {
        this.parentValue = parentValue;
        leftChild = rightChild = null; // Initialize left and right child references to null
    }
}

// Class to find the nearest values to a target value in a binary tree
public class NearestValuesFinder {
    
    // Method to find the nearest values to a target value in a binary tree
    public List<Integer> findNearestValues(Node root, double target, int numVals) {
        // Priority queue to store the closest values to the target
        // Comparator is used to prioritize values based on their distance to the target
        PriorityQueue<Integer> closestVals = new PriorityQueue<>(numVals, (a, b) -> Double.compare(Math.abs(b - target), Math.abs(a - target)));
        // Perform in-order traversal of the tree to find nearest values
        inOrderTraversal(root, target, closestVals, numVals);
        // Convert priority queue to a list and return
        return new ArrayList<>(closestVals);
    }

    // Helper method to perform in-order traversal of the tree
    private void inOrderTraversal(Node root, double target, PriorityQueue<Integer> closestVals, int numVals) {
        // Base case: If the root is null, return
        if (root == null)
            return;

        // Traverse left subtree
        inOrderTraversal(root.leftChild, target, closestVals, numVals);

        // Check if the priority queue has fewer values than required
        if (closestVals.size() < numVals) {
            // If so, add the current node's value to the queue
            closestVals.offer(root.parentValue);
        } else {
            // Calculate the difference between the target and the current node's value
            double currentDiff = Math.abs(target - root.parentValue);
            // Calculate the difference between the target and the value at the top of the queue
            double farthestDiff = Math.abs(target - closestVals.peek());
            // If the current node's value is closer to the target than the farthest value in the queue, replace it
            if (currentDiff < farthestDiff) {
                closestVals.poll(); // Remove the farthest value from the queue
                closestVals.offer(root.parentValue); // Add the current node's value to the queue
            }
        }

        // Traverse right subtree
        inOrderTraversal(root.rightChild, target, closestVals, numVals);
    }

    // Main method to test the NearestValuesFinder class
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
}
