package demo.leetcode.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 3.Given a string, find the length of the longest substring without repeating characters.
 * eg. "abcabcbb"
 */
public class LongestSubstringWithout {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0) return 0;
        int i=0,j=0,maxlength=0;
        Set<Character> set = new HashSet<>();
        for(i = 0; i< s.length(); i++){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                maxlength = Math.max(maxlength,set.size());
            } else {
                while (set.contains(s.charAt(i))) {  //这里需要注意连续set中有字符的情况
                    set.remove(s.charAt(j)); // 删除j
                    j++;
                }
                set.add(s.charAt(i));
            }
        }
        return maxlength;
    }


    public static void main(String args[]){
        LongestSubstringWithout l = new LongestSubstringWithout();
        System.out.println(l.lengthOfLongestSubstring("pwwkew"));
    }
}
