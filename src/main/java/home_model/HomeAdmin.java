package home_model;

import db_strategy.*;

public class HomeAdmin implements IHome
{
    string[][] void search(string title, string author)
    {
        Operation operation = new Search();
        var result = operation.doOperation(title, author);
    }

    public void addBook()
    {

    }

    public void addToMyList()
    {

    }

    public void removeBooks()
    {

    }
}