package k35.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Condition {

    public enum Op {
        AND,
        OR,
        EXPR
    }

    public final Op op;
    public final List<Condition> childs;
    public final Optional<String> expr;

    private Condition(Op op, List<Condition> childs, Optional<String> expr) {
        this.op = op;
        this.childs = childs;
        this.expr = expr;
    }

    public static Condition and(Condition... childs) {
        return new Condition(Op.AND, Arrays.asList(childs), Optional.empty());
    }

    public static Condition or(Condition... childs) {
        return new Condition(Op.OR, Arrays.asList(childs), Optional.empty());
    }

    public static Condition expression(String expr) {
        return new Condition(Op.EXPR, new ArrayList<>(), Optional.ofNullable(expr));
    }

    public String build() {

        if (op == Op.EXPR)
            return expr.orElse("");

        final String joinOpStr = this.op == Op.OR ? "or" : "and";
        return this.childs
                .stream()
                .map(Condition::build)
                .collect(Collectors.joining(joinOpStr));
    }

}
