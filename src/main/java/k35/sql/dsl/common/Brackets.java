package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.Arrays;
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
    public static Brackets around() {
        return new Brackets("()");
    }

    /**
     * Create expression in brackets
     *
     * @param - sql expression
     * @return
     */
    public static Brackets around(String expr) {
        return new Brackets("(" + expr + ")");
    }

    /**
     * Create expression in brackets
     *
     * @param exprs
     * @return
     */
    public static Brackets around(String... exprs) {
        return around(Arrays.stream(exprs).collect(Collectors.joining(", ")));
    }


    /**
     * Create expression in brackets
     *
     * @param - sql builder
     * @return
     */
    public static Brackets around(SqlBuilder builder) {
        return around(builder.sql());
    }

    /**
     * Create expression in brackets
     *
     * @param builders
     * @return
     */
    public static Brackets around(SqlBuilder... builders) {
        return around(Arrays.stream(builders).map(SqlBuilder::sql).toArray(String[]::new));
    }

    @Override
    public String sql() {
        return this.value;
    }

}
