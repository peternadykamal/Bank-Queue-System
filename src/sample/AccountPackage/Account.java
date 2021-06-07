package sample.AccountPackage;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class Account {
    protected int accountId;
    protected ArrayList<AccountOperation> accountHistory = new ArrayList<>();
    private double balance;
    protected int minBalance;
    public boolean accountState = true;
    //accountState = false in all accounts when one of accounts have penaltyUnderMin
    protected boolean penaltyUnderMin=true;
    protected double  interestRate;
    private LocalDateTime lastAddedInterest;
    private double forEvery = 3.0; //add interest for every 3 min.

    public int getAccountId() {
        return accountId;
    }
    public ArrayList<AccountOperation> getAccountHistory() {
        return accountHistory;
    }
    public int getMinBalance() {
        return minBalance;
    }
    public boolean isPenaltyUnderMin() {
        return penaltyUnderMin;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public double getBalance() {
        addInterest();
        return balance;
    }
    protected void setBalance(double balance) {
        this.balance = balance;
        if (getBalance() >= minBalance) penaltyUnderMin=false;
        else
        {
            penaltyUnderMin=true;
            //accountState= false;
        }
    }

    public Account(){
        lastAddedInterest = LocalDateTime.now();
    }

    private void addInterest(){
//        long diff = ChronoUnit.MONTHS.between(lastAddedInterest.withDayOfMonth(1), LocalDateTime.now().withDayOfMonth(1));
//        to get the difference between two dates in months
//        int N = (int) Math.floor(diff);
//        if (N>0){
//            balance = balance*Math.pow((1+interestRate),N);
//            lastAddedInterest = lastAddedInterest.plusMonths(N);
//        }


        long diff = ChronoUnit.MINUTES.between(lastAddedInterest, LocalDateTime.now());
        //get the difference between two dates
        int N = (int) Math.floor(diff/forEvery);
        if (N>0){
            if(penaltyUnderMin == false && accountState == true) balance = balance*Math.pow((1+interestRate),N);
            lastAddedInterest = lastAddedInterest.plusMinutes((long) (N*forEvery));
        }
    }
}
