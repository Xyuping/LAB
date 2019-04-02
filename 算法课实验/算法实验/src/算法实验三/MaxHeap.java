package 算法实验三;

public class MaxHeap {
	public static void fixDown(int[] data, int i, int n) {
		int num = data[i];
		int son = i * 2 + 1;
		while (son <= n) {
			if (son + 1 <= n && data[son + 1] > data[son])
				son++;
			if (num > data[son])
				break;
			data[i] = data[son];
			i = son;
			son = i * 2 + 1;
		}
		data[i] = num;
	}

	public static void fixUp(int[] data, int n) {
		int num = data[n];
		int father = (n - 1) / 2;
		while (data[father] < num && n != 0) {
			data[n] = data[father];
			n = father;
			father = (n - 1) / 2;
		}
		data[n] = num;
	}

	public static void delete(int[] data, int n) {
		data[0] = data[n];
		data[n] = -1;
		fixDown(data, 0, n - 1);
	}

	public static void insert(int[] data, int num, int n) {
		data[n] = num;
		fixUp(data, n);
	}

	public static void creat(int[] data, int n) {
		for (int i = (n - 1) / 2; i >= 0; i--)
			fixDown(data, i, n);
	}

}
