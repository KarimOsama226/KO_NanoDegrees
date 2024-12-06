<!--
Problem 7: Request Routing in a Web Server with a Trie

Provide an explanation for your answer, clearly organizing your thoughts into 
concise and easy-to-understand language.

Focus on explaining the reasoning behind your decisions rather than giving a 
detailed description of the code. For instance, why did you choose a particular 
data structure? Additionally, discuss the efficiency of your solution in terms 
of time and space complexity. If necessary, you can support your explanation 
with code snippets or mathematical formulas. For guidance on how to write 
formulas in markdown, refer to https://docs.github.com/en/get-started/writing-on-github/working-with-advanced-formatting/writing-mathematical-expressions.
-->
#RouteTrieNode:
Represents each node in the Trie.
    Contains:
    children: Dictionary to store sub-paths.
    handler: String to store the handler for the current route.
#RouteTrie:
Stores the entire route structure using RouteTrieNode.
    Methods:
    insert: Adds a route by traversing and creating nodes as needed.
    find: Searches for a route and returns its handler.
#Router:
Uses RouteTrie to manage routes and handlers.
    Methods:
    add_handler: Adds a route and its handler.
    lookup: Finds the handler for a given route, returns the not_found_handler if not found.
    split_path: Splits the path into parts and removes empty strings (handles trailing slashes).