package db_strategy;

import javateam.DataSqliteController;

import java.util.Vector;

public class LogUser implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function for login user.
          Accepts Login and Password.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String login = str[0];
        String password = str[1];

        String command = "SELECT * FROM user WHERE login='" + login + "' and password='" + password + "';";
        Vector<?> found = database.data_command_getdata(command);

        result.add(found.size() == 2); //TO CHECK

        database.close();

        return result;
    }
}
