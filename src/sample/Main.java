package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.AccountPackage.SavingsAccount;

import java.time.*;

public class Main extends Application {
    protected static int TIME_MACHINE = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Bank Queue System");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) throws InterruptedException {
        Testing.savingsAccount();
        //launch(args);
    }
    public static LocalDateTime now(){
        Clock presentTime = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        Clock clock = Clock.offset(presentTime, Duration.ofDays(TIME_MACHINE));
        return LocalDateTime.now(clock);
    }
}
