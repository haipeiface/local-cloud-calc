package demo.leetcode.string;

/**
 * 5.Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * 两种情况，选取中心，？abba？，？abcba？
 * 三个条件，左边没有越界，右边没有越界，左边字符等于右边字符。
 * 则更新
 */
public class LongestPalindromicSubstring {

    int start = 0, maxLength = 1;
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        for (int i=0; i < s.length(); i++){
            start = expandAroundCenter(i,i+1, s);
            start = expandAroundCenter(i-1, i+1, s);
        }
        return s.substring(start, start+maxLength);
    }

    //[a,b,a]
    //helper function
    public int expandAroundCenter(int left, int right, String s) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            if (right - left + 1 > maxLength){
                maxLength = right - left + 1;  //[0,1,2] length = 2-0 +1;
                start = left;
            }
            left--;
            right++;
        }
        return start;
    }

    public static void main(String args[]){
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        String res = l.longestPalindrome("babad");
        System.out.println(res);
    }
}
