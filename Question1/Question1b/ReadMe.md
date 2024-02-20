# Engine Time Calculator

This Java class provides a method to calculate the total time required for engine maintenance considering a split cost. 

## Example

``` java
int[] engineTimes = {1, 2, 3}; 
// Example array of engine times
int splitCost = 1; // Example split cost
int totalTime = EngineTimeCalculator.calculateTotalTime(engineTimes, splitCost);
System.out.println("Total time: " + totalTime); 
// Print the total time

// Output - 4 units
```