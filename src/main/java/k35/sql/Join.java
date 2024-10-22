package k35.sql;

import java.util.Optional;

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

    private Join(JoinType joinType, String table) {
        this.joinType = joinType;
        this.table = table;
    }

    public static Join leftJoin(String table) {
        return new Join(JoinType.LEFT, table);
    }

    public static Join rightJoin(String table) {
        return new Join(JoinType.RIGHT, table);
    }

    public static Join innerJoin(String table) {
        return new Join(JoinType.INNER, table);
    }

    public static Join fullJoin(String table) {
        return new Join(JoinType.FULL, table);
    }

    private Optional<Condition> on = Optional.empty();

    public Join on(String expression) {
        return on(Condition.expression(expression));
    }

    public Join on(Condition condition) {
        this.on = Optional.ofNullable(condition);
        return this;
    }

    public String buildSql() {

        final String onSqlStr = this.on.map(o -> " on " + o.buildSql()).orElse("");

        return joinType.sql + " join " + table + onSqlStr;
    }

}
