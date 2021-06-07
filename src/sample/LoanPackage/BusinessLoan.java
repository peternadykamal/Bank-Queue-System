package sample.LoanPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BusinessLoan extends Loan
{
    //double interestRate = 0.1;
// time period atwal > moderator
    public BusinessLoan(int loanedAmount, int timePeriod)
    {
        super(loanedAmount, timePeriod);
        this.interestRate = 0.1;
        this.isSecured = true;
    }

    public BusinessLoan(int loanID, int loanedAmount, int timePeriod, double interest,
                        LocalDateTime loanBirthDate, int paidMonths, boolean penalty) {
        super(loanID, loanedAmount, timePeriod, interest, loanBirthDate, paidMonths, penalty);
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

    @Override
    public String[] getProperties() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String BirthDate= getLoanBirthDate().format(format);
        String[] array = {String.valueOf(getLoanedAmount()), String.valueOf(getTimePeriod()),
                String.valueOf(getInterest()),BirthDate,String.valueOf(getPaidMonths()),
                String.valueOf(checkPenalty()),"Business"};
        return array;
    }
}
