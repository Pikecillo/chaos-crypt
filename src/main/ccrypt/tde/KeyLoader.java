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

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;

/**
 * Reader and writer of TDE keys.
 */
public class KeyLoader {

    /**
     * Reads a TDE key from a file.
     *
     * @param inputFilename Name of input file.
     * @return The key.
     */
    public static Key read(String inputFilename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
        String keyString = "", line;
        
        while((line = reader.readLine()) != null) {
            keyString += line;
        }
        
        reader.close();
        
        return new Key(keyString);
    }

    /**
     * Write a TDE key to a file.
     *
     * @param key The key.
     * @param outputFilename Name of the output file.
     */
    public static void write(Key key, String outputFilename) throws IOException {
        FileOutputStream output = new FileOutputStream(outputFilename);
        String strKey = key.toString();
        int stride = 64;
        
        for(int i = 0; i < strKey.length(); i += stride) {
            String line = (i == 0 ? "" : "\n")
                    + strKey.substring(i, i + stride).toLowerCase();
            output.write(line.getBytes());
        }
        output.close();
    }
}
