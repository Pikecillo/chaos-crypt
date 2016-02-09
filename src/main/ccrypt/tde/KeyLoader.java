/*======================================================================
 
 Copyright (C) 2009-2015. Mario Rincon-Nigro.

 This file is a part of Chaos-Crypt.

 This is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Chaos-Crypt is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Chaos-Crypt.  If not, see <http://www.gnu.org/licenses/>.

======================================================================*/

package ccrypt.tde;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import ccrypt.cmn.Vector;
import ccrypt.cmn.Matrix;

/**
 * Loader of a Text Dependent Encryption method key.
 */
public class KeyLoader {
    
    // The laoded key
    private Key key;
    
    /*
      For creating instances of a key loader.
      path is the path to the KEY file.
      The network associated to the key has the
      size indicated by the second argument
    */
    public KeyLoader(String path, int size)
	throws FileNotFoundException, InputMismatchException {
	
	Scanner scan = null;
	
	double state[] = new double[size];
	double coupling[][] = new double[size][size];
	
	// Create an instance of a scanner for reading the file
	scan = new Scanner(new File(path));
	
	try {
	    // Read the initial state of the network
	    for(int i = 0 ; i < size ; i++)
		state[i] = scan.nextDouble();
	    
	    // Read the coupling coefficients
	    for(int i = 0 ; i < size ; i++)
		for(int j = 0 ; j < size ; j++)
		    coupling[i][j] = scan.nextDouble();
	    
	    // Create the instance of the key
	    key = new Key(new Vector(state), new Matrix(coupling));
	    
	    scan.close();
	} catch (InputMismatchException e) {
	    scan.close();
	    
	    throw e;
	}
    }
    
    public Key getKey(){
	return key;
    }
}
