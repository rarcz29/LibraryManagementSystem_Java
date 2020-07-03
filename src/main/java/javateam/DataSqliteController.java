package javateam;

import java.sql.*;
import java.util.Vector;

// data.db:
//     book: id_book(int/AI), author(varchar(45)), data(datetime), description(text/null), id_bookstand(int), title(varchar(45)), type(varchar(45))
//     bookstand: id_bookstand(int/AI), description(text/null)
//     list: id_book(int), id_user(int), status(int)
//     user: id_user(int/AI), acces(int), login(varchar(45)), password(varchar(45))
//   * sqlite_sequence: name(*), seq(*)

public class DataSqliteController {
    private static Connection connection = null;  // łącze
    private static Statement statement = null;    // polecenia
    private static ResultSet rs = null;           // odpowiedź
    private static ResultSetMetaData meta = null; // info dodatkowe

    DataSqliteController() {
        // get connection to database
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
    void close(){
        // close connection to database
        try {
            if (this.connection != null)
                if (this.statement != null)
                    if (this.rs != null)
                        this.rs.close();
                    this.statement.close();
                this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // TODO dodać polecenia generujące bazę
    boolean generate_database(){
        String generate_sql = "";
        return this.data_command(generate_sql);
    }

    //przykładowe
    public Vector data_get_book(){
        String select_sql = "select * from book;";
        return data_command_get(select_sql);
    }

    //przykładowe
    public boolean data_insert_book(String title, String author, String type, String desrciption, String data, String id_bookstand){ //przykładowe
        String insert_sql = "INSERT INTO \"main\".\"book\" (\"title\", \"author\", \"type\", \"description\", \"data\", \"id_bookstand\")"
                + "VALUES ('"+title+"', '"+author+"', '"+type+"', '"+desrciption+"', '"+data+"', "+id_bookstand+");";
        return data_command(insert_sql);
    }

    public boolean data_command(String command_sql){
        // Bez odczytu, zwraca boolean,
        // true jeśli wykonano, false gdy nie wykonano lub błąd.
        boolean status = false;
        try
        {
            // run transaction
            this.statement.execute("BEGIN TRANSACTION;");
            this.statement.execute(command_sql);
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

    public Vector data_command_get(String command_sql){
        // Odczyt tabel, zwraca Vector,
        // vektor z tablicą String[], null gdy niewykonano lub błąd
        // pierwsze pole wektora zawiera nazwy kolumn.
        int max_column;
        Vector dane = null;
        try
        {
            // run command
            rs = statement.executeQuery(command_sql);

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
