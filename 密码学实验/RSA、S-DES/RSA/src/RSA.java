import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;
public class RSA {
	private ArrayList<BigInteger> Kr = new ArrayList<BigInteger>();
	public ArrayList<BigInteger> Ku = new ArrayList<BigInteger>();
	public RSA() {
		getKeys();
	}
	private void getKeys() {
		BigInteger p = getInput();
		BigInteger q = getInput();
		BigInteger N = p.multiply(q);
		BigInteger r = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
		BigInteger e = get_e(r);
		BigInteger d = get_d(r,e);// d× e ≡ 1 (mod (p-1)(q-1))。
		Ku.add(e);
		Ku.add(N);
		Kr.add(d);
		Kr.add(N);
		System.out.println("Ku="+Ku+" ; Kr="+Kr);
	}
	public ArrayList<BigInteger> getKu() {
		return Ku;
	}
	
	public ArrayList<BigInteger> E( ArrayList<BigInteger> M, ArrayList<BigInteger> Ku) {
		 ArrayList<BigInteger> C = new  ArrayList<BigInteger>();
		for(int i=0;i<M.size();i++) {
			C.add(M.get(i).modPow(Ku.get(0), Ku.get(1)));
		}
		return C;
	}
	
	public  ArrayList<BigInteger> D( ArrayList<BigInteger> C) {
		 ArrayList<BigInteger> M = new  ArrayList<BigInteger>();
		for(int i=0;i<C.size();i++) {
			M.add(C.get(i).modPow(Kr.get(0), Kr.get(1)));
		}
		return M;
	}
	private BigInteger get_d(BigInteger r,BigInteger e) {
		for(BigInteger k=new BigInteger("0");;) {
			if(((k.multiply(r).add(new BigInteger("1")).mod(e)).equals(new BigInteger("0")))) {
				return (k.multiply(r).add(new BigInteger("1"))).divide(e);
			}
			k=k.add(new BigInteger("1"));
		}
	}
	private BigInteger getInput() {
		System.out.println("请输入一个质数:");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		BigInteger p = new BigInteger(s);
		return p;
	}
	private BigInteger get_e(BigInteger r) {
		BigInteger e=new BigInteger("2");
		while(e.compareTo(r)==-1) { //e<r
			if (isCoprime(e,r))
				return e;
			e=e.add(new BigInteger("1"));
		}
		return new BigInteger("-1");
	}

private boolean isCoprime(BigInteger x,	BigInteger y)
{
	BigInteger tmp=new BigInteger("0");
        //使用求商判断法，如果输入的x<y，第一次循环会交换x和y的位置
        while(true)
        {
            tmp=x.mod(y);
            if(tmp.equals(new BigInteger("0")))
            {
                break;
            }
            else
            {
                x=y;
                y=tmp;
            }
        }
        if(y.equals(new BigInteger("1")))         //最大公约数为1,所以互质
            return true;
        else              //最大公约数大于1，所以不互质
            return false;
 
    }
}



