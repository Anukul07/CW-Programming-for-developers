# Explorer: Shortest Route Finder

Explorer is a Java program that finds the shortest route to collect all keys in a map with obstacles.

## Description

This program implements a breadth-first search algorithm to find the shortest route to collect all keys in a map. The map consists of cells, where each cell can be one of the following:

- 'S': Starting point
- 'P': Passable cell
- 'W': Obstacle (cannot pass through)
- Lowercase letters (e.g., 'q', 'r'): Keys to be collected

The program starts at the starting point and explores adjacent cells to collect all keys while avoiding obstacles.
