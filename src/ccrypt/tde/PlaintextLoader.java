package tde;

import java.io.InputStream;
import java.io.IOException;
import java.util.Vector;

/*
    Plaintext loader for the TDE
*/
public class PlaintextLoader {

    byte plaintext[];

    /*
	For creating and instance of a plaintext loader
	given and input stream
    */
    public PlaintextLoader(InputStream input){
	int n;
	byte buffer[] = new byte[1024];
	
	Vector<Byte> vector = new Vector<Byte>();

	try {
	    while((n = input.read(buffer)) != -1){
		for(int i = 0 ; i < n ; i++)
		    vector.add(buffer[i]);
	    }
	} catch (IOException e){
	    e.printStackTrace();
	    System.exit(1);
	}

	plaintext = new byte[vector.size()];

	for(int i = 0 ; i < vector.size() ; i++)
	    plaintext[i] = vector.elementAt(i).byteValue();
    }

    // Returns the loaded plaintext as an array of bytes
    public byte [] getPlaintext(){

	return plaintext;
    }
}