import java.util.Scanner;

public class Key {

	private int[] keys;
	private int[] K1;
	private int[] K2;
	private int[] key1 = new int[5];
	private int[] key2 = new int[5];

	public int[] getK1() {
		return K1;
	}

	public int[] getK2() {
		return K2;
	}

	public void getKey() {
		 keys = getK();
		keys = replace_P10(keys);
		for (int i = 0; i < 5; i++) {
			key1[i] = keys[i];
			key2[i] = keys[i + 5];
		}
		LS(key1, 1);
		LS(key2, 1);
		K1 = replace_P8(key1, key2);
		LS(key1, 2);
		LS(key2, 2);
		K2 = replace_P8(key1, key2);
	}

	// 获得10bit 密钥输入
	private int[] getK() {
		System.out.println("请输入10bit二进制密钥:");
		Scanner sc = new Scanner(System.in);
		String key = sc.next();
		if (key.length() != 10) {
			System.out.println("输入位数错误,请输入十位二进制数字!");
		}
		int[] keys = new int[10];
		for (int i = 0; i < 10; i++) {
			String s = key.substring(i, i + 1);
			if (s.equals("0") || s.equals("1"))
				keys[i] = new Integer(s);
			else {
				System.out.println("输入错误!请输入10bit二进制密钥");
			}
		}
		return keys;
	}

	private int[] getK2(int[] k, int n) {
		if (n != 0) {
			if (k[n - 1] == 0)
				k[n - 1] = 1;
			else
				k[n - 1] = 0;
		}

		if (k[n] == 0)
			k[n] = 1;
		else
			k[n] = 0;
		return k;
	}

	// 置换 P10
	private int[] replace_P10(int[] k) {
		int[] temp = new int[10];
		temp[0] = k[2];
		temp[1] = k[4];
		temp[2] = k[1];
		temp[3] = k[6];
		temp[4] = k[3];
		temp[5] = k[9];
		temp[6] = k[0];
		temp[7] = k[8];
		temp[8] = k[7];
		temp[9] = k[5];
		return temp;
	}

//		循环左移动n位
	private void LS(int[] k, int n) {

		int[] temp = new int[5];
		for (int i = 0; i < 5; i++) {
			temp[i] = k[i];
		}
		for (int i = 0; i < n; i++) {
			k[k.length - i - 1] = temp[n - 1 - i];
		}
		for (int i = 0; i < k.length - n; i++) {
			k[i] = temp[i + n];
		}
	}

	// 置换P8
	private int[] replace_P8(int[] k1, int[] k2) {
		int[] temp = new int[10];
		int[] key_8 = new int[8];
		for (int i = 0; i < 5; i++) {
			temp[i] = k1[i];
			temp[i + 5] = k2[i];
		}
		key_8[0] = temp[5];
		key_8[1] = temp[2];
		key_8[2] = temp[6];
		key_8[3] = temp[3];
		key_8[4] = temp[7];
		key_8[5] = temp[4];
		key_8[6] = temp[9];
		key_8[7] = temp[8];

		return key_8;
	}

}
