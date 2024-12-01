
## Reasoning Behind Decisions:
    Using the List indexing to get the last item (-1) to get its hash and use it in the new appended data
    Make sure to include the gensis block if the chain is empty 
## Time Efficiency (Blockchain):
    O(1) as we are accessing the [-1]element
## Space Efficiency (Blockchain):
    O(n) where n is the number of Blocks in the chain

## Time Efficiency (Block):
    O(n) where n is the size of data when calculating the hash
    O(m) where m is the number of blocks added to the chain
    O(n*m)
## Space Efficiency (Block):
    O(n) where n is the size of data stored
    O(m) where m is the number of blocks added to the chain
    O(n*m)