
## Reasoning Behind Decisions:
Using Heap give us the pop of least frequent -the hint was given in the imports-
and following the instructions as stated in the problem statment
-The Only thing i did not get was that, why taking last two elements the A and C, instead of talking one at a time as the rest of the nodes
## Time Efficiency:
O(f): calculate the frequencies
O(nlogn): heap construction
O(m): length of string of the Codes
encoding -> O(f+m+ n*log(n))
decoding -> O(n) where n is number of bits
## Space Efficiency:
freq : O(k): number of unique characters
tree: O(k + constant): for the tree construction
code: O(n) n: input string size
total O(k+n)
