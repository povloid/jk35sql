package k35.sql.dsl.dml;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import k35.sql.dsl.common.Condition;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

public final class Delete implements SqlBuilder, Map<Delete> {

	private final String table;
	private final Optional<Condition> condition;
	private final Optional<List<String>> returninig;

	private Delete(String table, Optional<Condition> condition, Optional<List<String>> returninig) {
		super();
		this.table = table;
		this.condition = condition;
		this.returninig = returninig;
	}

	public static Delete from(String table) {
		return new Delete(table, Optional.empty(), Optional.empty());
	}

	public Delete where(String expression) {
		return where(Condition.expression(expression));
	}

	public Delete where(Condition condition) {
		return new Delete(table, Optional.ofNullable(condition), returninig);
	}

	public Delete returning(String... fields) {
		return new Delete(table, condition, Optional.of(Arrays.asList(fields)));
	}

	public Delete map(UnaryOperator<Delete> fn) {
		final var newDelete = new Delete(table, condition, returninig);
		return fn.apply(newDelete);
	}

	@Override
	public String buildSql() {
		final String whereSqlStr = this.condition.map(o -> "where " + o.buildSql()).orElse("");

		final String returningSqlStr = returninig
				.map(list -> "returning " + list.stream().collect(Collectors.joining(", "))).orElse("");

		return Arrays.asList("delete from", table, whereSqlStr, returningSqlStr).stream().filter(o -> !o.isBlank())
				.map(String::trim).collect(Collectors.joining(" "));

	}

}
