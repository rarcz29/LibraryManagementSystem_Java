package javateam;

import java.sql.*;
import java.util.Scanner;
import java.util.Vector;

public class DataSqliteController {
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet rs = null;

    DataSqliteController() {
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/database/data.db"); //TODO check connectoin in (.jar) -> data.db

            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory", it probably means no database file is found
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    //TODO add more functions

    public boolean data_add_book(String title, String author, String type, int id_biblioteka){
        boolean status = false;
        try
        {
            // run transaction
            this.statement.execute("BEGIN TRANSACTION;");
            this.statement.execute("INSERT INTO \"main\".\"ksiazki\" (\"tytul\", \"autor\", \"gatunek\", \"id_biblioteka\")"
                   + "VALUES ('"+title+"','"+author+"','"+type+"',"+id_biblioteka+");");
            this.statement.execute("COMMIT;");
            status = true;
        }catch (SQLException e) {
            this.statement.execute("ROLLBACK;");
        }
        finally {
            return status;
        }
    }

    public Vector data_get_available_book(){
        int column = 4; // columns
        int i, j;
        Vector dane = new Vector();
        String info[] = new String[column];
        try
        {
            // run command
            rs = statement.executeQuery("select * from vksiazkidostepne;");
            // generate vector from querry
            while(rs.next()){
                info[0] = rs.getString("tytul");
                info[1] = rs.getString("autor");
                info[2] = rs.getString("id_ksiazki");
                info[3] = rs.getString("id_biblioteka");
                dane.add(info.clone());
            }
            rs.close();
        }catch (SQLException e) {
            System.err.println(e);
        }
        finally {
            return dane;
        }
    }

    public static void main(String[] args) {  //test_only
        System.out.println("*test_only*");

        DataSqliteController dataroot = null;

        // run commands in try
        try {
            // set connection to database
            dataroot = new DataSqliteController();

            // <communication there>

            //dataroot.testonlydata_select();
            //dataroot.testonlydata_statistics();
            //dataroot.testonlydata_insert_ksiazka();
            //dataroot.testonlydata_view();
            dataroot.testonlydata_data_get_available_book();
        }
        finally {
            // close connection to database
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
    private void testonlydata_data_get_available_book() {  //test_only
        System.out.println("*testonlydata_data_get_available_book*");
        Vector data;
        data = this.data_get_available_book();

        System.out.println("+-------------------------------------+-----------------+----------+----------+");
        System.out.printf("| %35s | %15s | %8s | %8s |", "tytuł", "autor", "id_ksia", "id_bibl");
        System.out.println();
        System.out.println("+-------------------------------------+-----------------+----------+----------+");

        // get data from Vector
        for(int i=0; i<data.size();i++){
            String out[] = (String[]) data.get(i);
            System.out.printf("| %35s | %15s | %8s | %8s |", out[0], out[1], out[2], out[3]);
            System.out.println();
        }
        System.out.println("+-------------------------------------+-----------------+----------+----------+");
    }

    private void testonlydata_select() {  //test_only
        System.out.println("*testonlydata_select*");
        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from pracownicy;");

            System.out.println("testonlydata_select:");

            System.out.println("+-----------------+-----------------+--------------+---------------------------+-----------------+");
            System.out.printf("| %15s | %15s | %12s | %25s | %15s |", "id_pracownika", "nazwa_konta", "haslo", "email","id_biblioteka");
            System.out.println();
            System.out.println("+-----------------+-----------------+--------------+---------------------------+-----------------+");

            while (this.rs.next()) {
                // read the result set.
                System.out.printf("| %15s | %15s | %12s | %25s | %15s |", this.rs.getString("id_pracownika"), this.rs.getString("nazwa_konta"), this.rs.getString("haslo"), this.rs.getString("email"), this.rs.getString("id_biblioteka"));
                System.out.println();
            }
            System.out.println("+-----------------+-----------------+--------------+---------------------------+-----------------+");

        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    private void testonlydata_insert_ksiazka() {  //test_only
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
            if(this.data_add_book(tytul, autor, gatunek, id_biblioteka))
                System.out.print("OK");
            else
                System.out.println("FAIL");

        }
        finally {
            System.out.println();
        }
    }

    private void testonlydata_statistics() {  //test_only
        System.out.println("*testonlydata_statistics*");

        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from sqlite_sequence;");

            System.out.println("+----------------------+--------+");
            System.out.printf("| %20s | %6s |", "name", "seq");
            System.out.println();
            System.out.println("+----------------------+--------+");
            while (this.rs.next()) {
                // read the result set.
                System.out.printf("| %20s | %6s |", this.rs.getString("name"), this.rs.getString("seq"));
                System.out.println();
            }
            System.out.println("+----------------------+--------+");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void testonlydata_view() {  //test_only
        System.out.println("*testonlydata_viev*");

        try {
            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from vksiazkidostepne;");
            System.out.println("vksiazkidostepne");
            System.out.println("+------+-------------------------------------+------------+------+");
            while (this.rs.next()) {
                // read the result set.
                System.out.printf("| %4s | %35s | %10s | %4s |", this.rs.getString(1), this.rs.getString(2), this.rs.getString(3), this.rs.getString(4));
                System.out.println();
            }
            System.out.println("+------+-------------------------------------+------------+------+");

            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from vksiazkiniedostepne;");
            System.out.println("vksiazkiniedostepne");
            System.out.println("+------+-------------------------------------+------------+------+");
            while (this.rs.next()) {
                // read the result set.
                System.out.printf("| %4s | %35s | %10s | %4s |", this.rs.getString(1), this.rs.getString(2), this.rs.getString(3), this.rs.getString(4));
                System.out.println();
            }
            System.out.println("+------+-------------------------------------+------------+------+");

            // run sqlite command.
            this.rs = this.statement.executeQuery("select * from vuzytkownicy;");
            System.out.println("vuzytkownicy");
            System.out.println("+------+-----------------+-----------------+--------------------------------+--------------------------------+");
            while (this.rs.next()) {
                // read the result set.
                System.out.printf("| %4s | %15s | %15s | %30s | %30s |", this.rs.getString(1), this.rs.getString(2), this.rs.getString(3), this.rs.getString(4), this.rs.getString(5));
                System.out.println();
            }
            System.out.println("+------+-----------------+-----------------+--------------------------------+--------------------------------+");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
