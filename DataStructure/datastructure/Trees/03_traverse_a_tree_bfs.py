#!/usr/bin/env python
# coding: utf-8

# ## Traverse a tree (breadth first search)
# 
# We'll now practice implementing breadth first search (BFS).  You'll see breadth first search again when we learn about graph data structures, so BFS is very useful to know.

# ## Creating a sample tree
# 
# We'll create a tree that looks like the following:

# ![tree image](images/tree_01.png "Tree")

# In[ ]:


# this code makes the tree that we'll traverse

class Node(object):
        
    def __init__(self,value = None):
        self.value = value
        self.left = None
        self.right = None
    
    # set value of the node
    def set_value(self,value):
        self.value = value
    
    # get value of the node
    def get_value(self):
        return self.value
    
    # set a node for the left child
    def set_left_child(self,left):
        self.left = left
    
    # set a node for the right child
    def set_right_child(self, right):
        self.right = right
        
    # get the node of left child
    def get_left_child(self):
        return self.left
    
    # get the node of right child
    def get_right_child(self):
        return self.right

    # check if node has left child -> return boolean
    def has_left_child(self):
        return self.left != None
    
    # check if node has right child -> return boolean
    def has_right_child(self):
        return self.right != None
    
    # define __repr_ to decide what a print statement displays for a Node object
    def __repr__(self):
        return f"Node({self.get_value()})"
    
    def __str__(self):
        return f"Node({self.get_value()})"
    
    
class Tree():
    def __init__(self, value=None):
        self.root = Node(value)
        
    def get_root(self):
        return self.root

tree = Tree("apple")
tree.get_root().set_left_child(Node("banana"))
tree.get_root().set_right_child(Node("cherry"))
tree.get_root().get_left_child().set_left_child(Node("dates"))


# ## Breadth first search
# Breadth first traversal of the tree would visit the nodes in this order:  
# 
# ![tree image](images/tree_01.png "Tree")
# 
# apple, banana, cherry, dates

# #### Think through the algorithm
# 
# We are walking down the tree one level at a time. So we start with apple at the root, and next are banana and cherry, and next is dates.
# 
# 
# 1) start at the root node  
# 2) visit the root node's left child (banana), then right child (cherry)  
# 3) visit the left and right children of (banana) and (cherry).
# 
# ## Queue
# 
# Notice that we're waiting until we visit "cherry" before visiting "dates".  It's like they're waiting in line.  We can use a queue to keep track of the order. 
# 
# Python's `collections` module has a specialized `deque` datatype (ref: [deque datatype documentation](https://docs.python.org/3/library/collections.html#collections.deque)). We can append a new element to the right of the list with `append` method and to the left of the list with `appendleft`. To remove and return the right element of the list, we can use `pop` method.

# ## Define a queue class

# In[ ]:


# import deque datatype from collections module
from collections import deque
# instantiate a deque object
q = deque()
# add 2 elements to the left of the deque
q.appendleft("apple")
q.appendleft("banana")
print(q)


# In[ ]:


# remove and return the most right element of the list
q.pop()


# In[ ]:


print(q)


# In[ ]:


len(q)


# In[ ]:


from collections import deque
class Queue():
    def __init__(self):
        self.q = deque()
        
    def enq(self,value):
        self.q.appendleft(value)
        
    def deq(self):
        if len(self.q) > 0:
            return self.q.pop()
        else:
            return None
    
    def __len__(self):
        return len(self.q)
    
    def __repr__(self):
        if len(self.q) > 0:
            s = "<enqueue here>\n_________________\n" 
            s += "\n_________________\n".join([str(item) for item in self.q])
            s += "\n_________________\n<dequeue here>"
            return s
        else:
            return "<queue is empty>"


# In[ ]:


q = Queue()
q.enq("apple")
q.enq("banana")
q.enq("cherry")
print(q)


# In[ ]:


print(q.deq())


# In[ ]:


print(q)


# ## Walk through the steps with code
# 
# We're going to translate what we're doing by hand into code, one step at a time.  This will help us check if our code is doing what we expect it to do.
# 
# ![tree image](images/tree_01.png "Tree")

# In[ ]:


visit_order = list()
q = Queue()

# start at the root node and add it to the queue
node = tree.get_root()
q.enq(node)
print(q)


# In[ ]:


# dequeue the next node in the queue. 
# "visit" that node
# also add its children to the queue

