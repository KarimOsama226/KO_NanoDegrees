#!/usr/bin/env python
# coding: utf-8

# ### Problem statement
# 
# Given an `input_list` and a `target`, return the pair of indices in the list that holds the values which sum to the `target`. For example, 
# 
# `input_list = [1, 5, 9, 7]` and `target = 8`, the answer would be `[0, 3]` 
# 
# **Note**<br>
# 1. The best solution takes O(n) time. *This means that you cannot traverse the given list more than once.* **Hint - Think of an additional data structure that you should use here.** 
# 2. You can assume that the list does not have any duplicates.

# In[1]:


def pair_sum_to_target(input_list, target):
    # TODO: Write pair sum to target function
    result = dict()
    for item in input_list:



# In[2]:


def test_function(test_case):
    output = pair_sum_to_target(test_case[0], test_case[1])
    print(output)
    if sorted(output) == test_case[2]:
        print("Pass")
    else:
        print("Fail")


# In[ ]:


test_case_1 = [[1, 5, 9, 7], 8, [0, 3]]
test_function(test_case_1)


# In[ ]:


test_case_2 = [[10, 5, 9, 8, 12, 1, 16, 6], 16, [0, 7]]
test_function(test_case_2)


# In[ ]:


test_case_3 = [[0, 1, 2, 3, -4], -4, [0, 4]]
test_function(test_case_3)


# <span class="graffiti-highlight graffiti-id_snm0ke6-id_tv0tye7"><i></i><button>Hide Solution</button></span>

# In[ ]:


def pair_sum_to_target(input_list, target):
    
    # Create a dictionary.
    # Each element of the input_list would become a "key", and
    # the corresponding index in the input_list would become the "value"
    index_dict = dict()
    
    # Traverse through the input_list
    for index, element in enumerate(input_list):
        
        # `in` is the way to test for the existence of a "key" in a dictionary
        if (target - element) in index_dict:
            
            # Return the TWO indices that sum to the target
            return [index_dict[target - element], index]

        index_dict[element] = index

    return [-1,-1]              # If the target is not achieved

#with more comments
def longest_consecutive_subsequence(input_list):
    
    # Create a dictionary.
    # Each element of the input_list would become a "key", and
    # the corresponding index in the input_list would become the "value"
    element_dict = dict()

    # Traverse through the input_list, and populate the dictionary
    # Time complexity = O(n) 
    for index, element in enumerate(input_list):
        element_dict[element] = index
        # After this loop, element_dict will look like this:
        # {element1: index1, element2: index2, ..., elementN: indexN}
        # For example, if input_list = [5, 4, 7, 10, 1, 3, 55, 2],
        # element_dict will be {5: 0, 4: 1, 7: 2, 10: 3, 1: 4, 3: 5, 55: 6, 2: 7}

    # Represents the length of longest subsequence
    max_length = -1
    
    # Represents the index of smallest element in the longest subsequence
    starts_at = -1  

    # Traverse again - Time complexity = O(n) 
    for index, element in enumerate(input_list):
        current_starts = index
        element_dict[element] = -1         # Mark as visited
        # After marking, element_dict will have the current element's value set to -1
        # This indicates that the element has been processed.

        current_count = 1                  # length of the current subsequence

        '''CHECK ONE ELEMENT FORWARD'''
        current = element + 1              # `current` is the expected number

        # Check if the expected number is available (as a key) in the dictionary,
        # and it has not been visited yet (i.e., value > 0)
        while current in element_dict and element_dict[current] > 0:
            current_count += 1             # increment the length of subsequence 
            element_dict[current] = -1     # Mark as visited
            current = current + 1          
            # This loop continues until there are no more consecutive numbers found
            # For example, if element = 1, it will check 2, 3, 4, 5 and mark them as visited.

        '''CHECK ONE ELEMENT BACKWARD'''
        current = element - 1             # `current` is the expected number

        while current in element_dict and element_dict[current] > 0:    
            current_starts = element_dict[current]         # index of smallest element in the current subsequence
            current_count += 1                             # increment the length of subsequence 
            element_dict[current] = -1                     # Mark as visited
            current = current - 1
            # This loop checks for consecutive numbers in the backward direction
            # For example, if element = 5, it will check 4, 3, 2, 1 and mark them as visited.

        '''If length of current subsequence >= max length of previously visited subsequence'''
        if current_count >= max_length:
            if current_count == max_length and current_starts > starts_at:
                continue
            starts_at = current_starts            # index of smallest element in the current (longest so far) subsequence
            max_length = current_count            # store the length of current (longest so far) subsequence

    start_element = input_list[starts_at]          # smallest element in the longest subsequence

    # return a NEW list starting from `start_element` to `(start_element + max_length)` 
    return [element for element in range(start_element, start_element + max_length)]
