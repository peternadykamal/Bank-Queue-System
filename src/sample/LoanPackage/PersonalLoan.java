package sample.LoanPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PersonalLoan extends Loan
{


    //interest 0.14
    //time period a2al > moderator
    public PersonalLoan(int loanedAmount, int timePeriod) {
        super(loanedAmount, timePeriod);
        this.interestRate = 0.14;
        this.isSecured = false;
    }
    public PersonalLoan(int loanID, int loanedAmount, int timePeriod, double interest,
                        LocalDateTime loanBirthDate, int paidMonths, boolean penalty) {
        super(loanID, loanedAmount, timePeriod, interest, loanBirthDate, paidMonths, penalty);
    }

    @Override
    public String[] getProperties() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String BirthDate= getLoanBirthDate().format(format);
        String[] array = {String.valueOf(getLoanedAmount()), String.valueOf(getTimePeriod()),
                String.valueOf(getInterest()),BirthDate,String.valueOf(getPaidMonths()),
                String.valueOf(checkPenalty()),"Personal"};
        return array;
    }


}
