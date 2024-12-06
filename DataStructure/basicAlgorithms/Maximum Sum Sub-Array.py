#!/usr/bin/env python
# coding: utf-8

# ### Problem Statement
# You are given an array `arr` having `n` integers. You have to find the maximum sum of contiguous subarray among all the possible subarrays. This problem is commonly called as [Maximum Subarray Problem](https://en.wikipedia.org/wiki/Maximum_subarray_problem). Solve this problem in *O(nlogn)* time, using Divide and Conquer approach. 
# 
# 
# **Example 1**<br>
# Input: `arr = [-2, 1, -3, 5, 0, 3, 2, -5, 4]`<br>
# Output: `Maximum Sum = 10` for the  `subarray = [5, 0, 3, 2]`<br>
# 
# **Example 2**<br>
# Input: `arr = [-2, -5, 6, -2, -3, 1, 5, -6]`<br>
# Output: `Maximum Sum = 7`  for the  `subarray = [6, -2, -3, 1, 5]`<br>
# 
# ***As of now, let's not return the subarray itself.***

# ---
# ### The Idea
# Divide the given array into three subarray w.r.t. the middle, say Left, Right, and Cross subarrays. Recurse on the Left part, and Right part untill you reach the base condition, i.e. single element in a subarray. 
# 
# Calculate the **maximum sum** of the Left, Right, and Cross subarrays, say `L`, `R`, and `C` respectively. **Return the maximum of `L`, `R`, and `C`.** 
# 
# 
# #### Logic to Calculate `C`, the Maximum sum of a "Cross" Subarray
# Start from the middle index, and traverse (sum the elements) in the left direction. Keep track of the maximum sum on the left part, say `leftMaxSum`. Similarly, start from the (middle +1) index, and traverse (sum the elements) in the right direction.  Keep track of the maximum sum on the right part, say `rightMaxSum`. Return the `(leftMaxSum + rightMaxSum)`, as `C`. The following exmaple would help you imagine the solution better:
# 
# 
# <img style="float: center;" src="DNC.png">
# <p style="text-align:center;">The solution is 13.</p>
# 
# --- 
# ### Pseudocode and Time Complexity Analysis
# `maxSubArrayRecursive(arr, start, stop)`&emsp;&emsp;&emsp;&emsp; <font color="red">*T(n)*</font> <br> 
# &emsp;&emsp;` 1. if (start==stop):`<br>
# &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;`return arr[start]`<br><br>
# &emsp;&emsp;` 2. Calculate mid index`&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <font color="red">*constant*</font> <br><br>
# &emsp;&emsp;` 3. L = maxSubArrayRecursive(arr, start, mid) `&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <font color="red">*T($\frac{n}{2}$)*</font> <br><br>
# &emsp;&emsp;` 4. R = maxSubArrayRecursive(arr, mid+1, stop) `&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <font color="red">*T($\frac{n}{2}$)*</font> <br><br>
# &emsp;&emsp;` 5. C = maxCrossingSum(arr, start, mid, stop) ` &emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <font color="red">*$\Theta(n)$*</font> <br><br>
# &emsp;&emsp;` 6. return max(C, max(L,R)) `&emsp;&emsp;&emsp;&emsp;&emsp;&emsp; <font color="red">*constant*</font> <br>
# <br>
# 
# Total time of execution $T(n)$ = $2*T(\frac{n}{2}) + \Theta(n) \equiv O(nlogn)$
# 
# ### Exercise - Write the function definition here. 

# In[ ]:


def maxSubArray(arr):
    '''
    param: An array `arr`
    return: The maximum sum of the contiguous subarray. 
    No need to return the subarray itself.
    '''
    pass


# <span class="graffiti-highlight graffiti-id_ojenmxb-id_pw4giwj"><i></i><button>Show Solution</button></span>

# ### Test - Let's test your function

# In[ ]:


# Test your code
arr = [-2, 7, -6, 3, 1, -4, 5, 7] 
print("Maximum Sum = ",maxSubArray(arr))     # Outputs 13


# In[ ]:


# Test your code
arr = [-2, 1, -3, 4, -1, 2, 1, -5, 4] 
print("Maximum Sum = ",maxSubArray(arr))     # Outputs 6


# In[ ]:


# Test your code
arr = [-4, 14, -6, 7] 
print("Maximum Sum = ",maxSubArray(arr))     # Outputs 15


# In[ ]:


# Test your code
arr = [-2, 1, -3, 5, 0, 3, 2, -5, 4]
print("Maximum Sum = ",maxSubArray(arr))     # Outputs 10


# In[ ]:


# Test your code
arr = [-2, -5, 6, -2, -3, 1, 5, -6]
print("Maximum Sum = ",maxSubArray(arr))     # Outputs 7

