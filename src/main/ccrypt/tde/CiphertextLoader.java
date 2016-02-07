package ccrypt.tde;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Vector;

/*
    Ciphertext loader for the TDE
*/
public class CiphertextLoader {

    int ciphertext[];

    /*
	For creating an instance of a CiphertextLoader from
	an input stream
    */
    public CiphertextLoader(InputStream input) throws Exception{

	Scanner scan = new Scanner(input);
	Vector<Integer> vector = new Vector<Integer>();

	try {
	    while(scan.hasNextInt()){
		vector.add(scan.nextInt());
	    }

	    ciphertext = new int[vector.size()];

	    for(int i = 0 ; i < vector.size() ; i++)
		ciphertext[i] = vector.elementAt(i).intValue();
	} catch (Exception e){
	    throw e;
	}
    }

    /*
	Returns the ciphertext as a sequence of integers
    */
    public int [] getCiphertext(){

	return ciphertext;
    }
}
