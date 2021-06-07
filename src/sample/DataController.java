package sample;

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

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class DataController {
    private static String host = "PeterDesktop";
    private static String portNumber = "3306";
    private static String dataBase = "bank_db";
    private static String userName = "bank user";
    private static String password = "12345678";
    private static Connection connection;

    public static boolean startConnection(){
        String url = "JDBC" + ":" + "mysql" +"://" + host + ":" + portNumber + "/" + dataBase;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,userName,password);
        } catch (SQLException | ClassNotFoundException throwables) {
            return false;
        }
        return true;
    }
    public static boolean closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    public static Account selectAccount(int AccountID) throws SQLException {
        if(startConnection() == true){
            Statement statement = connection.createStatement();
        }
        return null;
    }
    public static boolean insertClint(Client client){
        String[] array = client.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  people" +
                    "(first_name , last_name , phone_number, address , password , person_type)" +
                    "values (?,?,?,?,?,?)");
            for (int i=0 ;i<6;i++){
                statement.setString(i+1,array[i]);
            }
            statement.execute();

            statement = connection.prepareStatement("select id from bank_db.people order by id desc limit 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                client.id = (int) resultSet.getObject("id");
            }
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean insertEmployee(Person person){
        String[] array = person.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  people" +
                    "(first_name , last_name , phone_number, address , password , salary ,working_hours, person_type)" +
                    "values (?,?,?,?,?,?,?,?)");
            for (int i=0 ;i<8;i++){
                statement.setString(i+1,array[i]);
            }
            statement.execute();

            statement = connection.prepareStatement("select id from bank_db.people order by id desc limit 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                person.id = (int) resultSet.getObject("id");
            }
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean insertAccount(Account account,Client client){
        String[] array = account.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  accounts" +
                    "(balance  , accountState  , penaltyUnderMin , lastAddedInterest , " +
                    " account_type, withdrawTimesInThisMonths, currentMonthNumber, person_id )" +
                    "values (?,?,?,?,?,?,?,?)");
            for (int i=0 ;i<8;i++){
                if(i<array.length){
                    if(i == 1 || i==2) statement.setBoolean(i+1, Boolean.parseBoolean(array[i]));
                    else statement.setString(i+1,array[i]);
                }
                else if (i==7) statement.setString(i+1, String.valueOf(client.id));
                else statement.setNull(i+1, java.sql.Types.NULL);
            }
            statement.execute();

            statement = connection.prepareStatement("select id from bank_db.accounts order by id desc limit 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                account.accountId = (int) resultSet.getObject("id");
            }
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean insertLoan(Loan loan,Client client){
        String[] array = loan.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("insert into loans" +
                    "(loanedAmount ,timePeriod ,interest ,loanBirthDate ," +
                    " paidMonths ,penalty ,loan_type ,person_id)" +
                    "values (?,?,?,?,?,?,?,?)");
            for (int i=0 ;i<8;i++){
                if(i<array.length){
                    if(i == 5) statement.setBoolean(i+1, Boolean.parseBoolean(array[i]));
                    else statement.setString(i+1,array[i]);
                }
                else if (i==7) statement.setString(i+1, String.valueOf(client.id));
            }
            statement.execute();

            statement = connection.prepareStatement("select id from bank_db.loans order by id desc limit 1");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                loan.loanID = (int) resultSet.getObject("id");
            }

        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    public static Client selectClint(int personId){
        Client client;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select person_type,id,first_name,last_name,phone_number" +
                        ",address,password from people where id = "+ personId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> array = new ArrayList<>();
            while (resultSet.next()){
                array.add(String.valueOf(resultSet.getObject("person_type")));
                array.add(String.valueOf(resultSet.getObject("id")));
                array.add(String.valueOf(resultSet.getObject("first_name")));
                array.add(String.valueOf(resultSet.getObject("last_name")));
                array.add(String.valueOf(resultSet.getObject("phone_number")));
                array.add(String.valueOf(resultSet.getObject("address")));
                array.add(String.valueOf(resultSet.getObject("password")));
            }
            if(array.size()>0){
                if(!Objects.equals(array.get(0), "client")) throw new SQLException();
                else {
                    client = new Client(
                            Integer.parseInt(array.get(1)),array.get(2),array.get(3),
                            array.get(4),array.get(5),array.get(6),
                            selectAccountList(Integer.parseInt(array.get(1))),
                            selectLoanList(Integer.parseInt(array.get(1)))
                    );
                }
            }
            else throw new SQLException();
        } catch (SQLException throwables) {
            return null;
        }
        return client;
    }
    private static ArrayList<Account> selectAccountList(int personId){
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select account_type,id,balance,accountState,penaltyUnderMin" +
                            ",lastAddedInterest,withdrawTimesInThisMonths,currentMonthNumber " +
                            "from accounts where person_id = "+ personId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ArrayList<String> array = new ArrayList<>();
                array.add(String.valueOf(resultSet.getObject("account_type")));
                array.add(String.valueOf(resultSet.getObject("id")));
                array.add(String.valueOf(resultSet.getObject("balance")));
                array.add(String.valueOf(resultSet.getBoolean("accountState")));
                array.add(String.valueOf(resultSet.getBoolean("penaltyUnderMin")));
                array.add(String.valueOf(resultSet.getObject("lastAddedInterest")));
                array.add(String.valueOf(resultSet.getObject("withdrawTimesInThisMonths")));
                array.add(String.valueOf(resultSet.getObject("currentMonthNumber")));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
                LocalDateTime dateTime = LocalDateTime.parse(array.get(5), formatter);
                if(array.get(0).equals("Savings")){
                    SavingsAccount savingsAccount = new SavingsAccount(
                            Integer.parseInt(array.get(1)),Double.parseDouble(array.get(2)),
                            Boolean.parseBoolean(array.get(3)),Boolean.parseBoolean(array.get(4)),
                            dateTime,Integer.parseInt(array.get(6)),
                            Integer.parseInt(array.get(7))
                    );
                    accounts.add(savingsAccount);
                }
                else if(array.get(0).equals("MoneyMarket")){
                    MoneyMarketAccount moneyMarketAccount = new MoneyMarketAccount(
                            Integer.parseInt(array.get(1)),Double.parseDouble(array.get(2)),
                            Boolean.parseBoolean(array.get(3)),Boolean.parseBoolean(array.get(4)),
                            dateTime
                    );
                    accounts.add(moneyMarketAccount);
                }
            }
            if(accounts.size()==0) return new ArrayList<Account>();
        } catch (SQLException throwables) {
            return null;
        }
        return accounts;
    }
    private static ArrayList<Loan> selectLoanList(int personId){
        ArrayList<Loan> loans = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select loan_type,id,loanedAmount,timePeriod,interest" +
                            ",loanBirthDate,paidMonths,penalty " +
                            "from loans where person_id = "+ personId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                ArrayList<String> array = new ArrayList<>();
                array.add(String.valueOf(resultSet.getObject("loan_type")));
                array.add(String.valueOf(resultSet.getObject("id")));
                array.add(String.valueOf(resultSet.getObject("loanedAmount")));
                array.add(String.valueOf(resultSet.getObject("timePeriod")));
                array.add(String.valueOf(resultSet.getObject("interest")));
                array.add(String.valueOf(resultSet.getObject("loanBirthDate")));
                array.add(String.valueOf(resultSet.getObject("paidMonths")));
                array.add(String.valueOf(resultSet.getBoolean("penalty")));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
                LocalDateTime BirthDate = LocalDateTime.parse(array.get(5), formatter);
                if(array.get(0).equals("Business")){
                    BusinessLoan businessLoan = new BusinessLoan(
                            Integer.parseInt(array.get(1)),Integer.parseInt(array.get(2)),
                            Integer.parseInt(array.get(3)),Double.parseDouble(array.get(4)),
                            BirthDate,Integer.parseInt(array.get(6)),Boolean.parseBoolean(array.get(7))
                    );
                    loans.add(businessLoan);
                }
                else if(array.get(0).equals("Personal")){
                    PersonalLoan personalLoan = new PersonalLoan(
                            Integer.parseInt(array.get(1)),Integer.parseInt(array.get(2)),
                            Integer.parseInt(array.get(3)),Double.parseDouble(array.get(4)),
                            BirthDate,Integer.parseInt(array.get(6)),Boolean.parseBoolean(array.get(7))
                    );
                    loans.add(personalLoan);
                }
            }
            if(loans.size()==0) return new ArrayList<Loan>();
        } catch (SQLException throwables) {
            return null;
        }
        return loans;
    }
    public static Teller selectTeller(int personId){
        Teller employee;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select person_type,id,first_name,last_name,phone_number" +
                            ",address,password,salary,working_hours from people where id = "+ personId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> array = new ArrayList<>();
            while (resultSet.next()){
                array.add(String.valueOf(resultSet.getObject("person_type")));
                array.add(String.valueOf(resultSet.getObject("id")));
                array.add(String.valueOf(resultSet.getObject("first_name")));
                array.add(String.valueOf(resultSet.getObject("last_name")));
                array.add(String.valueOf(resultSet.getObject("phone_number")));
                array.add(String.valueOf(resultSet.getObject("address")));
                array.add(String.valueOf(resultSet.getObject("password")));
                array.add(String.valueOf(resultSet.getObject("salary")));
                array.add(String.valueOf(resultSet.getObject("working_hours")));
            }
            if(array.size()>0){
                if(!Objects.equals(array.get(0), "teller")) throw new SQLException();
                else {
                    employee = new Teller(
                            Integer.parseInt(array.get(1)),array.get(2),array.get(3),
                            array.get(4),array.get(5),array.get(6),
                            Double.parseDouble(array.get(7)),Integer.parseInt(array.get(8))
                    );
                }
            }
            else throw new SQLException();
        } catch (SQLException throwables) {
            return null;
        }
        return employee;
    }
    public static Moderator selectModerator(int personId){
        Moderator employee;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "select person_type,id,first_name,last_name,phone_number" +
                            ",address,password,salary,working_hours from people where id = "+ personId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> array = new ArrayList<>();
            while (resultSet.next()){
                array.add(String.valueOf(resultSet.getObject("person_type")));
                array.add(String.valueOf(resultSet.getObject("id")));
                array.add(String.valueOf(resultSet.getObject("first_name")));
                array.add(String.valueOf(resultSet.getObject("last_name")));
                array.add(String.valueOf(resultSet.getObject("phone_number")));
                array.add(String.valueOf(resultSet.getObject("address")));
                array.add(String.valueOf(resultSet.getObject("password")));
                array.add(String.valueOf(resultSet.getObject("salary")));
                array.add(String.valueOf(resultSet.getObject("working_hours")));
            }
            if(array.size()>0){
                if(!Objects.equals(array.get(0), "moderator")) throw new SQLException();
                else {
                    employee = new Moderator(
                            Integer.parseInt(array.get(1)),array.get(2),array.get(3),
                            array.get(4),array.get(5),array.get(6),
                            Double.parseDouble(array.get(7)),Integer.parseInt(array.get(8))
                    );
                }
            }
            else throw new SQLException();
        } catch (SQLException throwables) {
            return null;
        }
        return employee;
    }

    public static boolean updateAccount(Account account,Client client){
        String[] array = account.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("update accounts" +
                    " set balance = ? , accountState = ? , penaltyUnderMin = ? , lastAddedInterest = ? , " +
                    " account_type = ? , withdrawTimesInThisMonths = ? , currentMonthNumber = ? , person_id = ?" +
                    " where id = " + account.accountId);
            for (int i=0 ;i<8;i++){
                if(i<array.length){
                    if(i == 1 || i==2) statement.setBoolean(i+1, Boolean.parseBoolean(array[i]));
                    else statement.setString(i+1,array[i]);
                }
                else if (i==7) statement.setInt(i+1, client.id);
                else statement.setNull(i+1, java.sql.Types.NULL);
            }
            statement.execute();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean updateLoan(Loan loan, Client client){
        String[] array = loan.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("update loans" +
                    " set loanedAmount = ? ,timePeriod = ? ,interest = ? ,loanBirthDate = ? , " +
                    " paidMonths = ? ,penalty = ? ,loan_type = ? ,person_id = ? " +
                    " where id =" + loan.loanID);
            for (int i=0 ;i<8;i++){
                if(i<array.length){
                    if(i == 5) statement.setBoolean(i+1, Boolean.parseBoolean(array[i]));
                    else statement.setString(i+1,array[i]);
                }
                else if (i==7) statement.setString(i+1, String.valueOf(client.id));
            }
            statement.execute();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean updateClint(Client client){
        String[] array = client.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("update people" +
                    " set first_name = ? , last_name = ? , phone_number = ? , address = ? , password = ? , person_type = ? " +
                    " where id = " + client.id);
            for (int i=0 ;i<6;i++){
                statement.setString(i+1,array[i]);
            }
            statement.execute();

            for (Account account : client.getAccounts()) {
                DataController.updateAccount(account, client);
            }
            for (Loan loan : client.getLoans()) {
                DataController.updateLoan(loan, client);
            }
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean updateEmployee(Person person){
        String[] array = person.getProperties();
        try {
            PreparedStatement statement = connection.prepareStatement("update people" +
                    " set first_name = ? , last_name = ? , phone_number = ? , address = ? , password = ? " +
                    ", salary = ? ,working_hours = ? , person_type = ? " +
                    " where id = " + person.id);
            for (int i=0 ;i<8;i++){
                statement.setString(i+1,array[i]);
            }
            statement.execute();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }

    public static boolean dropAccount(Account account){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from  accounts" +
                    " where id = " + account.accountId);
            statement.execute();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean dropLoan(Loan loan){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from loans" +
                    " where id = " + loan.loanID);
            statement.execute();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
    public static boolean dropPerson(Person person){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from people" +
                    " where id = " + person.id);
            statement.execute();
        } catch (SQLException throwables) {
            return false;
        }
        return true;
    }
}
