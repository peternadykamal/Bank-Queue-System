package sample.PersonPackage;

import sample.PersonPackage.Moderator;
import sample.PersonPackage.Person;
import sample.PersonPackage.Teller;

public class Admin extends Person
{
    public Admin(String firstName, String lastName, String phoneNumber, String address, String password) {
        super(firstName, lastName, phoneNumber, address, password);
    }
    public void createTeller(Teller teller)
     {

     }

    public void removeTeller(Teller teller) {}
    public void updateTeller(Teller teller){}
    public void createModerator(Moderator moderator){}
    public void removeModerator(Moderator moderator){}
    public void updateModerator(Moderator moderator){}

    @Override
    public String[] getProperties() {
        return new String[0];
    }
}
