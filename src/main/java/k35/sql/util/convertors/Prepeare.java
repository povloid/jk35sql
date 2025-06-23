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

    public Prepeare add(String param, Date value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public Prepeare add(String param, LocalTime value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public Prepeare add(String param, LocalDate value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public Prepeare add(String param, LocalDateTime value) {
        return addObject(param, Optional.ofNullable(value).map(this::prepeare).orElse(null));
    }

    public <T> Prepeare addOptional(String param, Optional<T> ovalue) {
        return addObject(param, ovalue.map(this::prepeare).orElse(null));
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
    
    public Prepeare add(String param, List<Boolean> values, Boolean[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<Short> values, Short[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<Integer> values, Integer[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<BigDecimal> values, BigDecimal[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<String> values, String[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<UUID> values, UUID[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<Date> values, Date[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<LocalDate> values, LocalDate[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<LocalTime> values, LocalTime[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
    }

    public Prepeare add(String param, List<LocalDateTime> values, LocalDateTime[] initArray) {
        if (values == null) return addObject(param, null);
        return add(param, values.toArray(initArray));
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
