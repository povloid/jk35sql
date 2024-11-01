package k35.sql.dsl.dml;

import java.util.Optional;

import k35.sql.dsl.common.Condition;
import k35.sql.dsl.interfaces.SqlBuilder;

public final class Join implements SqlBuilder {

	private enum JoinType {
		LEFT("left"),
		RIGHT("right"),
		INNER("inner"),
		FULL("full");

		public final String sql;

		JoinType(String sql) {
			this.sql = sql;
		}
	}

	private final JoinType joinType;
	private final String table;
	private final Optional<Condition> on;

	private Join(JoinType joinType, String table, Optional<Condition> on) {
		this.joinType = joinType;
		this.table = table;
		this.on = on;
	}

	public static Join leftJoin(String table) {
		return new Join(JoinType.LEFT, table, Optional.empty());
	}

	public static Join rightJoin(String table) {
		return new Join(JoinType.RIGHT, table, Optional.empty());
	}

	public static Join innerJoin(String table) {
		return new Join(JoinType.INNER, table, Optional.empty());
	}

	public static Join fullJoin(String table) {
		return new Join(JoinType.FULL, table, Optional.empty());
	}

	public Join on(String expression) {
		return on(Condition.expression(expression));
	}

	public Join on(Condition condition) {
		return new Join(joinType, table, Optional.ofNullable(condition));
	}

	public String buildSql() {

		final String onSqlStr = this.on.map(o -> " on " + o.buildSql()).orElse("");

		return joinType.sql + " join " + table + onSqlStr;
	}

}
