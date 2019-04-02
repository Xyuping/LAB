package 算法实验一;

import java.util.ArrayList;

public class Q_sort {
	
	public static void quickSort(ArrayList<Integer> arr) {  
        if(arr.size()>0) {  
            quickSort(arr, 0 , arr.size()-1);  
        }  
    }  
    private static void quickSort(ArrayList<Integer> arr, int low, int high) {  
        if( low > high) {  
            return;  
        }  
        int i = low;  
        int j = high;  
        Integer key = arr.get(low);  
        //一趟排序  
        while( i< j) {  
            //从右往左找到第一个小于key的数  
            while(i<j && arr.get(j).compareTo(key)==1 ){  
                j--;  
            }  
            // 从左往右找到第一个大于key的数  <= key
            while( i<j &&( arr.get(i).compareTo(key)==0||arr.get(i).compareTo(key)==-1)) {  
                i++;  
            }  
            //交换  
            if(i<j) {  
               Integer p = arr.get(i);  
                arr.set(i, arr.get(j)) ;  
                arr.set(j,p);  
            }  
        }  
        // 调整key的位置  
        Integer p = arr.get(i);  
        arr.set(i,arr.get(low)); 
        arr.set(low,p);  
        // 对key左边进行快速排序  
        quickSort(arr, low, i-1 );  
        //对key右边进行快速排序  
        quickSort(arr, i+1, high);  
    }  
}
