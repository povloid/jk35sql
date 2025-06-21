package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public final class Prepeare {

    private final Map<String, Object> map;

    private Prepeare() {
        this.map = new HashMap<>();
    }

    private Prepeare(Map<String, Object> map) {
        this.map = map;
    }

    public static Prepeare of() {
        return new Prepeare();
    }

    private Prepeare addObject(String param, Object value) {
        this.map.put(param, value);
        return this;
    }

    public Prepeare add(String param, boolean value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, Boolean value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, short value) {
        return addObject(param, value);
    }

    public Prepeare add(String param, Short value) {
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

    public Prepeare add(String param, UUID value) {
        return addObject(param, value);
    }

    public <T> Prepeare add(String param, List<T> values) {
        if (values == null) return addObject(param, null);
        if (values.isEmpty()) return addObject(param, new Object[0]);

        final var firstElement = values.get(0);

        if (firstElement instanceof Boolean)
            return add(param, values.toArray(new Boolean[0]));

        if (firstElement instanceof Short)
            return add(param, values.toArray(new Short[0]));

        if (firstElement instanceof Integer)
            return add(param, values.toArray(new Integer[0]));

        if (firstElement instanceof Long)
            return add(param, values.toArray(new Long[0]));

        if (firstElement instanceof BigDecimal)
            return add(param, values.toArray(new BigDecimal[0]));

        if (firstElement instanceof String)
            return add(param, values.toArray(new String[0]));

        if (firstElement instanceof UUID)
            return add(param, values.toArray(new UUID[0]));

        if (firstElement instanceof Date)
            return add(param, values.toArray(new Date[0]));

        if (firstElement instanceof LocalDateTime)
            return add(param, values.toArray(new LocalDateTime[0]));

        if (firstElement instanceof LocalDate)
            return add(param, values.toArray(new LocalDate[0]));

        if (firstElement instanceof LocalTime)
            return add(param, values.toArray(new LocalTime[0]));

        throw new IllegalArgumentException();

    }

    public Prepeare add(String param, Boolean[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, Short[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, Integer[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, Long[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, BigDecimal[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, String[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, UUID[] values) {
        return addObject(param, values);
    }

    public Prepeare add(String param, LocalDate[] values) {
        return addObject(param,
                values == null
                        ? null
                        : Arrays.stream(values)
                        .map(this::prepeare)
                        .toArray(java.sql.Date[]::new));
    }

    public Prepeare add(String param, LocalTime[] values) {
        return addObject(param,
                values == null
                        ? null
                        : Arrays.stream(values)
                        .map(this::prepeare)
                        .toArray(java.sql.Time[]::new));
    }

    public Prepeare add(String param, LocalDateTime[] values) {
        return addObject(param,
                values == null
                        ? null
                        : Arrays.stream(values)
                        .map(this::prepeare)
                        .toArray(java.sql.Timestamp[]::new));
    }

    public Prepeare add(String param, Date[] values) {
        return addObject(param,
                values == null
                        ? null
                        : Arrays.stream(values)
                        .map(this::prepeare)
                        .toArray(java.sql.Timestamp[]::new));
    }

    public Prepeare add(String param, Date value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public Prepeare add(String param, LocalTime value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public Prepeare add(String param, LocalDate value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public <T> Prepeare addOptional(String param, Optional<T> ovalue) {
        return addObject(param, ovalue.map(this::prepeare).orElse(null));
    }

    private java.sql.Timestamp prepeare(Date value) {
        return new java.sql.Timestamp(value.getTime());
    }

    private java.sql.Time prepeare(LocalTime value) {
        return java.sql.Time.valueOf(value);
    }

    private java.sql.Date prepeare(LocalDate value) {
        return java.sql.Date.valueOf(value);
    }

    private java.sql.Timestamp prepeare(LocalDateTime value) {
        return java.sql.Timestamp.valueOf(value);
    }

    private Object prepeare(Object value) {
        return value;
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

}
