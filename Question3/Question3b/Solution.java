package Question3.Question3b;

import java.util.*;

// Class to represent an edge in a graph
class Edge {
    int start, end, weight; // Define variables to store the start vertex, end vertex, and weight of the edge

    Edge(int start, int end, int weight) { // Constructor to initialize the edge with start, end, and weight
        this.start = start;                // Assign the start vertex
        this.end = end;                    // Assign the end vertex
        this.weight = weight;              // Assign the weight of the edge
    }
}

// Class to perform union-find operations
class UnionFinder {
    int[] parent, rank; // Define arrays to store parent and rank of each vertex

    UnionFinder(int n) { // Constructor to initialize parent and rank arrays
        parent = new int[n];    // Initialize parent array
        rank = new int[n];      // Initialize rank array
        for (int i = 0; i < n; i++) { // Loop through all vertices
            parent[i] = i;      // Set parent of each vertex to itself
            rank[i] = 0;        // Initialize rank of each vertex to 0
        }
    }

    int search(int i) { // Method to find the root of the set containing vertex i
        if (parent[i] != i)
            parent[i] = search(parent[i]);   // Path compression: Set parent of i to the root of its set
        return parent[i];                   // Return the root of the set
    }

    void combine(int x, int y) { // Method to merge two sets containing vertices x and y
        int xroot = search(x);    // Find the root of the set containing x
        int yroot = search(y);    // Find the root of the set containing y

        if (rank[xroot] < rank[yroot])      // If rank of x's set is less than rank of y's set
            parent[xroot] = yroot;          // Set parent of x's root to y's root
        else if (rank[xroot] > rank[yroot]) // If rank of x's set is greater than rank of y's set
            parent[yroot] = xroot;          // Set parent of y's root to x's root
        else {                              // If ranks are equal
            parent[yroot] = xroot;          // Set parent of y's root to x's root
            rank[xroot]++;                  // Increment rank of x's set
        }
    }
}

// Class to represent the structure of a graph
class GraphStructure {
    int V, E; // Define variables to store the number of vertices and edges in the graph
    List<Edge> edges; // Define a list to store edges of the graph

    GraphStructure(int v, int e) { // Constructor to initialize the graph with given number of vertices and edges
        V = v;          // Assign the number of vertices
        E = e;          // Assign the number of edges
        edges = new ArrayList<>(); // Initialize the list of edges
    }

    // Method to find Minimum Spanning Tree (MST) using a minimum heap
    void findMinimumSpanningTreeUsingHeap() {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight)); // Create a priority queue (min-heap) to store edges sorted by weight
        Collections.addAll(minHeap, edges.toArray(new Edge[0])); // Add all edges to the min-heap

        UnionFinder uf = new UnionFinder(V); // Create a Union-Find data structure to keep track of connected components

        int edgeCount = 0; // Initialize a variable to count the number of edges in the MST
        while (edgeCount < V - 1 && !minHeap.isEmpty()) { // Iterate until MST has V-1 edges or min-heap is empty
            Edge nextEdge = minHeap.poll(); // Remove the edge with minimum weight from the min-heap
            int x = uf.search(nextEdge.start); // Find the root of the set containing the start vertex of the edge
            int y = uf.search(nextEdge.end);   // Find the root of the set containing the end vertex of the edge

            if (x != y) { // If start and end vertices belong to different sets (i.e., adding this edge does not create a cycle)
                uf.combine(x, y); // Merge the sets containing start and end vertices
                System.out.println(nextEdge.start + " -- " + nextEdge.end + " == " + nextEdge.weight); // Print the edge as part of MST
                edgeCount++; // Increment the edge count
            }
        }
    }
}

// Class representing a minimum heap
class MinHeap {
    private int[] heap; // Define an array to store elements of the heap
    private int size;   // Define a variable to store the current size of the heap
    private int maxSize; // Define a variable to store the maximum size of the heap

    public MinHeap(int maxSize) { // Constructor to initialize the heap with maximum size
        this.maxSize = maxSize; // Assign the maximum size
        this.size = 0;           // Initialize the current size to 0
        heap = new int[this.maxSize + 1]; // Create an array to store heap elements
        heap[0] = Integer.MIN_VALUE; // Set the first element to the smallest possible integer (sentinel value)
    }

    private int parent(int pos) { // Method to return the index of the parent of a node at given position
        return pos / 2; // Return the index of the parent
    }

    private int leftChild(int pos) { // Method to return the index of the left child of a node at given position
        return (2 * pos); // Return the index of the left child
    }

    private int rightChild(int pos) { // Method to return the index of the right child of a node at given position
        return (2 * pos) + 1; // Return the index of the right child
    }

    private boolean isLeaf(int pos) { // Method to check if a node at given position is a leaf node
        return pos >= (size / 2) && pos <= size; // Return true if the node is a leaf, false otherwise
    }

