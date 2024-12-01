## Reasoning Behind Decisions:
as Already resolved we use the Stack structure to list all subgroups in one pool and loop over it 
and in the process we may encouter the user inside one of the groups we are investing as well

## Time Efficiency:
O(n+m) where n is the number of subgroups where the worst case is the user is listed inside the last subgroup so the stack shall go through all the subgroups
and m is the number of users per subgroup
## Space Efficiency:
O(s) where s is the stack depth of the groups