package CashRegister;
import ItemOptions.ItemOptions;
import ItemOptions.Item;

import java.util.ArrayList;
import java.util.Scanner;
public class UserInput {
    public Scanner readIn = new Scanner(System.in);
    String askUserFor; // This will be output before the user input.
    String informUserError; // This will be output if user did something wrong.
    public ItemOptions itemData;
    ArrayList<Item> items;



    // Constructor
    public UserInput()   {
        items = new ArrayList<>();
    }

    // Constructor 2
    public UserInput(String input, String error)   {
        setStrings(input, error);
    }

    /*
    ***********************
    *       METHODS       *
    ***********************
    */

    // Returns a positive integer
    public int readInt(String input, String error)    {
        setStrings(input, error);
        return Integer.parseInt(getNumber());
    }


    // Returns a String
    public String readString(String askInput, String error)  {
        String userInput;
        setStrings(askInput, error);

        do {
            System.out.print(this.askUserFor);
            userInput = readIn.nextLine();
            if(userInput == null || userInput.isBlank() || userInput.isEmpty())
                System.out.println(this.informUserError);
        } while(userInput == null || userInput.isBlank() || userInput.isEmpty());
        return userInput;
    }

    // Asks user for a String to add an optional comment for reviews
    public String readComment(String askInput) {
        String userInput;
        setInputMsg(askInput);
        System.out.println(this.askUserFor);
        userInput = readIn.nextLine();
        return userInput;
    }

    // Returns a positive double
    public double readDouble(String input, String error) {
        setStrings(input, error);
        return Double.parseDouble(getNumber());
    }

    public String readID(String input, String error)    {
        String id;
        boolean validInput;
        setStrings(input, error);
        do {
            System.out.print(this.askUserFor);
            id = readIn.nextLine();
            id = (id.startsWith("ID") ? id : "ID" + id); // Removes ID from input.
            validInput = isNumber(id.substring(2));
            if(validInput == false)
                System.out.println(this.informUserError);
        } while(validInput == false);
        return id;
    }


    // Gets user input as a number
    public String getNumber()   {
        String input;
        do {
            System.out.print(this.askUserFor);
            input = readIn.nextLine();
            if(isNumber(input) == false)
                System.out.println(this.informUserError);
        } while(isNumber(input) == false);
        return input;
    }

    // Checks if input is a positive number above 0.00
    public boolean isNumber(String number)   {
        if(number == null) {
            return false;
        }
        else if(number.startsWith("ID")) {
            number = number.substring(2, number.length());
        }
        // skipping else since return would stop the method.
        try {
            double stringToDouble = Double.parseDouble(number);
            if(stringToDouble < 0)
                return false;
        }
        catch(NumberFormatException error)  {
            return false;
        }
        return true;
    }

    // Gets userInput for menu and checks if it's valid.
    public int getUserOption(int maxValue, String input, String error)  {
        int userOption;
        setStrings(input, error);
        do {
            userOption = readInt(input, error);
            if(userOption > maxValue)
                System.out.println(error);
        } while(userOption < 0 || userOption > maxValue);
        return userOption;
    }

    // Sets inputMessage and outputError
    public void setStrings(String input, String error)  {
        this.askUserFor = input;
        this.informUserError = error;
    }
    public void setInputMsg(String input)  {
        this.askUserFor = input;
               }

    public String inputID(String input, String error)    {
        items = itemData.copyItems();
        String id;
        boolean checkExistance=false;

        do {
            id = readID(input, error);

            for(int i = 0; i < items.size(); i++)   {
                if(items.get(i).getId().equals(id)) {
                    checkExistance=true;
                }
            }

            if(!items.isEmpty() && !checkExistance) {
                System.out.println(error);
            }
            else    {
                checkExistance = false;
            }
        } while(checkExistance);
        String stringID= id;
        return stringID;
    }

    public String validID(){
        items = itemData.copyItems();
        boolean duplicate = true;
        String userIn;
        String valid="";
        do{
            userIn=readString("Please give ID of the item: ","You have given a wrong ID");

            for(int i =0;i<items.size();i++){
                if(userIn.equals(items.get(i).getId())){
                    valid=userIn;
                    duplicate=false;
                }
            }
            if(duplicate){
                System.out.println("You have given a non-existing ID, please try again!");
            }
            if(duplicate){
                for(int i =0;i<items.size();i++){
                    if(!userIn.equals(items.get(i).getId())){
                        duplicate=true;
                    }
                }
            }

        }while(duplicate);
        return valid;
    }

    public void closeScanner()   {
        readIn.close();
    }
}
