package k35.sql.dsl.dml;

import k35.sql.dsl.common.Predicate;
import k35.sql.dsl.common.Table;
import k35.sql.dsl.interfaces.Map;
import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public final class Select implements SqlBuilder, Map<Select> {

    private final String fields;
    private final Optional<String> table;
    private final Optional<List<Join>> joins;
    private final Optional<String> where;
    /**
     * False is ASC True is DESC
     */
    private final Optional<Boolean> orderByDirection;
    private final Optional<String> limitParameter;
    private final Optional<String> offsetParameter;
    private final Optional<String[]> groupByFields;

    public Select(
            String fields,
            Optional<String> table,
            Optional<List<Join>> joins,
            Optional<String> where,
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

    public static Select select(String fields) {
        return new Select(
                fields,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
    }

    public static Select select(SqlBuilder... sqlBuilders) {
        return select(List.of(sqlBuilders).stream().map(SqlBuilder::sql).collect(Collectors.joining(", ")));
    }

    public static Select all() {
        return select("*");
    }

    public static Select select() {
        return all();
    }

    public static Select count() {
        return select("count(*)");
    }


    public Select asCount() {
        return fields("count(*)");
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

    public Select from(Table table) {
        return from(table.sql());
    }

    public record JoinChain(Select select, Join join) {

        public Select on(SqlBuilder sqlBuilder) {
            return on(sqlBuilder.sql());
        }

        public Select on(String condition) {

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

    public JoinChain innerJoin(Table table) {
        return innerJoin(table.sql());
    }

    public JoinChain leftJoin(Table table) {
        return leftJoin(table.sql());
    }

    public JoinChain rightJoin(Table table) {
        return rightJoin(table.sql());
    }

    public JoinChain fullJoin(Table table) {
        return fullJoin(table.sql());
    }

    public Select where(Predicate predicate) {
        return where(predicate.sql());
    }

    public Select where(String condition) {
        return new Select(fields,
                table,
                joins,
                Optional.ofNullable(condition).filter(s -> !s.isBlank()),
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
                Optional.ofNullable(parameter).filter(s -> !s.isBlank()),
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
                Optional.ofNullable(offset).filter(s -> !s.isBlank()));
    }

    private Optional<String[]> orderByFields = Optional.empty();

    public Select orderBy(SqlBuilder... sqlBuilders) {
        return orderBy(List.of(sqlBuilders).stream().map(SqlBuilder::sql).collect(Collectors.joining(", ")));
    }

    public Select orderBy(String... fields) {
        return new Select(this.fields,
                table,
                joins,
                where,
                groupByFields,
                Optional.ofNullable(fields).filter(fs -> fs.length > 0),
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
                Optional.ofNullable(fields).filter(fs -> fs.length > 0),
                orderByFields,
                orderByDirection,
                limitParameter,
                offsetParameter);
    }

    public Select fields(String fields) {
        return new Select(fields,
                table,
                joins,
                where,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());
    }

    /**
     * apply function
     */
    @Override
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

    /**
     * Build sql
     */
    @Override
    public String sql() {

        final String fieldsSqlStr = fields;

        final String tableSqlStr = this.table.map(String::trim).orElse("");

        final String joinsSqlStr = joins
                .map(items -> items.stream().map(Join::sql).collect(Collectors.joining(" ")))
                .orElse("");

        final String whereSqlStr = this.where.map(o -> "where " + o).orElse("");

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
