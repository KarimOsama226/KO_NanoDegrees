#!/usr/bin/env python
# coding: utf-8

# # Longest Palindromic Subsequence
# 
# In general, a palindrome is a string that reads the same backwards as forwards, e.g., `MADAM`. In the last notebook, we saw that in a given string, a subsequence is an ordered set of characters that need not necessarily be a contiguous substring, e.g., `ABC` is a subsequence in `AXBYC` with `length = 3`. 
# 
# The **Longest Palindromic Subsequence** (LPS) is the most lengthy sequence of characters that is a palindrome. In this notebook, you'll be tasked with finding the **length of the LPS in a given string of characters**.
# 
# Examples:
# 1. With an input string, `MAXDYAM`, the LPS is `MADAM`, which has `length = 5`
# 1. With an input string, `BxAoNxAoNxA`, the LPS is `ANANA`, which has `length = 5`
# 1. With an input string, `BANANO`, the LPS is `NAN`, which has `length = 3`
# 1. With an input string, `ABBDBCACB`, the LPS is `BCACB`, which has `length = 5`
# 
# In this notebook, we'll focus on finding an optimal solution to finding the length of the LPS task, using dynamic programming. There will be some similarities to the Longest Common Subsequence (LCS) task, which is outlined in detail in a previous notebook. It is recommended that you start with that notebook before trying out this task.
# 
# ## Dynamic Programming Approach - Storing Pre-Computed Values
# 
# Similar to how you compared **two strings** in the LCS (Longest Common Subsequence) task, you can compare the characters in just **one string** with itself, in this LPS task. 
# 
# * The LPS solution algorithm depends on how the characters of a given string are related to each other. 
# 
# * For a string of length *n* characters, you can build an `n x n` matrix. The 2-D matrix will have characters of the given string on the top as well as on the left side.
# 
# * The matrix will store the solution to **smaller subproblems** first, and gradually adding up more characters to the problem. In this case, **a subproblem is to find the length of the LPS, up to a certain point in the string**. 
# 
# 
# Read the rules below to fill up the matrix cells. 

# 
# ### The Matrix Rules
# 
# You can efficiently fill up this matrix one cell at a time. Each grid cell only depends on the values in the grid cells that are directly on bottom and to the left of it, or on the diagonal/bottom-left. The rules are as follows:
# * Start with an `n x n ` matrix where *n* is the number of characters in a given string. The diagonal cells should all have the value 1 for the base case, the rest can be zeros.
# * As you traverse your string:
#     * If there is a match, fill that grid cell with the value to the bottom-left of that cell ***plus two***.
#     * If there is not a match, take the ***maximum value*** from either directly to the left or the bottom cell, and carry that value over to the non-match cell.
# * After completely filling the matrix, ***the top-right cell will hold the final LPS length***.
# 
# Note: The lower diagonal values will remain 0 in all cases.
# 
# ---
# 
# It may be helpful to try filling up a matrix on paper before you start your code solution. If you get stuck with this task, you may look at some example matrices below, before consulting the complete solution code.

# ### Example Matrices
# 
# Example LPS Subproblem matrix 1:
# 
# ```
# input_string = 'BANANO'
# 
# LPS subproblem matrix:
#   
#      B  A  N  A  N  O
# B  [[1, 1, 1, 3, 3, 3],
# A   [0, 1, 1, 3, 3, 3],
# N   [0, 0, 1, 1, 3, 3],
# A   [0, 0, 0, 1, 1, 1],
# N   [0, 0, 0, 0, 1, 1],
# O   [0, 0, 0, 0, 0, 1]]
# 
# LPS length:  3
# ```
# 
# Example LPS Subproblem matrix 2:
# ```
# input_string = 'TACOCAT'
# 
# LPS subproblem matrix:
# 
#      T  A  C  O  C  A  T
# T  [[1, 1, 1, 1, 3, 5, 7],
# A   [0, 1, 1, 1, 3, 5, 5],
# C   [0, 0, 1, 1, 3, 3, 3],
# O   [0, 0, 0, 1, 1, 1, 1],
# C   [0, 0, 0, 0, 1, 1, 1],
# A   [0, 0, 0, 0, 0, 1, 1],
# T   [0, 0, 0, 0, 0, 0, 1]]
# 
# LPS length:  7
# ```
# 

# ### Exercise - Write the function definition here

# In[ ]:


def lps(input_string): 
    
    # TODO: Complete this implementation of the LPS function
    # The function should return one value: the LPS length for the given input string
    pass


# <span class="graffiti-highlight graffiti-id_d28fhk7-id_3yrlf09"><i></i><button>Show Solution</button></span>

# ### Test - Let's test your function

# In[ ]:


def test_function(test_case):
    string = test_case[0]
    solution = test_case[1]
    output = lps(string)
    print(output)
    if output == solution:
        print("Pass")
    else:
        print("Fail")


# In[ ]:


string = 'BxAoNxAoNxA'
solution = 5
test_case = [string, solution]
test_function(test_case)


# In[ ]:


string = 'BANANO'
solution = 3
test_case = [string, solution]
test_function(test_case)


# In[ ]:


string = "TACOCAT"
solution = 7
test_case = [string, solution]
test_function(test_case)


# ### Time Complexity
# 
# **What is the time complexity of the above implementation?**
# 
# In the solution, we are looping over the elements of our `input_string` using two `for` loops; these are each of $O(N)$ and nested this becomes $O(N^2)$. This behavior dominates our optimized solution.
