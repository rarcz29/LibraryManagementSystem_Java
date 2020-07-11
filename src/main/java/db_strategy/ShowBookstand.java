package db_strategy;

import javateam.DataSqliteController;
import java.util.Vector;

public class ShowBookstand implements Operation{
    @Override
    public Vector<?> doOperation(String...str){

        DataSqliteController database = new DataSqliteController();
        Vector<?> result;

        String command = "SELECT * FROM bookstand;";

        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
