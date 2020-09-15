package javateam;

import db_strategy.AddUser;
import db_strategy.Operation;

import java.util.Vector;

public class Register {
    public boolean regMethod(String login, String password){
        Operation operation = new AddUser();
        Vector result = operation.doOperation(login, password);

        return (boolean)result.get(0);
    }
}
