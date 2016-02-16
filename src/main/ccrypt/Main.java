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

package ccrypt;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

import ccrypt.TextLoader;
import ccrypt.cipher.TextDependentKey;
import ccrypt.cipher.TextDependentKeyLoader;
import ccrypt.cipher.TextDependentCipher;

class Main {

    private static String mode;

    private static String keyFilename;

    private static String inputFilename;

    private static String outputFilename;

    private enum ErrorCode {
	KEY_NOT_FOUND, FILE_NOT_FOUND, MALFORMED_KEY,
	OUTPUT_ERROR, ARGUMENT_ERROR, INVALID_MODE,
	UNEXPECTED_ERROR
    };

    private static void generateKey(String outFilename) {
        try {
            TextDependentKeyLoader.write(TextDependentKey.create(),
					 outFilename);
        }
        catch (IOException e) {
            exitWithErrorMessage(ErrorCode.OUTPUT_ERROR, "");
        }
    }

    private static TextDependentKey loadKey(String inFilename) {
        TextDependentKey key = null;

        try {
            key = TextDependentKeyLoader.read(keyFilename);
        } catch(IOException e) {
            exitWithErrorMessage(ErrorCode.KEY_NOT_FOUND, keyFilename);
        }

        return key;
    }

    private static byte[] loadText(String inFilename) {
        byte text[] = null;

        try {
            text = (new TextLoader(
                    new FileInputStream(inFilename))).getText();
        } catch(FileNotFoundException e) {
            exitWithErrorMessage(ErrorCode.FILE_NOT_FOUND, inFilename);
        } catch(IOException e) {
            exitWithErrorMessage(ErrorCode.UNEXPECTED_ERROR, inFilename);
        }

        return text;
    }

    public static void main(String args[]) {
        parseArguments(args);

        if(mode.compareTo("g") == 0) {
            generateKey(outputFilename);
            return;
        }

        TextDependentKey key = loadKey(keyFilename);
        byte inputText[] = loadText(inputFilename);

        TextDependentCipher tde =
                new TextDependentCipher(key);

        byte outputText[] = null;
        if(mode.compareTo("e") == 0) {
            outputText = tde.encrypt(inputText);
        }
        else {
            outputText = tde.decrypt(inputText);
        }

        // Write result to output file
        try{
            FileOutputStream out = new FileOutputStream(outputFilename);
            out.write(outputText);
            out.close();
        } catch(IOException e) {
            exitWithErrorMessage(ErrorCode.OUTPUT_ERROR, outputFilename);
        }
    }

    /**
     * Reads the argument and sets the environment for TDE
     */
    private static void parseArguments(String args[]){

        if(args.length == 0)
            exit(true);

        if(args.length == 2) {
            mode = args[0];
            outputFilename = args[1];
        }
        else if(args.length == 4) {
            mode = args[0];
            keyFilename = args[1];
            inputFilename = args[2];
            outputFilename = args[3];
        }
        else {
            // If wrong number of parameters
            exitWithErrorMessage(ErrorCode.ARGUMENT_ERROR);
        }	

        // If wrong mode parameter
        if(mode.compareTo("d") != 0 &&
                mode.compareTo("e") != 0 &&
                mode.compareTo("g") != 0)
            exitWithErrorMessage(ErrorCode.INVALID_MODE, mode);
    }

    /**
     * Shows usage and stops execution
     */
    private static void exit(boolean showUsage) {

        if(showUsage) usage();

        System.exit(0);
    }

    /**
     * Show error messages
     */
    private static void exitWithErrorMessage(ErrorCode error) {
	exitWithErrorMessage(error, "");
    }

    /**
     * Shows error messages with additional info
     */
    private static void exitWithErrorMessage(ErrorCode error,
					     String additional) {
        boolean showUsage = false;
        String message;

        switch(error) {

        case INVALID_MODE:
            message = "Error - Invalid mode: ";
            showUsage = true;
            break;

        case KEY_NOT_FOUND:
            message = "Error - Key not found: ";
            break;

        case FILE_NOT_FOUND:
            message = "Error - Input file not found: ";
            break;

        case MALFORMED_KEY:
            message = "Error - Malformed key";
            break;

        case OUTPUT_ERROR:
            message = "Error - Unable to write file: ";
            break;

        case ARGUMENT_ERROR:
            message = "Error - Some input arguments are missing";
            showUsage = true;
            break;

        default:
            message = "Error - Unexpected termination";
            break;
        }

        System.err.println(message + additional);

        exit(showUsage);
    }

    /**
     * Prints TDE usage
     */
    private static void usage(){
        String message =
	    "\nUsage: ./ccrypt [g output] | [(e | d) key input output]\n" +
	    "\tmodes\n" +
	    "\t\tg --> generate key\n" +
	    "\t\te --> encrypt\n" +
	    "\t\td --> decrypt\n" +
	    "\tkey  --> path to key file\n" +
	    "\tinput --> input plaintext/ciphertext filename\n" +
	    "\toutput --> output key/ciphertext/plaintext filename\n";
	
        System.err.println(message);
    }
}
