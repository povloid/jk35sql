package k35.sql.dsl.dml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import k35.sql.dsl.common.Condition;
import k35.sql.dsl.common.Parameter;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

public final class Update implements SqlBuilder, Map<Update> {

	private final String table;
	private final List<SetValue> sets;
	private final Optional<Condition> condition;
	private final Optional<List<String>> returninig;

	private Update(String table, List<SetValue> sets, Optional<Condition> condition,
			Optional<List<String>> returninig) {
		super();
		this.table = table;
		this.sets = sets;
		this.condition = condition;
		this.returninig = returninig;
	}

	public static Update update(String table) {
		return new Update(table, new ArrayList<>(), Optional.empty(), Optional.empty());
	}

	private final record SetValue(String field, String parameter) implements SqlBuilder {
		@Override
		public String buildSql() {
			return field + " = " + parameter;
		}
	}

	public Update setValue(Parameter parameter) {
		return setValue(parameter.field(), parameter.parameter());
	}

	public Update setValue(String field) {
		return setValue(field, ":" + field);
	}

	public Update setValue(String field, String parameter) {
		final var newSets = new ArrayList<>(this.sets);
		newSets.add(new SetValue(field, parameter));

		return new Update(table, newSets, condition, returninig);
	}

	public Update where(String expression) {
		return where(Condition.expression(expression));
	}

	public Update where(Condition condition) {
		return new Update(table, sets, Optional.ofNullable(condition), returninig);
	}

	public Update returning(String... fields) {
		return new Update(table, sets, condition, Optional.of(Arrays.asList(fields)));
	}

	public Update map(UnaryOperator<Update> fn) {
		final var newUpdate = new Update(table, sets, condition, returninig);
		return fn.apply(newUpdate);
	}

	@Override
	public String buildSql() {

		final String setSqlStr = "set " + sets.stream().map(SetValue::buildSql).collect(Collectors.joining(", "));

		final String whereSqlStr = this.condition.map(o -> "where " + o.buildSql()).orElse("");

		final String returningSqlStr = returninig
				.map(list -> "returning " + list.stream().collect(Collectors.joining(", "))).orElse("");

		return Arrays.asList("update", table, setSqlStr, whereSqlStr, returningSqlStr).stream()
				.filter(o -> !o.isBlank()).map(String::trim).collect(Collectors.joining(" "));
	}

}
