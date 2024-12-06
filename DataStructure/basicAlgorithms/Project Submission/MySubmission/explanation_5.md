<!--
Problem 5: Autocomplete with Tries

Provide an explanation for your answer, clearly organizing your thoughts into
concise and easy-to-understand language.

Focus on explaining the reasoning behind your decisions rather than giving a 
detailed description of the code. For instance, why did you choose a particular 
data structure? Additionally, discuss the efficiency of your solution in terms 
of time and space complexity. If necessary, you can support your explanation 
with code snippets or mathematical formulas. For guidance on how to write 
formulas in markdown, refer to https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/writing-mathematical-expressions.
-->
TrieNode:

Stores children and marks the end of words.
suffixes: Finds all suffixes recursively.
Trie:

Manages the root node and handles word insertion and prefix searches.
How It Works:

Words are inserted character by character.
For a prefix, the node is found (find), and suffixes are listed from that node (suffixes).