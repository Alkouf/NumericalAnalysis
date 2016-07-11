package examples;

import java.util.Random;

import algorithms.GaussSiedel;
import algorithms.PALU;

public class ExpamplePALU {

	public static void main(String[] args) {

		int d = 5;
		double[][] A=new double [d][d];

		Random rand = new Random();

		for(int i=0; i<d; i++){
			for (int j=0; j<d; j++){
				A[i][j]= rand.nextInt(101);
			}
		}

		double[] b=new double [d];

		for(int i=0; i<d; i++){
			b[i]= rand.nextInt(101);
		}

		
		//First the instantiation of PALU object decomposes the matrix A
		PALU palu = new PALU(A);

		//then you can retrieve the decomposed A
		palu.getL();
		palu.getU();
		
		//or solve equation, A.X = b , where X is the vector of unknown variables, and b is the resulted vector
		palu.calculateX(b);

		
		
		//though it can be applied to any matrix with non-zero elements on the diagonals, 
		//convergence is only guaranteed if the matrix is either diagonally dominant, 
		//or symmetric and positive definite.
		GaussSiedel.solveGaussSiedel(A, b,20);
		
	}

}
