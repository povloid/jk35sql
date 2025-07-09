package k35.sql.dsl.common;

import k35.sql.dsl.interfaces.SqlBuilder;

import java.util.function.UnaryOperator;

/**
 * SQL Predicate...
 */
public final class Predicate implements SqlBuilder {

    private final String value1;

    private Predicate(String value1) {
        this.value1 = value1;
    }

    /**
     * Create predicate fabric method
     *
     * @return - Predicate
     */
    public static Predicate of() {
        return new Predicate("");
    }

    /**
     * Create predicate fabric method
     *
     * @param value1 - sql value
     * @return Predicate
     */
    public static Predicate of(String value1) {
        return new Predicate(value1);
    }

    /**
     * Create predicate fabric method
     *
     * @param builder - sql builder
     * @return Predicate
     */
    public static Predicate of(SqlBuilder builder) {
        return of(builder.sql());
    }

    /**
     * Apply function to predicate
     *
     * @param fun - predicate transform function
     * @return Predicate
     */
    public Predicate map(UnaryOperator<Predicate> fun) {
        return fun.apply(new Predicate(this.value1));
    }

    /**
     * = (some bind value)
     *
     * @param value2 - sql value
     * @return Predicate
     */
    public Predicate equal(String value2) {
        return new Predicate(this.value1 + " = " + value2);
    }

    /**
     * = (some bind value)
     *
     * @param builder - sql builder
     * @return Predicate
     */
    public Predicate equal(SqlBuilder builder) {
        return equal(builder.sql());
    }

    /**
     * <> (some bind value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate notEqual(String value2) {
        return new Predicate(this.value1 + " <> " + value2);
    }

    /**
     * <> (some bind value)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate notEqual(SqlBuilder builder) {
        return notEqual(builder.sql());
    }

    /**
     * < (some bind value)
     *
     * @param value2 - string value
     * @return next predicate
     */
    public Predicate lessThen(String value2) {
        return new Predicate(this.value1 + " < " + value2);
    }

    /**
     * < (some bind value)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate lessThen(SqlBuilder builder) {
        return lessThen(builder.sql());
    }

    /**
     * <= (some bind value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate lessOrEqual(String value2) {
        return new Predicate(this.value1 + " <= " + value2);
    }

    /**
     * <= (some bind value)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate lessOrEqual(SqlBuilder builder) {
        return lessOrEqual(builder.sql());
    }

    /**
     * > (some bind value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate greaterThan(String value2) {
        return new Predicate(this.value1 + " > " + value2);
    }

    /**
     * > (some bind value)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate greaterThan(SqlBuilder builder) {
        return greaterThan(builder.sql());
    }

    /**
     * >= (some bind value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate greaterOrEqual(String value2) {
        return new Predicate(value1 + " >= " + value2);
    }

    /**
     * >= (some bind value)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate greaterOrEqual(SqlBuilder builder) {
        return greaterOrEqual(builder.sql());
    }

    /**
     * between :from and :to
     *
     * @param from - sql value
     * @param to   - sql value
     * @return next predicate
     */
    public Predicate between(String from, String to) {
        return new Predicate(value1 + " between " + from + " and " + to);
    }

    /**
     * between :from and :to
     *
     * @param from - sql value
     * @param to   - sql value
     * @return next predicate
     */
    public Predicate between(SqlBuilder from, SqlBuilder to) {
        return between(from.sql(), to.sql());
    }

    /**
     * not between :from and :to
     *
     * @param from - sql value
     * @param to   - sal value
     * @return next predicate
     */
    public Predicate notBetween(String from, String to) {
        return new Predicate(value1 + " not between " + from + " and " + to);
    }

    /**
     * value is :value
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate is(String value2) {
        return new Predicate(value1 + " is " + value2);
    }

    /**
     * value is :builder
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate is(SqlBuilder builder) {
        return is(builder.sql());
    }

    /**
     * value is not :value
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate isNot(String value2) {
        return new Predicate(value1 + " is not " + value2);
    }

    /**
     * value is not :builder
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate isNot(SqlBuilder builder) {
        return isNot(builder.sql());
    }

    /**
     * value is true
     *
     * @return next predicate
     */
    public Predicate isTrue() {
        return new Predicate(value1 + " is true");
    }

    /**
     * value is not true
     *
     * @return next predicate
     */
    public Predicate isNotTrue() {
        return new Predicate(value1 + " is not true");
    }

    /**
     * value is false
     *
     * @return next predicate
     */
    public Predicate isFalse() {
        return new Predicate(value1 + " is false");
    }

    /**
     * value is not false
     *
     * @return next predicate
     */
    public Predicate isNotFalse() {
        return new Predicate(value1 + " is not false");
    }

    /**
     * value is null
     *
     * @return next predicate
     */
    public Predicate isNull() {
        return new Predicate(value1 + " is null");
    }

    /**
     * value is not null
     *
     * @return next predicate
     */
    public Predicate isNotNull() {
        return new Predicate(value1 + " is not null");
    }

    /**
     * value in (:value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate in(String value2) {
        return new Predicate(value1 + " in (" + value2 + ")");
    }

    /**
     * value in (:builder)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate in(SqlBuilder builder) {
        return in(builder.sql());
    }

    /**
     * vlaue not in (:value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate notIn(String value2) {
        return new Predicate(value1 + " not in (" + value2 + ")");
    }

    /**
     * value not in (:builder)
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate notIn(SqlBuilder builder) {
        return notIn(builder.sql());
    }

    /**
     * value is unknown
     *
     * @return next predicate
     */
    public Predicate isUnknown() {
        return new Predicate(value1 + " is unknown");
    }

    /**
     * value is not unknown
     *
     * @return next predicate
     */
    public Predicate isNotUnknown() {
        return new Predicate(value1 + " is not unknown");
    }

    /**
     * value and
     *
     * @return next predicate
     */
    public Predicate and() {
        return new Predicate(value1 + " and");
    }

    /**
     * value and :value
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate and(String value2) {
        return new Predicate(value1 + " and " + value2);
    }

    /**
     * value and :builder
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate and(SqlBuilder builder) {
        return and(builder.sql());
    }

    /**
     * value or
     *
     * @return next predicate
     */
    public Predicate or() {
        return new Predicate(value1 + " or");
    }

    /**
     * value or :value
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate or(String value2) {
        return new Predicate(value1 + " or " + value2);
    }

    /**
     * value or builder
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate or(SqlBuilder builder) {
        return or(builder.sql());
    }

    /**
     * value not
     *
     * @return next predicate
     */
    public Predicate not() {
        return new Predicate(value1 + " not");
    }

    /**
     * value not :value
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate not(String value2) {
        return new Predicate(value1 + " not " + value2);
    }

    /**
     * value not :builder
     *
     * @param builder - sql builder
     * @return next predicate
     */
    public Predicate not(SqlBuilder builder) {
        return not(builder.sql());
    }

    /**
     * like (some bind value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate like(String value2) {
        return new Predicate(this.value1 + " like " + value2);
    }

    /**
     * like (some bind value)
     *
     * @param value2 - sql value
     * @return next predicate
     */
    public Predicate ilike(String value2) {
        return new Predicate(this.value1 + " ilike " + value2);
    }

    /**
     * Build SQL
     */
    @Override
    public String sql() {
        return value1;
    }

}