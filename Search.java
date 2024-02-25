package parishregister;

public class Search 
{
    private String fullName, location, date;

    public Search(String fullName, String location, String date) 
    {
        this.fullName = fullName;
        this.location = location;
        this.date = date;
    }

    public String getFullName() 
    {
        return fullName;
    }

    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getDate() 
    {
        return date;
    }

    public void setDate(String date) 
    {
        this.date = date;
    }
}
