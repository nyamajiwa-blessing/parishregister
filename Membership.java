package parishregister;


public class Membership 
{
    private int id;
    private String diocese, parish, place, home, spouse, baptised, married, numberOfChildren;

    public Membership(int id, String diocese, String parish, String place, String home, String spouse, String baptised, String married, String numberOfChildren) {
        this.id = id;
        this.diocese = diocese;
        this.parish = parish;
        this.place = place;
        this.home = home;
        this.spouse = spouse;
        this.baptised = baptised;
        this.married = married;
        this.numberOfChildren = numberOfChildren;
    }
    
    public String getDiocese() 
    {
        return diocese;
    }

    public void setDiocese(String diocese) 
    {
        this.diocese = diocese;
    }

    public String getParish() 
    {
        return parish;
    }

    public void setParish(String parish) 
    {
        this.parish = parish;
    }

    public String getPlace() 
    {
        return place;
    }

    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getHome() 
    {
        return home;
    }

    public void setHome(String home) 
    {
        this.home = home;
    }

    public String getSpouse() 
    {
        return spouse;
    }

    public void setSpouse(String spouse) 
    {
        this.spouse = spouse;
    }

    public String getBaptised() 
    {
        return baptised;
    }

    public void setBaptised(String baptised) 
    {
        this.baptised = baptised;
    }

    public String getMarried() 
    {
        return married;
    }

    public void setMarried(String married) 
    {
        this.married = married;
    }

    public String getNumberOfChildren() 
    {
        return numberOfChildren;
    }

    public void setNumberOfChildren(String numberOfChildren) 
    {
        this.numberOfChildren = numberOfChildren;
    }
}
