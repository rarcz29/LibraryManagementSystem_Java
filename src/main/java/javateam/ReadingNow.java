package javateam;

import db_strategy.CurrentReadingBook;
import db_strategy.DelFromList;
import db_strategy.Operation;
import db_strategy.ShowList;

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

        public Vector<?> confirm() {
            Vector<Boolean> result;
            Operation currentbook = new CurrentReadingBook();
            String idUser = User.getInstance().getUserIdAsString();

            if(index < readingNowData.size() && index >= 0) {
                String idBook = String.valueOf(((String[])readingNowData.get(index))[0]);
                result = (Vector<Boolean>) currentbook.doOperation(idUser, idBook);
            }
            else {
                result = new Vector<>();
                result.add(false);
            }
            return result;
        }

        public Vector<?> delete() {
            Vector<Boolean> result;
            Operation currentbook = new DelFromList();
            String idUser = User.getInstance().getUserIdAsString();

            if(index < readingNowData.size() && index >= 0) {
                String idBook = String.valueOf(((String[])readingNowData.get(index))[0]);
                result = (Vector<Boolean>) currentbook.doOperation(idUser, idBook);
            }
            else {
                result = new Vector<>();
                result.add(false);
            }
            return result;
        }

    }
}