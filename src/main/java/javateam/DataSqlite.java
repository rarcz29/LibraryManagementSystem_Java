package javateam;

import java.sql.*;

//TODO data.db

public class DataSqlite {
    //public static void dataTest() throws ClassNotFoundException {         //normal
    public static void main(String[] args) throws ClassNotFoundException {  //test

        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/sqlite/data.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from pracownicy");

            System.out.println("id_pracownika" + "nazwa_konta" + "haslo" + "email" + "id_biblioteka");
            while (rs.next()) {
                // read the result set

                System.out.println(rs.getString("id_pracownika") + rs.getString("nazwa_konta")
                        + rs.getString("haslo") + rs.getString("email") + rs.getString("id_biblioteka"));

            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
