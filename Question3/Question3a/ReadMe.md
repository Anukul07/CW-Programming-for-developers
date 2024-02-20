# Score Tracker

ScoreTracker is a Java class designed to track scores and calculate the median score.

``` java     
        
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

    // Output

    // Median 1: 87.8
    // Median 2: 87.1