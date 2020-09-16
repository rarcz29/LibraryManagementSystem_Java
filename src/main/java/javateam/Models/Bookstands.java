package javateam.Models;

import java.util.Vector;

import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.AddBookstand;

public class Bookstands
{
    public boolean AddBookstand(String description)
    {
        Operation operation = new AddBookstand();
        var result = operation.doOperation(description);

        System.out.println(result.size());

        if(result.size() == 132)
            return true;

        return false;
    }
}