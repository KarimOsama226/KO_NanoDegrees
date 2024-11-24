#queue_in_python

class Queue:
    def __init__(self):
        self.newlist = None
    
    def size(self):
        return len(self.newlist)
    
    def enqueue(self, item):
        if self.newlist is None:
            self.newlist=list(str(item))
        else:
            self.newlist.append(str(item))
        print(self.newlist)

    def dequeue(self):
        print(self.newlist)
        data = self.newlist.pop(0)
        print(data)
        return data
        


q = Queue()
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)
# Test size
print("Pass" if (q.size() == 3) else "Fail1")

# Test dequeue
print("Pass" if (q.dequeue() == '1') else "Fail2")

# Test enqueue
q.enqueue(4)
print("Pass" if (q.dequeue() == '2') else "Fail3")
print("Pass" if (q.dequeue() == '3') else "Fail4")
print("Pass" if (q.dequeue() == '4') else "Fail5")
q.enqueue(5)
print("Pass" if (q.size() == 1) else "Fail6 ")

'''
# Solution 

class Queue:
    def __init__(self):
         self.storage = []
    
    def size(self):
         return len(self.storage)
    
    def enqueue(self, item):
         self.storage.append(item)

    def dequeue(self):
         return self.storage.pop(0)

        
# Setup
q = Queue()
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)

# Test size
print ("Pass" if (q.size() == 3) else "Fail")

# Test dequeue
print ("Pass" if (q.dequeue() == 1) else "Fail")

# Test enqueue
q.enqueue(4)
print ("Pass" if (q.dequeue() == 2) else "Fail")
print ("Pass" if (q.dequeue() == 3) else "Fail")
print ("Pass" if (q.dequeue() == 4) else "Fail")
q.enqueue(5)
print ("Pass" if (q.size() == 1) else "Fail")
'''