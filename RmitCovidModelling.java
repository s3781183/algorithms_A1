import java.io.PrintStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 * Framework to test the graph implementations for assignment 1, 2021,s1.
 *
 * There should be no need to change this for task A.  If you need to make changes for task B, please make a copy, then modify the copy for task B.
 *
 * @author Jeffrey Chan, 2021.
 */
public class RmitCovidModelling
{
	/** Name of class, used in error messages. */
	protected static final String progName = "RmitCovidModelling";

	/** Standard outstream. */
	protected static final PrintStream outStream = System.out;

	/**
	 * Print help/usage message.
	 */
	public static void usage(String progName) {
		System.err.println(progName + ": [-f <filename to load graph>] [-o <filename to store output>]<implementation>] <implementation>");
		System.err.println("<implementation> = <adjlist | adjmat | incmat>");
		System.err.println("If the -o optional output filename is specified, then non-interative mode will be used and output is written to the specified.  Otherwise interative mode is assumed and output is written to System.out.");
		System.exit(1);
	} // end of usage()


	/**
	 * Print error message to System.err.
	 */
	public static void printErrorMsg(String msg) {
		System.err.println("> " + msg);
	} // end of printErrorMsg()


	/**
	 * Print error message to specified outWriter.
	 *
	 * @param outWriter PrintWriter to write error messages to.
	 * @param msg Error message.
	 */
	public static void printErrorMsg(PrintWriter outWriter, String msg) {
		outWriter.println("> " + msg);
	} // end of printErrorMsg()


	/**
	 * Process the operation commands coming from inReader, and updates the graph according to the operations.
	 *
	 * @param inReader Input reader where the operation commands are coming from.
	 * @param graph The graph which the operations are executed on.
	 * @param sirModel SIR model object that will run the SIR simulation.
	 * @param outWriter Where to outputs should be written to.
	 *
	 * @throws IOException If there is an exception to do with I/O.
	 */
	public static void processOperations(BufferedReader inReader, ContactsGraph graph, SIRModel sirModel,
			PrintWriter outWriter)
		throws IOException
	{
		String line;
		int lineNum = 1;
		boolean bQuit = false;

		// continue reading in commands until we either receive the quit signal or there are no more input commands
		while (!bQuit && (line = inReader.readLine()) != null) {
			String[] tokens = line.split(" ");

			// check if there is at least an operation command
			if (tokens.length < 1) {
				printErrorMsg("not enough tokens.");
				continue;
			}

			String command = tokens[0];
			long startTime = 0;
			long endTime=0;
			try {
				// determine which operation to execute
				switch (command.toUpperCase()) {
					// add vertex
					case "AV":
						if (tokens.length == 2) {
							startTime = System.nanoTime();
							graph.addVertex(tokens[1]);
							endTime = System.nanoTime();
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}
						break;
	                // add edge
					case "AE":
						if (tokens.length == 3) {
							startTime = System.nanoTime();
							graph.addEdge(tokens[1], tokens[2]);
							endTime = System.nanoTime();
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}
						break;
					// toogle SIR state of vertex
					case "TV":
						if (tokens.length == 2) {
							graph.toggleVertexState(tokens[1]);
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}
						break;
					// remove/delete vertex
					case "DV":
						if (tokens.length == 2) {
							startTime = System.nanoTime();
							graph.deleteVertex(tokens[1]);
							endTime = System.nanoTime();
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}
						break;
					// remove/delete edge
					case "DE":
						if (tokens.length == 3) {
							startTime = System.nanoTime();
							graph.deleteEdge(tokens[1], tokens[2]);
						    endTime = System.nanoTime();
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}
						break;
					// k-nearest neighbourhood
					case "KN":
						outWriter.println("# " + line);
						if (tokens.length == 3) {
							startTime = System.nanoTime();
							int k = Integer.parseInt(tokens[1]);
							endTime = System.nanoTime();
							if (k < 0) {
								printErrorMsg("k should be 0 or greater");
							}
							else {
								String[] neighbours = graph.kHopNeighbours(k, tokens[2]);

								outWriter.println(tokens[2] + ": " + String.join(" ", neighbours));
							}
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}

						break;
					// print vertices
					case "PV":
						outWriter.println("# " + line);
						graph.printVertices(outWriter);
						break;
	                // print edges
					case "PE":
						outWriter.println("# " + line);
						graph.printEdges(outWriter);
						break;
					// run SIR model
					case "SIR":
						outWriter.println("# " + line);
						if (tokens.length == 4) {
							String[] seedVertices = tokens[1].split(";");
							float infectionProb = Float.parseFloat(tokens[2]);
							float recoverProb = Float.parseFloat(tokens[3]);
							startTime = System.nanoTime();
							sirModel.runSimulation(graph, seedVertices, infectionProb, recoverProb, outWriter);
							endTime = System.nanoTime();
						}
						else {
							printErrorMsg("incorrect number of tokens.");
						}

						break;
					// quit
					case "Q":
						bQuit = true;
						break;
					default:
						printErrorMsg("Unknown command.");
				} // end of switch()
				outWriter.println(((double)(endTime - startTime)) / Math.pow(10, 9));
			}
			catch (IllegalArgumentException e) {
				printErrorMsg(e.getMessage());
			}

			lineNum++;
		}

	} // end of processOperations()



