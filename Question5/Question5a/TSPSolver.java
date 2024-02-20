package Question5.Question5a;

import java.util.ArrayList; // Importing ArrayList class from java.util package for dynamically sized arrays.
import java.util.List; // Importing List interface from java.util package for collections.
import java.util.Random; // Importing Random class from java.util package for generating random numbers.

// Class definition for solving the Traveling Salesman Problem (TSP).
public class TSPSolver {
    private double[][] pheromoneMatrix; // Matrix to store pheromone levels between vertices.
    private int[][] distanceMatrix; // Matrix to store distances between vertices.
    private int numOfVertices; // Number of vertices in the TSP problem.
    private int numOfAnts; // Number of ants (solutions) to be generated.
    private double alpha; // Alpha parameter for balancing pheromone importance.
    private double beta; // Beta parameter for balancing distance importance.
    private double evaporationRate; // Rate at which pheromone evaporates.
    private double initialPheromone; // Initial pheromone level on edges.

    // Constructor to initialize TSPSolver with parameters.
    public TSPSolver(int numOfVertices, int numOfAnts, double alpha, double beta, double evaporationRate, double initialPheromone) {
        this.numOfVertices = numOfVertices; // Initializing number of vertices.
        this.numOfAnts = numOfAnts; // Initializing number of ants.
        this.alpha = alpha; // Initializing alpha parameter.
        this.beta = beta; // Initializing beta parameter.
        this.evaporationRate = evaporationRate; // Initializing evaporation rate.
        this.initialPheromone = initialPheromone; // Initializing initial pheromone level.
        pheromoneMatrix = new double[numOfVertices][numOfVertices]; // Initializing pheromone matrix.
        distanceMatrix = new int[numOfVertices][numOfVertices]; // Initializing distance matrix.
    }

    // Method to initialize the pheromone matrix with initial values.
    public void initializePheromoneMatrix() {
        for (int i = 0; i < numOfVertices; i++) { // Looping through rows of the matrix.
            for (int j = 0; j < numOfVertices; j++) { // Looping through columns of the matrix.
                pheromoneMatrix[i][j] = initialPheromone; // Setting initial pheromone level.
            }
        }
    }

