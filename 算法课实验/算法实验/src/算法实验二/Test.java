package 算法实验二;

import java.io.File;
import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		File file = new File("Data/lab2/input.txt");
		ArrayList<int[]> stones = new ArrayList<int[]>();
		stones = Data.getData(file);
		for(int[] num :stones) {
			if(num[0]!=0) {
				int N =  num[0];//石子堆数
				Stones sto = new Stones(num,N);
				System.out.println(sto.stone_merge_min(N)+" "+sto.stone_merge_max(N));
			}
		}
		
	}

}
