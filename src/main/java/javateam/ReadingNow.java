package javateam;

import db_strategy.CurrentReadingBook;
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
        int index;
        int indexPrevious;

        @Override
        public boolean hasNext() {
            return index < readingNowData.size();
        }

        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                indexPrevious = index;
                return readingNowData.get(index++);
            }
            return null;
        }

        public Object previous() {
            if(this.hasNext()){
                indexPrevious = index;
                return readingNowData.get(index--);
            }
            return null;
        }

        public Vector<?> confirm() {
            Vector<Boolean> result;
            Operation currentbook = new CurrentReadingBook();
            String idUser = User.getInstance().getUserIdAsString();

            if(indexPrevious < readingNowData.size()) {
                String idBook = String.valueOf(((String[])readingNowData.get(indexPrevious))[0]);
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
