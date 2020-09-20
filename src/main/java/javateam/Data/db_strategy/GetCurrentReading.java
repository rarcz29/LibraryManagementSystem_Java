package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;

import java.util.Vector;

public class GetCurrentReading implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function returns current reading book.
          Accepts User ID.
         */
        DataSqliteController database = new DataSqliteController();
        Vector<?> result;
        String user_id = str[0];

        String command = "SELECT book.title, book.author " +
                "FROM book INNER JOIN list " +
                "ON book.id_book=list.id_book " +
                "WHERE list.id_user="+user_id+" AND " +
                "list.status=1;";

        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
