import java.sql.SQLException;
import java.util.ArrayList;

public class Bank {

    private DbService database = new DbService();

    Customer openAccount(String firstName, String lastName, String ssn, AccountType type, Double balance) throws SQLException {
        int accountId = database.AddAccount(firstName, lastName, ssn, type, balance);
        Customer customer = database.GetAccount(accountId);
        return customer;
    }

    boolean closeAccount(int accountId) throws SQLException {
        return database.DeleteAccount(accountId);
    }

    Customer getCustomer(int accountId) throws SQLException {
        return database.GetAccount(accountId);
    }

    ArrayList<Customer> getCustomers() throws SQLException {
        return database.GetAllAccounts();
    }

    void withdraw(int accountId, double amount) throws InsufficientFundsException, SQLException {
        Customer customer = getCustomer(accountId);
        if (amount > customer.getAccount().getBalance()) {
            throw new InsufficientFundsException();
        }
        double newBalance = customer.getAccount().getBalance() - amount;
        database.UpdateAccount(accountId, newBalance);
    }

    void deposit(int accountId, double amount) throws InvalidAmountException, SQLException {
        Customer customer = getCustomer(accountId);
        if (amount <= 0) {
            throw new InvalidAmountException();
        }
        double amountToDeposit = amount;
        database.UpdateAccount(accountId, customer.getAccount().getBalance() + amountToDeposit);
    }
}
