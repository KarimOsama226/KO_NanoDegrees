<!--
Problem 3: Rearrange Array Digits

Provide an explanation for your answer, clearly organizing your thoughts into
concise and easy-to-understand language.

Focus on explaining the reasoning behind your decisions rather than giving a 
detailed description of the code. For instance, why did you choose a particular 
data structure? Additionally, discuss the efficiency of your solution in terms 
of time and space complexity. If necessary, you can support your explanation 
with code snippets or mathematical formulas. For guidance on how to write 
formulas in markdown, refer to https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/writing-mathematical-expressions.
-->
A heap to heapify the elements each time inserted to the heap, what do you think of that solution:
1- create a max heap ,
2- heapify it by continuously letting the root be the max
3- after the heap is completed we can follow an equation like :
1st Number: Pop *(10 ^ n) 
2nd number Pop * (10^ n) 
n starts from 0 and increment by 1 
repeat till the heap is empty, so the Most significant digits are the max 2 numbers