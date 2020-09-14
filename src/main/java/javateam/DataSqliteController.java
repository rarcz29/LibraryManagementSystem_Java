package javateam;

import java.sql.*;
import java.util.Vector;

public class DataSqliteController {
    private static Connection connection = null;  // łącze
    private static Statement statement = null;    // polecenia
    private static ResultSet rs = null;           // odpowiedź
    private static ResultSetMetaData meta = null; // info dodatkowe
    private String url = "jdbc:sqlite:data.db";

    public DataSqliteController() {
        // get connection to database
        try {
            // load the sqlite-JDBC driver using the current class loader
            Class.forName("org.sqlite.JDBC");

            // create a database connection
            connection = DriverManager.getConnection(url);

            statement = connection.createStatement();
            statement.setQueryTimeout(20);  // set timeout to 20 sec.

            // generate new database, if not exists
            if(!this.generate_database())
                System.err.println("The database has not been created");

        } catch (SQLException e) {
            // if the error message is "out of memory", it probably means no database file is found
            e.printStackTrace();
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
    public void close(){
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

    private boolean generate_database(){
        boolean status = true;
        String[] generate_sql = new String[4];
        String user_sql;
        generate_sql[0] = "CREATE TABLE IF NOT EXISTS \"bookstand\" (\n" +
                "\"id_bookstand\"INTEGER NOT NULL,\n" +
                "\"description\"TEXT NOT NULL,\n" +
                "PRIMARY KEY(\"id_bookstand\" AUTOINCREMENT)\n" +
                ");";
        generate_sql[1] = "CREATE TABLE IF NOT EXISTS \"list\" (\n" +
                "\"id_user\"INTEGER NOT NULL,\n" +
                "\"id_book\"INTEGER NOT NULL,\n" +
                "\"status\"INTEGER NOT NULL DEFAULT 0,\n" +
                "FOREIGN KEY(\"id_user\") REFERENCES \"user\"(\"id_user\"),\n" +
                "FOREIGN KEY(\"id_book\") REFERENCES \"book\"(\"id_book\")\n" +
                ");";
        generate_sql[2] = "CREATE TABLE IF NOT EXISTS \"user\" (\n" +
                "\"id_user\"INTEGER NOT NULL,\n" +
                "\"login\"VARCHAR(45) NOT NULL UNIQUE,\n" +
                "\"password\"VARCHAR(45) NOT NULL,\n" +
                "\"acces\"TINYINT NOT NULL,\n" +
                "PRIMARY KEY(\"id_user\" AUTOINCREMENT)\n" +
                ");";
        generate_sql[3] = "CREATE TABLE IF NOT EXISTS \"book\" (\n" +
                "\"id_book\"INTEGER NOT NULL,\n" +
                "\"id_bookstand\"INTEGER NOT NULL,\n" +
                "\"title\"VARCHAR(80) NOT NULL,\n" +
                "\"author\"VARCHAR(80) NOT NULL,\n" +
                "\"data\"DATETIME NOT NULL,\n" +
                "\"type\"VARCHAR(45) NOT NULL,\n" +
                "\"description\"TEXT,\n" +
                "FOREIGN KEY(\"id_bookstand\") REFERENCES \"bookstand\"(\"id_bookstand\"),\n" +
                "PRIMARY KEY(\"id_book\" AUTOINCREMENT)\n" +
                ");";
        user_sql = "INSERT INTO \"user\" (\"id_user\",\"login\",\"password\",\"access\") VALUES (1,'admin','admin',1);";
        try
        {
            // generate
            this.statement.execute("BEGIN TRANSACTION;");
            this.statement.execute(generate_sql[0]);
            this.statement.execute(generate_sql[1]);
            this.statement.execute(generate_sql[2]);
            this.statement.execute(generate_sql[3]);
            this.statement.execute(user_sql);
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
    public boolean data_command(String command_sql){
        // returns true if done
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
    public Vector data_command_getdata(String command_sql){
        // return Vector containing data, or null
        // first row contains names of columns
        int max_column;
        Vector dane = null;
        try
        {
            // run command
            rs = statement.executeQuery(command_sql);

            dane = new Vector();

            meta = rs.getMetaData();
            max_column = meta.getColumnCount();

            String[] info= new String[max_column];

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
