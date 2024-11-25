#Permutation:

import copy                                           # `copy` module
# list1 = [0, 1, 2]
# list2 = [7, 8, 9]                                     
# compoundList1 = [list1, list2]                        # create a compound object
'''ASSIGNMENT OPERATION - Points a new reference to the existing object.'''
# compoundList2 = compoundList1
# # id() - returns the identity of the object passed
# print(id(compoundList1) == id(compoundList2))          # True - compoundList2 is the same object as compoundList1
# print(id(compoundList1[0]) == id(compoundList2[0]))    # True - compoundList2[0] is the same object as compoundList1[0]
# '''SHALLOW COPY'''
# compoundList2 = copy.copy(compoundList1)
# print(id(compoundList1) == id(compoundList2))          # False - compoundList2 is now a new object
# print(id(compoundList1[0]) == id(compoundList2[0]))    # True - compoundList2[0] is the same object as compoundList1[0]
# '''DEEP COPY'''
# compoundList2 = copy.deepcopy(compoundList1)
# print(id(compoundList1) == id(compoundList2))          # False - compoundList2 is now a new object
# print(id(compoundList1[0]) == id(compoundList2[0]))    # False - compoundList2[0] is now a new object
# Code

def permute(inputList,counter):
    """
    Args: myList: list of items to be permuted
    Returns: list of permutation with each permuted item being represented by a list
    """
    print(inputList)
    listToReturn = [[]]
    if(counter == 0):
        print("Hi")
        print(inputList)
        return listToReturn
    singleList=[]
    x=inputList[-1]
    for item in range(len(inputList)- 2, -1, -1):
        temp = inputList[item]
        inputList[item]= inputList[item+1]
        inputList[item+1]=temp
        singleList = inputList
        listToReturn=copy.deepcopy(singleList)
        print(singleList)
        # print(listToReturn)
    
    permute(inputList,counter-1)

# Test Cases 
# Helper Function
def check_output(output, expected_output):
    """
    Return True if output and expected_output
    contains the same lists, False otherwise.
    
    Note that the ordering of the list is not important.
    
    Examples:
        check_output([ [0, 1], [1, 0] ] ], [ [1, 0], [0, 1] ]) returns True
    Args:
        output(list): list of list
        expected_output(list): list of list
    
    Returns:
        bool
    """
    o = copy.deepcopy(output)  # so that we don't mutate input
    e = copy.deepcopy(expected_output)  # so that we don't mutate input
    
    o.sort()
    e.sort()
    return o == e
print(permute([0, 1, 2],3))
# print("Pass" if  (check_output(permute([]), [[]])) else "Fail")
# print("Pass" if  (check_output(permute([0]), [[0]])) else "Fail")
# print("Pass" if  (check_output(permute([0, 1]), [[0, 1], [1, 0]])) else "Fail")
# print("Pass" if  (check_output(permute([0, 1, 2]), [[0, 1, 2], [0, 2, 1], [1, 0, 2], [1, 2, 0], [2, 0, 1], [2, 1, 0]])) else "Fail")

'''
# Recursive Solution 
"""
Args: myList: list of items to be permuted
Returns: compound list: list of permutation with each permuted item being represented by a list
"""
import copy                                # We will use `deepcopy()` function from the `copy` module

def permute(inputList):
    
    # a compound list
    finalCompoundList = []                  # compoundList to be returned 
    
    # Terminaiton / Base condition
    if len(inputList) == 0:
        finalCompoundList.append([])
        
    else:
        first_element = inputList[0]        # Pick one element to be permuted
        after_first = slice(1, None)        # `after_first` is an object of type 'slice' class
        rest_list = inputList[after_first]  # convert the `slice` object into a list
        
        # Recursive function call
        sub_compoundList = permute(rest_list)
        
        # Iterate through all lists of the compoundList returned from previous call
        for aList in sub_compoundList:
            
            # Permuted the `first_element` at all positions 0, 1, 2 ... len(aList) in each iteration
            for j in range(0, len(aList) + 1): 
                
                # A normal copy/assignment will change aList[j] element
                bList = copy.deepcopy(aList)  
                
                # A new list with size +1 as compared to aList
                # is created by inserting the `first_element` at position j in bList
                bList.insert(j, first_element)
                
                # Append the newly created list to the finalCompoundList
                finalCompoundList.append(bList)
                
    return finalCompoundList
'''