package utilities;


public class Utils {

	/**
	 * Left multiplication of square array to vector. A.<b>v<b>
	 * 
	 * @param A the square matrix 
	 * @param v the vector
	 * @return the resulted vector
	 */
	static public double[] arr_vec(double [][] A, double [] v){
		//multiplication of array to a vector from left

		int x= A[0].length;

		double [] R= new double [x];


		if(v.length != x){
			System.out.println("Cannot multiply, incorrenct dimensions");
			return R;
		}

		for(int i=0; i<x ; i++ ){
			for(int j=0; j<x; j++){
				R[i] += A[i][j]*v[j];
			}
		}

		return R;

	}

	//print square array
	static public void printA(double[][] A){
		int x= A[0].length;
		for(int i=0; i< x; i++){
			for(int j=0; j< x; j++){
				System.out.print(A[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	//print vector
	static public void printV(double[] v){
		for(int i=0;i<v.length; i++){
			System.out.println(v[i]+"\t");
		}
		System.out.println();
	}

	//returns true if Arrays are equal
	static public boolean equalsA(double[][] A, double[][] B ){//not tested yet
		if(A[0].length != B[0].length){
			System.out.println("Different lenghts");
			return false;
		}
		int l= A[0].length;

		for (int i=0; i<l ; i++){
			for(int j=0; j<l; j++){
				if(A[i][j] != B[i][j]){
					return false;
				}
			}
		}

		return true;
	}

	//returns true if Arrays are equal
	static public boolean equalsV(double[] A, double[] B ){
		if(A.length != B.length){
			System.out.println("Different lenghts");
			return false;
		}
		int l= A.length;

		for (int i=0; i<l ; i++){
			if(A[i] != B[i]){
				return false;
			}
		}

		return true;
	}

	//returns l2 norm 
	static public double l2V(double[] A, double[] B ){
		double dist=0;
		if(A.length != B.length){
			System.out.println("Different lenghts");
			return -1;
		}
		int l= A.length;

		for (int i=0; i<l ; i++){
			dist+=Math.pow((A[i]-B[i]), 2);
		}


		return Math.sqrt(dist);
	}


	//array Multiplication
	static public double [][] arrMult(double[][] A, double[][] B){
		//dimensions must have the same size
		int l =A[0].length;
		double [][] C= new double [l][l];
		for(int i=0; i<l; i++){
			for(int j=0; j<l;j++){

				//C[i][j] 
				for(int k=0; k<l;k++){
					C[i][j]+= A[i][k]*B[k][j];
				}

			}
		}	

		return C; 
	}

}
