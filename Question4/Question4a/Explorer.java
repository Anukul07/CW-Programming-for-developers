package Question4.Question4a;

import java.util.*;

// Define the Explorer class
class Explorer {
    // Define constants for directions, origin, and obstacle
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static final char ORIGIN = 'S';
    private static final char OBSTACLE = 'W';

    // Method to find the shortest route
    public static int findShortestRoute(char[][] map) {
        // Get the number of rows and columns in the map
        int rows = map.length;
        int cols = map[0].length;

        // Initialize a boolean array to keep track of visited positions
        boolean[][] visited = new boolean[rows][cols];
        
        // Initialize a queue to perform breadth-first search
        Queue<Coordinates> queue = new LinkedList<>();
        
        // Initialize the starting position
        Coordinates initial = null;
        
        // Find the starting position and initialize it
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == ORIGIN) {
                    initial = new Coordinates(i, j);
                    break;
                }
            }
        }
        
        // If the starting position is not found, return -1 (impossible)
        if (initial == null) {
            return -1;
        }
        
        // Mark the starting position as visited and enqueue it
        visited[initial.row][initial.col] = true;
        queue.offer(initial);
        
        // Count the total number of keys in the map
        int totalKeys = 0;
        for (char[] row : map) {
            for (char cell : row) {
                if (Character.isLowerCase(cell)) {
                    totalKeys++;
                }
            }
        }
        
        // Initialize variables to keep track of keys collected and steps taken
        int keysCollected = 0;
        int steps = 0;
        
        // Perform breadth-first search
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinates current = queue.poll();
                // Check if all keys are collected, if yes, return the number of steps
                if (keysCollected == totalKeys) {
                    return steps;
                }
                // Explore adjacent cells in four directions
                for (int[] direction : DIRECTIONS) {
                    int newRow = current.row + direction[0];
                    int newCol = current.col + direction[1];
                    // Check if the new position is valid and not visited and not an obstacle
                    if (isValidPosition(rows, cols, newRow, newCol) && !visited[newRow][newCol] && map[newRow][newCol] != OBSTACLE) {
                        // Mark the new position as visited
                        visited[newRow][newCol] = true;
                        // If the cell contains a key, collect it
                        if (Character.isLowerCase(map[newRow][newCol])) {
                            keysCollected++;
                        }
                        // Enqueue the new position
                        queue.offer(new Coordinates(newRow, newCol));
                    }
                }
            }
            // Increment the number of steps
            steps++;
        }
        // If all keys cannot be collected, return -1 (impossible)
        return -1;
    }
    
    // Method to check if a position is valid
    private static boolean isValidPosition(int rows, int cols, int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
    
    // Define a Coordinates class to represent a position
    private static class Coordinates {
        int row;
        int col;
        Coordinates(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    // Main method to test the findShortestRoute method
    public static void main(String[] args) {
        // Define a map with obstacles, keys, and starting point
        char[][] map1 = {
            {'S', 'P', 'q', 'P', 'P'},
            {'W', 'W', 'W', 'P', 'W'},
            {'r', 'P', 'Q', 'P', 'R'}
        };
        // Find the shortest route in the map
        int shortestRoute1 = findShortestRoute(map1);
        // Print the result
        if (shortestRoute1 != -1) {
            System.out.println("The minimum number of moves required: " + shortestRoute1);
        } else {
            System.out.println("It is impossible to collect all the keys.");
        }
    }
}
