import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		RSA rsa = new RSA();
		ArrayList<BigInteger> M = getMessage();
		ArrayList<BigInteger> C = rsa.E(M, rsa.getKu());
		ArrayList<BigInteger> P = rsa.D(C);
		System.out.print("加密结果为:");
		System.out.println(C);
		System.out.print("解密结果为:");
		System.out.println(P);

	}
	private static ArrayList<BigInteger> getMessage() {
		System.out.println("请输入要加密的消息:");
		Scanner sc = new Scanner(System.in);
		String[] input = sc.next().split(" ");
		ArrayList<BigInteger> M = new ArrayList<BigInteger>();
		for(int i=0;i<input.length;i++) {
			M.add(new BigInteger(input[i]));
		}
		return M;
	}
}
