package parishregister;


public class Baptism 
{
    private int id;
    private String fullName, birthDate, place, baptismLocation, baptisor, marriedTo;

    public Baptism(int id, String fullName, String birthDate, String place, String baptismLocation, String baptisor, String marriedTo) 
    {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.place = place;
        this.baptismLocation = baptismLocation;
        this.baptisor = baptisor;
        this.marriedTo = marriedTo;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getFullName() 
    {
        return fullName;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getBirthDate() 
    {
        return birthDate;
    }

    public void setBirthDate(String birthDate) 
    {
        this.birthDate = birthDate;
    }

    public String getPlace() 
    {
        return place;
    }

    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getBaptismLocation() 
    {
        return baptismLocation;
    }

    public void setBaptismLocation(String baptismLocation) 
    {
        this.baptismLocation = baptismLocation;
    }

    public String getBaptisor() 
    {
        return baptisor;
    }

    public void setBaptisor(String baptisor) 
    {
        this.baptisor = baptisor;
    }

    public String getMarriedTo() 
    {
        return marriedTo;
    }

    public void setMarriedTo(String marriedTo) 
    {
        this.marriedTo = marriedTo;
    }
    
    
}
