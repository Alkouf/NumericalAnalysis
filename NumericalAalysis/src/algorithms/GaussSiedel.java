package algorithms;

import utilities.Utils;

public class GaussSiedel {
	
	static public double[] solveGaussSiedel(double[][] A,  double[] b,int iterations){

		int x= A.length;
		
		double [] X = new double[x];
		
		int i=0;
		
		for(i=0;i<iterations;i++){
			
			for(int j=0;j<x;j++){//for every row 
				
				X[j]= b[j];
				
				for(int r=0;r<x;r++){ //for every other suntelesti
					if(r!=j){
						X[j] -= X[r]*A[j][r];
					}
				}
				X[j]/=A[j][j];
			}
			/*System.out.println("Iteration "+i+":");
			System.out.println("Vector y:");
			Utils.printV(y);*/
			
			
		}
		
		System.out.println("SOLUTION: ");
		
		System.out.println("vector X= ");
		Utils.printV(X);
		
		System.out.println("A*x:");
		Utils.printV(Utils.arr_vec(A, X));
		
		System.out.println("Vector b:");
		Utils.printV(b);
		
		System.out.println("Euclidean distance |A.X-b|: ");
		System.out.println(Utils.l2V(Utils.arr_vec(A, X), b));
		
		return X.clone();
	}

	//though it can be applied to any matrix with non-zero elements on the diagonals, 
	//convergence is only guaranteed if the matrix is either diagonally dominant, 
	//or symmetric and positive definite.
	
}
