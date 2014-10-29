import java.util.*;

public class DPTest {
	public static int distinctSubsequence(String s, String p) {
		int[][] res = new int[s.length()+1][p.length()+1];
		for (int i=0; i<=s.length(); i++) {
			res[i][0] = 1;
		}
		for (int i=1; i<=p.length(); i++) {
			res[0][i] = 0;
		}
		for (int i=1; i<=s.length(); i++) {
			for (int j=1; j<=p.length(); j++) {
				if (s.charAt(i-1)==p.charAt(j-1)) {
					res[i][j] = res[i-1][j-1] + res[i-1][j];
				} else {
					res[i][j] = res[i-1][j];
				}
			}
		}
		return res[s.length()][p.length()];
	}
	
	public static int LCS(String s1, String s2) {
		int[][] l = new int[s1.length()+1][s2.length()+1];
		for (int i=0; i<=s1.length(); i++)
			l[i][0] = 0;
		for (int i=0; i<=s2.length(); i++)
			l[0][i] = 0;
		for (int i=1; i<=s1.length(); i++) {
			for (int j=1; j<=s2.length(); j++) {
				if (s1.charAt(i-1)==s2.charAt(j-1)) {
					l[i][j] = l[i-1][j-1] + 1;
				} else {
					l[i][j] = Math.max(l[i][j-1], l[i-1][j]);
				}
			}
		}
		return l[s1.length()][s2.length()];
	}
	
	public static int longestSubStrNoRepeat(String s) {
		if (s==null)
			return 0;
		int[] visit = new int[256];
		for (int i=0; i<256; i++)
			visit[i] = -1;
		int maxLen = 0;
		int len = 0;
		for (int i=0; i<s.length(); i++) {
			int c = s.charAt(i);
			if (visit[c]==-1) {
				len++;
			} else {
				if (i-len > visit[c]) {
					//outside current window
					len++;
				} else {
					//need to update current window
					maxLen = len>maxLen?len:maxLen;
					len = i-visit[c];
				}
			}
			visit[c] = i;
		}
		maxLen = len>maxLen?len:maxLen;
		return maxLen;
	}

        public static int maxSumSubArray(int[] a) {
            if (a.length==0)
                return 0;
            int[] sum = new int[a.length];
            int max = a[0];
            sum[0] = a[0];
            for (int i=1; i<a.length; i++) {
                if (sum[i-1]<0) {
                  sum[i] = a[i];
                } else {
                  sum[i] = sum[i-1] + a[i];
                }
                max = max > sum[i] ? max:sum[i];
            }
            return max;
        }

        public static int minimalCoins(int[] c, int sum) {
            if (sum<=0)
                return 0;
            if (c.length==0)
                return Integer.MAX_VALUE;
            int minCoin = Integer.MAX_VALUE;
            for (int i=0; i<c.length; i++) {
              minCoin = minCoin<c[i] ? minCoin:c[i];
            }
            if (sum<minCoin)
                return Integer.MAX_VALUE;
            int[] m = new int[sum+1];
            for (int i=0; i<minCoin; i++) {
                m[i]  = 0;
            }
            for (int i=minCoin; i<=sum; i++ ) {
              int min = Integer.MAX_VALUE;
              for (int coin : c) {
                  if (i-coin >= 0) {
                      min = Math.min(min, m[i-coin]+1);
                  }
              }
              m[i] = min;
            }
            return m[sum];
        }

        public static boolean interleavingString(String s1, String s2, String s3) {
            if (s3.length() != s1.length()+s2.length())
                return false;
            boolean[][] res = new boolean[s1.length()+1][s2.length()+1];
            for (int i=0; i<=s1.length(); i++)
              res[i][0] = s1.substring(0, i).equals(s3.substring(0, i));
            for (int i=0; i<=s2.length(); i++)
              res[0][i] = s2.substring(0, i).equals(s3.substring(0, i));
            for (int i=1; i<=s1.length(); i++) {
              for (int j=1; j<=s2.length(); j++) {
                res[i][j] = (s1.charAt(i-1)==s3.charAt(i+j-1) && res[i-1][j]) ||
                            (s2.charAt(j-1)==s3.charAt(i+j-1) && res[i][j-1]);
              }
            }
            return res[s1.length()][s2.length()];
        }

	public static void main(String[] args) {
		System.out.println("Distinct Subsequence:");
		System.out.println(distinctSubsequence("raat", "rat"));
		assert distinctSubsequence("bbb", "b")==3:"bbb & b should be 3";
		System.out.println("LCS:");
		assert LCS("abcde", "xace")==3:"\"abcde\", \"xace\" should be 3";
		assert LCS("abcd", "xyz")==0;
		assert LCS("abcde", "a")==1;
		System.out.println("longestSubStrNoRepeat:");
		assert longestSubStrNoRepeat("aaa")==1;
		assert longestSubStrNoRepeat("abcabcd")==4;
		assert longestSubStrNoRepeat("abcbce")==3;
		assert longestSubStrNoRepeat(null)==0:"null should return 0";
                System.out.println("maxSumSubArray:");
                int[] a = {4, -1, 1, 2};
                int[] b = {-1, -1, -2};
                assert maxSumSubArray(a)==6 :"maxSumSubArray(a)";
                assert maxSumSubArray(b)==-1 :"maxSumSubArray(b)";
                System.out.println("minimalCoins:");
                int[] coin = {1, 2, 5, 10};
                assert minimalCoins(coin, 15)==2;
                assert minimalCoins(coin, 5)==1;
                assert minimalCoins(coin, 3)==2;
                assert minimalCoins(coin, 99)==12;
                System.out.println("interleavingString:");
                assert interleavingString("abc", "def", "adebfc")==true;;
                assert interleavingString("abc", "def", "debafc")==false;;
                assert interleavingString("a", "def", "defa")==true;;
	}

}

