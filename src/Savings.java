public class Savings extends Account{
    private static String accountType = "Savings";

    Savings(int accountNumber, double initialDeposit){
        super(accountNumber);
        this.setBalance(initialDeposit);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.Savings;
    }
}
