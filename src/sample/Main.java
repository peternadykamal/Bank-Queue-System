package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.AccountPackage.Account;
import sample.AccountPackage.MoneyMarketAccount;
import sample.AccountPackage.SavingsAccount;
import sample.LoanPackage.BusinessLoan;
import sample.LoanPackage.Loan;
import sample.LoanPackage.PersonalLoan;
import sample.PersonPackage.Client;
import sample.PersonPackage.Moderator;
import sample.PersonPackage.Person;
import sample.PersonPackage.Teller;

import java.time.*;

public class Main extends Application {
    protected static int TIME_MACHINE = 0; // as a property in the main class
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Bank Queue System");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        //launch(args);
        System.out.println(DataController.startConnection());
        Teller teller = DataController.selectTeller(45);

        System.out.println(DataController.closeConnection());
    }
    public static LocalDateTime now(){
        Clock presentTime = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Clock clock = Clock.offset(presentTime, Duration.ofDays(TIME_MACHINE));
        return LocalDateTime.now(clock);
    }
}
