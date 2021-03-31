package demo.leetcode.dp;

public class LongestIncreasingSubsequence {
    public static int longestIncreasingSubsequence(int[] nums) {
        // dp[i]表示以nums[i]为结尾的最长上升子序列的长度
        int []dp = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 初始值为1
            dp[i] = 1;
            for (int j = 0; j < i; j = i - 1) {
                if (nums[j] < nums[i]) {
                    // 若nums[j] < nums[i]那么可以接在该序列后，更新状态
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // 记录所有状态中的最大值
            if (dp[i] > ans) {
                ans = dp[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,2,4,5,3,7};
        int res = longestIncreasingSubsequence(nums);
        System.out.println(res);
    }
}
