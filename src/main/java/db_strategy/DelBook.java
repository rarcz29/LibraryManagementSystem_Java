package db_strategy;

import javateam.DataSqliteController;
import java.util.Vector;

public class DelBook implements Operation{
    @Override
    public Vector<?> doOperation(String...str){

        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_book = str[0];

        String command = "DELETE FROM book WHERE id_book="+id_book+";";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
