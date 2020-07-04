package javateam;

public class User
{
    // instance of the singleton
    private static User instance = null;

    // private constructor
    private User() {}

    // get instance of the class
    public static User getInstance()
    {
        if (instance == null)
            instance = new User();

        return instance;
    }
}
