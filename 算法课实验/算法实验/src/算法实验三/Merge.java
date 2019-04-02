package 算法实验三;

public class Merge {
	private int[] data;
	private int N;
	public Merge(int[]data,int N) {
		this.data=data;
		this.N=N;
	}
	public int merge_min() {
		int sum=0;
		for(int i =0;i<N-1;i++) {
			int num1 = data[0];
			MinHeap.delete(data, N-i-1);
			int num2 = data[0];
			MinHeap.delete(data,N-i-2);
			sum+=count(num1,num2);
			int num=num1+num2;
			MinHeap.insert(data, num, N-i-2);
		}
		return sum;
	}
	public int merge_max() {
		int sum=0;
		for(int i =0;i<N-1;i++) {
			int num1 = data[0];
			MaxHeap.delete(data, N-i-1);
			int num2 = data[0];
			MaxHeap.delete(data,N-i-2);
			sum+=count(num1,num2);
			int num=num1+num2;
			MaxHeap.insert(data, num, N-i-2);
		}
		return sum;
	}
	private int count(int n,int m) {
		return m+n-1;
	}

}
