#!/usr/bin/env python
# coding: utf-8

# # Heapsort
# 
# A heapsort is an in-place sorting algorithm that treats an array like a binary tree and moves the largest values to the end of the heap until the full array is sorted.  
# 
# The main steps in a heapsort are:
# 1. Convert the array into a maxheap (a complete binary tree with decreasing values) 
# 2. Swap the top element with the last element in the array (putting it in it's correct final position)
# 3. Repeat with `arr[:len(arr)-1]` (all but the sorted elements)
# 
# ## Visualization of a heapsort
# ![animation of a heap sort](https://upload.wikimedia.org/wikipedia/commons/4/4d/Heapsort-example.gif)
# 
# ["Heapsort example"](https://commons.wikimedia.org/wiki/File:Heapsort-example.gif) by Swfung8. Used under [CC BY-SA 3.0](https://creativecommons.org/licenses/by-sa/3.0/deed.en).
# 
# ## Problem statement
# 
# In the cell below, see if you can code a `heapsort` function that takes an array (or Python list) and performs a heapsort on it. You will have to complete the heapify

# In[1]:


def heapsort(arr):
    heapify(arr, len(arr), 0)
    
def heapify():
    """
    :param: arr - array to heapify
    n -- number of elements in the array
    i -- index of the current node
    TODO: Converts an array (in place) into a maxheap, a complete binary tree with the largest values at the top
    """


# <span class="graffiti-highlight graffiti-id_1h50lwk-id_kuae7he"><i></i><button>Show Solution</button></span>

# In[6]:


def test_function(test_case):
    heapsort(test_case[0])
    if test_case[0] == test_case[1]:
        print("Pass")
    else:
        print("False")


# In[7]:


arr = [3, 7, 4, 6, 1, 0, 9, 8, 9, 4, 3, 5]
solution = [0, 1, 3, 3, 4, 4, 5, 6, 7, 8, 9, 9]

test_case = [arr, solution]

test_function(test_case)


# In[8]:


arr = [5, 5, 5, 3, 3, 3, 4, 4, 4, 4]
solution = [3, 3, 3, 4, 4, 4, 4, 5, 5, 5]
test_case = [arr, solution]
test_function(test_case)


# In[9]:


arr = [99]
solution = [99]
test_case = [arr, solution]
test_function(test_case)


# In[10]:


arr = [0, 1, 2, 5, 12, 21, 0]
solution = [0, 0, 1, 2, 5, 12, 21]
test_case = [arr, solution]
test_function(test_case)

