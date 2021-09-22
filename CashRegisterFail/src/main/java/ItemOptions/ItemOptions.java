package ItemOptions;
import CashRegister.SystemOutput;
import CashRegister.UserInput;

public class ItemOptions {
    SystemOutput sysOut;
    UserInput readIn;

    // Constructor
    public ItemOptions() {
        sysOut = new SystemOutput();
        readIn = new UserInput();
        int input;

        sysOut.itemMenu(); // Prints Item Options menu.
        input = readIn.readInt( "Type an option: ", "Incorrect input!"); // Gets only numbers

        String Burak = "Askan Burak";
        Burak.substring(0, 4);
    }

    /*
     ***********************
     *       METHODS       *
     ***********************
     */



}
