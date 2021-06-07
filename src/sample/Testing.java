package sample;

import com.github.javafaker.Faker;
import sample.AccountPackage.Account;
import sample.AccountPackage.MoneyMarketAccount;
import sample.AccountPackage.SavingsAccount;
import sample.LoanPackage.BusinessLoan;
import sample.LoanPackage.Loan;
import sample.LoanPackage.PersonalLoan;
import sample.PersonPackage.Client;
import sample.PersonPackage.Moderator;
import sample.PersonPackage.Teller;

import java.util.ArrayList;
import java.util.Random;

public class Testing {
    public static void savingsAccount(){
        SavingsAccount account = new SavingsAccount(500);
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());
        System.out.println(account.withdraw(500));
        System.out.println(account.getBalance());

        System.out.println(account.withdraw(500));
        System.out.println(account.getBalance());

        account.deposit(500);
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());

        Main.TIME_MACHINE=30;
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());

        account.deposit(1500);
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());
        Main.TIME_MACHINE+=60;
        System.out.println(account.getBalance());

        for (int i=0;i<7;i++){
            System.out.println(account.withdraw(50));
        }
        System.out.println(account.getBalance());
        Main.TIME_MACHINE+=5;
        System.out.println(account.getBalance());
        for (int i=0;i<7;i++){
            System.out.println(account.withdraw(500));
        }
        System.out.println(account.getBalance());
    }
    public static void moneyMarketAccount(){
        MoneyMarketAccount account = new MoneyMarketAccount(500);
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());
        System.out.println(account.withdraw(500));
        System.out.println(account.getBalance());

        System.out.println(account.withdraw(500));
        System.out.println(account.getBalance());

        account.deposit(1000);
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());

        Main.TIME_MACHINE=30;
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());

        account.deposit(2000);
        System.out.println(account.getBalance());
        System.out.println(account.isPenaltyUnderMin());
        Main.TIME_MACHINE+=60;
        System.out.println(account.getBalance());

        for (int i=0;i<7;i++){
            System.out.println(account.withdraw(50));
        }
        System.out.println(account.getBalance());
        Main.TIME_MACHINE+=5;
        System.out.println(account.getBalance());
        for (int i=0;i<7;i++){
            System.out.println(account.withdraw(500));
        }
        System.out.println(account.getBalance());
    }
    public static void testInsertAccount(){
        Faker faker = new Faker();

        Client client = new Client(faker.name().firstName(),faker.name().lastName(),Testing.randomPhoneNumber(),faker.address().city()+", "+faker.address().country(),"3210");
        System.out.println(DataController.startConnection());
        DataController.insertClint(client);
        System.out.println(DataController.closeConnection());
        System.out.println(client.id);

        MoneyMarketAccount account = new MoneyMarketAccount(500);
        System.out.println(account.withdraw(500));
        System.out.println(account.withdraw(500));
        account.deposit(3600);
        System.out.println(account.withdraw(500));

        System.out.println(DataController.startConnection());
        DataController.insertAccount(account,client);
        System.out.println(DataController.closeConnection());
    }
    public static void testSelectClient(){
        System.out.println(DataController.startConnection());
        Client client = DataController.selectClint(5);
        String[] array = client.getProperties();
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
        ArrayList<Account> accounts = client.getAccounts();
        for (Account x : accounts) {
            if (x instanceof MoneyMarketAccount) {
                System.out.println("MoneyMarketAccount");
                System.out.println(x.accountId);
                System.out.println(x.getBalance());
            }
            else if(x instanceof SavingsAccount){
                System.out.println("SavingsAccount");
                System.out.println(x.accountId);
                System.out.println(x.getBalance());
            }
        }
        System.out.println(DataController.closeConnection());
    }
    public static void addingAccountsTodb(){
        System.out.println(DataController.startConnection());
        for (int i = 5; i < 10; i++) {
            Client client = DataController.selectClint(i+1);
            int accountsNumber = Testing.randomNumber(3,0);
            for (int j = 0; j < accountsNumber; j++) {
                int accountType = Testing.randomNumber(2,0);
                int isUnderMin = Testing.randomNumber(2,0);
                MoneyMarketAccount moneyMarketAccount;
                SavingsAccount savingsAccount;
                if(accountType == 0){//moneyMarket
                    if(isUnderMin==0){
                        moneyMarketAccount = new MoneyMarketAccount(2600);
                    }
                    else {
                        moneyMarketAccount = new MoneyMarketAccount(1500);
                    }
                    DataController.insertAccount(moneyMarketAccount,client);
                }
                else {//savings
                    if(isUnderMin==0){
                        savingsAccount = new SavingsAccount(1600);
                    }
                    else {
                        savingsAccount = new SavingsAccount(500);
                    }
                    DataController.insertAccount(savingsAccount,client);
                }
            }
        }
        System.out.println(DataController.closeConnection());
    }
    public static void addingLoansTodb(){
        System.out.println(DataController.startConnection());
        for (int i = 5; i < 10; i++) {
            Client client = DataController.selectClint(i+1);
            int accountsNumber = Testing.randomNumber(3,0);
            for (int j = 0; j < accountsNumber; j++) {
                int loanType = Testing.randomNumber(2,0);
                MoneyMarketAccount moneyMarketAccount;
                SavingsAccount savingsAccount;
                if(loanType == 0){//personal
                    PersonalLoan personalLoan = new PersonalLoan(1000,5);
                    DataController.insertLoan(personalLoan,client);
                }
                else {//Business
                    BusinessLoan businessLoan = new BusinessLoan(15000,24);
                    DataController.insertLoan(businessLoan,client);
                }
            }
        }
        System.out.println(DataController.closeConnection());
    }
    public static void testSelectAccountList(){
        System.out.println(DataController.startConnection());
        ArrayList<Account> accounts /*= DataController.selectAccountList(2)*/ = null;
        for (Account x : accounts) {
            if (x instanceof MoneyMarketAccount) {
                System.out.println("MoneyMarketAccount");
                System.out.println(x.accountId);
                System.out.println(x.getBalance());
            }
            else if(x instanceof SavingsAccount){
                System.out.println("SavingsAccount");
                System.out.println(x.accountId);
                System.out.println(x.getBalance());
            }
        }
        System.out.println(DataController.closeConnection());
    }
    public static void testSelectLoanList(){
        System.out.println(DataController.startConnection());
        ArrayList<Loan> loans = null/*= DataController.selectLoanList(8)*/;
        for (Loan x : loans) {
            if (x instanceof BusinessLoan) {
                System.out.println("BusinessLoan");
                System.out.println(x.loanID);
                System.out.println(x.getLoanedAmount());
            }
            else if(x instanceof PersonalLoan){
                System.out.println("PersonalLoan");
                System.out.println(x.loanID);
                System.out.println(x.getLoanedAmount());
            }
        }
        System.out.println(DataController.closeConnection());
    }
    public static void testSelectTeller(){
        System.out.println(DataController.startConnection());
        Teller teller = DataController.selectTeller(44);
        System.out.println(teller);
        if(teller!=null){
            String[] array = teller.getProperties();
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]+" ");
            }
            System.out.println();
        }
        System.out.println(DataController.closeConnection());
    }
    public static void testSelectModerator(){
        System.out.println(DataController.startConnection());
        Moderator moderator = DataController.selectModerator(35);
        System.out.println(moderator);
        if(moderator !=null){
            String[] array = moderator.getProperties();
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]+" ");
            }
            System.out.println();
        }
        System.out.println(DataController.closeConnection());
    }
    public static int randomNumber(int max,int min){
        //if max=5 ,min=0 , the numbers range is {4,3,2,1,0}
        if(max>=min){
            Random r = new Random();
            return r.nextInt(max-min) + min;
        }
        else return 0;
    }
    public static String randomPhoneNumber(){
        int x = randomNumber(4,0);//010,011,012,015
        String number="";
        for (int i = 0; i < 8; i++) {
            number = number + randomNumber(10,0);
        }
        if(x==0) return "010" + number;
        else if (x==1) return "011" + number;
        else if(x==2) return  "012" + number;
        else return  "015" + number;
    }
}
