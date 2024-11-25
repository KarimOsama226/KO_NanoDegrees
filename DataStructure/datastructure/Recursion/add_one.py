def add_one(arr):
    """
    :param: arr - list of digits representing some number x
    return a list with digits represengint (x + 1)
    """
    if arr == [9]:
        return [1,0]
    if arr[-1] < 9:
        arr[-1]+=1
    else:
        arr = add_one(arr[:-1]) + [0]
    return arr

# A helper function for Test Cases
def test_function(test_case):
    arr = test_case[0]
    solution = test_case[1]
    
    output = add_one(arr)
    for index, element in enumerate(output):
        if element != solution[index]:
            print("Fail")
            return
    print("Pass")   
#TC1
arr = [0]
solution = [1]
test_case = [arr, solution]
test_function(test_case)
#TC2
arr = [1, 2, 3]
solution = [1, 2, 4]
test_case = [arr, solution]
test_function(test_case)
#TC3
arr = [9, 9, 9]
solution = [1, 0, 0, 0]
test_case = [arr, solution]
test_function(test_case)

'''
# Recursive Solution
def add_one(arr):
    """
    :param: arr - list of digits representing some number x
    return a list with digits represengint (x + 1)
    """
    # Base case 
    if arr == [9]:
        return [1, 0]
    
    # A simple case, where we just need to increment the last digit
    if arr[-1] < 9:
        arr[-1] += 1

    # Case when the last digit is 9.
    else:
        """Recursive call"""
        # We have used arr[:-1], that means all elements of the list except the last one.
        # Example, for original input arr=[1,2,9], we will pass [1,2] in next call.
        arr = add_one(arr[:-1]) + [0]
        
    return arr
'''