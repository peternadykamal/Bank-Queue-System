package sample.PersonPackage;

import sample.AccountPackage.Account;
import sample.DataController;
import sample.LoanPackage.Loan;

public class Teller extends Person{
    protected double salary;
    protected int working_hours;
    protected Client selectedClint;
    protected Account selectedAccount;
    protected Loan selectedLoan;

    public Teller(String firstName, String lastName, String phoneNumber, String address,
                  String password, double salary, int working_hours) {
        super(firstName, lastName, phoneNumber, address, password);
        this.salary = salary;
        this.working_hours = working_hours;
    }

    public Teller(int id, String firstName, String lastName, String phoneNumber, String address, String password, double salary, int working_hours) {
        super(id, firstName, lastName, phoneNumber, address, password);
        this.salary = salary;
        this.working_hours = working_hours;
    }

    public double getSalary() {
        return salary;
    }
    public int getWorking_hours() {
        return working_hours;
    }

    public boolean signInClint(int id,String password){
        DataController.startConnection();
        Client client = DataController.selectClint(id);
        DataController.startConnection();
        if(client != null){
            if(client.checkPassword(password)){
                selectedClint = client;
                return true;
            }
            else return false;
        }
        else return false;
    }
    public boolean signOutClint(){
        boolean flag = false;
        if(selectedClint != null){
            DataController.startConnection();
            flag = DataController.updateClint(selectedClint);
            DataController.closeConnection();
            selectedClint = null;
        }
        return flag;
    }
    public double checkBalance(int accountId){
        if(selectedClint != null){
            for (Account account : selectedClint.getAccounts()) {
                if(account.accountId == accountId){
                    return account.getBalance();
                }
            }
        }
        return 0;
    }

    @Override
    public String[] getProperties() {
        String[] array = {firstName,lastName,
                phoneNumber,address,
                password, String.valueOf(salary), String.valueOf(working_hours),"teller"};
        return array;
    }
}
