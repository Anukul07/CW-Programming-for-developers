package Question3.Question3a;

import java.util.Stack;

public class ScoreTracker {
    private Stack<Double> scores; // Declare a private field 'scores' of type Stack to store scores.

    public ScoreTracker() { // Constructor for ScoreTracker class.
        scores = new Stack<>(); // Initialize the 'scores' stack.
    }

    public void addScore(double score) { // Method to add a score to the scores stack.
        // Find the index where the new score should be inserted to maintain sorted order
        int index = 0; // Initialize index variable to 0.
        while (index < scores.size() && scores.get(index) < score) { // Loop to find the correct index for the new score.
            index++; // Increment index.
        }
        // Insert the score at the appropriate position
        scores.insertElementAt(score, index); // Insert the score at the calculated index.
    }

    public double getMedianScore() { // Method to calculate the median score.
        int size = scores.size(); // Get the size of the scores stack.
        if (size == 0) { // If there are no scores in the stack.
            throw new IllegalStateException("No scores added"); // Throw an exception indicating no scores have been added.
        }

        if (size % 2 == 0) { // If the number of scores is even.
            // If even number of scores, return the average of the middle two
            int middleIndex1 = size / 2 - 1; // Calculate the index of the first middle score.
            int middleIndex2 = size / 2; // Calculate the index of the second middle score.
            return (scores.get(middleIndex1) + scores.get(middleIndex2)) / 2.0; // Return the average of the middle two scores.
        } else {
            // If odd number of scores, return the middle score
            return scores.get(size / 2); // Return the middle score.
        }
    }

    public static void main(String[] args) { // Main method to test the ScoreTracker class.
        ScoreTracker scoreTracker = new ScoreTracker(); // Create a new instance of ScoreTracker.
        scoreTracker.addScore(85.5); // Add a score to the score tracker.
        scoreTracker.addScore(92.3); // Add a score to the score tracker.
        scoreTracker.addScore(77.8); // Add a score to the score tracker.
        scoreTracker.addScore(90.1); // Add a score to the score tracker.
        double median1 = scoreTracker.getMedianScore(); // Calculate the median score.
        System.out.println("Median 1: " + median1); // Print the median score.

        scoreTracker.addScore(81.2); // Add a score to the score tracker.
        scoreTracker.addScore(88.7); // Add a score to the score tracker.
        double median2 = scoreTracker.getMedianScore(); // Calculate the median score.
        System.out.println("Median 2: " + median2); // Print the median score.
    }
}
