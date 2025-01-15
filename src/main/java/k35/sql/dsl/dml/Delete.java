package k35.sql.dsl.dml;

import k35.sql.dsl.common.Predicate;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * '
 * SQL Delete...
 */
public final class Delete implements SqlBuilder, Map<Delete> {

    private final String table;
    private final Optional<String> where;
    private final List<String> returning;

    private Delete(String table, Optional<String> where, List<String> returning) {
        super();
        this.table = table;
        this.where = where;
        this.returning = returning;
    }

    public static Delete from(String table) {
        return new Delete(table, Optional.empty(), List.of());
    }

    public Delete where(String condition) {
        return new Delete(
                table,
                Optional.ofNullable(condition).filter(s -> !s.isBlank()),
                returning);
    }

    public Delete where(Predicate predicate) {
        return where(predicate.sql());
    }

    public Delete returning(String... fields) {
        return new Delete(table, where, List.of(fields));
    }

    public Delete map(UnaryOperator<Delete> fn) {
        final var newDelete = new Delete(table, where, returning);
        return fn.apply(newDelete);
    }

    @Override
    public String sql() {
        final String whereSqlStr = this.where.map(o -> "where " + o).orElse("");

        final String returningSqlStr = Optional.ofNullable(returning)
                .filter(o -> o.size() > 0)
                .map(list -> "returning " + list.stream().collect(Collectors.joining(", "))).orElse("");

        return Arrays.asList("delete from", table, whereSqlStr, returningSqlStr).stream().filter(o -> !o.isBlank())
                .map(String::trim).collect(Collectors.joining(" "));

    }

}
