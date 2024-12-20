"""
Problem 1: Square Root of an Integer

In this problem, you need to find the square root of a given integer without using 
any Python libraries. You should return the floor value of the square root.

Below is a function signature that serves as a starting point for your implementation. 
Your task is to complete the body of the function. Additionally, some test cases are 
provided to help you verify the correctness of your implementation. If necessary, add 
test cases to verify that your algorithm is working properly.

The expected time complexity is O(log(n)).
"""

def sqrt(number: int) -> int:
    """
    Calculate the floored square root of a number

    Args:
    number(int): Number to find the floored square root

    Returns:
    int: Floored square root
    """
    if number == 0 or number == 1: 
        return number
    #let's start from midpoint :
    num = number // 2
    print (f"before {num}")
    while (num < number):
        if num * num == number:
            print (f"Equals {num}")
            return num # Exact value
        else:
            if num * num > number:
               num -= (num)//2
               print (f"GREATER {num}")
               if num*num < number:
                   if (num+1)*(num+1) > number:
                       return num # return the smallest value

            else:
                num+= (num)//2
                print (f"less {num}")
                if(num * num > number):
                    if (num-1)*(num-1) < number:
                        return num-1 #SIMULATES the floor option 
                    
                
    return num
if __name__ == "__main__":
    # Test cases
    print("Pass" if 3 == sqrt(9) else "Fail")   # Expected Output: Pass
    print("Pass" if 0 == sqrt(0) else "Fail")   # Expected Output: Pass
    print("Pass" if 4 == sqrt(16) else "Fail")  # Expected Output: Pass
    print("Pass" if 1 == sqrt(1) else "Fail")   # Expected Output: Pass
    print("Pass" if 5 == sqrt(27) else "Fail")  # Expected Output: Pass
