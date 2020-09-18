package javateam.Models;

import javateam.Data.db_strategy.CurrentReadingBook;
import javateam.Data.db_strategy.DelFromList;
import javateam.Data.db_strategy.Operation;
import javateam.Data.db_strategy.ShowList;
import javateam.Data.db_strategy.GetCurrentReading;
import javateam.User;

import java.util.Iterator;
import java.util.Vector;

interface Container {
    ReadingNow.ReadingNowIterator getReadingNowIterator();
}

public class ReadingNow implements Container{
    Operation getData = new ShowList();
    String userId = User.getInstance().getUserIdAsString();
    private Vector<?> readingNowData = getData.doOperation(userId);

    public ReadingNow(){
        readingNowData.remove(0);
    }

    public ReadingNowIterator getReadingNowIterator(){
        return new ReadingNowIterator();
    }

    public String getCurrentReadingBook()
    {
        Operation operation = new GetCurrentReading();
        var result = operation.doOperation(User.getInstance().getUserIdAsString());

        if (result.size() > 1)
        {
            String[] arr = (String[])result.get(1);
            return "\"" + arr[0] + "\" by " + arr[1];
        }

        return "Notning";
    }

    public class ReadingNowIterator implements Iterator {
        int index = -1;
        //int indexPrevious = 0;

        @Override
        public boolean hasNext() {
            return index < readingNowData.size() - 1;
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Object next() {
            //if (index < 0) index ++;
            if(this.hasNext()){
                //indexPrevious = index;
                return readingNowData.get(++index);
            }
            return readingNowData.get(index);
        }

        public Object previous() {
            //if (index >= readingNowData.size()) index --;
            if(this.hasPrevious()){
                //indexPrevious = index;
                return readingNowData.get(--index);
            }
            return readingNowData.get(index);
        }

        public boolean confirm() {
            boolean result;
            Operation currentbook = new CurrentReadingBook();
            String idUser = User.getInstance().getUserIdAsString();

            if(index < readingNowData.size() && index >= 0) {
                String idBook = String.valueOf(((String[])readingNowData.get(index))[0]);
                result = ((Vector<Boolean>)currentbook.doOperation(idUser, idBook)).get(0);
            }
            else {
                result = false;
            }
            return result;
        }

        public boolean delete() {
            boolean result;
            Operation currentbook = new DelFromList();
            String idUser = User.getInstance().getUserIdAsString();

            if(index < readingNowData.size() && index >= 0) {
                String idBook = String.valueOf(((String[])readingNowData.get(index))[0]);
                result = (Vector<Boolean>)currentbook.doOperation(idUser, idBook).get(0);
            }
            else {
                result = false;
            }
            return result;
        }

    }
}
