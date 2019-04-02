package 算法实验一;

import java.util.ArrayList;

public class Distance {
	public static int getDistanceSum(ArrayList<Integer> arr,int position){
		int sum = 0;
		for(Integer i : arr) {
			sum+=Math.abs(i-position);
		}
		return sum;
	}
}
