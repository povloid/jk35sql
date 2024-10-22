package k35.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Select implements SqlBuilder {

    public static Select select(String... fields) {
        return new Select(fields);
    }

    protected final List<String> fields;

    private Select(String... fields) {
        this.fields = Arrays.asList(fields);
    }

    private Optional<String> table = Optional.empty();

    public Select from(String table) {
        this.table = Optional.ofNullable(table);
        return this;
    }

    private Optional<List<Join>> joins = Optional.empty();

    private void addJoin(Join join) {
        this.joins = (joins.isPresent() ? joins : Optional.of(new ArrayList<Join>()))
                .map(list -> {
                    list.add(join);
                    return list;
                });
    }

    public record JoinChain(Select selectSqlBuider, Join join) {
        public Select on(Condition condition) {
            selectSqlBuider.addJoin(join.on(condition));
            return selectSqlBuider;
        }
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

    private Optional<Condition> condition = Optional.empty();

    public Select where(String expression) {
        return where(Condition.expression(expression));
    }

    public Select where(Condition condition) {
        this.condition = Optional.ofNullable(condition);
        return this;
    }

    private Optional<String> limitParameter = Optional.empty();

    public Select limit(String parameter) {
        this.limitParameter = Optional.ofNullable(parameter);
        return this;
    }

    private Optional<String> offsetParameter = Optional.empty();

    public Select offset(String offset) {
        this.offsetParameter = Optional.ofNullable(offset);
        return this;
    }

    private Optional<String[]> orderByFields = Optional.empty();

    public Select orderBy(String... fields) {
        this.orderByFields = Optional.ofNullable(fields);
        return this;
    }

    /**
     * False is ASC
     * True is DESC
     */
    private Optional<Boolean> orderByDirection = Optional.empty();

    public Select asc() {
        this.orderByDirection = Optional.of(false);
        return this;
    }

    public Select desc() {
        this.orderByDirection = Optional.of(true);
        return this;
    }

    private Optional<String[]> groupByFields = Optional.empty();

    public Select groupBy(String... fields) {
        this.groupByFields = Optional.ofNullable(fields);
        return this;
    }

    public String buildSql() {

        final String fieldsSqlStr = fields.isEmpty()
                ? "*"
                : fields.stream().collect(Collectors.joining(", "));

        final String tableSqlStr = this.table.map(String::trim).orElse("");

        final String joinsSqlStr = joins
                .map(items -> items.stream().map(Join::buildSql).collect(Collectors.joining(" ")))
                .orElse("");

        final String whereSqlStr = this.condition.map(o -> "where " + o.buildSql()).orElse("");

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
