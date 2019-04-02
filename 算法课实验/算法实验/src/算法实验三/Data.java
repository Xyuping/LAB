package 算法实验三;


	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.util.ArrayList;

	public class Data {
		public static ArrayList<int[]> getData(File file) {
			ArrayList<int[]> arr = new ArrayList<int[]>();
			try {
				BufferedReader br = new  BufferedReader (new FileReader(file));
				String s;
				while((s  = br.readLine())!="0"&&s!=null){
					Integer count = new Integer(s);
					int[] num = new int[count+1];
					num[count]=count;
					s=br.readLine();
					for(int i = 0;i<count;i++) {
						num[i]=new Integer(s.split(" ")[i]);
					}
					arr.add(num);
				}
				br.close();
			}catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			return arr;
		}

	}


