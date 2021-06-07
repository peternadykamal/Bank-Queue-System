package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
import sample.AccountPackage.SavingsAccount;
import sample.LoanPackage.BusinessLoan;
import sample.LoanPackage.Loan;
import sample.LoanPackage.PersonalLoan;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private static int TIME_MACHINE=0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Bank Queue System");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Main.now());
        TIME_MACHINE=5;
        System.out.println(Main.now());
        LocalDateTime.now();
        Main.now();
       //Loan l = new Loan(10000,12);
        //BusinessLoan l = new BusinessLoan(10000,12,0.1);
        PersonalLoan l = new PersonalLoan(10000,12,0.14);
       TIME_MACHINE+=90;
        System.out.println(l.getInterest());
        l.payLoan(0);
        System.out.println(l.checkPenalty());


//        SavingsAccount account = new SavingsAccount(500);
//        System.out.println(account.getBalance());
//        System.out.println(account.isPenaltyUnderMin());
//        System.out.println(account.withdraw(500));
//        System.out.println(account.getBalance());
//
//        account.deposit(500);
//        System.out.println(account.getBalance());
//        System.out.println(account.isPenaltyUnderMin());
//
//        TimeUnit.MINUTES.sleep(3);
//        System.out.println(account.getBalance());
//        System.out.println(account.isPenaltyUnderMin());
//
//        account.deposit(1500);
//        System.out.println(account.getBalance());
//        System.out.println(account.isPenaltyUnderMin());
//        TimeUnit.MINUTES.sleep(6);
//        System.out.println(account.getBalance());
//
//        for (int i=0;i<7;i++){
//            System.out.println(account.withdraw(50));
//        }
//        TimeUnit.MINUTES.sleep(4);
//        for (int i=0;i<7;i++){
//            System.out.println(account.withdraw(500));
//        }
//        System.out.println(account.getBalance());
        //launch(args);
    }
    public static LocalDateTime now()
    {
        Clock presentTime = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Clock clock = Clock.offset(presentTime, Duration.ofDays(TIME_MACHINE));
        return LocalDateTime.now(clock);
    }
}
