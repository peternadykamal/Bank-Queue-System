package sample;

import sample.AccountPackage.MoneyMarketAccount;
import sample.AccountPackage.SavingsAccount;

import java.util.concurrent.TimeUnit;

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
}
