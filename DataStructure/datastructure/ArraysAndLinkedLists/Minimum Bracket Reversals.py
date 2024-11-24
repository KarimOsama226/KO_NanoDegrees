#Minimum Bracket Reversals
class LinkedListNode:

    def __init__(self, data):
        self.data = data
        self.next = None

class Stack:

    def __init__(self):
        self.num_elements = 0
        self.head = None

    def push(self, data):
        new_node = LinkedListNode(data)
        if self.head is None:
            self.head = new_node
        else:
            new_node.next = self.head
            self.head = new_node
        self.num_elements += 1

    def pop(self):
        if self.is_empty():
            return None
        temp = self.head.data
        self.head = self.head.next
        self.num_elements -= 1
        # print(f"popping {temp}")
        return temp

    def top(self):
        if self.head is None:
            return None
        return self.head.data

    def size(self):
        return self.num_elements

    def is_empty(self):
        return self.num_elements == 0
    
def minimum_bracket_reversals(input_string):
    """
    Calculate the number of reversals to fix the brackets.

    Args:
       input_string (string): String used for bracket reversal calculation
    Returns:
       int: Number of bracket reversals needed
    """
    # if len(input_string)%2 != 0 :
    #     return -1
    # else:
    #     # print(input_string)
    #     stack = Stack()
    #     shortage = 0
    #     for char in input_string:
    #         # print(f"Looping over {char}")
    #         if char == '{':
    #             # print(f"pushing{char}")
    #             stack.push(char)
    #         elif char == '}':
    #             if stack.pop() is None:
    #                 shortage+=1
    #     print(stack.size())
    #     print(shortage)
    #     print(stack.size()/2)
    #     return ((stack.size()+shortage)/2)
#need to consider the orienation which means that logic will not require any movement for "}{" but it should be 2 so the final is "{}"
# TODO: Write function here
#The Solution :
    if len(input_string) % 2 == 1:
        return -1

    stack = Stack()
    count = 0
    for bracket in input_string:
        if stack.is_empty():
            stack.push(bracket)
        else:
            top = stack.top()
            if top != bracket:
                if top == '{':
                    stack.pop()
                    continue
            stack.push(bracket)

    ls = list()
    while not stack.is_empty():
        first = stack.pop()
        second = stack.pop()
        ls.append(first)
        ls.append(second)
        if first == '}' and second == '}':
            count += 1
        elif first == '{' and second == '}':
            count += 2
        elif first == '{' and second == '{':
            count += 1
    return count
  
def test_function(test_case):
    input_string = test_case[0]
    expected_output = test_case[1]
    output = minimum_bracket_reversals(input_string)
    
    if output == expected_output:
        print("Pass")
    else:
        print("Fail")

test_case_1 = ["}}}}", 2]
test_function(test_case_1)
test_case_1 = ["}}}}", 2]
test_function(test_case_1)
test_case_3 = ["{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{}}}}}", 13]
test_function(test_case_3)
test_case_4= ["}{}{}{}{}{}{}{}{}{}{}{}{}{}{}{", 2]
test_function(test_case_4)
test_case_5 = ["}}{}{}{}{}{}{}{}{}{}{}{}{}{}{}", 1]
test_function(test_case_5)

def minimum_bracket_reversals(input_string):
    if len(input_string) % 2 == 1:
        return -1

    stack = Stack()
    count = 0

    # Traverse through the input string
    for bracket in input_string:
        if bracket == '{':
            stack.push(bracket)
        else:  # bracket == '}'
            if not stack.is_empty() and stack.top() == '{':
                stack.pop()
            else:
                stack.push(bracket)

    # Calculate the number of reversals needed
    while not stack.is_empty():
        first = stack.pop()
        second = stack.pop()
        
        # If both are '}}' or '{{', one reversal is needed
        if first == second:
            count += 1
        else:
            # If one is '{' and the other is '}', two reversals are needed
            count += 2

    return count