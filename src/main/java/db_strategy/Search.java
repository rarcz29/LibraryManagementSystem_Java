package db_strategy;

import javateam.DataSqliteController;
import java.util.Vector;

public class Search implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function returns a list of matching results
          Accepts Title and Author.
         */
        DataSqliteController database = new DataSqliteController();
        Vector<?> result;
        String title = str[0];
        String author = str[1];

        String command = "SELECT * FROM book WHERE title like '%"+title+"%' and author like '%"+author+"%';";

        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
