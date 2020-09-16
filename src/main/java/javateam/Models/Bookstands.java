package javateam.Models;

import java.util.Vector;

import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.AddBookstand;
import javateam.Data.db_strategy.ShowBookstand;

public class Bookstands
{
    public void AddBookstand(String description)
    {
        Operation operation = new AddBookstand();
        operation.doOperation(description);
    }

    public Vector<?> GetBookstands()
    {
        Operation operation = new ShowBookstand();
        return operation.doOperation();
    }
}