package db_strategy;

import java.util.Vector;

public interface Operation {
    public Vector<Object> doOperation(String...str);
}
