"""
@Created: Thu Dec  5 08:48:18 2019
@author: aihbnu01
@problem: https://practice.geeksforgeeks.org/problems/subarray-with-given-sum/0
"""

#code
def array_prb_0(N,S,arr):
    #arr=[1,2,3,4,5,6,7,8,9,10] #unsorted input array
    #N=10 #array size
    #S=15 #sum to find
    currsum=0#variable for counting current sum
    start_pos=0 #variable to maintain start position of sub array
    end_pos=0 #variable to maintain end position of sub array
    for i in range(0,N-1): # iterating through whole array from 0th to N-1
        currsum+=arr[i] # adding the element value to sum
        if(currsum>S): # current sum greater than S remove left most element and increase start counter
            end_pos=i+1
            while(currsum>S):
                start_pos+=1
                currsum = currsum - arr[start_pos]
        if(currsum==S):
            end_pos=i
            break
        if(i==N-1 and currsum!= S):
            start_pos = -1
            end_pos = -1
    #print(start_pos+1)
    #print(end_pos+1)
    return(str(start_pos+1)+" "+str(end_pos+1))

T=int(input())
for i in range(0,T):
    N, S = x, y = [int(x) for x in input().split()] 
    arr = list(map(int, input().split()))
    print(array_prb_0(N,S,arr))