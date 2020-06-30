package javateam;

import java.sql.*;

//TODO upgrade data.db

public class DataSqliteController {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    //public static void dataSet() throws ClassNotFoundException { }      //normal

    DataSqliteController() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/database/data.db"); //TODO check connectoin in (.jar) -> data.db

            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {  //test_only
        System.out.println("*test_only*");
        System.out.println("przykładowe użycie-select");

        // create DataSqlite instance with connection to database
        DataSqliteController dataroot = new DataSqliteController();
        dataroot.testonlydata_select();

    }

    public void testonlydata_select() {  //test_only
        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from pracownicy");

            System.out.println("id_pracownika" + " | " + "nazwa_konta"
                    + " | " + "haslo" + " | " + "email"
                    + " | " + "id_biblioteka");

            while (this.rs.next()) {
                // read the result set.
                System.out.println(this.rs.getString("id_pracownika") + " | " + this.rs.getString("nazwa_konta")
                        + " | " + this.rs.getString("haslo") + " | " + this.rs.getString("email")
                        + " | " + this.rs.getString("id_biblioteka"));

            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (this.connection != null)
                    // close connection with database.
                    this.connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
