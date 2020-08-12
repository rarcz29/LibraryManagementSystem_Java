package home_model;

import java.util.Vector;

public interface IHome
{
    Vector<?> search(String title, String author);
    void addBook(String title, String author, String type,
                      String description, String bookstandId);
    void addToMyList(String userId, String bookId);
    void removeBook(String bookId);
}