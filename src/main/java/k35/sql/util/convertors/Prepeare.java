package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public final class Prepeare {

    private final Map<String, ?> map;

    private Prepeare() {
        this.map = new HashMap<>();
    }

    private Prepeare(Map<String, ?> map) {
        this.map = map;
    }

    public static Prepeare of() {
        return new Prepeare();
    }

    private Prepeare addObject(String param, Object value) {
        final var newMap = new HashMap<String, Object>(map);
        newMap.put(param, value);

        return new Prepeare(newMap);
    }

    public Prepeare add(String param, boolean value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, Boolean value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, int value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, Integer value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, long value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, Long value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, double value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, Double value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, BigDecimal value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, String value) {
        return addObject(param, value);
    }


    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");


    public Prepeare add(String param, List<?> values) {

        if (values == null) return addObject(param, null);

        final var str = values.stream()
                .map(value -> {
                    if (value instanceof String) return "\"" + value + "\"";
                    if (value instanceof Date o) return "\"" + dateFormat.format(o) + "\"";
                    if (value instanceof LocalDateTime o) return "\"" + o.toString() + "\"";
                    if (value instanceof LocalDate o) return "\"" + o.toString() + "\"";
                    if (value instanceof LocalTime o) return "\"" + o.toString() + "\"";
                    else return value + "";
                }).collect(Collectors.joining(","));

        return addObject(param, "{" + str + "}");
    }

    public Prepeare add(String param, Date value) {
        return addObject(param, Optional.ofNullable(value).map(Date::getTime).map(java.sql.Timestamp::new).orElse(null));
    }

    public Prepeare add(String param, LocalTime value) {
        return addObject(param, Optional.ofNullable(value).map(java.sql.Time::valueOf).orElse(null));
    }

    public Prepeare add(String param, LocalDate value) {
        return addObject(param, Optional.ofNullable(value).map(java.sql.Date::valueOf).orElse(null));
    }

    public Prepeare addOptional(String param, Optional<?> ovalue) {
        return addObject(param, ovalue.orElse(null));
    }

    public Prepeare addOptional(String param, OptionalInt ovalue) {
        return addObject(param, ovalue.isPresent() ? ovalue.getAsInt() : null);
    }

    public Prepeare addOptional(String param, OptionalLong ovalue) {
        return addObject(param, ovalue.isPresent() ? ovalue.getAsLong() : null);
    }

    public Prepeare addOptional(String param, OptionalDouble ovalue) {
        return addObject(param, ovalue.isPresent() ? ovalue.getAsDouble() : null);
    }

    public Prepeare addOptionalTime(String param, Optional<Date> ovalue) {
        return addObject(param, ovalue.map(Date::getTime).map(java.sql.Time::new).orElse(null));
    }

    public Prepeare addOptionalDate(String param, Optional<Date> ovalue) {
        return addObject(param, ovalue.map(Date::getTime).map(java.sql.Date::new).orElse(null));
    }

    public Prepeare addOptionalTimestamp(String param, Optional<Date> ovalue) {
        return addObject(param, ovalue.map(Date::getTime).map(java.sql.Timestamp::new).orElse(null));
    }

    public Map<String, ?> getMap() {
        return map;
    }

}
