package sample.LoanPackage;

import sample.AccountPackage.Account;
import sample.AccountPackage.SavingsAccount;
import sample.Main;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Loan
{
    protected int loanID;
    protected int loanedAmount;
    protected int timePeriod;
    protected double interestRate;
    protected double interest;
    protected boolean isSecured;
    protected LocalDateTime loanBirthDate;
    protected int paidMonths=0;
    protected boolean penalty;
    public Loan (){}
    public Loan(int loanedAmount, int timePeriod , double interestRate)
    {
        this.interestRate = interestRate;
        this.loanedAmount= loanedAmount;
        this.timePeriod = timePeriod;
        loanBirthDate=Main.now();
        interest = loanedAmount*interestRate*Math.pow(1+interestRate,timePeriod)/(Math.pow(1+interestRate,timePeriod)-1);

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

    public double getInterest()
    {
        interest = loanedAmount*interestRate*Math.pow(1+interestRate,timePeriod)/(Math.pow(1+interestRate,timePeriod)-1);
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

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getLoanAge()
    {
       // double age= ChronoUnit.MINUTES.between(loanBirthDate,LocalDateTime.now());
        double age= ChronoUnit.MONTHS.between(loanBirthDate.withDayOfMonth(1),Main.now().withDayOfMonth(1));
        return age;
    }
    public boolean checkPenalty() //ispenalty
    {
       return penalty;
    }

    public void payLoan(int months)
    {
       paidMonths+= months;
       if(paidMonths>= getLoanAge())
           penalty = false;
       else
           penalty = true;
}
}
