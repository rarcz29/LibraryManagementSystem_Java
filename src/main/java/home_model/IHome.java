package home_model;

public interface IHome
{
    string[][] search(string title, string author);
    void addBook(string tittle, string author, string type,
                 string description, string bookstandId);
    void addToMyList();
    void removeBooks();
}