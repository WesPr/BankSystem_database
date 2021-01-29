public class Cheque extends Account{
    private static String accountType = "Cheque";

    Cheque(int accountNumber, double initialDeposit){
        super(accountNumber);
        this.setBalance(initialDeposit);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.Cheque;
    }
}
