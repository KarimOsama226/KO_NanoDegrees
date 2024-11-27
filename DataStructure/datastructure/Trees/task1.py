class Node:
    def __init__(self,value=None):
        self.leftChild = None
        self.rightChild = None
        self.value = value
    def getValue(self):
        return self.value
    def setValue(self,value):
        self.value=value
    def setLeftChild(self,node):
        self.leftChild=node
    def setRightChild(self,node):
        self.rightChild=node
    def getLeftChild(self):
        return self.leftChild
    def getRightChild(self):
        return self.rightChild
    def has_left_child(self):
        return not (self.leftChild == None)
    def has_right_child (self):
        return not (self.rightChild == None) 
    

class Tree:
    def __init__(self,value=None):
        self.root = Node(value)
    def getRoot(self):
        return self.root
    