    private void swap(int fpos, int spos) { // Method to swap two elements in the heap
        int tmp; // Temporary variable to hold the value during swapping
        tmp = heap[fpos]; // Store the value of first element in temporary variable
        heap[fpos] = heap[spos]; // Assign the value of second element to the first element
        heap[spos] = tmp; // Assign the value stored in temporary variable to the second element
    }

    public boolean isEmpty() { // Method to check if the heap is empty
        return size == 0; // Return true if heap size is 0, false otherwise
    }

    private void heapify(int pos) { // Method to heapify the subtree rooted at given position
        if (!isLeaf(pos)) { // If the node is not a leaf node
            if (heap[pos] > heap[leftChild(pos)] || heap[pos] > heap[rightChild(pos)]) { // If the node is greater than any of its children
                if (heap[leftChild(pos)] < heap[rightChild(pos)]) { // If left child is smaller than right child
                    swap(pos, leftChild(pos)); // Swap the node with its left child
                    heapify(leftChild(pos)); // Recursively heapify the left subtree
                } else {
                    swap(pos, rightChild(pos)); // Swap the node with its right child
                    heapify(rightChild(pos)); // Recursively heapify the right subtree
                }
            }
        }
    }

    public void insert(int element) { // Method to insert an element into the heap
        if (size >= maxSize) { // If heap is full
            return; // Return without inserting
        }
        heap[++size] = element; // Increment size and insert the element at the last position
        int current = size; // Store the current position
        while (heap[current] < heap[parent(current)]) { // While the element is smaller than its parent
            swap(current, parent(current)); // Swap the element with its parent
            current = parent(current); // Move to the parent position
        }
    }

    public int remove() { // Method to remove and return the minimum element from the heap
        int popped = heap[1]; // Store the minimum element
        heap[1] = heap[size--]; // Replace the root with the last element and decrement size
        heapify(1); // Heapify the root
        return popped; // Return the minimum element
    }

    public void print() { // Method to print the heap
        for (int i = 1; i <= size / 2; i++) { // Loop through all non-leaf nodes
            System.out.print(" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2 * i] + " RIGHT CHILD : " + heap[2 * i + 1]); // Print parent and its children
            System.out.println(); // Move to the next line
        }
    }
}

// Class to implement priority queue using minimum heap
class PriorityQueueImplementation {
    private MinHeap minHeap; // Define a minimum heap

    public PriorityQueueImplementation(int maxSize) { // Constructor to initialize priority queue with maximum size
        minHeap = new MinHeap(maxSize); // Create a minimum heap with specified maximum size
    }

    public void enqueue(int priority) { // Method to enqueue an element into the priority queue
        minHeap.insert(priority); // Insert the element into the minimum heap
    }

    public int dequeue() { // Method to dequeue and return the minimum element from the priority queue
        return minHeap.remove(); // Remove and return the minimum element from the minimum heap
    }

    public boolean isEmpty() { // Method to check if the priority queue is empty
        return minHeap.isEmpty(); // Check if the minimum heap is empty
    }

    public void printQueue() { // Method to print the priority queue
        minHeap.print(); // Print the minimum heap
    }
}

public class Solution {
    public static void main(String[] args) {
        int V = 4; // Define the number of vertices in the graph
        int E = 5; // Define the number of edges in the graph
        GraphStructure graph = new GraphStructure(V, E); // Create a graph structure with given number of vertices and edges

        graph.edges.add(new Edge(0, 1, 10)); // Add edges to the graph
        graph.edges.add(new Edge(0, 2, 6));  
        graph.edges.add(new Edge(0, 3, 5));  
        graph.edges.add(new Edge(1, 3, 15)); 
        graph.edges.add(new Edge(2, 3, 4));  

        graph.findMinimumSpanningTreeUsingHeap(); // Find the Minimum Spanning Tree using minimum heap 

        PriorityQueueImplementation priorityQueue = new PriorityQueueImplementation(15); // Create a priority queue
        priorityQueue.enqueue(5); // Enqueue elements into the priority queue
        priorityQueue.enqueue(3); 
        priorityQueue.enqueue(17); 
        priorityQueue.enqueue(10); 
        priorityQueue.enqueue(84); 
        priorityQueue.enqueue(19); 
        priorityQueue.enqueue(6); 
        priorityQueue.enqueue(22); 
        priorityQueue.enqueue(9); 

        System.out.println();

        System.out.println("The Priority Queue is:");
        priorityQueue.printQueue(); // Print the priority queue

        System.out.println("Dequeued element: " + priorityQueue.dequeue()); // Dequeue elements from the priority queue
        System.out.println("Dequeued element: " + priorityQueue.dequeue()); 

        System.out.println("The Priority Queue is:");
        priorityQueue.printQueue(); // Print the priority queue after dequeuing
    }
}
