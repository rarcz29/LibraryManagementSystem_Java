package db_strategy;

import javateam.DataSqliteController;

import java.util.Vector;

public class ShowBooks implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function returns book list.
         */
        DataSqliteController database = new DataSqliteController();
        Vector<?> result;

        String command = "SELECT * FROM book;";
        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
