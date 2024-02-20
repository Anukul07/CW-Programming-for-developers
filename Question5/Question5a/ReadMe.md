# TSP Solver
The TSPSolver class is a Java implementation of an Ant Colony Optimization (ACO) algorithm to solve the Traveling Salesman Problem (TSP). This algorithm simulates the behavior of ants to find an approximate solution to the TSP, which involves finding the shortest possible tour that visits each city exactly once and returns to the original city.

## Features

 - **Ant Colony Optimization algorithm**: The class implements the ACO algorithm to find an approximate solution to the TSP.
 - **Customizable parameters**: The algorithm allows customization of parameters such as the number of ants, pheromone evaporation rate, and importance of pheromone versus distance.
 - **Dynamic programming approach**: The algorithm dynamically updates pheromone levels on edges based on the quality of the tours generated by ants.


 ``` java

 // Define distance matrix
int[][] distanceMatrix = {
    {0, 2, 5, 7},
    {2, 0, 4, 6},
    {5, 4, 0, 3},
    {7, 6, 3, 0}
};

// Set parameters
int numOfVertices = 4;
int numOfAnts = 15;
double alpha = 0.5;
double beta = 1.5;
double evaporationRate = 0.3;
double initialPheromone = 0.05;

// Instantiate TSPSolver
TSPSolver tspSolver = new TSPSolver(numOfVertices, numOfAnts, alpha, beta, evaporationRate, initialPheromone);
tspSolver.initializePheromoneMatrix();
tspSolver.setDistanceMatrix(distanceMatrix);

// Find shortest tour
List<Integer> shortestTour = tspSolver.findShortestTour();
System.out.println("Shortest tour: " + shortestTour);