package db_strategy;


import javateam.DataSqliteController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class AddBook implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function adding book to book list.
          Accepts ID Bookstand, Title, Author, Type and Description.
          */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_bookstand = str[0];
        String title = str[1];
        String author = str[2];
        String type = str[3];
        String description = str[4];

        String command = "SELECT * FROM bookstand WHERE id_bookstand=" + id_bookstand + ";";
        Vector<?> found = database.data_command_getdata(command);

        if(found.size() == 2){
            command = "INSERT INTO book (id_bookstand, title, author, data, type, description) " +
                    "VALUES ('"+id_bookstand+"', '"+title+"', '"+author+"', '"+dtf.format(now)+"', '"+type+"', '"+description+"');";
            result.add(database.data_command(command));
        }
        else{
            System.out.println("Podany regal nie istnieje!");
            result.add(false);
        }

        database.close();

        return result;
    }
}
