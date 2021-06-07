package sample.PersonPackage;

public class Moderator extends Teller{
    public Moderator(String firstName, String lastName, String phoneNumber, String address,
                     String password, double salary, int working_hours) {
        super(firstName, lastName, phoneNumber, address, password, salary, working_hours);
    }

    public Moderator(int id, String firstName, String lastName, String phoneNumber, String address,
                     String password, double salary, int working_hours) {
        super(id, firstName, lastName, phoneNumber, address, password, salary, working_hours);
    }

    public String[] getProperties() {
        String[] array = {firstName,lastName,
                phoneNumber,address,
                password, String.valueOf(salary),
                String.valueOf(working_hours),"moderator"};
        return array;
    }
}
