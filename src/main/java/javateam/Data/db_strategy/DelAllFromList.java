package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;

import java.util.Vector;

public class DelAllFromList implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function delete book from list for reading.
          Accepts ID Book.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_book = str[0];

        String command = "DELETE FROM list WHERE id_book="+id_book+";";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
