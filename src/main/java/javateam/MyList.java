package javateam;

import java.util.Vector;

import db_strategy.DelFromList;
import db_strategy.Operation;
import db_strategy.ShowList;

public class MyList
{
    public Vector<?> getList()
    {
        Operation operation = new ShowList();
        return operation.doOperation();
    }
}
