package db_strategy;

import javateam.DataSqliteController;

import java.util.Vector;

public class DelBookstand implements Operation{
    @Override
    public Vector<?> doOperation(String...str){

        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_bookstand = str[0];

        String command = "DELETE FROM bookstand WHERE id_bookstand="+id_bookstand+";";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
