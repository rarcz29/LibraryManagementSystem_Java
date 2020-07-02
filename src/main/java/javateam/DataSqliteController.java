package javateam;

import java.sql.*;
import java.util.Vector;

public class DataSqliteController {
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet rs = null;

    DataSqliteController() {
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/database/data.db"); //TODO check connectoin in (.jar) -> data.db, or create new

            statement = connection.createStatement();
            statement.setQueryTimeout(20);  // set timeout to 20 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory", it probably means no database file is found
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    //TODO add more functions

    // Propozycja dodania, zwraca boolean,
    // true jeśli wykonano, false gdy nie wykonano lub błąd.
    // * transakcja dla każdej tabeli oddzielnie
    public boolean data_insert_ksiazki(String title, String author, String type, int id_biblioteka){
        boolean status = false;
        try
        {
            // run transaction
            this.statement.execute("BEGIN TRANSACTION;");
            this.statement.execute("INSERT INTO \"main\".\"ksiazki\" (\"tytul\", \"autor\", \"gatunek\", \"id_biblioteka\")"  //TODO dostosować do nowej bazy
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


    // polecenie wykonywane z zewnątrz
    public Vector data_select_ksiazki(){
        String sql = "select * from ksiazki;";  //TODO dostosować do nowej bazy
        return data_select(sql);
    }

    // Propozycja odczytania, zwraca Vector,
    // pierwsze pole wektora zawiera nazwy kolumn,
    // w ten sposób można odebrać każde pole oddzielnie.
    // * kompatybinly z każdą tabelą - zmienne polecenie sql
    private Vector data_select(String sql){
        int max_column;
        Vector dane = new Vector();
        ResultSetMetaData meta = null;
        try
        {
            // run command
            rs = statement.executeQuery(sql);

            meta = rs.getMetaData();
            max_column = meta.getColumnCount();

            String info[] = new String[max_column];

            for (int i = 0; i < max_column; i++) {
                info[i] = meta.getColumnName(i + 1);
            }
            dane.add(info.clone());

            while(rs.next()){
                for (int i = 0; i < max_column; i++) {
                    info[i] = rs.getString(i + 1);
                }
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
}
