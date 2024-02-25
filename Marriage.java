package parishregister;

public class Marriage 
{
    private int id;
    private String bridesName, groomsName, place, marriedDate, witnessOne, witnessTwo;

    public Marriage(int id, String bridesName, String groomsName, String place, String marriedDate, String witnessOne, String witnessTwo) 
    {
        this.id = id;
        this.bridesName = bridesName;
        this.groomsName = groomsName;
        this.place = place;
        this.marriedDate = marriedDate;
        this.witnessOne = witnessOne;
        this.witnessTwo = witnessTwo;
    }

    

    public String getBridesName() 
    {
        return bridesName;
    }

    public void setBridesName(String bridesName) 
    {
        this.bridesName = bridesName;
    }

    public String getGroomsName() 
    {
        return groomsName;
    }

    public void setGroomsName(String groomsName) 
    {
        this.groomsName = groomsName;
    }

    public String getPlace() 
    {
        return place;
    }

    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getMarriedDate() 
    {
        return marriedDate;
    }

    public void setMarriedDate(String marriedDate) 
    {
        this.marriedDate = marriedDate;
    }

    public String getWitnessOne() 
    {
        return witnessOne;
    }

    public void setWitnessOne(String witnessOne) 
    {
        this.witnessOne = witnessOne;
    }

    public String getWitnessTwo() 
    {
        return witnessTwo;
    }

    public void setWitnessTwo(String witnessTwo) 
    {
        this.witnessTwo = witnessTwo;
    }
    
    
}
