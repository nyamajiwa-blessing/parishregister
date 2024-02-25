package parishregister;


public class Financials 
{
    private int id;
    private String fullName, amountPaid, month, week;

    public Financials(int id, String fullName, String amountPaid, String month, String week) 
    {
        this.id = id;
        this.fullName = fullName;
        this.amountPaid = amountPaid;
        this.month = month;
        this.week = week;
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

    public String getAmountPaid() 
    {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) 
    {
        this.amountPaid = amountPaid;
    }

    public String getMonth() 
    {
        return month;
    }

    public void setMonth(String month) 
    {
        this.month = month;
    }

    public String getWeek() 
    {
        return week;
    }

    public void setWeek(String week) 
    {
        this.week = week;
    }
    
    
}
