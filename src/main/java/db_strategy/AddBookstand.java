package db_strategy;

import javateam.DataSqliteController;

import java.util.Vector;

public class AddBookstand implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function adding bookstand.
          Accepts Description.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String description = str[0];

        String command = "INSERT INTO bookstand (description) VALUES ('"+description+"');";
        result.add(database.data_command(command));

        database.close();

        return result;
    }
}
