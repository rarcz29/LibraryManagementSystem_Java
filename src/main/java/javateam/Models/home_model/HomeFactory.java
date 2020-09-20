package javateam.Models.home_model;

public class HomeFactory
{
    public Home create(String type)
    {
        if(type == null)
            return null;

        if(type.equalsIgnoreCase("ADMIN"))
            return new HomeAdmin();

        else if(type.equalsIgnoreCase("USER"))
            return new HomeUser();

        return null;
    }
}