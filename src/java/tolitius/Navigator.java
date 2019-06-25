package tolitius;

import java.util.List;
import java.util.Map;

public interface Navigator<T> {
    T getIn(Object data, List path);
}
