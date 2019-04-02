
public class Algorithm {
	private int[] left=new int[4];
	private int[] right=new int[4];
	private int[] K1;
	private int[] K2;
	
	public Algorithm(int[] K1,int[] K2) {
		this.K1 = K1;
		this.K2 = K2;
	}
	public int[] f(int[] p) {
		IP(p);
		int[] p1=fk(p,K1);		
		int[] p2=SW(p1);		
		int[] p3 = fk(p2,K2);
		int[] p4 = _IP(p3);		
		return p4;
	}
	public int[] d(int[] c) {
		
		IP(c);
		int[] p2 = fk(c,K2);
		int[] p3 = SW(p2);
		int[] p4 = fk(p3,K1);
		int[] p5= _IP(p4);
		return p5;
	}
	
	private int[] fk(int[] p,int[]K) {
		for (int i=0;i<4;i++) {
			left[i]=p[i];
			right[i]=p[i+4];
		}
		
		int[] s=E_P();
		int[][] P= XOR(s,K);
		int[] s_4 = S(P);
		P4(s_4);
		int[] l = XOR_4(left,s_4);
		int[] output = new int[8];
		for(int i=0;i<4;i++) {
			output[i]=l[i];
			output[i+4]=right[i];
		}
		
		return output;
	}
	
	
	private void IP(int[] p) {
		int[] temp = new int[8];
		for(int i=0;i<8;i++) {
			temp[i]=p[i];
		}
		p[0]=temp[1];
		p[1]=temp[5];
		p[2]=temp[2];
		p[3]=temp[0];
		p[4]=temp[3];
		p[5]=temp[7];
		p[6]=temp[4];
		p[7]=temp[6];
	}
	
	private int[] E_P() {
		int[] temp=new int[8];
		temp[0]=right[3];
		temp[1]=right[0];
		temp[2]=right[1];
		temp[3]=right[2];
		temp[4]=right[1];
		temp[5]=right[2];
		temp[6]=right[3];
		temp[7]=right[0];
		return temp;
	}
	private int[][] XOR(int[]S,int[]K1) {
		int[][] P= new int[2][4];
		for(int j=0;j<2;j++) {
			for(int i=0;i<4;i++) {
				if (S[i+j*4]==K1[i+j*4])
					P[j][i]=0;
				else 
					P[j][i]=1;
			 }
		}
		return P;
	}
	private int[] S(int[][]P) {
		int [][] S0 = {{1,0,3,2},{3,2,1,0},{0,2,1,3},{3,1,3,2}};
		int row = P[0][0]*2+P[0][3];
		int col = P[0][1]*2+P[0][2];
		int[] output = new int[4];
		output[0] = S0[row][col]/2;
		output[1] = S0[row][col]%2;
		
		int [][] S1 = {{0,1,2,3},{2,0,1,3},{3,0,1,0},{2,1,0,3}};
		int row1 = P[1][0]*2+P[1][3];
		int col1 = P[1][1]*2+P[1][2];
		
		output[2] = S1[row1][col1]/2;
		output[3] = S1[row1][col1]%2;
		
		return output;
	}
	
	private void P4(int[]S) {
		int[] temp=new int[4];
		for(int i=0;i<4;i++) {
			temp[i]=S[i];
		}
		S[0]=temp[1];
		S[1]=temp[3];
		S[2]=temp[2];
		S[3]=temp[0];
		
	}
	private int[] XOR_4(int[]l,int[]s) {
		int[] output = new int[4];
		for(int i=0;i<4;i++) {
			if(l[i]==s[i]) 
				output[i]=0;
			else
				output[i]=1;
		}
		return output;
	}
	private int[] SW(int[]p) {
		int[] output = new int[8];
		for(int i=0;i<4;i++) {
			output[i]=p[i+4];
			output[i+4]=p[i];
		}
		return output;
	}
	private int[] _IP(int[] p) {
		
		int[] output = new int[8];
		
		output[0]=p[3];
		output[1]=p[0];
		output[2]=p[2];
		output[3]=p[4];
		output[4]=p[6];
		output[5]=p[1];
		output[6]=p[7];
		output[7]=p[5];
		return output;
		
	}
}
