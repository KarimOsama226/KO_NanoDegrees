"""
Problem 7: Request Routing in a Web Server with a Trie

This module implements an HTTPRouter using a Trie data structure.

The HTTPRouter takes a URL path like "/", "/about", or 
"/blog/2019-01-15/my-awesome-blog-post" and determines the appropriate handler 
to return. The Trie is used to efficiently store and retrieve handlers based on 
the parts of the path separated by slashes ("/").

The RouteTrie stores handlers under path parts, and the Router delegates adding 
and looking up handlers to the RouteTrie. The Router also includes a not found 
handler for paths that are not found in the Trie and handles trailing slashes 
to ensure requests for '/about' and '/about/' are treated the same.

You sould implement the function bodies the function signatures. Use the test 
cases provided below to verify that your algorithm is correct. If necessary, 
add additional test cases to verify that your algorithm works correctly.
"""

from typing import Optional
class RouteTrieNode:
    """
    A node in the RouteTrie, representing a part of a route.
    """
    def __init__(self):
        """
        Initialize a RouteTrieNode with an empty dictionary for children and no handler.
        """
        self.children = {}  # Map from part of the route to the child RouteTrieNode
        self.handler = None  # The handler for this specific route, if any


class RouteTrie:
    """
    A trie (prefix tree) for storing routes and their handlers.
    """
    def __init__(self, root_handler: str):
        """
        Initialize the RouteTrie with a root handler.
        """
        self.root = RouteTrieNode()
        self.root.handler = root_handler  # Set the handler for the root node

    def insert(self, path_parts: list[str], handler: str) -> None:
        """
        Insert a route and its handler into the trie.
        """
        current_node = self.root
        for part in path_parts:
            if part not in current_node.children:
                current_node.children[part] = RouteTrieNode()
            current_node = current_node.children[part]
        # Assign the handler to the last node
        current_node.handler = handler

    def find(self, path_parts: list[str]) -> Optional[str]:
        """
        Find the handler for a given route.
        """
        current_node = self.root
        for part in path_parts:
            if part not in current_node.children:
                return None
            current_node = current_node.children[part]
        return current_node.handler


class Router:
    """
    A router to manage routes and their handlers using a RouteTrie.
    """
    def __init__(self, root_handler: str, not_found_handler: str):
        """
        Initialize the Router with a root handler and a not-found handler.
        """
        self.route_trie = RouteTrie(root_handler)
        self.not_found_handler = not_found_handler

    def add_handler(self, path: str, handler: str) -> None:
        """
        Add a handler for a route.
        """
        path_parts = self.split_path(path)
        self.route_trie.insert(path_parts, handler)

    def lookup(self, path: str) -> str:
        """
        Look up a route and return the associated handler.
        """
        if path == "":  # Treat empty string as invalid
            return self.not_found_handler
        path_parts = self.split_path(path)
        if len(path_parts) == 0:  # If the path is "/"
            return self.route_trie.root.handler
        handler = self.route_trie.find(path_parts)
        return handler if handler else self.not_found_handler

    def split_path(self, path: str) -> list[str]:
        """
        Split the path into parts and remove empty parts to handle trailing slashes.
        """
        return [part for part in path.split('/') if part]

if __name__ == '__main__':
    # create the router and add a route
    router = Router("root handler", "not found handler")
    router.add_handler("/home/about", "about handler")

    # Edge case: Empty path
    print(router.lookup(""))
    # Expected output: 'not found handler'

    # Normal case: Path not found
    print(router.lookup("/home/contact"))
    # Expected output: 'not found handler'

    # Normal case: Path with multiple segments
    print(router.lookup("/home/about/me"))
    # Expected output: 'not found handler'

    # Normal case: Path with exact match
    print(router.lookup("/home/about"))
    # Expected output: 'about handler'
