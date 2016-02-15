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

import ccrypt.cmn.Matrix;
import ccrypt.cmn.Vector;
import ccrypt.tde.Key;
import ccrypt.tde.KeyGenerator;
import ccrypt.tde.KeyLoader;
import ccrypt.tde.TextLoader;
import ccrypt.tde.TextDependentEncryption;

class CCryptMain {

    private static String mode;

    private static String keyFilename;

    private static String inputFilename;

    private static String outputFilename;

    private final static int KEY_NOT_FOUND = 0;
    private final static int FILE_NOT_FOUND = 1;
    private final static int MALFORMED_KEY = 2;
    private final static int OUTPUT = 3;
    private final static int ARGUMENTS = 4;
    private final static int INVALID_MODE = 5;
    private final static int UNEXPECTED = 6;

    private static void generateKey(String outFilename) {
        try {
            KeyLoader.write(KeyGenerator.create(), outputFilename);
        }
        catch (IOException e) {
            exitWithErrorMessage(OUTPUT, "");
        }
    }

    private static Key loadKey(String inFilename) {
        Key key = null;

        try {
            key = KeyLoader.read(keyFilename);
        } catch(IOException e) {
            exitWithErrorMessage(KEY_NOT_FOUND, keyFilename);
        }

        return key;
    }

    private static byte[] loadText(String inFilename) {
        byte text[] = null;

        try {
            text = (new TextLoader(
                    new FileInputStream(inFilename))).getText();
        } catch(FileNotFoundException e) {
            exitWithErrorMessage(FILE_NOT_FOUND, inFilename);
        } catch(IOException e) {
            exitWithErrorMessage(UNEXPECTED, inputFilename);
        }

        return text;
    }

    public static void main(String args[]) {
        parseArguments(args);

        if(mode.compareTo("g") == 0) {
            generateKey(outputFilename);
            return;
        }

        Key key = loadKey(keyFilename);
        byte inputText[] = loadText(inputFilename);

        TextDependentEncryption tde =
                new TextDependentEncryption(key);

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
            exitWithErrorMessage(OUTPUT, outputFilename);
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
            exitWithErrorMessage(ARGUMENTS, "");
        }	

        // If wrong mode parameter
        if(mode.compareTo("d") != 0 &&
                mode.compareTo("e") != 0 &&
                mode.compareTo("g") != 0)
            exitWithErrorMessage(INVALID_MODE, mode);
    }

    /**
     * Shows usage and stops execution
     */
    private static void exit(boolean showUsage) {

        if(showUsage) usage();

        System.exit(0);
    }

    /**
     * Shows error messages
     */
    private static void exitWithErrorMessage(int error, String additional) {
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

        case OUTPUT:
            message = "Error - Unable to write file: ";
            break;

        case ARGUMENTS:
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
