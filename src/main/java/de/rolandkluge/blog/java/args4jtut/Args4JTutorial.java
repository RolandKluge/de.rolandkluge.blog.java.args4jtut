package de.rolandkluge.blog.java.args4jtut;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

/**
 * Tiny application showcasing the core abilities of Args4J.
 *
 * @author Roland Kluge
 *
 */
public class Args4JTutorial
{

    public static void main(final String[] args)
    {
        final Args4JTutorial application = new Args4JTutorial();
        application.configure(args);
    }

    @Option(name = "-o", aliases = "--output", required = true, usage = "Target/output file")
    private File outputFile;

    @Option(name = "-c", aliases = "--config", required = false, usage = "Configuration parameter (may be used multiple times)")
    private List<String> configurationParameters;

    @Option(name = "-h", aliases = "--help", required = false, usage = "Print help text")
    private boolean printHelp = false;

    // All non-option arguments may be treated with @Argument
    @Argument
    private List<File> inputFiles = new ArrayList<File>();

    /**
     * Configure application and return an appropriate exit code in order to signal if an error
     * occurred or the application shall be terminated as only the help message has been printed.
     *
     * @param args
     *            the program options
     * @return the "exit code" of the paring process
     */
    public int configure(final String[] args)
    {
        final CmdLineParser parser = new CmdLineParser(this);
        parser.setUsageWidth(80);

        try {
            parser.parseArgument(args);

        }
        catch (final CmdLineException e) {

            if (this.printHelp) {
                System.err.println("Usage:");
                parser.printUsage(System.err);
                System.err.println();
                return 2;
            }
            else {

                System.err.println(e.getMessage());
                parser.printUsage(System.err);
                System.err.println();
                return 3;
            }
        }

        /*
         * Output variables for debugging
         */
        System.err.println("Output file: " + this.outputFile);
        System.err.println("Input files: " + this.inputFiles);
        System.err.println("Configuration: " + this.configurationParameters);

        return 0;
    }

}
