package sample.LoanPackage;

import sample.Main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public abstract class Loan
{
    public int loanID;
    protected int loanedAmount;
    protected int timePeriod;
    protected double interestRate;
    protected double interest;
    protected boolean isSecured;
    protected LocalDateTime loanBirthDate;
    protected int paidMonths;
    protected boolean penalty;
    public Loan(int loanedAmount, int timePeriod)
    {
        this.loanedAmount= loanedAmount;
        this.timePeriod = timePeriod;
        loanBirthDate=Main.now();
        interest = loanedAmount*interestRate*Math.pow(1+interestRate,timePeriod)/(Math.pow(1+interestRate,timePeriod)-1);
        interest = Math.round(interest*100.0)/100.0;
        penalty = false;
        paidMonths = 0;
    }

    public Loan(int loanID, int loanedAmount, int timePeriod, double interest,
                LocalDateTime loanBirthDate, int paidMonths, boolean penalty) {
        this.loanID = loanID;
        this.loanedAmount = loanedAmount;
        this.timePeriod = timePeriod;
        this.interest = interest;
        this.loanBirthDate = loanBirthDate;
        this.paidMonths = paidMonths;
        this.penalty = penalty;
    }

    public int getLoanID() {
        return loanID;
    }
    public int getLoanedAmount() {
        return loanedAmount;
    }
    public int getTimePeriod() {
        return timePeriod;
    }
    public double getInterestRate() {
        return interestRate;
    }
    public double getInterest() {
        interest = loanedAmount*interestRate*Math.pow(1+interestRate,timePeriod)/(Math.pow(1+interestRate,timePeriod)-1);
        interest = Math.round(interest*100.0)/100.0;
        return interest;
    }
    public boolean isSecured() {
        return isSecured;
    }
    public LocalDateTime getLoanBirthDate() {
        return loanBirthDate;
    }
    public int getPaidMonths() {
        return paidMonths;
    }

    public double getLoanAge() {
       // double age= ChronoUnit.MINUTES.between(loanBirthDate,LocalDateTime.now());
        double age= ChronoUnit.MONTHS.between(loanBirthDate.withDayOfMonth(1),Main.now().withDayOfMonth(1));
        return age;
    }
    public boolean checkPenalty() {
        //ispenalty
       return penalty;
    }
    public void payLoan(int months) {
       paidMonths+= months;
       if(paidMonths>= getLoanAge())
           penalty = false;
       else
           penalty = true;
    }

    public abstract String[] getProperties();
}
