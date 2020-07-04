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

    // user variables
    private String username;
    private int userid;
    private bool isAdmin;

    // getters
    public String getUsername() { return username; }
    public int getUserid() { return userid; }
    public bool getIsAdmin() { return isAdmin; }

    // setter
    public void setData(String name, int id, bool isAdmin)
    {
        username = name;
        userid = id;
        this.isAdmin = isAdmin;
    }
}
