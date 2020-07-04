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
    private int userId;
    private boolean isAdmin;

    // getters
    public String getUsername() { return username; }
    public int getUserId() { return userId; }
    public boolean getIsAdmin() { return isAdmin; }

    // getters as String
    public String getUserIdAsString() { return String.valueOf(userId); }
    public String getIsAdminAsString() { return String.valueOf(isAdmin); }

    // setter
    public void setData(String name, int id, boolean isAdmin)
    {
        username = name;
        userId = id;
        this.isAdmin = isAdmin;
    }
}
