package sample.AccountPackage;

import sample.Main;

import java.time.LocalDateTime;

public class AccountOperation {
    private int accountID;
    private LocalDateTime date;
    private double amount;
    private String type;

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public AccountOperation(int accountID,double amount, operationType type){
        this.type = type.toString();
        date = Main.now();
        this.amount = amount;
        this.accountID = accountID;
    }
}