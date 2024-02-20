package Question1.Question1b;

import java.util.Arrays; // Importing Arrays utility to sort the array

public class EngineTimeCalculator {

    public static int calculateTotalTime(int[] engineTimes, int splitCost) {
        // Sort the array of engine times
        Arrays.sort(engineTimes);

        int totalTime = 0; // Variable to store the total time

        // Handle the first two engines separately
        int engine1Time = engineTimes[0]; // Get the time for the first engine
        int engine2Time = engineTimes[1]; // Get the time for the second engine
        totalTime = splitCost + Math.max(engine1Time, engine2Time); // Calculating the total time

        // Iterate over the remaining engines
        for (int i = 2; i < engineTimes.length; i++) { // Loop through the rest of the engines
            int engineTime = engineTimes[i]; // Get the time for the current engine
            totalTime = splitCost + Math.max(totalTime, engineTime); // Calculating the total time considering split cost
        }

        return totalTime; // Returning the total time
    }

    public static void main(String[] args) {
        int[] engineTimes = {1, 2, 3}; // Example array of engine times
        int splitCost = 1; // Example split cost
        int totalTime = calculateTotalTime(engineTimes, splitCost); // Calling the method to calculate total time
        System.out.println("Total time: " + totalTime); // Printing the total time
    }
}