package k35.sql.dsl.interfaces;

import java.util.function.UnaryOperator;

public interface Map<T> {

	public T map(UnaryOperator<T> fn);

}
