package javateam.Data.db_strategy;

import javateam.Data.DataSqliteController;

import java.util.Vector;

public class CurrentReadingBook implements Operation{
    @Override
    public Vector<?> doOperation(String...str){
        /*
          Function sets book as 'Current reading book'.
          Accepts ID User and ID Book.
          */
        DataSqliteController database = new DataSqliteController();
        Vector<Object> result = new Vector<>();
        String id_user = str[0];
        String id_book = str[1];

        String command = "SELECT * FROM list WHERE id_user="+id_user+" and id_book="+id_book+";";
        Vector<?> found = database.data_command_getdata(command);

        if(found.size() > 1) {

            command = "SELECT * FROM list WHERE id_book=" + id_book + " and status=1;";
            Vector<?> test = database.data_command_getdata(command);

            if (test.size() == 1) {
                command = "UPDATE list SET status=0 WHERE id_user=" + id_user + " and status=1;";
                System.out.println(database.data_command(command));
                command = "UPDATE list SET status=1 WHERE id_user=" + id_user + " and id_book=" + id_book + ";";
                result.add(database.data_command(command));
            } else {
                //System.out.println("Wybrany użytkownik lub książka");
                result.add(false);
            }
        }
        else result.add(false);

        database.close();

        return result;
    }
}
