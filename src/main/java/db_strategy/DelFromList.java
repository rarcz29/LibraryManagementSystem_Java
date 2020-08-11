package db_strategy;

import javateam.DataSqliteController;

import java.util.Vector;

public class DelFromList implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function delete book from list for reading.
          Accepts ID ID User and ID Book.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_user = str[0];
        String id_book = str[1];

        String command = "DELETE FROM list WHERE id_user="+id_user+" and id_book="+id_book+";";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
