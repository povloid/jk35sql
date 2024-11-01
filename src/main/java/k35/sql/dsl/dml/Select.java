package k35.sql.dsl.dml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import k35.sql.dsl.common.Condition;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

public final class Select implements SqlBuilder, Map<Select> {

	private final List<String> fields;
	private final Optional<String> table;
	private final Optional<List<Join>> joins;
	private final Optional<Condition> where;
	/**
	 * False is ASC True is DESC
	 */
	private final Optional<Boolean> orderByDirection;
	private final Optional<String> limitParameter;
	private final Optional<String> offsetParameter;
	private final Optional<String[]> groupByFields;

	public Select(
			List<String> fields,
			Optional<String> table,
			Optional<List<Join>> joins,
			Optional<Condition> where,
			Optional<String[]> groupByFields,
			Optional<String[]> orderByFields,
			Optional<Boolean> orderByDirection,
			Optional<String> limitParameter,
			Optional<String> offsetParameter) {
		super();
		this.fields = fields;
		this.table = table;
		this.joins = joins;
		this.where = where;
		this.limitParameter = limitParameter;
		this.offsetParameter = offsetParameter;
		this.groupByFields = groupByFields;
		this.orderByFields = orderByFields;
		this.orderByDirection = orderByDirection;
	}

	public static Select select(String... fields) {
		return new Select(Arrays.asList(fields), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
	}

	public Select from(String table) {

		return new Select(fields,
				Optional.ofNullable(table),
				joins,
				where,
				groupByFields,
				orderByFields,
				orderByDirection,
				limitParameter,
				offsetParameter);
	}

	public record JoinChain(Select select, Join join) {
		public Select on(Condition condition) {

			final var joins = new ArrayList<>(select.joins.orElse(new ArrayList<>()));
			joins.add(join.on(condition));

			return new Select(select.fields,
					select.table,
					Optional.of(joins),
					select.where,
					select.groupByFields,
					select.orderByFields,
					select.orderByDirection,
					select.limitParameter,
					select.offsetParameter);
		}

		public Select on(String expression) {
			return on(Condition.expression(expression));
		}
	}

	public JoinChain innerJoin(String table) {
		return new JoinChain(this, Join.innerJoin(table));
	}

	public JoinChain leftJoin(String table) {
		return new JoinChain(this, Join.leftJoin(table));
	}

	public JoinChain rightJoin(String table) {
		return new JoinChain(this, Join.rightJoin(table));
	}

	public JoinChain fullJoin(String table) {
		return new JoinChain(this, Join.fullJoin(table));
	}

	public Select where(String expression) {
		return where(Condition.expression(expression));
	}

	public Select where(Condition condition) {
		return new Select(fields,
				table,
				joins,
				Optional.ofNullable(condition),
				groupByFields,
				orderByFields,
				orderByDirection,
				limitParameter,
				offsetParameter);
	}

	public Select limit(String parameter) {

		return new Select(fields,
				table,
				joins,
				where,
				groupByFields,
				orderByFields,
				orderByDirection,
				Optional.ofNullable(parameter),
				offsetParameter);
	}

	public Select offset(String offset) {
		return new Select(fields,
				table,
				joins,
				where,
				groupByFields,
				orderByFields,
				orderByDirection,
				limitParameter,
				Optional.ofNullable(offset));
	}

	private Optional<String[]> orderByFields = Optional.empty();

	public Select orderBy(String... fields) {
		return new Select(this.fields,
				table,
				joins,
				where,
				groupByFields,
				Optional.ofNullable(fields),
				orderByDirection,
				limitParameter,
				offsetParameter);
	}

	public Select asc() {
		return new Select(fields,
				table,
				joins,
				where,
				groupByFields,
				orderByFields,
				Optional.of(false),
				limitParameter,
				offsetParameter);
	}

	public Select desc() {
		return new Select(fields,
				table,
				joins,
				where,
				groupByFields,
				orderByFields,
				Optional.of(true),
				limitParameter,
				offsetParameter);
	}

	public Select groupBy(String... fields) {
		return new Select(this.fields,
				table,
				joins,
				where,
				Optional.ofNullable(fields),
				orderByFields,
				orderByDirection,
				limitParameter,
				offsetParameter);
	}

	public Select map(UnaryOperator<Select> f) {
		final var newSelect = new Select(this.fields,
				table,
				joins,
				where,
				groupByFields,
				orderByFields,
				orderByDirection,
				limitParameter,
				offsetParameter);

		return f.apply(newSelect);
	}

	public String buildSql() {

		final String fieldsSqlStr = fields.isEmpty()
				? "*"
				: fields.stream().collect(Collectors.joining(", "));

		final String tableSqlStr = this.table.map(String::trim).orElse("");

		final String joinsSqlStr = joins
				.map(items -> items.stream().map(Join::buildSql).collect(Collectors.joining(" ")))
				.orElse("");

		final String whereSqlStr = this.where.map(o -> "where " + o.buildSql()).orElse("");

		final String groupBySqlStr = this.groupByFields
				.map(o -> "group by " + Arrays.asList(o)
						.stream()
						.collect(Collectors.joining(", ")))
				.orElse("");

		final String orderBySqlStr = this.orderByFields
				.map(o -> "order by " + Arrays.asList(o).stream().collect(Collectors.joining(", "))
						+ orderByDirection.map(isDesc -> Boolean.TRUE.equals(isDesc) ? " desc" : " asc").orElse(""))
				.orElse("");

		final String limitSqlStr = limitParameter.map(o -> "limit " + o).orElse("");

		final String offsetSqlStr = offsetParameter.map(o -> "offset " + o).orElse("");

		return Arrays
				.asList("select", fieldsSqlStr,
						"from", tableSqlStr,
						joinsSqlStr,
						whereSqlStr,
						groupBySqlStr,
						orderBySqlStr,
						limitSqlStr,
						offsetSqlStr)
				.stream()
				.filter(o -> !o.isBlank())
				.map(String::trim)
				.collect(Collectors.joining(" "));
	}

}
