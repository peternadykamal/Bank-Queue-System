package sample.PersonPackage;

import sample.AccountPackage.Account;
import sample.AccountPackage.MoneyMarketAccount;
import sample.AccountPackage.SavingsAccount;
import sample.DataController;
import sample.LoanPackage.BusinessLoan;
import sample.LoanPackage.Loan;
import sample.LoanPackage.PersonalLoan;

public class Moderator extends Teller{
    public Moderator(String firstName, String lastName, String phoneNumber, String address,
                     String password, double salary, int working_hours) {
        super(firstName, lastName, phoneNumber, address, password, salary, working_hours);
    }

    public Moderator(int id, String firstName, String lastName, String phoneNumber, String address,
                     String password, double salary, int working_hours) {
        super(id, firstName, lastName, phoneNumber, address, password, salary, working_hours);
    }

    public boolean createClint(String firstName, String lastName, String phoneNumber,
                               String address, String password){
        Client client = new Client(firstName,lastName,phoneNumber,address,password);
        DataController.startConnection();
        boolean flag = DataController.insertClint(client);
        DataController.closeConnection();
        return flag;
    }
    public boolean removeClint(int clientId){
        boolean flag = false;
        DataController.startConnection();
        Client client = DataController.selectClint(clientId);
        if(client != null){
            flag = DataController.dropPerson(client);
        }
        DataController.closeConnection();
        return flag;
    }
    public boolean updateClint(){
        boolean flag = false;
        DataController.startConnection();
        if(selectedClint != null){
            flag = DataController.updateClint(selectedClint);
        }
        DataController.closeConnection();
        return flag;
    }

    public boolean createAccount(String type, double balance){
        boolean flag = true;
        if(selectedClint!=null){
            DataController.startConnection();
            if(type.equals("MoneyMarketAccount")){
                MoneyMarketAccount account = new MoneyMarketAccount(balance);
                flag = DataController.insertAccount(account,selectedClint);
                selectedClint.addAccount(account);
                updateClint();
            }
            else if(type.equals("SavingsAccount")){
                SavingsAccount account = new SavingsAccount(balance);
                flag = DataController.insertAccount(account,selectedClint);
                selectedClint.addAccount(account);
                updateClint();
            }
            DataController.closeConnection();
        }
        return flag;
    }
    public boolean removeAccount(int accountID){
        boolean flag = false;
        if (selectedClint!=null){
            Account account = selectedClint.getAccount(accountID);
            if(account!=null){
                DataController.startConnection();
                flag = DataController.dropAccount(account);
                DataController.closeConnection();
                selectedClint.removeAccount(accountID);
                updateClint();
            }
        }
        return flag;
    }
    public boolean updateAccount(int accountID){
        boolean flag = false;
        if(selectedClint!=null){
            Account account = selectedClint.getAccount(accountID);
            DataController.startConnection();
            if(account != null){
                flag = DataController.updateAccount(account,selectedClint);
            }
            DataController.closeConnection();
        }
        return flag;
    }
    public void openAccount(int accountId){
        if(selectedClint!=null){
            Account account = selectedClint.getAccount(accountId);
            selectedAccount = account;
        }
    }

    public boolean createLoan(String type,int loanedAmount,int timePeriod) {
        boolean flag = true;
        if (selectedClint != null) {
            DataController.startConnection();
            if (type.equals("BusinessLoan")) {
                BusinessLoan loan = new BusinessLoan(loanedAmount, timePeriod);
                flag = DataController.insertLoan(loan, selectedClint);
                selectedClint.addLoan(loan);
                updateClint();
            } else if (type.equals("PersonalLoan")) {
                PersonalLoan loan = new PersonalLoan(loanedAmount, timePeriod);
                flag = DataController.insertLoan(loan, selectedClint);
                selectedClint.addLoan(loan);
                updateClint();
            }
            DataController.closeConnection();
        }
        return flag;
    }
    public boolean removeLoan(int loanID){
        boolean flag = false;
        if (selectedClint!=null){
            Loan loan = selectedClint.getLoan(loanID);
            if(loan!=null){
                DataController.startConnection();
                flag = DataController.dropLoan(loan);
                DataController.closeConnection();
                selectedClint.removeLoan(loanID);
                updateClint();
            }
        }
        return flag;
    }
    public boolean updateLoan(int loanID){
        boolean flag = false;
        if(selectedClint!=null){
            Loan loan = selectedClint.getLoan(loanID);
            DataController.startConnection();
            if(loan != null){
                flag = DataController.updateLoan(loan,selectedClint);
            }
            DataController.closeConnection();
        }
        return flag;
    }
    public void openLoan(int loanID){
        if(selectedClint!=null){
            Loan loan = selectedClint.getLoan(loanID);
            selectedLoan = loan;
        }
    }

    public void depositMoney(int Amount,int accountid ) {
        DataController.startConnection();
        // Account a = selectedClint.getAccount(accountid);
        if(selectedClint.getAccount(accountid) instanceof MoneyMarketAccount)
        {
            ((MoneyMarketAccount) selectedClint.getAccount(accountid)).deposit(Amount);
        }

        else if(selectedClint.getAccount(accountid) instanceof SavingsAccount)
        {
            ((SavingsAccount) selectedClint.getAccount(accountid)).deposit(Amount);
        }
        updateClint();
    }
    public void withdrawMoney(int Amount,int accountid) {
        if(selectedClint.getAccount(accountid) instanceof MoneyMarketAccount)
        {
            ((MoneyMarketAccount) selectedClint.getAccount(accountid)).withdraw(Amount);
        }

        else if(selectedClint.getAccount(accountid) instanceof SavingsAccount)
        {
            ((SavingsAccount) selectedClint.getAccount(accountid)).withdraw(Amount);
        }
        updateClint();

    }
    public void payLoan(int months,int loanid) {
        selectedClint.getLoan(loanid).payLoan( months);
        updateClint();

    }
    public int timesRemaingForLoan(int loanid) {

        int x = selectedClint.getLoan(loanid).getTimePeriod();
        int y = selectedClint.getLoan(loanid).getPaidMonths() ;

        return x-y;
    }

    public String[] getProperties() {
        String[] array = {firstName,lastName,
                phoneNumber,address,
                password, String.valueOf(salary),
                String.valueOf(working_hours),"moderator"};
        return array;
    }
}
