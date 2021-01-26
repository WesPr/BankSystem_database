public class Customer{
    private final String firstName;
    private final String lastName;
    private final String ssn;
    private final Account account;


    public Customer(String firstName, String lastName, String ssn, Account account){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.account = account;

    }

    @Override
    public String toString(){
        return "\nCustomer Information\n" +
                "First name " + firstName +
                "Last name " + lastName +
                "SSN: " + ssn +
                account;

    }

    public String basicInfo(){
        return "Name: " + firstName + " " + lastName + " |" +
                " SSN: " + ssn;
    }

    public Account getAccount(){
        return account;
    }
}