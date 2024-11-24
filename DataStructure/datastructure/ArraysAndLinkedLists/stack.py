class Stack:
    
    def __init__(self, initial_size = 10):
        self.arr = [None for _ in range(initial_size)]
        self.next_index = 0
        self.num_elements = 0
    def _handle_stack_capacity_full(self):
        old_arr = self.arr
        self.arr = [None for _ in range(len(self.arr)+1)]
        for index in range (len(old_arr)):
            self.arr[index]=old_arr[index]
    def push (self,value):
        # for index in range(len(self.arr)-1):
        #     if self.arr[index] is None:
        #         self.arr[index] = value
        #         self.num_elements +=1
        #         break
        if self.next_index >= len(self.arr):
            self._handle_stack_capacity_full()
        self.arr[self.next_index]=value
        self.next_index+=1
        self.num_elements+=1
    def size (self):
        return self.num_elements
    def is_empty(self):
        if self.num_elements == 0:
            return True
        else:
            return False
    def pop (self):
        if self.is_empty():
            return None
        data = self.arr[self.num_elements-1]
        self.num_elements-=1
        self.next_index-=1
        return data
foo = Stack()
print(foo.arr)
print(foo.is_empty())
print(foo.size())
foo.push(1)
foo.push(2)
print(foo.arr)
print(foo.pop())
print(foo.arr)
foo.push(3)
foo.push(4)
print(foo.arr)
print(foo.size())
print(foo.is_empty())
