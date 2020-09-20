package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;

import java.util.Vector;

public class DelCurrentReadingBook implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function deletes current reading book from list.
          Accepts User ID.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_user = str[0];

        String command = "DELETE FROM list WHERE id_user="+id_user+" AND status=1;";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
