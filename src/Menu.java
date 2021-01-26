import java.util.ArrayList;
import java.util.Scanner;

public class Menu{
    //Instance variables.
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public static void main(String[] args) {
        //Main instance.
        Menu menu = new Menu();
        menu.runMenu();
    }
    // Method prints header, while loop runs until exit is True, prints menu,
    // calls getInput method and passes assigned variable to performAction method.
    public void runMenu(){
        printHeader();
        while (!exit){
            printMenu();
            int choice = getInput();
            performAction(choice);
        }
    }
    //Method prints generic welcome message upon start of program.
    private void printHeader() {
        System.out.println("+----------------+");
        System.out.println("|    Welcome     |");
        System.out.println("+----------------+");
    }
    //Method prints generic selection menu.
    private void printMenu() {
        System.out.println("+--------------------------+");
        System.out.println("| Please make a selection: |");
        System.out.println("|  ----------------------  |");
        System.out.println("| 1: Create a new Account. |");
        System.out.println("| 2: Make a deposit.       |");
        System.out.println("| 3: Make a withdrawal.    |");
        System.out.println("| 4: List account balance. |");
        System.out.println("| 5: Exit.                 |");
        System.out.println("+--------------------------+");

    }
    //Method uses try catch block to assign integer from keyboard and return it, print error if anything other than integer
    //and print error if number is outside of 1-5 using an if statement.
    //Do-while loop to ensure it is run at least once.
    private int getInput() {
        int choice = 0;
        do {
                try{
                    choice = Integer.parseInt(keyboard.nextLine());
                }
                catch (NumberFormatException e){
                    System.out.println("Invalid selection. Numbers only");
                }
                if (choice < 1 || choice > 5){
                    System.out.println("Invalid selection. Enter a number from 1 - 5 please");
                }
        }while (choice < 1 || choice > 5);
        return choice;
    }
    //Method uses inputted parameter to pass to a switch statement, toggling through menu.
    private void performAction(int choice) {
        switch (choice){
            case 1:
                createAccount();
                break;
            case 2:
                makeDeposit();
                break;
            case 3:
                makeWithdrawal();
                break;
            case 4:
                listAccountBalances();
                break;
            case 5:
                System.out.println("Goodbye.");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown error");
                break;

        }
    }

    //Method to create cheque or savings account with customer details, this will determine initialDeposit required.
    private void createAccount() {
        String firstName = " ", lastName = " ", ssn = " ", accountType =" ";
        double initialDeposit = 0;

        boolean valid = false;
        while(!valid){
            System.out.print("Please enter an account type(Cheque/Savings): ");
            accountType = keyboard.nextLine();
            if(accountType.equalsIgnoreCase("Cheque") || accountType.equalsIgnoreCase("Savings")){
                valid = true;
            }
            else{
                System.out.println("Invalid, please choose Cheque or Savings.");
            }
        }

        valid = false;
        while(!valid) {
            System.out.print("Please enter your first name: ");
            firstName = keyboard.nextLine();
            if(firstName.isEmpty())
                System.out.println("First name cant be empty");
            else{
                valid = true;
            }
        }

        valid = false;
        while(!valid) {
            System.out.print("Please enter your last name: ");
            lastName = keyboard.nextLine();
            if(lastName.isEmpty())
                System.out.println("Last name cant be empty");
            else{
                valid = true;
            }
        }

        //Check whether SSN has already been used.
        valid = false;
        while(!valid){
            ArrayList<Customer> customers = bank.getCustomers();
            boolean ssnValid = false;
            while(!ssnValid) {
                System.out.print("Please enter your social security number: ");
                ssn = keyboard.nextLine();
                if(ssn.isEmpty())
                    System.out.println("SSN cant be empty");
                else{
                    ssnValid = true;
                }
            }
            if(customers.size() > 0) {
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getSsn().equals(ssn)) {
                        System.out.println("Duplicate SSN");
                    }
                    else{
                        valid = true;
                    }
                }
            }else
                valid = true;
            }
        valid = false;
        while(!valid){
            System.out.print("Please enter initial deposit: ");
            try{
                initialDeposit = Double.parseDouble(keyboard.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Invalid, please enter a numeric number.");
            }
            if (accountType.equalsIgnoreCase("Cheque")){
                if (initialDeposit < 50){
                    System.out.println("Cheque accounts require initial deposits of €50");
                }
                else {
                    valid = true;
                }
            } else if (accountType.equalsIgnoreCase("Savings")){
                if (initialDeposit < 100){
                    System.out.println("Cheque accounts require initial deposits of €100");
                }
                else {
                    valid = true;
                }
            }
        }
        //Account instance created for either cheque child or savings child using if statement.
        Account account;
        if (accountType.equalsIgnoreCase("Cheque")){
            account = new Cheque(initialDeposit);
        }
        else{
            account = new Savings(initialDeposit);
        }
        //Customer instance created, account passed as parameter.
        Customer customer = new Customer(firstName, lastName, ssn, account);
        //Customer added to bank arraylist.
        bank.addCustomer(customer);
    }
    //Method to make deposit per customer selected.
    private void makeDeposit(){
        int account = selectAccount();
        if (account >= 0) {
            System.out.print("How much would you like to deposit?: ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            //retrieves customer from arraylist using getCustomer(Bank class), gets their account from Customer class
            //and deposits using deposit function from Account class.
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }
    //Method to make withdrawal per customer selected.
    private void makeWithdrawal(){
        int account = selectAccount();
        if (account >= 0) {
            System.out.print("How much would you like to withdraw?: ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            //retrieves customer from arraylist using getCustomer(Bank class), gets their account from Customer class
            //and withdraws using withdraw function from Account class.
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
    }

    //Method uses an arraylist of customers from called method getCustomers in Bank class. -1 returned for all errors.
    private int selectAccount(){
        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <= 0){
            System.out.println("No customers");
            return -1;
        }
        System.out.println("Select an account: ");
        //for loop prints out customers basic info using basicInfo from Customer class.
        for(int i = 0; i < customers.size(); i++){
            System.out.println((i+1) + ") " + customers.get(i).basicInfo());
        }
        int account = 0;
        System.out.print("Please enter your selection: ");
        try{
            //-1 from input due to indexing starting from 0.
            account = Integer.parseInt(keyboard.nextLine()) - 1;
        }
        catch (NumberFormatException e){
            account = -1;
        }
        if (account < 0 || account > customers.size()){
            System.out.println("Invalid account selected");
            account = -1;
        }
        return account;
    }
    //Method assigns selectAccount method to account variable, then calls getCustomer method from Bank class and getAccount from Customer class.
    private void listAccountBalances() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println(bank.getCustomer(account).getAccount());
        }
    }


}