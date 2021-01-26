public class Account {
    private double balance = 0;
    private int accountNumber;
    private static int numberOfAccounts = 1003467900;

    public Account(){
        this.accountNumber = numberOfAccounts++;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getBalance() {
        return balance;
    }


    public int getAccountNumber() {
        return accountNumber;
    }

    public void withdraw(double amount){
        if(amount > balance){
            System.out.println("You have insufficient funds.");
            return;
        }
        balance -= amount;
        System.out.println("You have withdrawn €" + amount);
        System.out.println("Balance is " + balance);
    }

    public void deposit(double amount){
        if(amount <= 0){
            System.out.println("You cant deposit negative or zero money");
            return;
        }
        balance += amount;
        System.out.println("You have deposited €" + amount);
        System.out.println("Balance is " + balance);
    }

}


