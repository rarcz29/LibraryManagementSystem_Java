package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;
import java.util.Vector;

public class Search implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function returns a list of matching results
          Accepts Title, Author, Type and BookstandId.
         */
        DataSqliteController database = new DataSqliteController();
        Vector<?> result;
        String title = str[0];
        String author = str[1];
        String genre = str[2];
        String bookstandId = str[3];
        String command;

        if (bookstandId.isEmpty())
        {
            command = "SELECT * FROM book WHERE " +
                    "title like '%"+title+"%' and " +
                    "author like '%"+author+"%' and " +
                    "type like '%"+genre+"%'";
        }
        else
        {
            command = "SELECT * FROM book WHERE " +
                    "title like '%"+title+"%' and " +
                    "author like '%"+author+"%' and " +
                    "type like '%"+genre+"%' and " +
                    "id_bookstand like '"+bookstandId+"'";
        }

        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
