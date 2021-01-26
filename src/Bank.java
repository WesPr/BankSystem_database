import java.util.ArrayList;

public class Bank {

    ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public Customer getCustomer(int account){
        return customers.get(account);
    }

    public ArrayList<Customer> getCustomers(){
        return customers;
    }

}
