package sample.AccountPackage;

import sample.Main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public abstract class Account {
    public int accountId;
    //protected ArrayList<AccountOperation> accountHistory = new ArrayList<>();
    private double balance;
    protected int minBalance;
    public boolean accountState;
    //accountState = false in all accounts when one of accounts have penaltyUnderMin
    protected boolean penaltyUnderMin;
    protected double  interestRate;
    private LocalDateTime lastAddedInterest;
    //private double forEvery = 3.0; //add interest for every 3 min.

    public int getAccountId() {
        return accountId;
    }
//    public ArrayList<AccountOperation> getAccountHistory() {
//        return accountHistory;
//    }
    public int getMinBalance() {
        return minBalance;
    }
    public boolean isPenaltyUnderMin() {
        return penaltyUnderMin;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public LocalDateTime getLastAddedInterest() {
        return lastAddedInterest;
    }
    public double getBalance() {
        addInterest();
        return balance;
    }


    protected void setBalance(double balance) {
        this.balance = balance;
        if (getBalance() >= minBalance) penaltyUnderMin=false;
        else penaltyUnderMin=true;
    }

    public Account(){
        accountState = true;
        penaltyUnderMin=true;
        lastAddedInterest = Main.now();
    }

    public Account(int accountId, boolean accountState, boolean penaltyUnderMin, LocalDateTime lastAddedInterest) {
        this.accountId = accountId;
        this.accountState = accountState;
        this.penaltyUnderMin = penaltyUnderMin;
        this.lastAddedInterest = lastAddedInterest;
    }

    private void addInterest(){
        long diff = ChronoUnit.MONTHS.between(lastAddedInterest.withDayOfMonth(1), Main.now().withDayOfMonth(1));
        //to get the difference between two dates in months
        int N = (int) Math.floor(diff);
        if (N>0){
            if(penaltyUnderMin == false && accountState == true) balance = balance*Math.pow((1+interestRate),N);
            lastAddedInterest = lastAddedInterest.plusMonths(N);
        }
//        long diff = ChronoUnit.MINUTES.between(lastAddedInterest, Main.now());
//        //get the difference between two dates
//        int N = (int) Math.floor(diff/forEvery);
//        if (N>0){
//            if(penaltyUnderMin == false && accountState == true) balance = balance*Math.pow((1+interestRate),N);
//            lastAddedInterest = lastAddedInterest.plusMinutes((long) (N*forEvery));
//        }
    }

    public abstract String[] getProperties();
}
