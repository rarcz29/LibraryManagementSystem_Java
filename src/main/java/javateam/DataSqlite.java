package javateam;

import java.sql.*;

//TODO poprawić data.db

public class DataSqlite {
    private static Connection connection = null;
    private static Statement statement = null;

    //public static void dataSet() throws ClassNotFoundException { }      //normal

    DataSqlite() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/sqlite/data.db"); //TODO sprawdzić poprawność poza edytorem (jar) -> data.db

            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {  //test
        System.out.println("*test_only*");
        System.out.println("przykładowe użycie-select");

        try {
            // create DataSqlite instance with connection to database
            DataSqlite dataroot = new DataSqlite();

            // run sqlite command.
            ResultSet rs = statement.executeQuery("select * from pracownicy");

            System.out.println("id_pracownika" + " | " + "nazwa_konta"
                    + " | "  + "haslo" + " | " + "email"
                    + " | "  + "id_biblioteka");

            while (rs.next()) {
                // read the result set.
                System.out.println(rs.getString("id_pracownika") + " | "  + rs.getString("nazwa_konta")
                        + " | " + rs.getString("haslo") + " | "  + rs.getString("email")
                        + " | "  + rs.getString("id_biblioteka"));

            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    // close connection with database.
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
