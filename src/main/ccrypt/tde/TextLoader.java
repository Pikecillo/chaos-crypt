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

import java.io.InputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Text loader
 */
public class TextLoader {
    
    byte text[];
    
    /**
     * Create a text loader.
     *
     * @param input Text input stream from where to read.
     */
    public TextLoader(InputStream input) throws IOException {
	int n;
	byte buffer[] = new byte[1024];
	
	Vector<Byte> vector = new Vector<Byte>();
	
	while((n = input.read(buffer)) != -1) {
	    for(int i = 0 ; i < n ; i++) {
		vector.add(buffer[i]);
	    }
	}
	
	text = new byte[vector.size()];
	
	for(int i = 0 ; i < vector.size() ; i++) {
	    text[i] = vector.elementAt(i).byteValue();
	}
    }
    
    /**
     * Get the text as an array of bytes.
     *
     * @return The text as an array of bytes.
     */
    public byte [] getText(){
	
	return text;
    }
}
