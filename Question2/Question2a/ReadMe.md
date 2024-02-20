# Garment Machine

This Java program implements a `GarmentMachine` class that calculates the minimum moves required to balance garments across different devices.

## Overview

The `GarmentMachine` class provides a method `minimumMoves(int[] devices)` which takes an array representing the number of garments on each device as input and returns the minimum number of moves required to balance the garments evenly across all devices.

```java

public static void main(String[] args) {
    // Sample devices array
    int[] devices = {1, 0, 5};
    // Create an instance of GarmentMachine
    GarmentMachine machine = new GarmentMachine();
    // Call minimumMoves method and print the result
    System.out.println("Minimum moves required: " + machine.minimumMoves(devices));
}

// Output - Minimum moves required: 4

```