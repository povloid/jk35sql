package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

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


    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");


    public <T> Prepeare add(String param, List<T> values) {
        if (values == null) return addObject(param, null);

        final var firstElement = values.get(0);

        if (firstElement instanceof String)
            return add(param, values.toArray(new String[values.size()]));

        if (firstElement instanceof Integer)
            return add(param, values.toArray(new Integer[values.size()]));

        if (firstElement instanceof Date)
            return add(param, values.toArray(new Date[values.size()]));

        if (firstElement instanceof LocalDateTime)
            return add(param, values.toArray(new LocalDateTime[values.size()]));

        if (firstElement instanceof LocalDate)
            return add(param, values.toArray(new LocalDate[values.size()]));

        if (firstElement instanceof LocalTime)
            return add(param, values.toArray(new LocalTime[values.size()]));

        throw new IllegalArgumentException();
    }

    public Prepeare add(String param, Object[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, String[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, Integer[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, LocalDate[] values) {
        return addObject(param,
                Arrays.stream(values)
                        .map(java.sql.Date::valueOf)
                        .toArray(java.sql.Date[]::new));
    }

    public Prepeare add(String param, LocalTime[] values) {
        return addObject(param,
                Arrays.stream(values)
                        .map(java.sql.Time::valueOf)
                        .toArray(java.sql.Time[]::new));
    }

    public Prepeare add(String param, LocalDateTime[] values) {
        return addObject(param,
                Arrays.stream(values)
                        .map(java.sql.Timestamp::valueOf)
                        .toArray(java.sql.Timestamp[]::new));
    }

    public Prepeare add(String param, Date[] values) {
        return addObject(param,
                Arrays.stream(values)
                        .map(Date::getTime)
                        .map(java.sql.Timestamp::new)
                        .toArray(java.sql.Timestamp[]::new));
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
