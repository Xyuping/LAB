package 算法实验一;

import java.io.File;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		File file = new File("Data/lab1/input.txt");
		ArrayList<Integer> arr = Data.getData(file);
		Q_sort.quickSort(arr);
		int distanceSum = Distance.getDistanceSum(arr, arr.get(arr.size()/2));
		System.out.println(distanceSum);
	}
	

}
