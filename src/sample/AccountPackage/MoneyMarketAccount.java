package sample.AccountPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MoneyMarketAccount extends Account implements IAccount{
    public MoneyMarketAccount(double balance) {
        super();
        minBalance = 2500;
        interestRate = 8/100.0;
        deposit(balance);
    }

    public MoneyMarketAccount(int accountId, double balance, boolean accountState, boolean penaltyUnderMin, LocalDateTime lastAddedInterest) {
        super(accountId, accountState, penaltyUnderMin, lastAddedInterest);
        minBalance = 2500;
        interestRate = 8/100.0;
        setBalance(balance);
    }

    @Override
    public boolean withdraw(double amount) {
        double balance = getBalance();
        if(balance >= amount){
//            AccountOperation operation = new AccountOperation(accountId,amount,operationType.withdraw);
//            this.accountHistory.add(operation);
            setBalance(balance-amount);
            return true;
        }
        else return false;
    }

    @Override
    public void deposit(double amount) {
//        AccountOperation operation = new AccountOperation(accountId,amount,operationType.deposit);
//        this.accountHistory.add(operation);
        double balance = getBalance();
        setBalance(balance+amount);
    }

    @Override
    public String[] getProperties() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String lastAddedInterest= getLastAddedInterest().format(format);
        String[] array = {String.valueOf(getBalance()), String.valueOf(accountState),
                String.valueOf(penaltyUnderMin),lastAddedInterest,"MoneyMarket"};
        return array;
    }
}
