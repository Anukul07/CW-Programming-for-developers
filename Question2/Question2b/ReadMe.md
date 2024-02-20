# Secret Knowledge Detector

This Java application is built to uncover members who are privy to secret information within a group by analyzing their meeting interactions.

## Overview

The program employs a graph-based approach, leveraging a union-find data structure to identify individuals who possess the secret knowledge. It processes meetings chronologically and identifies individuals connected to the first person who holds the secret.

## Example

``` java

int n = 5; // Number of people
int[][] meetings = {
    {0, 2, 1}, // Meeting 1: person 0 and person 2 meet at time 1
    {1, 3, 2}, // Meeting 2: person 1 and person 3 meet at time 2
    {2, 4, 3}  // Meeting 3: person 2 and person 4 meet at time 3
};
int firstPerson = 0; // Index of the first person who knows the secret

// Output - People who know the secret: [0, 2, 4]

```
