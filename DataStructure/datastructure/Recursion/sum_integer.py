#sum_integer
def sum_integers(n):
    if n == 0:
        return 0
    else:
        return n+ sum_integers(n-1)

print (sum_integers(5))