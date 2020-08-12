package home_model;

import java.util.Vector;

public interface IHome
{
    Vector<?> search(String title, String author);
    Vector<?> addBook(String tittle, String author, String type,
                 String description, String bookstandId);
    Vector<?> addToMyList();
    Vector<?> removeBooks();
}