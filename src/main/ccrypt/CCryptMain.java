package ccrypt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import ccrypt.tde.CiphertextLoader;
import ccrypt.tde.Key;
import ccrypt.tde.KeyLoader;
import ccrypt.tde.PlaintextLoader;
import ccrypt.tde.TextDependentEncryption;

class CCryptMain {

    // Perturbate option
    private static boolean perturbate;
    // Path to the key
    private static String keypath;
    // Mode (encryption/decryption)
    private static String mode;
    // Path to ciphertext file
    private static String inputfile;
    // Path to output file
    private static String outputfile;
    // Error numbers
    private final static int KEY_NOT_FOUND = 0;
    private final static int INPUT_NOT_FOUND = 1;
    private final static int MALFORMED_KEY = 2;
    private final static int OUTPUT = 3;
    private final static int ARGUMENTS = 4;
    private final static int CORRUPTED_CIPHERTEXT = 5;
    private final static int UNEXPECTED = 6;

    public static void main(String args[]){

	KeyLoader keyLoader = null;

	parseArguments(args);

	// Handle key file not found
	try{
	    keyLoader = new KeyLoader(keypath, 8);
	} catch(FileNotFoundException e){
	    errorMessage(KEY_NOT_FOUND, keypath);
	} catch(InputMismatchException e) {
	    errorMessage(MALFORMED_KEY, "");
	}

	Key key = keyLoader.getKey();
	TextDependentEncryption tde = new TextDependentEncryption(key,
						perturbate);
	FileInputStream input = null;

	// Handle input file not found
	try {
	    input = new FileInputStream(inputfile);
	} catch(FileNotFoundException f) {
	    errorMessage(INPUT_NOT_FOUND, "filepath");
	}

	if(mode.compareTo("e") == 0){
	    PlaintextLoader pl = new PlaintextLoader(input);
	    byte plaintext[] = pl.getPlaintext();
	    
	    writeIntegersToFile(outputfile, tde.encrypt(plaintext));
	}

	if(mode.compareTo("d") == 0){
	    CiphertextLoader cl = null;

	    try{
		cl = new CiphertextLoader(input);
	    } catch(Exception e){
		errorMessage(CORRUPTED_CIPHERTEXT, "");
	    }

	    int ciphertext[] = cl.getCiphertext();

	    writeBytesToFile(outputfile, tde.decrypt(ciphertext));
	}
    }

    /*
	Reads the argument and sets the environment for TDE
    */
    private static void parseArguments(String args[]){

	// If wrong number of parameters
	if(args.length < 4 || args.length > 6)
	    errorMessage(ARGUMENTS, "");

	mode = args[0];
	keypath = args[1];
	inputfile = args[2];
	outputfile = args[3];
	perturbate = false;

	// If wrong mode parameter
	if(mode.compareTo("d") != 0 && mode.compareTo("e") != 0)
	    abortTDE(true);

	// If perturbate parameter given
	if(args.length == 5){

	    // If wrong perturbate parameter
	    if(args[4].compareTo("1") != 0)
		abortTDE(true);

	    perturbate = true;
	}
    }

    /*
	Shows usage and stops execution
    */
    private static void abortTDE(boolean showUsage){

	if(showUsage) usage();

	System.exit(0);
    }

    /*
	Shows error messages
    */
    private static void errorMessage(int error, String additional){

	boolean showUsage = false;

	if(error == KEY_NOT_FOUND)
	    System.err.println("\nTDE ERROR Key file not found: "
			       + additional + "\n");

	if(error == INPUT_NOT_FOUND)
	    System.err.println("\nTDE ERROR Input file not found: "
			       + additional + "\n");

	if(error == MALFORMED_KEY)
	    System.err.println("\nTDE ERROR Malformed key\n");

	if(error == UNEXPECTED)
	    System.err.println("\nTDE ERROR Unexpected termination\n");

	if(error == OUTPUT)
	    System.err.println("\nTDE ERROR Unable to write file: "
			       + additional + "\n");

	if(error == ARGUMENTS){
	    System.err.println("\nTDE ERROR Wrong number of arguments\n");
	    showUsage = true;
	}

	abortTDE(showUsage);
    }

    /*
      Prints TDE usage
    */
    private static void usage(){
	String message =
	    "\nTDE usage: java TDE mode key input output [perturbate]\n" +
	    "\tmode --> (e | d)\n" +
	    "\tkey  --> path to key file\n" +
	    "\t\te: encrypt\n" +
	    "\t\td: decrypt\n" +
	    "\tinput --> full path to input plaintext/ciphertext file\n" +
	    "\toutput --> full path to output ciphertext/plaintext file\n" +
	    "\tperturbate (optional) --> 1\n" +
	    "Note: if encryption is performed with the perturbate\n" +
	    "option on, then decryption must be performed with the\n" +
	    "perturbation option on as well\n";
	System.err.println(message);
    }
    
    /*
      Writes the content of an array of bytes to a file
    */
    private static void writeBytesToFile(String name, byte content[]){
	
	FileOutputStream output;

	try{
	    output = new FileOutputStream(name);
	    output.write(content);
	} catch(IOException e) {
	    errorMessage(OUTPUT, name);
	}
    }

    /*
	Writes the contnt of an array of integers to a file
    */
    private static void writeIntegersToFile(String name, int content[]){

	BufferedWriter out;

	try {
	    out = new BufferedWriter(new FileWriter(name));
	    
	    for(int i = 0 ; i < content.length ; i++)
		out.write(Integer.toString(content[i]) + "\n");
	    
	    out.close();
	} catch (IOException e) {
	    errorMessage(OUTPUT, name);
	}
    }
}
