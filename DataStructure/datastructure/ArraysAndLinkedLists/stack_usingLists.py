class Stack:
    def __init__(self):
         self.list = []
    
    def size(self):
         return len(self.list)
    
    def push(self, item):
         self.list.append(item)

    def pop(self):
         if self.size() != 0:          
             return self.list.pop()
         else:
              return None

MyStack = Stack()

MyStack.push("Web Page 1")
MyStack.push("Web Page 2")
MyStack.push("Web Page 3")

print (MyStack.list)

MyStack.pop()
MyStack.pop()

print ("Pass" if (MyStack.list[0] == 'Web Page 1') else "Fail")

MyStack.pop()

print ("Pass" if (MyStack.pop() == None) else "Fail")
