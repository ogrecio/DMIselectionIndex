import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.io.PrintWriter;
import Jama.*; 



public class SelectionIndex  {

	public static void main(String[] args) throws IOException {
		/**
		 * Program execution starts here.
		 * 
		 */
		 if (args.length != 1) {
	        	System.out.println("Usage: java SelectionIndex <File with ABVs of traits in the index>");
	        	System.exit(0);
	        }
		 
		int i,j;
		/**Define the (co)variances between traits in H 
		 * Matrix C
		 */
		double[][] arrayC = {
				{0.04}	};
		Matrix C = new Matrix(arrayC);
		System.out.println("(co) Variances in the Breeding objective");
		C.print(10, 3);
		
		/**Define the (co)variances between traits in H and traits in I
		 * Matrix G
		 */
		double[][] arrayG = {
				{24.74,0.79,0.84,-0.03}	};
		Matrix G = new Matrix(arrayG);
		System.out.println("(co) Variances between the Breeding objective and the Selection index");
		G.print(10, 3);
		/**Define the (co)variances between traits in I
		 * Matrix P
		 */		
		double[][] arrayP = {
				{198400.00,4888.17,4215.37,-780.75},
				{4888.17,155.52,148.54,-14.90},
				{4215.37,148.54,266.24,-3.90},
				{-780.75,-14.90,-3.90,63.48} };
		Matrix P = new Matrix(arrayP);
		System.out.println("(co) Variances in the Selection index");
		P.print(10, 3);

		/**Define the economic values of traits in H 
		 * vector a
		 */
		double[][] arraya = {
				{1.0}};
		Matrix a= new Matrix(arraya);
		System.out.println("Economic values");
		a.print(10, 3);


		//Calculate the index coefficient b=Pinv*G*a
		 Matrix b = P.inverse().times(G.transpose()).times(a); //.minus(b);
		 System.out.println("Index coefficients");
		 b.print(8, 5);

		 
		//Calculate the accuracy of the index as sqrt [ (b'*G*a) / (a'*C*a) ]
		Matrix num=b.transpose().times(G.transpose()).times(a);
		Matrix den=a.transpose().times(C).times(a);
		System.out.println( "Accuracy of the index="+Math.sqrt(num.get(0, 0)/den.get(0, 0)) );
		
		
		
		PrintWriter outData = new PrintWriter ("values.txt");
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(new File (args[0])));
			//inFile.readLine(); // read header
			String line;i=0;
			while ( (line=inFile.readLine()) != null ) {
				i++;
	        	StringTokenizer st = new StringTokenizer(line, ",");
	        	if (st.countTokens() != 7){
			       	System.out.println("Data file different number of columns than 7 at "+i);
			       	System.runFinalization();
			    }
	        	outData.print(st.nextToken()+",");
	    		double [][] arrayY={
	    				{Double.parseDouble(st.nextToken())},{Double.parseDouble(st.nextToken())},{Double.parseDouble(st.nextToken())},{Double.parseDouble(st.nextToken())}
	    		};
	    		Matrix y = new Matrix(arrayY);
	    		double r = Double.parseDouble(st.nextToken())/100.d;
	    		double [][] arrayR={
						{r},{r},{r},{Double.parseDouble(st.nextToken())/100.d}};
	    		Matrix rel = new Matrix(arrayR);
	    		
	    		//Calculate the index value
	    		double [][] arrayrb= new double[b.getRowDimension()][1];
	    		 for (i=0;i<b.getRowDimension();i++){
	    				arrayrb[i][0]=b.get(i, 0)*rel.get(i, 0);
	    			}
	    		 Matrix rb = new Matrix(arrayrb);
	    		Matrix index =b.transpose().times(y);
	    		//index.print(outData, 12, 5);
	    		num=rb.transpose().times(G.transpose()).times(a);
	    		outData.println(index.get(0, 0)+","+num.get(0, 0)/den.get(0, 0) );
			}
			inFile.close();outData.close();
		} catch (FileNotFoundException e) {
			System.out.println("Genotype file not found. ");
		}
		
		

		/*
		*/
		outData.close();
		System.out.println("TERMINATED WITH NO ERRORS KNOWN");
		System.runFinalization();
	}	
}
