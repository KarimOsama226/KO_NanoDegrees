<!--
Problem 1: Square Root of an Integer

Provide an explanation for your answer, clearly organizing your thoughts into 
concise and easy-to-understand language.

Focus on explaining the reasoning behind your decisions rather than giving a 
detailed description of the code. For instance, why did you choose a particular 
data structure? Additionally, discuss the efficiency of your solution in terms 
of time and space complexity. If necessary, you can support your explanation 
with code snippets or mathematical formulas. For guidance on how to write 
formulas in markdown, refer to https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/writing-mathematical-expressions.
-->
Explanation:
first we start at the half of the number as the square root of number is always less than (or equal to) half of it,
after starting midway we multiply the newly caluculated number by its self and see if :
1- equals the number => then this is the square root
2- greater than the number => move midway to the 0 (num -=num //2), if less than the number, move midway to the number
3- after calulating the new value, we check the previous and next values in greater and less cases,
    a- if the new number^2 is greater than the number, and the (number-1)^2 is less than the number, then it is the closest answer (the floor is  num-1)
    b- if the new number^2 is less than the number, and the (numberr+1)^2 is greater than the number, then the (num) is the closest floor
4- after adjusting compare it in the loop if the (num^2) equals the number
5- the 0 and 1 can be considered a specail case so these sceanrios are treated that way 

Time Complexity:
O(log(n)) as we are following the same searching technique
Space Complexity:
O(1): it is independant on the input value