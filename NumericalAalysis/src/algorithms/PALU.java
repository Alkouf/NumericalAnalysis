package algorithms;

public class PALU {

	private double [][] P;
	private double [][] A;
	private double [][] L;
	private double [][] U;

	
	public void printP(){
		utilities.Utils.printA(P);
	}
	public void printA(){
		utilities.Utils.printA(A);
	}
	public void printL(){
		utilities.Utils.printA(L);
	}
	public void printU(){
		utilities.Utils.printA(U);
	}
	
	public double [][] getP(){
		return P.clone();
	}
	public double [][] getA(){
		return A.clone();
	}
	public double [][] getL(){
		return L.clone();
	}
	public double [][] getU(){
		return U.clone();
	}
	

	public PALU(double [][] A){

		if(A.length != A[0].length){
			System.err.println("Array A isn't square! return");
			return;
		}

		int x= A.length;

		P = new double[x][x];
		this.A = new double[x][x];
		L = new double[x][x];
		U = new double[x][x];

		//array P initially is identity matrix
		for(int i=0; i<x; i++){
			P[i][i] = 1;	
		}

		//Copying temporary array A to array A to start refining
		for(int i=0; i<x; i++){			
			System.arraycopy(A[i], 0, this.A[i], 0, x);
		}

		//Copying array A to array U to start refining
		for(int i=0; i<x; i++){			
			System.arraycopy(A[i], 0, U[i], 0, x);
		}


		//the column we need to nullify
		for(int c=0; c< x-1; c++){

			double max=c;
			int max_i=c;
			for(int i=c; i< x; i++){
				if(U[i][c]>max){
					max= U[i][c];
					max_i = i;
				}
			}

			//if the max isn't on the upper most row exchange the rows 
			if(max_i != c){
				//swap max_i with c on array A and array P
				//System.out.println("Swap row "+max_i +" with "+c);

				double temp ;
				for(int j=0; j< x; j++){
					temp =  U[c][j];
					U[c][j] = U[max_i][j];
					U[max_i][j] = temp;
				}
				for(int j=0; j< x; j++){
					temp =  L[c][j];
					L[c][j] = L[max_i][j];
					L[max_i][j] = temp;
				}
				for(int j=0; j< x; j++){
					temp =  P[c][j];
					P[c][j] = P[max_i][j];
					P[max_i][j] = (int)temp;
				}
				//System.out.println("P array after swapping");
				//utilities.Utils.printA(P);
			}

			//System.out.println("The max driver got on the current row");
			//utilities.Utils.printA(U);

			for(int i=c+1; i<x; i++){
				double k=U[i][c]/U[c][c]; 
				L[i][c] = k;
				//System.out.println("k="+k+" i="+i+", c="+c);

				for(int j=c; j<x; j++){
					U[i][j] = U[i][j]- U[c][j]*k;
				}
				//System.out.println("Substruct current row the below rows times k");
				//utilities.Utils.printA(U);
			}

		}

		for(int i=0; i<x; i++){
			L[i][i]=1;
		}


		System.out.println("P:");
		utilities.Utils.printA(P);
		System.out.println("A:");
		utilities.Utils.printA(A);
		System.out.println("L:");
		utilities.Utils.printA(L);
		System.out.println("U:");
		utilities.Utils.printA(U);

		boolean eq=utilities.Utils.equalsA(utilities.Utils.arrMult(P, A), utilities.Utils.arrMult(L, U));
		System.out.println("PA=LU : "+eq+"\n");
		if(!eq){
			System.out.println("PA=");
			utilities.Utils.printA(utilities.Utils.arrMult(P, A));
			System.out.println("LU=");
			utilities.Utils.printA(utilities.Utils.arrMult(L, U));
		}

	}
	
	/**
	 * 
	 * @return the vector X that solves the equation A.X = b
	 */
	public double[] calculateX(double [] b){
		
		int x= b.length;
		if(x!=A.length){
			System.err.println("Vector B has diffrent size than matrix A. Returning null.");
			return null;
		}
		
		double[] Pb = new double[x];
		Pb=utilities.Utils.arr_vec(P,b);
		//System.out.println("Vector Pb:");
		//utilities.Utils.printV(Pb);

		double[] y = new double[x];
		y = y(Pb,L);
		//System.out.println("Vector y:");
		//utilities.Utils.printV(y);

		double[] X = new double[x];
		X = x(y,U);
		System.out.println("Vector x:");
		utilities.Utils.printV(X);
		
		boolean eq=utilities.Utils.equalsV(utilities.Utils.arr_vec(A, X), b);
		System.out.println("A.X=b : "+eq);
		if(!eq){
			System.out.println("A.X=");
			utilities.Utils.printV(utilities.Utils.arr_vec(A, X));
			System.out.println("b=");
			utilities.Utils.printV(b);
		}
		
		return X.clone();
	}




	//calculates x vector
	static private double[] x(double [] Pb, double[][] L){
		int l= Pb.length;
		double[] x = new double[l];

		for(int i=l-1; i>=0; i--){

			x[i]= Pb[i];
			for(int j=l-1; j>i; j--){
				x[i] -= x[j]*L[i][j];
			}

			x[i] /= L[i][i];	
		}

		return x;

	}


	//calculates y vector
	static private double[] y(double [] Pb, double[][] L){
		int x= Pb.length;
		double[] y = new double[x];

		for(int i=0; i<x; i++){

			y[i]= Pb[i];
			for(int j=0; j<i; j++){
				y[i] -= y[j]*L[i][j];
			}

			y[i] /= L[i][i];	
		}

		return y;	
	}


}
