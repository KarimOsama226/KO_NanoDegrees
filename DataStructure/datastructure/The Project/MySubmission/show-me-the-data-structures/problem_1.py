from collections import OrderedDict
from typing import Any, Optional

class LRU_Cache:
    """
    A class to represent a Least Recently Used (LRU) cache.

    Attributes:
    -----------
    capacity : int
        The maximum number of items the cache can hold.
    cache : OrderedDict[int, Any]
        The ordered dictionary to store cache items.
    """

    def __init__(self, capacity: int) -> None:
        """
        Constructs all the necessary attributes for the LRU_Cache object.

        Parameters:
        -----------
        capacity : int
            The maximum number of items the cache can hold.
        """
        self.cache = {}
        self.capacity = capacity

    def get(self, key: int) -> Optional[Any]:
        """
        Get the value of the key if the key exists in the cache, otherwise return -1.

        Parameters:
        -----------
        key : int
            The key to be accessed in the cache.

        Returns:
        --------
        Optional[Any]
            The value associated with the key if it exists, otherwise -1.
        """
        if key in self.cache:
            #remove it and readd it to be The Last at the End
            value = self.cache.pop(key) 
            self.cache[key] = value
            return value
        return -1

    def set(self, key: int, value: Any) -> None:
        """
        Set or insert the value if the key is not already present. When the cache reaches 
        its capacity, it should invalidate the least recently used item before inserting 
        a new item.

        Parameters:
        -----------
        key : int
            The key to be inserted or updated in the cache.
        value : Any
            The value to be associated with the key.
        """
        if key is None:
            return -1
        if self.capacity == 0:
            self.remove()
        self.capacity-=1
        self.cache[key]=value

        
        
    def remove (self):
        #remove the first Key by iterating over them and get the fist iteration
        firstKey = next(iter(self.cache))
        self.cache.pop(firstKey)
        self.capacity +=1


if __name__ == '__main__':
    # Testing the LRU_Cache class

    # Test Case 1: Basic functionality
    our_cache = LRU_Cache(5)
    our_cache.set(1, 1)
    our_cache.set(2, 2)
    our_cache.set(3, 3)
    our_cache.set(4, 4)
    assert our_cache.get(1) == 1   # Returns 1
    assert our_cache.get(2) == 2   # Returns 2
    assert our_cache.get(9) == -1  # Returns -1 because 9 is not in the cache

    our_cache.set(5, 5)
    our_cache.set(6, 6)  # This should evict key 3
    assert our_cache.get(3) == -1  # Returns -1, 3 was evicted

    # Test Case 2 passing None as Value
    our_cache = LRU_Cache(5)
    assert our_cache.set(None,10) == -1  # Returns -1, as The key is None
    # Test Case 3 passing Large Values
    our_cache.set(1515151,1515151) 
    assert our_cache.get(1515151) == 1515151  # Returns the expected Value
    
    # Test Case 4 Make sure it will be overwritten using the same key
    our_cache.set(1,15)
    assert our_cache.get(1) == 15  # Returns the expected Value
    our_cache.set(1,17)
    assert our_cache.get(1) == 17  # Returns the expected Value
    