	/**
	 * Main method.  Determines which implementation to test and processes command line arguments.
	 */
	public static void main(String[] args) {

		// parse command line options
		OptionParser parser = new OptionParser("f:o:");
		OptionSet options = parser.parse(args);

		String inputFilename = null;
		String outFilename = null;
		// -f <inputFilename> specifies the file that contains edge list information to construct the initial graph with.
		if (options.hasArgument("f")) {
			inputFilename = (String) options.valueOf("f");
		}
		// -o <output filename> specifies the file where output is sent to.
		if (options.hasArgument("o")) {
			outFilename = (String) options.valueOf("o");
		}


		// non option arguments
		List<?> tempArgs = options.nonOptionArguments();
		List<String> remainArgs = new ArrayList<String>();
		for (Object object : tempArgs) {
			remainArgs.add((String) object);
		}


		// check number of non-option command line arguments
		if (remainArgs.size() > 1 || remainArgs.size() < 1) {
			System.err.println("Incorrect number of arguments.");
			usage(progName);
		}

		// parse non-option arguments
		String implementationType = remainArgs.get(0);


		// determine which graph implementation to test
		ContactsGraph graph = null;
		switch(implementationType) {
			case "adjlist":
				graph = new AdjacencyList();
				break;
			case "adjmat":
				graph = new AdjacencyMatrix();
				break;
			case "incmat":
				graph = new IncidenceMatrix();
				break;
			default:
				System.err.println("Unknown implmementation type.");
				usage(progName);
		}

		// construct SIRs model
		SIRModel sirModel = new SIRModel();


		// if file specified, then load file (assumed in Pajek .net format)
		if (inputFilename != null) {

			try {
				BufferedReader reader = new BufferedReader(new FileReader(inputFilename));

		    	String line;
		    	String delimiterRegex = "[ \t]+";
		    	String[] tokens;
		    	String srcLabel, tarLabel;

				// read in initial vertex line *Vertex XXX (XXX is number of vertices)
				int vertNum = -1;
				if ((line = reader.readLine()) != null) {
					tokens = line.trim().split(delimiterRegex);
					vertNum = Integer.parseInt(tokens[1]);
				}

				boolean bVertexPhrase = true;
		    	while ((line = reader.readLine()) != null) {
					// check if switch to edge phrase, which means line is *Edges
					if (line.compareTo("*Edges") == 0) {
						bVertexPhrase = false;
						continue;
					}
					// read in vertices
					if (bVertexPhrase) {
			    		tokens = line.trim().split(delimiterRegex);
						graph.addVertex(tokens[0]);
					}
					// otherwise in edge reading phrase
					else {
						tokens = line.trim().split(delimiterRegex);
						srcLabel = tokens[0];
			    		tarLabel = tokens[1];
						// add edge
						graph.addEdge(srcLabel, tarLabel);
					}
		    	}
			}
			catch (FileNotFoundException ex) {
				printErrorMsg("File " + args[1] + " not found.");
			}
			catch(IOException ex) {
				printErrorMsg("Cannot open file " + args[1]);
			}
		}

		// construct in and output streams/writers/readers, then process each operation.
		try {
			BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));

			// check if we need to redirect output to file
			PrintWriter outWriter = new PrintWriter(System.out, true);
			if (outFilename != null) {
				outWriter = new PrintWriter(new FileWriter(outFilename), true);
			}

			// process the operations
			processOperations(inReader, graph, sirModel, outWriter);
		} catch (IOException e) {
			printErrorMsg(e.getMessage());
		}

	} // end of main()

} // end of class RmitCovidModelling
