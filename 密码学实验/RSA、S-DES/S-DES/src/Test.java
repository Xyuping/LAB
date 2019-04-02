import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		 int[] p= getP();
		Key key = new Key();
			key.getKey();
			int[] K1;
			int[] K2;
			K1 = key.getK1();
			K2 = key.getK2();
			System.out.println("K1:");
			for (int i = 0; i < K1.length; i++) {
				System.out.print(K1[i]);
			}
			System.out.println();
			System.out.println("K2:");
			for (int i = 0; i < K2.length; i++) {
				System.out.print(K2[i]);
			}
			System.out.println();

			Algorithm fk = new Algorithm(K1, K2);
			int[] C = fk.f(p);
			System.out.println( "加密结果:");
			for (int i = 0; i < C.length; i++) {
				System.out.print(C[i]);
			}
			System.out.println();

			int[] P = fk.d(C);
			System.out.println("解密结果:");
			for (int i = 0; i < P.length; i++) {
				System.out.print(P[i]);
			}
		}

	private static int[] getP() {
		System.out.println("请输入 8bit 二进制明文:");
		Scanner sc = new Scanner(System.in);
		String p = sc.next();
		if (p.length() != 8) {
			System.out.println("输入位数错误,请输入8位二进制数字!");
		}
		int[] P = new int[8];
		for (int i = 0; i < 8; i++) {
			String s = p.substring(i, i + 1);
			if (s.equals("0") || s.equals("1"))
				P[i] = new Integer(s);
			else {
				System.out.println("输入错误!请输入二进制数字");
			}
		}
		return P;
	}
}
