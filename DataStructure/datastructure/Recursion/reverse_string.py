def reverse_string(input):
    """
    Reverse a given string using recursion.

    This function takes a string as input and returns a new string that is the reverse
    of the original. It uses a recursive approach to achieve this by repeatedly removing
    the first character from the string, reversing the rest, and then appending the
    first character to the reversed substring.

    Args:
        input (str): The string to be reversed.

    Returns:
        str: A new string that is the reverse of the input string.

    Example:
        >>> reverse_string("hello")
        'olleh'

    Details:
        - The function uses a base condition to terminate recursion when the string is
          empty, returning an empty string.
        - It utilizes the `slice()` function to extract a substring starting from the
          second character.
        - The function recursively reverses the substring and concatenates the first
          character to the reversed result.
    """
    if len(input) == 0:
        return ''
    else:
        return input[-1]+reverse_string(input[:-1])
    # TODO: Write your recursive string reverser solution here

print("Pass" if ("" == reverse_string("")) else "Fail")
print("Pass" if ("cba" == reverse_string("abc")) else "Fail")

#Udacity Solution:
'''
def reverse_string(input):
    
    # (Recursion) Termination condition / Base condition
    if len(input) == 0:
        return ""

    else:
        first_char = input[0]
        
        #
        The `slice()` function can accept upto the following three arguments.
        - start: [OPTIONAL] starting index. Default value is 0.
        - stop: ending index (exclusive)
        - step_size: [OPTIONAL] the increment size. Default value is 1.
        
        The return type of `slice()` function is an object of class 'slice'. 
        #
        the_rest = slice(1, None)     # `the_rest` is an object of type 'slice' class
        sub_string = input[the_rest]  # convert the `slice` object into a list
        
        # Recursive call
        reversed_substring = reverse_string(sub_string)
        
        return reversed_substring + first_char
'''