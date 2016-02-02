package it.unitn.bigdataprojet.sparkMllib;

import java.io.Serializable;

public class Administration implements Serializable {
/**
 * 
 * sex,age,before30,after60,age3060,gluten,lactoseA,B,C,
		    D,G,H,
		    J,L,M,
		    N,P,R,
		    S,V, N04B,A10A,N05AA,N05AB,N05AC,N05AD,N05AF,N05AG
	 * 
	 */
	private static final long serialVersionUID = -4761615443511813717L;
	//000000467	M	80	A	A02	A02B	A02BC		S
	int person;
	int sex;
	int age;
	int before30;
	int after60;
	int age3060;
	int gluten;
	int lactose;

	
	int	A,B,C,
		    D,G,H,
		    J,L,M,
		    N,P,R,
		    S,V, 

		/* first only with 1 level
		    A01,A02,A03,A04,A05,A06,A07,A08,A09,A10,A11,A12,A13,A14,A15,A16,
		    B01,B02,B03,B05,B06,
		    C01,C02,C03,C04,C05,C07,C08,C09,C10,
		    D01,D02,D03,D04,D05,D06,D07,D08,D09,D10,D11,
		    G01,G02,G03,G04,
		    H01,H02,H03,H04,H05,
		    J01,J02,J04,J05,J06,J07,
		    L01,L02,L03,L04,
		    M01,M02,M03,M04,M05,M09,
		    N01,N02,N03,N04,N05,N06,N07,
		    P01,P02,P03,
		    R01,R02,R03,R05,R06,R07,
		    S01,S02,S03,
		    V01,V03,V04,V06,V07,V08,V09,V10,V20,
	*/
            N04B,A10A,N05AA,N05AB,N05AC,N05AD,N05AF,N05AG;


	
	public Administration(Integer person, Integer sex, Integer age, Integer before30, Integer after60, Integer age3060,
			Integer gluten, Integer lactose, Integer a, Integer b, Integer c, Integer d, Integer g, Integer h,
			Integer j, Integer l, Integer m, Integer n, Integer p, Integer r, Integer s, Integer v, Integer n04b,
			Integer a10a, Integer n05aa, Integer n05ab, Integer n05ac, Integer n05ad, Integer n05af, Integer n05ag) {
		super();
		this.person = person;
		this.sex = sex;
		this.age = age;
		this.before30 = before30;
		this.after60 = after60;
		this.age3060 = age3060;
		this.gluten = gluten;
		this.lactose = lactose;
		A = a;
		B = b;
		C = c;
		D = d;
		G = g;
		H = h;
		J = j;
		L = l;
		M = m;
		N = n;
		P = p;
		R = r;
		S = s;
		V = v;
		N04B = n04b;
		A10A = a10a;
		N05AA = n05aa;
		N05AB = n05ab;
		N05AC = n05ac;
		N05AD = n05ad;
		N05AF = n05af;
		N05AG = n05ag;
	}




	public int getPerson() {
		return person;
	}




	public void setPerson(int person) {
		this.person = person;
	}




	public int getSex() {
		return sex;
	}




	public void setSex(int sex) {
		this.sex = sex;
	}




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public int getBefore30() {
		return before30;
	}




	public void setBefore30(int before30) {
		this.before30 = before30;
	}




	public int getAfter60() {
		return after60;
	}




	public void setAfter60(int after60) {
		this.after60 = after60;
	}




	public int getAge3060() {
		return age3060;
	}




	public void setAge3060(int age3060) {
		this.age3060 = age3060;
	}




	public int getGluten() {
		return gluten;
	}




	public void setGluten(int gluten) {
		this.gluten = gluten;
	}




	public int getLactose() {
		return lactose;
	}




	public void setLactose(int lactose) {
		this.lactose = lactose;
	}




	public int getA() {
		return A;
	}




	public void setA(int a) {
		A = a;
	}




	public int getB() {
		return B;
	}




	public void setB(int b) {
		B = b;
	}




	public int getC() {
		return C;
	}




	public void setC(int c) {
		C = c;
	}




	public int getD() {
		return D;
	}




	public void setD(int d) {
		D = d;
	}




	public int getG() {
		return G;
	}




	public void setG(int g) {
		G = g;
	}




	public int getH() {
		return H;
	}




	public void setH(int h) {
		H = h;
	}




	public int getJ() {
		return J;
	}




	public void setJ(int j) {
		J = j;
	}




	public int getL() {
		return L;
	}




	public void setL(int l) {
		L = l;
	}




	public int getM() {
		return M;
	}




	public void setM(int m) {
		M = m;
	}




	public int getN() {
		return N;
	}




	public void setN(int n) {
		N = n;
	}




	public int getP() {
		return P;
	}




	public void setP(int p) {
		P = p;
	}




	public int getR() {
		return R;
	}




	public void setR(int r) {
		R = r;
	}




	public int getS() {
		return S;
	}




	public void setS(int s) {
		S = s;
	}




	public int getV() {
		return V;
	}




	public void setV(int v) {
		V = v;
	}




	public int getN04B() {
		return N04B;
	}




	public void setN04B(int n04b) {
		N04B = n04b;
	}




	public int getA10A() {
		return A10A;
	}




	public void setA10A(int a10a) {
		A10A = a10a;
	}




	public int getN05AA() {
		return N05AA;
	}




	public void setN05AA(int n05aa) {
		N05AA = n05aa;
	}




	public int getN05AB() {
		return N05AB;
	}




	public void setN05AB(int n05ab) {
		N05AB = n05ab;
	}




	public int getN05AC() {
		return N05AC;
	}




	public void setN05AC(int n05ac) {
		N05AC = n05ac;
	}




	public int getN05AD() {
		return N05AD;
	}




	public void setN05AD(int n05ad) {
		N05AD = n05ad;
	}




	public int getN05AF() {
		return N05AF;
	}




	public void setN05AF(int n05af) {
		N05AF = n05af;
	}




	public int getN05AG() {
		return N05AG;
	}




	public void setN05AG(int n05ag) {
		N05AG = n05ag;
	}




	@Override
	public String toString() {
		return "Administration [person=" + person + ", sex=" + sex + ", age=" + age + ", before30=" + before30
				+ ", after60=" + after60 + ", age3060=" + age3060 + ", gluten=" + gluten + ", lactose=" + lactose
				+ ", A=" + A + ", B=" + B + ", C=" + C + ", D=" + D + ", G=" + G + ", H=" + H + ", J=" + J + ", L=" + L
				+ ", M=" + M + ", N=" + N + ", P=" + P + ", R=" + R + ", S=" + S + ", V=" + V + ", N04B=" + N04B
				+ ", A10A=" + A10A + ", N05AA=" + N05AA + ", N05AB=" + N05AB + ", N05AC=" + N05AC + ", N05AD=" + N05AD
				+ ", N05AF=" + N05AF + ", N05AG=" + N05AG + "]";
	}



	
	
}
