package chp15.DynamicProgramming;

import java.io.*;
import java.util.*;

public class Cut_Rod {
	public void handler() throws IOException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader("D:\\e-workspace\\Algorithm_Introduction\\src\\chp15\\DynamicProgramming\\cut_rod.in")));
		
		//read
		int n = sc.nextInt();
		int p_n = sc.nextInt();
		int[] p = new int[p_n+1];
		for(int i=1; i<=p_n; i++) {
			sc.nextInt();
			p[i] = sc.nextInt();
		}
		
		//handle
		//recursive updown
		int max_res = updown_cut_rod(p, n);
		System.out.println("updown_cut_rod: "+max_res);
		
		//memorized recursive updown
		int[] s = new int[n+1];
		max_res = memoried_updown_cut_rod(p, s, n);		
		System.out.println("memoried_updown_cut_rod: "+max_res);
		
		downup_cut_rod(p,n);
		
		reconstruct_downup_cut_rod(p, n);
				
		sc.close();
	}
	
	//Case 1
	public int updown_cut_rod(int[] p, int n) {
		if(n==0) return 0;
		int q = Integer.MIN_VALUE;
		for(int i=1; i<=p.length-1; i++) {
			if(n-i>=0) {
				q = Math.max(q, p[i]+updown_cut_rod(p, n-i));
			}			
		}		
		return q;
	}
	
	//Case 2
	public int memoried_updown_cut_rod(int[] p, int[] s, int n) {
		if(n==0) return 0;
		
		if(s[n]>0) return s[n];
		
		int q = Integer.MIN_VALUE;
		for(int i=1; i<=p.length-1; i++) {
			if(n-i>=0) {
				q = Math.max(q, p[i]+memoried_updown_cut_rod(p, s, n-i));
			}
		}
		s[n] = q;
		return q;
	}
	
	//Case 3
	public void downup_cut_rod(int[] p, int n) {
		int[][] dp = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				dp[i][j] = Integer.MIN_VALUE;
				for(int k=1; k<=p.length-1; k++) {
					if(i-k>=0) {
						dp[i][j] = Math.max(dp[i][j], p[k]+dp[i-k][j]);
					}					
				}
			}
		}
		
		System.out.println("downup_cut_rod: "+dp[n][n]);
		
	}
	
	//Case 4
	public void reconstruct_downup_cut_rod(int[] p, int n) {
		int[] dp = new int[n+1];
		int[] cut = new int[n+1];
		
		//handle
		for (int i = 1; i <= n; i++) {
			dp[i] = Integer.MIN_VALUE;
			for (int k = 1; k <= p.length - 1; k++) {
				if (i - k >= 0) {
					int tmp = p[k] + dp[i - k];
					if(tmp>dp[i]) {
						dp[i] = tmp;		
						cut[i] = k;
					}					
				}
			}
		}
		System.out.println("reconstruct_downup_cut_rod: "+dp[n]);
		
		
		//reconstruct
		int t_n = 0;
		for(int i=n; i>0;) {
			int k = cut[i];
			t_n = t_n + k;
			i = n-t_n;
			System.out.print(" "+k);
		}
	}
	
	public static void main(String[] args) {
		Cut_Rod m = new Cut_Rod();
		try {
			m.handler();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
