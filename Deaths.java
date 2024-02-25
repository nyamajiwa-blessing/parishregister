
package parishregister;


public class Deaths 
{
    private int id;
    private String firstName, surname, age, nextOfKin, deathDate, burialDate;

    public Deaths(int id, String firstName, String surname, String age, String nextOfKin, String deathDate, String burialDate) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.age = age;
        this.nextOfKin = nextOfKin;
        this.deathDate = deathDate;
        this.burialDate = burialDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getBurialDate() {
        return burialDate;
    }

    public void setBurialDate(String burialDate) {
        this.burialDate = burialDate;
    }
    
    
}
