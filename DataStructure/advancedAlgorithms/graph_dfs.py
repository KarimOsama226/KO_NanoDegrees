#!/usr/bin/env python
# coding: utf-8

# # Graph Depth First Search
# In this exercise, you'll see how to do a depth first search on a graph. To start, let's create a graph class in Python.

# In[1]:


class GraphNode(object):
    def __init__(self, val):
        self.value = val
        self.children = []
        
    def add_child(self, new_node):
        self.children.append(new_node)
    
    def remove_child(self, del_node):
        if del_node in self.children:
            self.children.remove(del_node)

class Graph(object):
    def __init__(self, node_list):
        self.nodes = node_list
        
    def add_edge(self, node1, node2):
        if(node1 in self.nodes and node2 in self.nodes):
            node1.add_child(node2)
            node2.add_child(node1)
            
    def remove_edge(self,node1,node2):
        if(node1 in self.nodes and node2 in self.nodes):
            node1.remove_child(node2)
            node2.remove_child(node1)


# Now let's create the graph.

# In[2]:


nodeG = GraphNode('G')
nodeR = GraphNode('R')
nodeA = GraphNode('A')
nodeP = GraphNode('P')
nodeH = GraphNode('H')
nodeS = GraphNode('S')

graph1 = Graph([nodeS, nodeH, nodeG, nodeP, nodeR, nodeA]) 
graph1.add_edge(nodeG, nodeR)
graph1.add_edge(nodeA, nodeR)
graph1.add_edge(nodeA, nodeG)
graph1.add_edge(nodeR, nodeP)
graph1.add_edge(nodeH, nodeG)
graph1.add_edge(nodeH, nodeP)
graph1.add_edge(nodeS, nodeR)


# ## Implement DFS
# Using what you know about DFS for trees, apply this to graphs. Implement the `dfs_search` to return the `GraphNode` with the value `search_value` starting at the `root_node`.

# In[ ]:


def dfs_search(root_node, search_value):
    pass


# In[3]:


# Solution                
def dfs_search(root_node, search_value):
    visited = set()                         # Sets are faster for lookups
    stack = [root_node]                     # Start with a given root node
    
    while len(stack) > 0:                   # Repeat until the stack is empty
            
        current_node = stack.pop()          # Pop out a node added recently 
        visited.add(current_node)           # Mark it as visited

        if current_node.value == search_value:
            return current_node

        # Check all the neighbours
        for child in current_node.children:

            # If a node hasn't been visited before, and not available in the stack already.
            if (child not in visited) and (child not in stack):         
                stack.append(child)
                


# <span class="graffiti-highlight graffiti-id_flg9zjy-id_4sn6eaw"><i></i><button>Show Solution</button></span>

# ### Tests

# In[6]:


assert nodeA == dfs_search(nodeS, 'A')
assert nodeS == dfs_search(nodeP, 'S')
assert nodeR == dfs_search(nodeH, 'R')


# In[ ]:




