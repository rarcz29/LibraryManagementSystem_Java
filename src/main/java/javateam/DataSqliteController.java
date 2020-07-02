package javateam;

import java.sql.*;
import java.util.Vector;

public class DataSqliteController {
    //private static Connection connection = null;
    //private static Statement statement = null;
    //private static ResultSet rs = null;
    // TODO zmienić po zakończeniu testów
    public static Connection connection = null;
    public static Statement statement = null;
    public static ResultSet rs = null;

    DataSqliteController() {

        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            // TODO check connectoin in (.jar) -> data.db, or create new
            connection = DriverManager.getConnection("jdbc:sqlite:src/database/data.db");

            statement = connection.createStatement();
            statement.setQueryTimeout(20);  // set timeout to 20 sec.

        } catch (SQLException e) {
            // if the error message is "out of memory", it probably means no database file is found
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    // TODO dodać nowe funkcje według potrzeb
    //  dostosować do nowej bazy

    public Vector data_select_ksiazki(){
        String select_sql = "select * from ksiazki;";
        return data_select(select_sql);
    }

    public boolean data_insert_ksiazki(String title, String author, String type, int id_biblioteka){
        String insert_sql = "INSERT INTO \"main\".\"ksiazki\" (\"tytul\", \"autor\", \"gatunek\", \"id_biblioteka\")"
                + "VALUES ('"+title+"','"+author+"','"+type+"',"+id_biblioteka+");";
        return data_insert(insert_sql);
    }

    // Propozycja dodania, zwraca boolean,
    // true jeśli wykonano, false gdy nie wykonano lub błąd.
    // * transakcja dla każdej tabeli oddzielnie
    // * kompatybinly - zmienne polecenie sql
    private boolean data_insert(String insert_sql){
        boolean status = false;
        try
        {
            // run transaction
            this.statement.execute("BEGIN TRANSACTION;");
            this.statement.execute(insert_sql);
            this.statement.execute("COMMIT;");
            status = true;
        }catch (SQLException e) {
            try {
                this.statement.execute("ROLLBACK;");
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return status;
    }

    // Propozycja odczytania, zwraca Vector,
    // vektor z tablicą String[], null gdy niewykonano lub błąd
    // pierwsze pole wektora zawiera nazwy kolumn.
    // * w ten sposób można odebrać każde pole oddzielnie
    // * kompatybinly - zmienne polecenie sql
    private Vector data_select(String select_sql){
        int max_column;
        Vector dane = null;
        ResultSetMetaData meta = null;
        try
        {
            // run command
            rs = statement.executeQuery(select_sql);

            dane = new Vector();

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
            e.printStackTrace();
        }
        return dane;
    }
}
