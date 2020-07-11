package db_strategy;

import javateam.DataSqliteController;

import java.util.Vector;

public class AddToList  implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function adding book to list for reading.
          Accepts ID User and ID Book.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_user = str[0];
        String id_book = str[1];

        String command = "INSERT INTO list (id_user, id_book, status) " +
                        "VALUES ("+id_user+", "+id_book+", 0);";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
