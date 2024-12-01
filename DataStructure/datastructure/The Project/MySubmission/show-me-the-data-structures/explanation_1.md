
## Reasoning Behind Decisions:
Get Operation is as simple as checking key in dict, if so, return value, else return -1,
but to implement the LRU, when finding a key value pair, we just pop it from its order, add it again taking advanatage that the dictionaries are keeping the sort of items the way they are added
Set operation checks first the capacity, if there are a room for new pair, procceed, otherwise remove (discussed later)
after making sure there is a room, just add it to the queue and adjust the capacity
Remove operation, create an iterable over the dictionary, remove the first index (as this should be the least one used)
## Time Efficiency:
O(1) as requested, no Looping, that's why it is O(1) as we are just add an item, and when getting it, Just find a key in dict, even in removing, it is just the first iteration
## Space Efficiency:
O(n) where n is the capacity which we can say O(5) if the n = 5, other local variables  are O(1) as they are all just a local variables not change when capacity is changed

