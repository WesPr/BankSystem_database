public abstract class Account {
    private double balance = 0;
    private int accountNumber;

    Account(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public abstract AccountType getAccountType();

    @Override
    public String toString(){
        return "Account Type: " + getAccountType().name() + " Account\n" +
                "Account Number: " + this.getAccountNumber() + "\n" +
                "Balance: " + this.getBalance() + "\n";
    }
    public double getBalance() {
        return balance;
    }

    public final void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

}


