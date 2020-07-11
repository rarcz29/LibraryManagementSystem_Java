package db_strategy;

import javateam.DataSqliteController;
import java.util.Vector;

public class AddBook implements Operation{
    @Override
    public Vector<Object> doOperation(String...str){

        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_bookstand = str[0];
        String title = str[1];
        String author = str[2];
        String type = str[3];
        String description = str[4];

        String command = "INSERT INTO book (id_bookstand, title, author, data, type, description) " +
                            "VALUES ("+id_bookstand+", '"+title+"', '"+author+"', now(), '"+type+"', '"+description+"');";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
