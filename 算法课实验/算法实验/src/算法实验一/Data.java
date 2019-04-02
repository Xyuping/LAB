package 算法实验一;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Data {
	public static ArrayList<Integer> getData(File file) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		try {
			BufferedReader br = new  BufferedReader (new FileReader(file));
			String s  = br.readLine();
			Integer count = new Integer(s);
			for(int i = 0;i<count;i++) {
				s=br.readLine();
				arr.add(new Integer(s.split(" ")[1]));
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return arr;
	}
}
