package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SQL In Brackets...
 */
public final class Brackets implements SqlBuilder {

    private final String value;

    private Brackets(String value) {
        this.value = value;
    }

    /**
     * Create expression in brackets
     *
     * @param - sql expression
     * @return
     */
    public static Brackets in(String expr) {
        return new Brackets("( " + expr + " )");
    }

    /**
     * Create expression in brackets
     *
     * @param - sql builder
     * @return
     */
    public static Brackets in(SqlBuilder... builders) {
        return in(List.of(builders).stream().map(SqlBuilder::sql).collect(Collectors.joining(",")));
    }

    @Override
    public String sql() {
        return this.value;
    }

}
