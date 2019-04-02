package 算法实验三;

public class Max {
	public void MaxAdjustDown(int arr[], int i, int n)
	{
	    int j = i * 2 + 1;//子节点 
	    while (j<n)
	    {
	        if (j+1<n && arr[j] > arr[j + 1])//子节点中找较小的
	        {
	            j++;
	        }
	        if (arr[i] < arr[j])
	        {
	            break;
	        }
	        swap(arr[i],arr[j]);
	        i = j;
	        j = i * 2 + 1;
	    }
	}
	public void MaxMakeHeap(int arr[], int n)//建堆
	{
	    int i = 0;
	    for (i = n / 2 - 1; i >= 0; i--)//((n-1)*2)+1 =n/2-1
	    {
	        MaxAdjustDown(arr, i, n);
	    }
	}
	public void MaxHeapSort(int arr[],int len)
	{
	    int i = 0;
	    MaxMakeHeap(arr, len);
	    for (i = len - 1; i >= 0; i--)
	    {
	        swap(arr[i], arr[0]);
	        MaxAdjustDown(arr, 0, i);
	    }

	}
	public void swap(int i,int j) {
		int temp = i;
		i=j;
		j=temp;
	}
}
