package k35.sql.util.convertors;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public final class Transform {

    private final ResultSet rs;

    private Transform(ResultSet rs) {
        this.rs = rs;
    }

    @FunctionalInterface
    public interface ExtractFunction<T> {
        T apply(Transform ts) throws SQLException;
    }

    @FunctionalInterface
    public interface TransformFunction<T> {
        T apply(ResultSet rs) throws SQLException;
    }

    public static <T> TransformFunction<T> of(ExtractFunction<T> extractor) {
        return (ResultSet rs) -> extractor.apply(new Transform(rs));
    }

    public boolean getBoolean(String field) throws SQLException {
        return rs.getBoolean(field);
    }

    public Boolean getBooleanObj(String field) throws SQLException {
        return rs.getObject(field, Boolean.class);
    }

    public int getInt(String field) throws SQLException {
        return rs.getInt(field);
    }

    public Integer getIntObj(String field) throws SQLException {
        return rs.getObject(field, Integer.class);
    }

    public int getIntFromNumeric111(String field) throws SQLException {
        return rs.getBigDecimal(field).intValue();
    }

    public short getShort(String field) throws SQLException {
        return rs.getShort(field);
    }

    public Short getShortObj(String field) throws SQLException {
        return rs.getObject(field, Short.class);
    }

    public long getLong(String field) throws SQLException {
        return rs.getLong(field);
    }

    public Long getLongObj(String field) throws SQLException {
        return rs.getObject(field, Long.class);
    }

    public String getString(String field) throws SQLException {
        return rs.getString(field);
    }

    public UUID getUUID(String field) throws SQLException {
        return UUID.fromString(rs.getString(field));
    }

    public BigDecimal getBigDecimal(String field) throws SQLException {
        return rs.getBigDecimal(field);
    }

    public LocalTime getLocalTime(String field) throws SQLException {
        return Optional.ofNullable(rs.getTime(field)).map(java.sql.Time::toLocalTime).orElse(null);
    }

    public LocalDate getLocalDate(String field) throws SQLException {
        return Optional.ofNullable(rs.getDate(field)).map(java.sql.Date::toLocalDate).orElse(null);
    }

    public Date getDate(String field) throws SQLException {
        return Optional.ofNullable(rs.getTimestamp(field)).map(java.sql.Timestamp::getTime).map(Date::new).orElse(null);
    }

    public List<String> getStrings(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (String[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(o -> o.length > 0)
                .map(List::of)
                .orElse(null);
    }

    public List<Integer> getIntegers(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (Integer[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(o -> o.length > 0)
                .map(List::of)
                .orElse(null);
    }

    public List<LocalDate> getLocalDates(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return ((java.sql.Date[]) o.getArray());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(o -> o.length > 0)
                .map(dates -> List.of(dates).stream().map(java.sql.Date::toLocalDate).toList())
                .orElse(null);
    }

    public List<LocalTime> getLocalTimes(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return ((java.sql.Time[]) o.getArray());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(o -> o.length > 0)
                .map(dates -> List.of(dates).stream().map(java.sql.Time::toLocalTime).toList())
                .orElse(null);
    }

    public List<LocalDateTime> getLocalDateTimes(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return ((java.sql.Timestamp[]) o.getArray());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(o -> o.length > 0)
                .map(dates -> List.of(dates).stream().map(java.sql.Timestamp::toLocalDateTime).toList())
                .orElse(null);
    }

    public List<Date> getDates(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return ((java.sql.Timestamp[]) o.getArray());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(o -> o.length > 0)
                .map(dates -> List.of(dates).stream().map(java.sql.Timestamp::getTime).map(Date::new).toList())
                .orElse(null);
    }

    public <T> Optional<T> getOptional(String field, Class<T> type) throws SQLException {
        return Optional.ofNullable(rs.getObject(field, type));
    }

    public Optional<Boolean> getOptionalBoolean(String field) throws SQLException {
        return Optional.ofNullable(rs.getBoolean(field));
    }

    public Optional<Integer> getOptionalInt(String field) throws SQLException {
        return Optional.ofNullable(rs.getObject(field, Integer.class));
    }

    public Optional<Integer> getOptionalIntFromNumeric111(String field) throws SQLException {
        return Optional.ofNullable(rs.getBigDecimal(field)).map(BigDecimal::intValue);
    }

    public Optional<Long> getOptionalLong(String field) throws SQLException {
        return Optional.ofNullable(rs.getObject(field, Long.class));
    }

    public Optional<String> getOptionalString(String field) throws SQLException {
        return Optional.ofNullable(rs.getString(field));
    }

    public Optional<UUID> getOptionalUUID(String field) throws SQLException {
        return Optional.ofNullable(rs.getString(field)).map(UUID::fromString);
    }

    public Optional<BigDecimal> getOptionalBigDecimal(String field) throws SQLException {
        return Optional.ofNullable(rs.getBigDecimal(field));
    }

    public Optional<Date> getOptionalTime(String field) throws SQLException {
        return Optional.ofNullable(rs.getTime(field)).map(java.sql.Time::getTime).map(Date::new);
    }

    public Optional<Date> getOptionalDate(String field) throws SQLException {
        return Optional.ofNullable(rs.getDate(field)).map(java.sql.Date::getTime).map(Date::new);
    }

    public Optional<Date> getOptionalTimestamp(String field) throws SQLException {
        return Optional.ofNullable(rs.getTimestamp(field)).map(java.sql.Timestamp::getTime).map(Date::new);
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T[]> getOptionalArray(String field, Class<T> type) throws SQLException {
        final var sqlArray = rs.getArray(field);

        if (Objects.isNull(sqlArray)) {
            return Optional.empty();
        }

        return Optional.of((T[]) sqlArray.getArray());
    }

}
