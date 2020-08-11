package db_strategy;

import java.util.Vector;

public interface Operation {
    /**
     * Interface for Strategy pattern.
     */
    Vector<?> doOperation(String...str);
}
