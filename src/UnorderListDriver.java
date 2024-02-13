import javax.swing.JOptionPane;

// CST-283
// Aaron Pelto
// Winter 2024

// Add the following method to the UnorderList class: insertAt(String s, int p)
// Method to insert a String element s at position p in the list (with position zero being the first element).
// if p is negative, or equal or greater than the number of elements in the list, the method should make no changes.
// The order for all subsequent elements should be unchanged. Any elements that are displaced by the insert should be moved down one position.
public class UnorderListDriver
{
    public static void main(String[] args)
    {
        UnorderList theList = new UnorderList("data.txt");

        // Check of list constructored OK and report
        if (theList.statusOK() == true)
        {
            JOptionPane.showMessageDialog(null,"Initial list:\n" + theList.toString());

            theList.delete("Field,Gwendolyn");
            JOptionPane.showMessageDialog(null,"After delete:\n" + theList.toString());

            theList.insert("Doe,Jane");
            JOptionPane.showMessageDialog(null,"After insert:\n" + theList.toString());

            String targetString = "Barnett,Peter";
            int position = theList.search(targetString);
            JOptionPane.showMessageDialog(null,targetString + " found at position " +
                    position);

            // Test #1
            theList.insertAt("Doe,John", 2);
            JOptionPane.showMessageDialog(null,"After insertAt:\n" + theList.toString());

            theList.writeToFile("listoutput.txt");
        }
        else
            JOptionPane.showMessageDialog(null,"Problem with File");

        // Test #2
        insertAtPosition(theList, "Doe,John", 2);

        System.exit(0);
    }

    // Test #2
    public static void insertAtPosition(UnorderList list, String s, int p) {
        list.insertAt(s, p);
        JOptionPane.showMessageDialog(null,"After insertAt:\n" + list.toString());
    }

}