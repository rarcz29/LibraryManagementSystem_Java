package javateam.Models;

import java.util.Vector;

import javateam.Data.db_strategy.DelFromList;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.ShowList;

public class MyList
{
    public Vector<?> getList()
    {
        Operation operation = new ShowList();
        return operation.doOperation();
    }

    public void removeBookFromList(String userId, String bookId)
    {
        Operation operation = new DelFromList();
        operation.doOperation(userId, bookId);
    }
}
