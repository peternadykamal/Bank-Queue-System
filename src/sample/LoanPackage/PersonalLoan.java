package sample.LoanPackage;

import java.time.LocalDateTime;

public class PersonalLoan extends Loan
{


    //interest 0.14
    //time period a2al > moderator
    public PersonalLoan(int loanedAmount, int timePeriod, double interestRate) {
        super(loanedAmount, timePeriod, interestRate);
    }


}
