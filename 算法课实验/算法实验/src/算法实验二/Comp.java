package 算法实验二;

public class Comp {
	public static int Min(int[][] score,int N) {
		int min=99999999;
	    for (int i=1;i<=N;i++)
	    {    if (min>score[i][N])
	        min=score[i][N];//取从第i堆开始的N堆的最小者
	    }
	    return min;
	}
	public static int Max(int[][] score,int N) {
		int max=-1;
	    for (int i=1;i<=N;i++)
	    {    if (max<score[i][N])
	        max=score[i][N];//取从第i堆开始的N堆的最小者
	    }
	    return max;
	}

}
