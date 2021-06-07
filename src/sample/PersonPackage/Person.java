package sample.PersonPackage;

public abstract  class Person {
    public int id;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String address;
    protected String password;

    protected String getPassword() {
        return password;
    }
    protected void setPassword(String password) {
        this.password = password;
    }

    public Person(int id,String firstName, String lastName, String phoneNumber, String address, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public Person(String firstName, String lastName, String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public boolean checkPassword(String s){
        //returns true when the given string is equal client password
        if(password.equals(s)) return true;
        else return false;
    }
    public abstract String[] getProperties();
}
