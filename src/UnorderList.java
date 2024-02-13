import java.util.Scanner;
import java.io.*;

public class UnorderList
{
    private String data[];         // Reference for data array
    private int numElems;          // Number of actual data elements stored in array
    private File inputfile;        // Reference for input file
    private boolean listStatusOK;  // Status of relative to file input

    public static final int LIST_CAPACITY = 1000;  // Default max size of array

    /**
     * Constructor
     * @param   filename    string value of local filename containing data
     */
    public UnorderList(String filename)
    {
        listStatusOK = false;     // Initialize to not OK until file loaded

        // Create file input object and attempt to instantiate scanner object 
        try
        {
            Scanner inputFileScanner;
            File inputfile;
            inputfile = new File(filename);  // Create file input object
            inputFileScanner = new Scanner(inputfile);

            data = new String[LIST_CAPACITY];

            // File processing loop - Continue while not empty
            // Read file element and store in array
            int i = 0;
            while (inputFileScanner.hasNext())
            {
                data[i] = inputFileScanner.next();   // Capture next string
                i++;                                 // Advance array marker
            }
            numElems = i;

            if (numElems > 0)                        // Declare list file input OK
                listStatusOK = true;

            inputFileScanner.close();   // Close file
        }
        catch (IOException e)  // If file error, report to console and crash
        {
            listStatusOK = false;
        }
    }

    /**
     * Accessor for list file status
     * @return   boolean indicating status of file.  True if file loaded OK; false otherwise.
     */
    public boolean statusOK()
    {
        return listStatusOK;
    }

    /**
     * Observer returning whether or not list is full
     * @return   true if list is full, false otherwise
     */
    public boolean isFull()
    {
        if (numElems == LIST_CAPACITY)
            return true;
        else
            return false;
    }

    /**
     * Insert new element into the list.
     * @param   New element to be added to the list
     * @pre     List array has at least one more position available
     */
    public void insert(String newElement)
    {
        data[numElems] = newElement;
        numElems++;
    }

    /**
     * Delete existing element into the list.  If element is not in list, nothing is done.
     * @param   Element to be deleted
     */
    public void delete(String oldElement)
    {
        int bottomElementIndex = numElems-1;

        int i = 0;
        while (i < numElems)
        {
            if (data[i].equals(oldElement))
            {
                data[i] = data[bottomElementIndex];
                numElems--;
            }
            i++;
        }
    }

    /**
     * The sequentialSearch method searches an array for a specific string.
     *
     * @param target  String to search for in list
     * @return The subscript of the value if found in the array, otherwise -1.
     */
    public int search(String target)
    {
        int index;        // Loop control variable
        int element;      // Position the value is found at
        boolean found;    // Flag indicating search results

        index = 0;        // Start search at index zero
        element = -1;     // Set to default values;
        found = false;    // assuming not found

        // Begin search of array from index zero forward.  
        // Search while not found and not yet at end of list
        while (!found && index < numElems) {
            if (data[index].equals(target)) // If found
            {
                found = true;               //   reset to terminate search
                element = index;            //   retain index of target value
            }
            index++;                        // Otherwise, advance to next element
        }

        return element;
    }

    /**
     * Return list as a string with elements separated by line feeds.
     */
    public String toString()
    {
        String outString = "";
        for (int i = 0; i < numElems; i++)
            outString += data[i] + "\n";

        return outString;
    }

    /**
     * This method receives an array of floating point values (double) 
     // sends it to the output file (declared as constant).
     *
     * @param outputfilename  String containing output file name
     */
    public void writeToFile(String outputfilename)
    {
        try
        {
            // Delclare and instantiate output writer object
            PrintWriter outputfileWriter;
            outputfileWriter = new PrintWriter(outputfilename);

            // Write data to file
            for (int i = 0; i < numElems; i++)
            {
                outputfileWriter.println(data[i]);
            }
            outputfileWriter.close();
        }
        catch (IOException e)
        {
            listStatusOK = false;
        }
    }

    public void insertAt(String s, int p) {
        if (p < 0 || p >= numElems || numElems == LIST_CAPACITY) {
            return;
        }

        for (int i = numElems; i > p; i--) {
            data[i] = data[i - 1];
        }

        data[p] = s;
        numElems++;
    }

}