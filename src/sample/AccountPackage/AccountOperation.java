package sample.AccountPackage;

import java.time.LocalDateTime;

public class AccountOperation {

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

    public AccountOperation(double amount, operationType type){
        this.type = type.toString();
        date = LocalDateTime.now();
        this.amount = amount;
    }
}