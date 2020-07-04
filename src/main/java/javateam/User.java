package javateam;

public class User
{
    private static User instance = null;

    private User() {}

    public static User getInstance()
    {
        if (instance == null)
            instance = new User();

        return instance;
    }
}
