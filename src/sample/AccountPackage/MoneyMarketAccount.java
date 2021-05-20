package sample.AccountPackage;

public class MoneyMarketAccount extends Account implements IAccount{

    public MoneyMarketAccount(double balance) {
        super();
        minBalance = 2500;
        interestRate = 8/100.0;
        deposit(balance);
    }

    @Override
    public boolean withdraw(double amount) {
        double balance = getBalance();
        if(balance >= amount){
            AccountOperation operation = new AccountOperation(amount,operationType.withdraw);
            this.accountHistory.add(operation);
            setBalance(balance-amount);
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
