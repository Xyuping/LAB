package 算法实验三;

import java.io.File;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		File file = new File("Data/lab3/input.txt");
		ArrayList<int[]> arr = new ArrayList<int[]>();
		ArrayList<int[]> arr2 = new ArrayList<int[]>();
		arr=Data.getData(file);
		arr2=Data.getData(file);
		for(int i = 0;i<arr.size();i++) {
			int[] data = arr.get(i);
			int[] data2 = arr2.get(i);
			MinHeap.creat(data, data.length-1);
			data2[data2.length-1]=0;
			MaxHeap.creat(data2, data2.length-1);
			Merge merge = new Merge(data,data[data.length-1]);
			Merge merge2 = new Merge(data2,data2.length-1);
			System.out.println(merge2.merge_max()+" "+merge.merge_min());
		}
	}

}
