#Queue_Array
class Queue:
    def __init__(self):
        self.arr = [0 for _ in range(10)]
        self.next_index = 0
        self.front_index= -1 #empty
        self.queue_size=0
    def enqueue (self,value):
        if self.next_index >= len(self.arr):
            self._handle_queue_capacity_full()
        self.arr[self.next_index] = value
        self.queue_size+=1
        #self.next_index+=1
        self.next_index = (self.next_index + 1) % len(self.arr) #assure be within range
        if self.front_index == -1:
            self.front_index =0 
    def size (self):
        return self.queue_size
    def is_empty(self):
        return self.queue_size == 0
    def front (self):
        if self.front_index == -1:
            return None #empty
        return self.arr[self.front_index]
    def dequeue(self):
        data = self.arr[self.front_index]
        self.queue_size-=1
        self.front_index +=1
        #print(data)
        #print(self.arr)
        return data
    def _handle_queue_capacity_full(self):
        old_arr=[]
        for index in range(len(self.arr)-1):
            old_arr[index] = self.arr[index]
        self.arr=[None for _ in range (len(old_arr)+1)]
        for index in range(len(old_arr)-1):
            self.arr[index] = old_arr[index] 
q = Queue()
print(q.arr)
print("Pass" if q.arr == [0, 0, 0, 0, 0, 0, 0, 0, 0, 0] else "Fail1")
q = Queue()
q.enqueue(1)
q.enqueue(2)
q.enqueue(3)

# Test size
print ("Pass" if (q.size() == 3) else "Fail2")

# Test dequeue
print ("Pass" if (q.dequeue() == 1) else "Fail3")

# Test enqueue
q.enqueue(4)
print ("Pass" if (q.dequeue() == 2) else "Fail4")
print ("Pass" if (q.dequeue() == 3) else "Fail5")
print ("Pass" if (q.dequeue() == 4) else "Fail6")
q.enqueue(5)
print ("Pass" if (q.size() == 1) else "Fail7")