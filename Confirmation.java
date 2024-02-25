package parishregister;

public class Confirmation 
{
//    private int id;
    private String firstName, surname, fathersName, mothersName, place, regDate, sponsor, baptismLocation;

    public Confirmation(String firstName, String surname, String fathersName, String mothersName, String place, String regDate, String sponsor, String baptismLocation) 
    {
//        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.fathersName = fathersName;
        this.mothersName = mothersName;
        this.place = place;
        this.regDate = regDate;
        this.sponsor = sponsor;
        this.baptismLocation = baptismLocation;
    }

//    public int getId() 
//    {
//        return id;
//    }
//
//    public void setId(int id) 
//    {
//        this.id = id;
//    }

    public String getFirstName() 
    {
        return firstName;
    }

    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    public String getSurname() 
    {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    public String getFathersName() 
    {
        return fathersName;
    }

    public void setFathersName(String fathersName) 
    {
        this.fathersName = fathersName;
    }

    public String getMothersName() 
    {
        return mothersName;
    }

    public void setMothersName(String mothersName) 
    {
        this.mothersName = mothersName;
    }

    public String getPlace() 
    {
        return place;
    }

    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getRegDate() 
    {
        return regDate;
    }

    public void setRegDate(String regDate) 
    {
        this.regDate = regDate;
    }

    public String getSponsor() 
    {
        return sponsor;
    }

    public void setSponsor(String sponsor) 
    {
        this.sponsor = sponsor;
    }

    public String getBaptismLocation() 
    {
        return baptismLocation;
    }

    public void setBaptismLocation(String baptismLocation) 
    {
        this.baptismLocation = baptismLocation;
    }
    
    
}
