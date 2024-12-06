#!/usr/bin/env python
# coding: utf-8

# ### Problem Statement
# 
# Write a function that takes an input array (or Python list) consisting of only `0`s, `1`s, and `2`s, and sorts that array in a single traversal.
# 
# Note that if you can get the function to put the `0`s and `2`s in the correct positions, this will aotumatically cause the `1`s to be in the correct positions as well.

# In[1]:


def sort_012(input_list):
    pass


# <span class="graffiti-highlight graffiti-id_rrxcwca-id_1f2p5yd"><i></i><button>Show Solution</button></span>

# In[7]:


def test_function(test_case):
    sort_012(test_case)
    print(test_case)
    if test_case == sorted(test_case):
        print("Pass")
    else:
        print("Fail")


# In[5]:


test_case = [0, 0, 2, 2, 2, 1, 1, 1, 2, 0, 2]
test_function(test_case)


# In[10]:


test_case = [2, 1, 2, 0, 0, 2, 1, 0, 1, 0, 0, 2, 2, 2, 1, 2, 0, 0, 0, 2, 1, 0, 2, 0, 0, 1]
test_function(test_case)


# In[20]:


test_case = [2, 2, 0, 0, 2, 1, 0, 2, 2, 1, 1, 1, 0, 1, 2, 0, 2, 0, 1]
test_function(test_case)

