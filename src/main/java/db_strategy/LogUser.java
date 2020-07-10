package db_strategy;

import javateam.DataSqliteController;
import java.util.Vector;

public class LogUser implements Operation{
    @Override
    public Vector<Object> doOperation(String...str){

        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        Vector<?> sum;
        String login = str[0];
        String password = str[1];

        String command = "SELECT COUNT(*) FROM user WHERE login='" + login + "' and password='" + password + "';";
        sum = database.data_command_getdata(command);

        String[] tab = (String[])sum.get(1);
        int found = Integer.parseInt(tab[0]);

        if(found == 1) result.add(true);
        else result.add(false);

        database.close();

        return result;
    }
}
