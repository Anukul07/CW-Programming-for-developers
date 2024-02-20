# Venue Decorator

This Java program, `VenueDecorator`, calculates the minimum cost for decorating venues with different themes. It ensures that adjacent venues have different themes by excluding the previously chosen theme in each iteration.

## How It Works

The `calculateMinimumCost` method in the `VenueDecorator` class takes a 2D array costMatrix as input. Each row of the matrix represents a venue, and each column represents a theme. The method iterates through each venue, finding the theme with the minimum cost while ensuring that adjacent venues have different themes.

## Example 

``` java

int[][] costMatrix = {
    {1, 3, 2}, 
    {4, 6, 8}, 
    {3, 1, 5}
};

// Minimum cost for venue decoration: 7 units

```