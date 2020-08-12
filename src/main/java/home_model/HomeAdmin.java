package home_model;

import db_strategy.Search;
import db_strategy.AddBook;

public class HomeAdmin implements IHome
{
    public Vector<?> search(String title, String author)
    {
        Operation operation = new Search();
        return operation.doOperation(title, author);
    }

    public Vector<?> addBook(String tittle, String author, String type,
                             String description, String bookstandId)
    {
        Operation operation = new AddBook();
        return operation.doOperation();
    }

    public Vector<?> addToMyList()
    {

    }

    public Vector<?> removeBooks()
    {

    }
}