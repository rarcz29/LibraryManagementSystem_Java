package javateam;

import java.sql.*;
import java.util.Scanner;

public class DataSqliteController {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    DataSqliteController() throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/database/data.db"); //TODO check connectoin in (.jar) -> data.db

            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory", it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {  //test_only
        System.out.println("*test_only*");

        // create DataSqlite instance with connection to database
        DataSqliteController dataroot = new DataSqliteController();

        // run commands
        try {
            //dataroot.testonlydata_select();
            dataroot.testonlydata_statistics();
            //dataroot.testonlydata_insert_ksiazka();
            dataroot.testonlydata_view();
        }
        // close connection
        finally {
            try {
                if (dataroot.connection != null)
                    // close connection with database.
                    dataroot.connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }

    public void testonlydata_select() {  //test_only
        System.out.println("*testonlydata_select*");

        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from pracownicy;");

            System.out.println("testonlydata_select:");
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
        }
    }

    public void testonlydata_insert_ksiazka() {  //test_only
        System.out.println("*testonlydata_insert*");

        try {
            String tytul = null;
            String autor = null;
            String gatunek = null;
            int id_biblioteka;

            //input
            Scanner scanner = new Scanner(System.in);
            System.out.println("podaj tytuł: ");
            tytul = scanner.nextLine();
            System.out.println("podaj autor: ");
            autor = scanner.nextLine();
            System.out.println("podaj gatunek: ");
            gatunek = scanner.nextLine();
            System.out.println("podaj id_biblioteka: ");
            id_biblioteka = scanner.nextInt();
            scanner.nextLine();

            System.out.print("insert status: ");

            // run sqlite command.
            this.statement.execute("INSERT INTO \"main\".\"ksiazki\" (\"tytul\", \"autor\", \"gatunek\", \"id_biblioteka\")" +
                    "VALUES ('"+tytul+"','"+autor+"','"+gatunek+"',"+id_biblioteka+");");

            System.out.print("OK");

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally {
            System.out.println();
        }
    }

    public void testonlydata_statistics() {  //test_only
        System.out.println("*testonlydata_statistics*");

        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from sqlite_sequence;");

            System.out.println("name" + " | " + "seq");
            while (this.rs.next()) {
                // read the result set.
                System.out.println(this.rs.getString("name") + " | " + this.rs.getString("seq"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void testonlydata_view() {  //test_only
        System.out.println("*testonlydata_viev*");

        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from vksiazkidostepne;");

            System.out.println("vksiazkidostepne");
            while (this.rs.next()) {
                // read the result set.
                System.out.println(this.rs.getString(1) + " | " + this.rs.getString(2) +
                        " | " + this.rs.getString(3) + " | " + this.rs.getString(4));
            }

            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from vksiazkiniedostepne;");

            System.out.println("vksiazkiniedostepne");
            while (this.rs.next()) {
                // read the result set.
                System.out.println(this.rs.getString(1) + " | " + this.rs.getString(2) +
                        " | " + this.rs.getString(3) + " | " + this.rs.getString(4));
            }

            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from vuzytkownicy;");

            System.out.println("vuzytkownicy");
            while (this.rs.next()) {
                // read the result set.
                System.out.println(this.rs.getString(1) + " | " + this.rs.getString(2) +
                        " | " + this.rs.getString(3) + " | " + this.rs.getString(4) +
                        " | " + this.rs.getString(5));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
