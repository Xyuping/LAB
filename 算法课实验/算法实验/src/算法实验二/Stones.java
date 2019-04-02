package 算法实验二;

public class Stones {
	int[] num;
	int N;
	public Stones(int[] num, int n) {
		this.num = num;
		N = n;
	}
	private int sum(int begin,int n)
	{
	    int total=0;
	    for (int i=begin;i<=begin+n-1;i++)
	    {   if(i==N)
	        total=total+num[N];//取代num[0]
	    else
	        total=total+num[i%N];
	    }
	    return total;
	}
	public int stone_merge_max(int N)
	{
	    int[][] score=new int[100][100];
	    int n,i,k,temp;
	    for (i=1;i<=N;i++)
	        score[i][1]=0;
	    
	    for (n=2;n<=N;n++)
	    {
	        for (i=1;i<=N;i++)
	        {
	            score[i][n]=score[i][1]+score[(i+1-1)%N+1][n-1];
	            for (k=2;k<=n-1;k++)
	            {
	                temp=score[i][k]+score[(i+k-1)%N+1][n-k];
	                if(temp >score[i][n])
	                    score[i][n] = temp;
	            }
	            score[i][n]+=sum(i,n);
	        }
	    }
	    return Comp.Max(score, N);
	}
	
	public int stone_merge_min(int N)
	{
	    int[][] score=new int[100][100];//score[i][j]:从第i堆石子开始的j堆石子合并后最小得分
	    int n,i,k,temp;
	    for (i=1;i<=N;i++)
	        score[i][1]=0;//一堆石子，合并得分为0
	    
	    for (n=2;n<=N;n++)//合并的石子的堆数
	    {
	        for (i=1;i<=N;i++)//合并起始位置
	        {
	            score[i][n]=score[i][1]+score[(i+1-1)%N+1][n-1];
	            for (k=2;k<=n-1;k++)//截断位置
	            {
	                temp=score[i][k]+score[(i+k-1)%N+1][n-k];
	                if(temp <score[i][n])
	                    score[i][n] = temp;//从第i开始的k堆是：第i+0堆到第(i+k-1)%N堆
	            }
	            score[i][n]+=sum(i,n);
	        }
	    }
	    return Comp.Min(score, N);
	}
}
