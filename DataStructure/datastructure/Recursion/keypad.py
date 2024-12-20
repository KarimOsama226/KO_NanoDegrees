#Keypad Example:
def get_characters(num):
    if num == 2:
        return "abc"
    elif num == 3:
        return "def"
    elif num == 4:
        return "ghi"
    elif num == 5:
        return "jkl"
    elif num == 6:
        return "mno"
    elif num == 7:
        return "pqrs"
    elif num == 8:
        return "tuv"
    elif num == 9:
        return "wxyz"
    else:
        return ""

def prepare_List(fList):
    theList=[]
    for subset in fList:
        for ch in subset:
            # print("*"*50)
            # print(index)
            # print("*"*50)
            for index in fList[1:]:
                group =  str(ch) + (index)               
                theList.append(group)
    return theList

def keypad(num):
    length = len(str(num))
    print(f"legnth of {num} is {length}")
    stream = str(num)
    if num == 0 or num == 1:
        return []
    finalList=[]
    for char in stream:
        # print (f"the char is {char}")
        chars= get_characters(int(char))
        # print(f"chars are {chars}")
        finalList.append(chars)
        # print(finalList)
    result =  prepare_List(finalList)
    print(result)
    return result
 

def test_keypad(input, expected_output):
    if sorted(keypad(input)) == expected_output:
        print("Yay. We got it right.")
    else:
        print("Oops! That was incorrect.")

# Example case
input = 23
expected_output = sorted(["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"])
test_keypad(input, expected_output)

# Base case: list with empty string
input = 0
expected_output = [""]
test_keypad(input, expected_output)

# Example case
input = 32
expected_output = sorted(["da", "db", "dc", "ea", "eb", "ec", "fa", "fb", "fc"])
test_keypad(input, expected_output)

# Example case
input = 8
expected_output = sorted(["t", "u", "v"])
test_keypad(input, expected_output)

input = 354
expected_output = sorted(["djg", "ejg", "fjg", "dkg", "ekg", "fkg", "dlg", "elg", "flg", "djh", "ejh", "fjh", "dkh", "ekh", "fkh", "dlh", "elh", "flh", "dji", "eji", "fji", "dki", "eki", "fki", "dli", "eli", "fli"])
test_keypad(input, expected_output)


'''
def keypad(num):
    
    # Base case
    if num <= 1:
        return [""]

    # If `num` is single digit, get the LIST having one element - the associated string
    elif 1 < num <= 9:
        return list(get_characters(num))

    # Otherwise `num` >= 10. Find the unit's (last) digits of `num` 
    last_digit = num % 10
    
    """Step 1"""
    # Recursive call to the same function with “floor” of the `num//10`
    small_output = keypad(num//10)               # returns a LIST of strings
    
    """Step 2"""
    # Get the associated string for the `last_digit`
    keypad_string = get_characters(last_digit)   # returns a string
    
    """Permute the characters of result obtained from Step 1 and Step 2"""
    output = list()

"""
    The Idea:
    Each character of keypad_string must be appended to the 
    end of each string available in the small_output
   """
    for character in keypad_string:
        for item in small_output:
            new_item = item + character
            output.append(new_item)
    
    return output                                # returns a LIST of strings

'''