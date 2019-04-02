package 算法实验三;

import java.io.IOException;

public  class MaxHeap1{
	public static void buildMaxHeap(int[] data , int lastIndex)  
    {  
        for (int i = (data.length - 1) / 2; i >= 0; i--)  
        {  
            int k = i;  
            while (2 * k + 1 <= lastIndex)  
            {  
                //biggerIndex是当前节点的左子树下标  
                int biggerIndex = 2 * k + 1;  
                //寻找最大的子树的其下标  
                if (biggerIndex < lastIndex)  
                {  
                    if (data[biggerIndex] < data[biggerIndex + 1])  
                    {  
                        biggerIndex ++;  
                    }  
                }  
                //将最大的孩子与自己交换位置  
                if (data[k] < data[biggerIndex])  
                {  
                    swap(data , k , biggerIndex);  
                    //由于交换，此时可能影响到下面的子节点（子节点大于根节点），所以还需要往下循环  
                    k = biggerIndex;  
                } else {  
                    //由于没有进行交换，不会影响到下面的孩子，此次循环可以结束了，  
                    //由于k的大小没有变化，只有依靠break才能结束循环  
                    break;  
                }  
            }  
        }  
    }  
  
    public static void swap(int[] data , int index0 , int index1)  
    {  
        data[index0] = data[index0] ^ data[index1];  
        data[index1] = data[index0] ^ data[index1];  
        data[index0] = data[index0] ^ data[index1];  
    }  
}

