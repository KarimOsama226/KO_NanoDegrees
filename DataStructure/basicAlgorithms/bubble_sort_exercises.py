#!/usr/bin/env python
# coding: utf-8

# # Bubble Sort Exercises
# Now that you know how about bubble sort works, you'll implement bubble sort for two exercises.

# ## Exercise 1

# Sam records when they wake up every morning. Assuming Sam always wakes up in the same hour, use bubble sort to sort by earliest to latest.
# 
# 

# In[ ]:


wakeup_times = [16,49,3,12,56,49,55,22,13,46,19,55,46,13,25,56,9,48,45]
def bubble_sort_1(l):
    # TODO: Implement bubble sort solution
    index = len(l)-1
    while(index):
        for j in range (index-1):
            if l[j] > l[j+1]:
                temp = l[j]
                l[j] = l[j+1]
                l[j+1] = temp
        index -=1
    
bubble_sort_1(wakeup_times)
print ("Pass" if (wakeup_times[0] == 3) else "Fail")


# <span class="graffiti-highlight graffiti-id_y26wn0b-id_uppmlq4"><i></i><button>Show Solution</button></span>

# ## Exercise 2

# Sam doesn't always go to sleep in the same hour. Given the following times Sam has gone to sleep, sort the times from latest to earliest.

# In[ ]:


# Entries are (h, m) where h is the hour and m is the minute
sleep_times = [(24,13), (21,55), (23,20), (22,5), (24,23), (21,58), (24,3)]

def bubble_sort_2(l):
    # TODO: Implement bubble sort solution
    index = len(l)-1
    while(index):
        for j in range (index-1):
            if l[j][0] > l[j+1][0]:
                temp = l[j][0]
                l[j][0] = l[j+1][0]
                l[j+1][0] = temp
        index -=1
    index = len(l)-1
    while(index):
        for j in range (index-1):
            if l[j][1] > l[j+1][1]:
                temp = l[j][1]
                l[j][1] = l[j+1][1]
                l[j+1][1] = temp
        index -=1
bubble_sort_2(sleep_times)
print ("Pass" if (sleep_times == [(24,23), (24,13), (24,3), (23,20), (22,5), (21,58), (21,55)]) else "Fail")


# <span class="graffiti-highlight graffiti-id_f6s1i29-id_hxr8nmt"><i></i><button>Show Solution</button></span>
