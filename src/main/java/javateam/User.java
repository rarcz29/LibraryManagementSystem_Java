package javateam;

public class User
{
    // instance of the singleton
    private static User instance = null;
    // object to synchronization (thread safety)
    private static Object mutex = new Object();

    // private constructor
    private User() {}

    // get instance of the class
    public static User getInstance()
    {
        User result = instance;

        if (result == null)
        {
            synchronized (mutex)
            {
                result = instance;
                if (result == null)
                    instance = result = new User();
            }
        }

        return instance;
    }
}
