package sample.AccountPackage;

import sample.Main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SavingsAccount extends Account implements IAccount{

    private static final int withdrawTimesLimit = 6;
    private int withdrawTimesInThisMonths;
    private int currentMonthNumber = Main.now().getMonthValue();
    //private static final int interval = 3;
    //minutes period check instead each month check
    private LocalDateTime timeToResetWithdrawTimes;

    public int getWithdrawTimesLimit() {
        return withdrawTimesLimit;
    }
    public int getWithdrawTimesInThisMonths() {
        return withdrawTimesInThisMonths;
    }

    public SavingsAccount(double balance){
        super();
        minBalance = 1500;
        interestRate = 4/100.0;
        withdrawTimesInThisMonths = 0;
        timeToResetWithdrawTimes = Main.now().plusMonths(1);
        deposit(balance);
    }

    @Override
    public boolean withdraw(double amount) {
        //check if currentMonthNumber is up tot date or not
        if(currentMonthNumber != Main.now().getMonthValue()){
            currentMonthNumber = Main.now().getMonthValue();
            withdrawTimesInThisMonths = 0;
        }

//        long diff = ChronoUnit.SECONDS.between(Main.now(), timeToResetWithdrawTimes);
//        if (diff <= 0) {
//            timeToResetWithdrawTimes = Main.now().plusSeconds((interval*60)+(diff%(interval*60)));
//            withdrawTimesInThisMonths = 0;
//        }

        double balance = getBalance();
        if(balance >= amount && withdrawTimesInThisMonths < withdrawTimesLimit){
            AccountOperation operation = new AccountOperation(amount,operationType.withdraw);
            this.accountHistory.add(operation);
            setBalance(balance-amount);
            withdrawTimesInThisMonths++;
            return true;
        }
        else return false;
    }

    @Override
    public void deposit(double amount) {
        AccountOperation operation = new AccountOperation(amount,operationType.deposit);
        this.accountHistory.add(operation);
        double balance = getBalance();
        setBalance(balance+amount);
    }
}
