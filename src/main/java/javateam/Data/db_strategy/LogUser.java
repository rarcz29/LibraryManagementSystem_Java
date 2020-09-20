package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;

import java.util.Vector;

public class LogUser implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function for login user.
          Accepts Login and Password.
          */
        DataSqliteController database = new DataSqliteController();
        String login = str[0];
        String password = str[1];

        String command = "SELECT id_user, login, access FROM user WHERE login='" + login + "' and password='" + password + "';";
        Vector<?> result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
