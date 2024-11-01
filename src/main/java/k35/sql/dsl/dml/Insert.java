package k35.sql.dsl.dml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import k35.sql.dsl.common.Parameter;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

public final class Insert implements SqlBuilder, Map<Insert> {

	private final String table;
	private final List<Value> values;
	private final Optional<List<String>> returninig;

	private Insert(String table, List<Value> values, Optional<List<String>> returninig) {
		super();
		this.table = table;
		this.values = values;
		this.returninig = returninig;
	}

	public static Insert into(String table) {
		return new Insert(table, new ArrayList<>(), Optional.empty());
	}

	private record Value(String field, String parameter) {

	}

	public Insert value(Parameter value) {
		return value(value.field(), value.parameter());
	}

	public Insert value(String field, String parameter) {
		final var newValues = new ArrayList<>(this.values);
		newValues.add(new Value(field, parameter));

		return new Insert(table, newValues, returninig);
	}

	public Insert returning(String... fields) {
		return new Insert(table, values, Optional.of(Arrays.asList(fields)));
	}

	public Insert map(UnaryOperator<Insert> fn) {
		final var newInsert = new Insert(table, values, returninig);
		return fn.apply(newInsert);
	}

	@Override
	public String buildSql() {

		final String fieldsSqlStr = this.values.isEmpty() ? ""
				: "( " + this.values.stream().map(Value::field).collect(Collectors.joining(", ")) + " )";

		final String valuesSqlStr = this.values.isEmpty() ? ""
				: "values ( " + this.values.stream().map(Value::parameter).collect(Collectors.joining(", ")) + " )";

		final String returningSqlStr = returninig
				.map(list -> "returning " + list.stream().collect(Collectors.joining(", "))).orElse("");

		return Arrays.asList("insert into", table, fieldsSqlStr, valuesSqlStr, returningSqlStr).stream()
				.filter(o -> !o.isBlank()).map(String::trim).collect(Collectors.joining(" "));
	}

}
