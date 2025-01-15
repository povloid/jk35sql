package k35.sql.dsl.dml;

import k35.sql.dsl.common.Predicate;
import k35.sql.dsl.common.Table;
import k35.sql.dsl.interfaces.SqlBuilder;

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
    private final Optional<String> on;

    private Join(JoinType joinType, String table, Optional<String> on) {
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

    public static Join leftJoin(Table table) {
        return leftJoin(table.sql());
    }

    public static Join rightJoin(Table table) {
        return rightJoin(table.sql());
    }

    public static Join innerJoin(Table table) {
        return innerJoin(table.sql());
    }

    public static Join fullJoin(Table table) {
        return fullJoin(table.sql());
    }

    public Join on(String expression) {
        return new Join(joinType, table, Optional.ofNullable(expression));
    }

    public Join on(Predicate predicate) {
        return on(predicate.sql());
    }

    /**
     * Build sql
     */
    @Override
    public String sql() {

        final String onSqlStr = this.on.map(o -> " on " + o).orElse("");

        return joinType.sql + " join " + table + onSqlStr;
    }

}