    // Method to set the distance matrix.
    public void setDistanceMatrix(int[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix; // Setting the distance matrix.
    }

    // Method to find the shortest tour among all ants.
    public List<Integer> findShortestTour() {
        Random random = new Random(); // Initializing random number generator.
        List<Integer> bestTour = null; // Initializing the best tour found so far.
        int bestTourLength = Integer.MAX_VALUE; // Initializing the length of the best tour.
        for (int iteration = 0; iteration < numOfAnts; iteration++) { // Looping through each ant.
            List<Integer> antTour = generateAntTour(random); // Generating a tour for the current ant.
            int antTourLength = calculateTourLength(antTour); // Calculating the length of the tour.
            if (antTourLength < bestTourLength) { // Checking if the current tour is better than the best tour.
                bestTourLength = antTourLength; // Updating the length of the best tour.
                bestTour = antTour; // Updating the best tour.
            }
            updatePheromoneTrail(antTour, antTourLength); // Updating pheromone levels based on the tour.
        }
        return bestTour; // Returning the best tour found.
    }

    // Method to generate a tour for a single ant.
    private List<Integer> generateAntTour(Random random) {
        List<Integer> antTour = new ArrayList<>(); // Initializing the tour for the ant.
        boolean[] visitedVertices = new boolean[numOfVertices]; // Array to track visited vertices.
        int currentVertex = random.nextInt(numOfVertices); // Selecting a random starting vertex.
        antTour.add(currentVertex); // Adding the starting vertex to the tour.
        visitedVertices[currentVertex] = true; // Marking the starting vertex as visited.
        while (antTour.size() < numOfVertices) { // Looping until all vertices are visited.
            int nextVertex = selectNextVertex(currentVertex, visitedVertices, random); // Selecting the next vertex.
            antTour.add(nextVertex); // Adding the next vertex to the tour.
            visitedVertices[nextVertex] = true; // Marking the next vertex as visited.
            currentVertex = nextVertex; // Updating the current vertex.
        }
        antTour.add(antTour.get(0)); // Completing the tour by returning to the starting vertex.
        return antTour; // Returning the tour for the ant.
    }

    // Method to select the next vertex for the ant's tour.
    private int selectNextVertex(int currentVertex, boolean[] visitedVertices, Random random) {
        double[] probabilities = new double[numOfVertices]; // Array to store selection probabilities.
        double probabilitiesSum = 0.0; // Variable to store the sum of probabilities.
        for (int vertex = 0; vertex < numOfVertices; vertex++) { // Looping through all vertices.
            if (!visitedVertices[vertex]) { // Checking if the vertex is not visited.
                double pheromoneLevel = Math.pow(pheromoneMatrix[currentVertex][vertex], alpha); // Calculating pheromone contribution.
                double distance = 1.0 / Math.pow(distanceMatrix[currentVertex][vertex], beta); // Calculating distance contribution.
                probabilities[vertex] = pheromoneLevel * distance; // Combining pheromone and distance contributions.
                probabilitiesSum += probabilities[vertex]; // Adding to the sum of probabilities.
            }
        }
        double randomValue = random.nextDouble(); // Generating a random value.
        double cumulativeProbability = 0.0; // Initializing cumulative probability.
        for (int vertex = 0; vertex < numOfVertices; vertex++) { // Looping through all vertices.
            if (!visitedVertices[vertex]) { // Checking if the vertex is not visited.
                double probability = probabilities[vertex] / probabilitiesSum; // Calculating probability of selecting the vertex.
                cumulativeProbability += probability; // Updating cumulative probability.
                if (randomValue <= cumulativeProbability) { // Checking if the random value falls within the cumulative probability.
                    return vertex; // Returning the selected vertex.
                }
            }
        }
        return -1; // Returning -1 if no vertex is selected.
    }

    // Method to calculate the length of a tour.
    private int calculateTourLength(List<Integer> tour) {
        int tourLength = 0; // Initializing tour length.
        for (int i = 0; i < tour.size() - 1; i++) { // Looping through tour vertices.
            int currentVertex = tour.get(i); // Getting the current vertex.
            int nextVertex = tour.get(i + 1); // Getting the next vertex.
            tourLength += distanceMatrix[currentVertex][nextVertex]; // Adding distance between current and next vertex.
        }
        return tourLength; // Returning the length of the tour.
    }

    // Method to update the pheromone trail based on a tour.
    private void updatePheromoneTrail(List<Integer> tour, int tourLength) {
        double pheromoneDeposit = 1.0 / tourLength; // Calculating pheromone deposit amount.
        for (int i = 0; i < tour.size() - 1; i++) { // Looping through tour vertices.
            int currentVertex = tour.get(i); // Getting the current vertex.
            int nextVertex = tour.get(i + 1); // Getting the next vertex.
            pheromoneMatrix[currentVertex][nextVertex] = (1 - evaporationRate) * pheromoneMatrix[currentVertex][nextVertex] + evaporationRate * pheromoneDeposit; // Updating pheromone level on the edge.
        }
    }

    // Main method to execute the TSPSolver.
    public static void main(String[] args) {
        int[][] distanceMatrix = { // Initializing the distance matrix.
            {0, 2, 5, 7},
            {2, 0, 4, 6},
            {5, 4, 0, 3},
            {7, 6, 3, 0}
        };

        int numOfVertices = 4; // Number of vertices in the TSP problem.
        int numOfAnts = 15; // Number of ants (solutions) to be generated.
        double alpha = 0.5; // Alpha parameter for balancing pheromone importance.
        double beta = 1.5; // Beta parameter for balancing distance importance.
        double evaporationRate = 0.3; // Rate at which pheromone evaporates.
        double initialPheromone = 0.05; // Initial pheromone level on edges.

        TSPSolver tspSolver = new TSPSolver(numOfVertices, numOfAnts, alpha, beta, evaporationRate, initialPheromone); // Creating an instance of TSPSolver.
        tspSolver.initializePheromoneMatrix(); // Initializing the pheromone matrix.
        tspSolver.setDistanceMatrix(distanceMatrix); // Setting the distance matrix.
        List<Integer> shortestTour = tspSolver.findShortestTour(); // Finding the shortest tour.
        System.out.println("Shortest tour: " + shortestTour); // Printing the shortest tour found.
    }
}
