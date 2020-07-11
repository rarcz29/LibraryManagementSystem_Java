package db_strategy;

import javateam.DataSqliteController;
import java.util.Vector;

public class ShowList implements Operation{
    @Override
    public Vector<?> doOperation(String...str){

        DataSqliteController database = new DataSqliteController();
        Vector<?> result;
        String id_user = str[0];

        String command = "SELECT book.id_book, book.id_bookstand, book.title, book.author, book.data, book.type, book.description" +
                " FROM book LEFT JOIN list ON book.id_book=list.id_book WHERE list.id_user="+id_user+";";

        result = database.data_command_getdata(command);

        database.close();

        return result;
    }
}
