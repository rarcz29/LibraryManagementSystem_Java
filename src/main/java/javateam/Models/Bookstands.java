package javateam.Models;

import java.util.Vector;

import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.AddBookstand;

public class Bookstands
{
    public void AddBookstand(String description)
    {
        Operation operation = new AddBookstand();
        operation.doOperation(description);
    }
}