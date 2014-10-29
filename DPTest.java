package test2;

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
		assert longestSubStrNoRepeat(null)==0;
	}

}
