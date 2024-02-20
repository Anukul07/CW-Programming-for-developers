package Question1.Question1a;

public class VenueDecorator {
    
    public static int calculateMinimumCost(int[][] costMatrix){

        // Storing the number of venues (rows) in the matrix
        int venueCount = costMatrix.length;
        // Storing the number of themes (columns) in the matrix
        int themeCount = costMatrix[0].length;
        // Initializing a variable to store the minimum total cost for venue decoration
        int totalMinimumCost = 0;
        // Initializing a variable to keep track of the excluded theme index
        // to ensure adjacent venues have different themes
        int excludedThemeIndex = -1;

        for (int i = 0; i < venueCount; i++) { // Looping through each venue
            int venueMinimumCost = Integer.MAX_VALUE; // Initializing with the maximum possible integer value

            for (int j = 0; j < themeCount; j++){ // Looping through each theme
                if (j != excludedThemeIndex){
                    venueMinimumCost = Math.min(venueMinimumCost, costMatrix[i][j]);
                }
            }

            totalMinimumCost += venueMinimumCost; // Adding the minimum cost for the venue
            
            // Updating the `excludedThemeIndex` variable separately ensures accurate exclusion
            // of the previously chosen theme during comparisons in subsequent venue iterations

            // Updating 'excludedThemeIndex' inside the loop caused it to incorrectly skip themes 
            // in subsequent iterations within the same venue

            for (int j = 0; j < themeCount; j++) { // Finding the index of the theme with the minimum cost in the current venue
                if (costMatrix[i][j] == venueMinimumCost) {
                    excludedThemeIndex = j; // Excluding this theme in the next venue
                    break;
                }
            }
        }
        return totalMinimumCost;
    }

    public static void main(String[] args) {
        
        int[][] costMatrix = {
            {1, 3, 2}, 
            {4, 6, 8}, 
            {3, 1, 5}
        };

        int minimumCost = calculateMinimumCost(costMatrix);
        System.out.println("Minimum cost for venue decoration: " + minimumCost + " units");
    }
}
