package sample.PersonPackage;

import sample.AccountPackage.Account;
import sample.LoanPackage.Loan;

import java.util.ArrayList;

public class Client extends Person{
    protected ArrayList<Account> accounts = new ArrayList<>();
    protected ArrayList<Loan> loans = new ArrayList<>();

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public ArrayList<Loan> getLoans() {
        return loans;
    }


    public Client(int id, String firstName, String lastName, String phoneNumber, String address,
                  String password,ArrayList<Account> accounts,ArrayList<Loan> loans) {
        super(id, firstName, lastName, phoneNumber, address, password);
        this.accounts = accounts;
        this.loans = loans;
    }

    public Client(String firstName, String lastName, String phoneNumber, String address, String password) {
        super(firstName, lastName, phoneNumber, address, password);
    }

    public void addAccount(Account account){
        accounts.add(account);
    }
    public boolean removeAccount(int accountId){
        boolean flag = false;
        for (int i = 0; i< accounts.size(); i++){
            if(accounts.get(i).getAccountId()==accountId){
                accounts.remove(i);
                flag = true;
            }
        }
        return flag;
    }

    public void addLoan(Loan loan){
        loans.add(loan);
    }
    public boolean removeLoan(int loanID){
        boolean flag = false;
        for (int i=0;i<loans.size();i++){
            if(loans.get(i).getLoanID() == loanID){
                loans.remove(i);
                flag = true;
            }
        }
        return flag;
    }
    public boolean checkClintPrivileges(){
        //if return value is true then there is no account under min
        boolean flag = true;
        if(checkLoansState() == false) flag = false;
        for (Account account : accounts) {
            if(account.isPenaltyUnderMin() == true){
                flag = false;
                break;
            }
        }
        if(flag==false){
            for (Account account : accounts) {
                account.accountState = false;
            }
        }
        else if(flag==true){
            for (Account account : accounts) {
                account.accountState = true;
            }
        }
        return flag;
    }
    public boolean checkLoansState(){
        boolean flag = true;
        for (Loan loan : loans) {
            if (loan.checkPenalty() == true) {
                flag = false;
                break;
            }
        }
        return flag;
    }
    public Account getAccount(int accountID){
        for (Account account : accounts) {
            if (account.accountId == accountID) return account;
        }
        return null;
    }
    public Loan getLoan(int loanID){
        for (Loan loan : loans) {
            if(loan.loanID == loanID) return loan;
        }
        return null;
    }
    public String[] getProperties(){
        String[] array = {firstName,lastName,
                phoneNumber,address,
                password,"client"};
        return array;
    }
}
