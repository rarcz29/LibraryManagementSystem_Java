package db_strategy;

import java.util.Vector;

public interface Operation {
    Vector<?> doOperation(String...str);
}
