package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;

import java.util.Vector;

public class ShowBookstand implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function returns book stands list.
         */
        DataSqliteController database = new DataSqliteController();
        Vector<?> result;

        String command = "SELECT * FROM bookstand;";

        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
