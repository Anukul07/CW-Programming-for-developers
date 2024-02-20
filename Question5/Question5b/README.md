# ISP Application

This Java application simulates an Internet Service Provider (ISP) network management system. It allows users to define a network of devices and find the devices impacted when a particular device loses power.

## Example

``` java

Suppose we have the following network:

0 -> 1
0 -> 2
1 -> 3
1 -> 6
2 -> 4
4 -> 6
4 -> 5
5 -> 7

// Output - 

01100000
10010010
10001000
01000000
00100110
00001001
01001000
00000100
Impacted Device List: [7, 5]

```