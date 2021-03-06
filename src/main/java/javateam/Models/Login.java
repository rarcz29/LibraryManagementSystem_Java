package javateam.Models;

import javateam.User;
import javateam.Data.db_strategy.LogUser;
import javateam.Data.db_strategy.Operation;

import java.util.Vector;

public class Login {
    public boolean logMethod(String login, String password)
    {
        Operation operation = new LogUser();
        Vector<?> found = operation.doOperation(login, password);

        if(found.size() == 2){
            User user = User.getInstance();
            String[] tab = (String[])found.get(1);

            int id_user = Integer.parseInt(tab[0]);
            boolean is_admin = tab[2].equals("1") ? true : false;

            user.setData(login, id_user, is_admin);
            return true;
        }

        return false;
    }
}
