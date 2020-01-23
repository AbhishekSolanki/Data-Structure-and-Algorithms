package com.dataqlo.DAA;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StringsAndArrays {
    /*
     * How do you reverse a given string in place?
     * https://hackernoon.com/20-string-coding-interview-questions-for-programmers-6b6735b6d31c
     * Approach: read from left and swap from right
     * */
    private String StringInPlace(String str) {
        if(str==null || str.isEmpty()){
            return str;
        }
        int n = str.length();
        char[] strArr = str.toCharArray();
      /*  for(int i =0, j=n-1;i<j;i++,j--){
            swap(strArr,i,j);
        }*/
        for(int i=0;i<n/2;i++){
            SwapStr(strArr,i,n-1-i%n);
        }
        return new String(strArr);
    }
    public static void SwapStr(char[] str,int i, int j){
        char temp = str[i];
        str[i]=str[j];
        str[j]=temp;
    }

    /*
     * How do you print duplicate characters from a string?
     * https://hackernoon.com/20-string-coding-interview-questions-for-programmers-6b6735b6d31c
     * Approach: hashmap to maintain count
     */
    private void PrintDuplicateCharInString(String str){
        HashMap<Character,Integer> dp = new HashMap<>();
        char[] strArr = str.toCharArray();
        for (char i:strArr) {
            if(dp.containsKey(i)!=true){
                dp.put(i,1);
            }else{
                dp.put(i,(dp.get(i))+1);
            }
        }
        Iterator dpIter = dp.entrySet().iterator();
        while (dpIter.hasNext()){
            Map.Entry mapElem = (Map.Entry) dpIter.next();
            if((int)mapElem.getValue()>1){
                System.out.println(mapElem.getKey()+ " "+mapElem.getValue());
            }
        }
    }

    public static void main(String[] args) {
        StringsAndArrays saa = new StringsAndArrays();
        // System.out.println(asr.StringInPlace("abcde"));
        //saa.PrintDuplicateCharInString("abcccddef");
    }
}
