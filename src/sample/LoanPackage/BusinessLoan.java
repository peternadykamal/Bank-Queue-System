package sample.LoanPackage;

import java.time.LocalDateTime;

public class BusinessLoan extends Loan
{
    //double interestRate = 0.1;
// time period atwal > moderator
    public BusinessLoan(){}

    public BusinessLoan(int loanedAmount, int timePeriod, double interestRate)
    {
        super(loanedAmount, timePeriod, interestRate);
        this.interestRate = interestRate;
    }

    @Override
    public LocalDateTime getLoanBirthDate() {
        return super.getLoanBirthDate();
    }

    @Override
    public double getLoanAge() {
        return super.getLoanAge();
    }

    @Override
    public void payLoan(int months)
    {
        super.payLoan(months);
    }

    @Override
    public double getInterest() {
        return super.getInterest();
    }
}
