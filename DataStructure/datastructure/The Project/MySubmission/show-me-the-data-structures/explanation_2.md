
## Reasoning Behind Decisions:
Treating the directory as a tree structure problem so we can take a path, loop over it, if the item is path, open it, else, a file, check the extenstion, 
if the desired one, add it to the output list
## Time Efficiency:
O(n) as n is number of (directories and files) as we check every single item once
## Space Efficiency:
O(o+m) where o is the output of desired output files extensions, and m is the max depth of recursice stack call 