node = q.deq()
visit_order.append(node)
if node.has_left_child():
    q.enq(node.get_left_child())
if node.has_right_child():
    q.enq(node.get_right_child())
    
print(f"visit order: {visit_order}")
print(q)


# In[ ]:


# dequeue the next node (banana)
# visit it, and add its children (dates) to the queue 

node = q.deq()
visit_order.append(node)
if node.has_left_child():
    q.enq(node.get_left_child())
if node.has_right_child():
    q.enq(node.get_right_child())
    
print(f"visit order: {visit_order}")
print(q)


# In[ ]:


# dequeue the next node (cherry)
# visit it, and add its children (there are None) to the queue 

node = q.deq()
visit_order.append(node)
if node.has_left_child():
    q.enq(node.get_left_child())
if node.has_right_child():
    q.enq(node.get_right_child())
    
print(f"visit order: {visit_order}")
print(q)


# In[ ]:


# dequeue the next node (dates)
# visit it, and add its children (there are None) to the queue 

node = q.deq()
visit_order.append(node)
if node.has_left_child():
    q.enq(node.get_left_child())
if node.has_right_child():
    q.enq(node.get_right_child())
    
print(f"visit order: {visit_order}")
print(q)


# ## Task: write the breadth first search algorithm

# In[ ]:


# BFS algorithm
def bfs(tree):
    visit_order=[]
    root = tree.get_root()
    q= Queue()
    q.enq(root)
    def move_bfs():
        node= q.deq()
        if node:
            visit_order.append(node)
            if node.get_left_child():
                q.enq(node.get_left_child())
            if node.get_right_child():
                q.enq(node.get_right_child())
            move_bfs()
    move_bfs()
    print(f"The Order of BFS is {visit_order}")
    return visit_order

# In[ ]:


# check solution: should be: apple, banana, cherry, dates
bfs(tree)


# ## Bonus Task: write a print function
# 
# Define the print function for the Tree class.  Nodes on the same level are printed on the same line. 
# 
# For example, the tree we've been using would print out like this:
# ```
# Node(apple)
# Node(banana) | Node(cherry)
# Node(dates) | <empty> | <empty> | <empty>
# <empty> | <empty>
# ```
# We'll have `<empty>` be placeholders so that we can keep track of which node is a child or parent of the other nodes.
# 
# **hint**: use a variable to keep track of which level each node is on.  For instance, the root node is on level 0, and its child nodes are on level 1.

# In[ ]:


# starter code

class Tree():
    def __init__(self, value=None):
        self.root = Node(value)
        
    def get_root(self):
        return self.root
    
    """
    define the print function
    """
    def __repr__(self):
        if not self.root:
            return "<empty tree>"

        # Level-order traversal
        q = Queue()
        visit_order = list()
        node = self.get_root()
        q.enq((node, 0))
        max_level = 0

        while len(q) > 0:
            node, level = q.deq()
            max_level = max(max_level, level)

            if node is None:
                visit_order.append(("<empty>", level))
                continue

            visit_order.append((node.value, level))

            if node.has_left_child():
                q.enq((node.get_left_child(), level + 1))
            else:
                q.enq((None, level + 1))

            if node.has_right_child():
                q.enq((node.get_right_child(), level + 1))
            else:
                q.enq((None, level + 1))

        # Centering nodes for better visualization
        result = ""
        current_level = 0
        max_width = 2 ** (max_level + 1) - 1  # Maximum width of the tree
        nodes_per_level = []
        row = []

        for value, level in visit_order:
            if level != current_level:
                nodes_per_level.append(row)
                row = []
                current_level = level
            row.append(value)

        nodes_per_level.append(row)  # Append the last level

        # Create the tree visualization
        for i, level_nodes in enumerate(nodes_per_level):
            space_between = max_width // (2 ** (i + 1))
            level_str = " " * space_between

            for node in level_nodes:
                if node == "<empty>":
                    level_str += " " * space_between + " "
                else:
                    level_str += str(node).center(space_between * 2) + " "

            result += level_str.rstrip() + "\n"

        return result





# In[ ]:


# check solution
tree = Tree("apple")
tree.get_root().set_left_child(Node("banana"))
tree.get_root().set_right_child(Node("cherry"))
tree.get_root().get_left_child().set_left_child(Node("dates"))
print(tree)


# ## Solution notebook
# The solution notebook is [here](03_traverse_a_tree_bfs_solution.ipynb)

# In[ ]:




