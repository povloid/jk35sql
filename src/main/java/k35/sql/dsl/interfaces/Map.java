package k35.sql.dsl.interfaces;

import java.util.function.UnaryOperator;

public interface Map<T> {

    T map(UnaryOperator<T> fn);

}
