<!--
Problem 4: Dutch National Flag Problem

Provide an explanation for your answer, clearly organizing your thoughts into
concise and easy-to-understand language.

Focus on explaining the reasoning behind your decisions rather than giving a 
detailed description of the code. For instance, why did you choose a particular 
data structure? Additionally, discuss the efficiency of your solution in terms 
of time and space complexity. If necessary, you can support your explanation 
with code snippets or mathematical formulas. For guidance on how to write 
formulas in markdown, refer to https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/writing-mathematical-expressions.
-->
The main idea resides to simply traverse single time and filter the results to be:
if 0 -> append to the list of zeroes 
if 1 -> append to the list of ones 
if 2 -> append to the list of twos 
then return the 3 lists concatened (added) together