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

    /**
     * public int getIntFromNumeric(String field) throws SQLException {
     * return rs.getBigDecimal(field).intValue();
     * }
     * <p>
     * public Optional<Integer> getIntFromNumericOptional(String field) throws SQLException {
     * return Optional.ofNullable(rs.getBigDecimal(field)).map(BigDecimal::intValue);
     * }
     */

    // Boolean --------------------------------------------------------------------------------------------------
    public boolean getBoolean(String field) throws SQLException {
        return rs.getBoolean(field);
    }

    public Boolean getBooleanObj(String field) throws SQLException {
        return rs.getObject(field, Boolean.class);
    }

    public Optional<Boolean> getOptionalBoolean(String field) throws SQLException {
        return Optional.ofNullable(rs.getBoolean(field));
    }

    public Optional<Boolean[]> getArrayOfBooleansOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (Boolean[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public Boolean[] getArrayOfBooleans(String field) throws SQLException {
        return getArrayOfBooleansOptional(field).orElse(null);
    }

    public Optional<List<Boolean>> getListOfBooleansOptional(String field) throws SQLException {
        return getArrayOfBooleansOptional(field).map(List::of);
    }

    public List<Boolean> getListOfBooleans(String field) throws SQLException {
        return getListOfBooleansOptional(field).orElse(null);
    }

    // Short -----------------------------------------------------------------------------------------------------

    public short getshort(String field) throws SQLException {
        return rs.getShort(field);
    }

    public Short getShort(String field) throws SQLException {
        return rs.getObject(field, Short.class);
    }

    public Optional<Short> getShortOptoinal(String field) throws SQLException {
        return Optional.ofNullable(rs.getObject(field, Short.class));
    }

    public Optional<Short[]> getArrayOfShortsOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (Short[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public Short[] getArrayOfShorts(String field) throws SQLException {
        return getArrayOfShortsOptional(field).orElse(null);
    }

    public Optional<List<Short>> getListOfShortsOptional(String field) throws SQLException {
        return getArrayOfShortsOptional(field).map(List::of);
    }

    public List<Short> getListOfShorts(String field) throws SQLException {
        return getListOfShortsOptional(field).orElse(null);
    }

    // UUID ------------------------------------------------------------------------------------------------------

    public UUID getUUID(String field) throws SQLException {
        return getUUIDOptional(field).orElse(null);
    }

    public Optional<UUID> getUUIDOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getString(field)).map(UUID::fromString);
    }

    public Optional<UUID[]> getArrayOfUUIDsOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (UUID[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public UUID[] getArrayOfUUIDs(String field) throws SQLException {
        return getArrayOfUUIDsOptional(field).orElse(null);
    }

    public Optional<List<UUID>> getListOfUUIDsOptional(String field) throws SQLException {
        return getArrayOfUUIDsOptional(field).map(List::of);
    }

    public List<UUID> getListOfUUIDs(String field) throws SQLException {
        return getListOfUUIDsOptional(field).orElse(null);
    }

    // BigDecimal ------------------------------------------------------------------------------------------------

    public BigDecimal getBigDecimal(String field) throws SQLException {
        return rs.getBigDecimal(field);
    }

    public Optional<BigDecimal> getBigDecimalOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getBigDecimal(field));
    }

    public Optional<BigDecimal[]> getArrayOfBigDecimalsOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (BigDecimal[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public BigDecimal[] getArrayOfBigDecimals(String field) throws SQLException {
        return getArrayOfBigDecimalsOptional(field).orElse(null);
    }

    public Optional<List<BigDecimal>> getListOfBigDecimalsOptional(String field) throws SQLException {
        return getArrayOfBigDecimalsOptional(field).map(List::of);
    }

    public List<BigDecimal> getListOfBigDecimals(String field) throws SQLException {
        return getListOfBigDecimalsOptional(field).orElse(null);
    }

    // Integer ---------------------------------------------------------------------------------------------------

    public int getint(String field) throws SQLException {
        return rs.getInt(field);
    }

    public Integer getInteger(String field) throws SQLException {
        return rs.getObject(field, Integer.class);
    }

    public Optional<Integer> getIntegerOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getObject(field, Integer.class));
    }

    public Optional<Integer[]> getArrayOfIntegersOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (Integer[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public Integer[] getArrayOfIntegers(String field) throws SQLException {
        return getArrayOfIntegersOptional(field).orElse(null);
    }

    public Optional<List<Integer>> getListOfIntegersOptional(String field) throws SQLException {
        return getArrayOfIntegersOptional(field).map(List::of);
    }

    public List<Integer> getListOfIntegers(String field) throws SQLException {
        return getListOfIntegersOptional(field).orElse(null);
    }

    // Long -----------------------------------------------------------------------------------------------------

    public long getlong(String field) throws SQLException {
        return rs.getLong(field);
    }

    public Long getLong(String field) throws SQLException {
        return rs.getObject(field, Long.class);
    }

    public Optional<Long> getLongOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getObject(field, Long.class));
    }

    public Optional<Long[]> getArrayOfLongsOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (Long[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public Long[] getArrayOfLongs(String field) throws SQLException {
        return getArrayOfLongsOptional(field).orElse(null);
    }

    public Optional<List<Long>> getListOfLongsOptional(String field) throws SQLException {
        return getArrayOfLongsOptional(field).map(List::of);
    }

    public List<Long> getListOfLongs(String field) throws SQLException {
        return getListOfLongsOptional(field).orElse(null);
    }

    // String ---------------------------------------------------------------------------------------------------

    public String getString(String field) throws SQLException {
        return rs.getString(field);
    }

    public Optional<String> getStringOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getString(field));
    }

    public Optional<String[]> getArrayOfStringsOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        return (String[]) o.getArray();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public String[] getArrayOfStrings(String field) throws SQLException {
        return getArrayOfStringsOptional(field).orElse(null);
    }

    public Optional<List<String>> getListOfStringsOptional(String field) throws SQLException {
        return getArrayOfStringsOptional(field).map(List::of);
    }

    public List<String> getListOfStrings(String field) throws SQLException {
        return getListOfStringsOptional(field).orElse(null);
    }

    // Date ---------------------------------------------------------------------------------------------------

    public Optional<Date> getDateOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getTimestamp(field)).map(java.sql.Timestamp::getTime).map(Date::new);
    }

    public Date getDate(String field) throws SQLException {
        return getDateOptional(field).orElse(null);
    }

    public Optional<Date[]> getArrayOfDatesOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        final var array = (java.sql.Timestamp[]) o.getArray();
                        return Arrays.stream(array)
                                .map(java.sql.Timestamp::getTime)
                                .map(Date::new)
                                .toArray(Date[]::new);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public Date[] getArrayOfDates(String field) throws SQLException {
        return getArrayOfDatesOptional(field).orElse(null);
    }

    public Optional<List<Date>> getListOfDatesOptional(String field) throws SQLException {
        return getArrayOfDatesOptional(field).map(List::of);
    }

    public List<Date> getListOfDates(String field) throws SQLException {
        return getListOfDatesOptional(field).orElse(null);
    }

    // LocalDate ---------------------------------------------------------------------------------------------------

    public Optional<LocalDate> getLocalDateOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getDate(field)).map(java.sql.Date::toLocalDate);
    }

    public LocalDate getLocalDate(String field) throws SQLException {
        return getLocalDateOptional(field).orElse(null);
    }

    public Optional<LocalDate[]> getArrayOfLocalDatesOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        final var array = (java.sql.Date[]) o.getArray();
                        return Arrays.stream(array)
                                .map(java.sql.Date::toLocalDate)
                                .toArray(LocalDate[]::new);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public LocalDate[] getArrayOfLocalDates(String field) throws SQLException {
        return getArrayOfLocalDatesOptional(field).orElse(null);
    }

    public Optional<List<LocalDate>> getListOfLocalDatesOptional(String field) throws SQLException {
        return getArrayOfLocalDatesOptional(field).map(List::of);
    }

    public List<LocalDate> getListOfLocalDates(String field) throws SQLException {
        return getListOfLocalDatesOptional(field).orElse(null);
    }

    // LocalTime ---------------------------------------------------------------------------------------------------

    public Optional<LocalTime> getLocalTimeOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getTime(field)).map(java.sql.Time::toLocalTime);
    }

    public LocalTime getLocalTime(String field) throws SQLException {
        return getLocalTimeOptional(field).orElse(null);
    }


    public Optional<LocalTime[]> getArrayOfLocalTimesOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        final var array = (java.sql.Time[]) o.getArray();
                        return Arrays.stream(array)
                                .map(java.sql.Time::toLocalTime)
                                .toArray(LocalTime[]::new);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public LocalTime[] getArrayOfLocalTimes(String field) throws SQLException {
        return getArrayOfLocalTimesOptional(field).orElse(null);
    }

    public Optional<List<LocalTime>> getListOfLocalTimesOptional(String field) throws SQLException {
        return getArrayOfLocalTimesOptional(field).map(List::of);
    }

    public List<LocalTime> getListOfLocalTimes(String field) throws SQLException {
        return getListOfLocalTimesOptional(field).orElse(null);
    }

    // LocalDateTime ---------------------------------------------------------------------------------------------------

    public Optional<LocalDateTime> getLocalDateTimeOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getTimestamp(field)).map(java.sql.Timestamp::toLocalDateTime);
    }

    public LocalDateTime getLocalDateTime(String field) throws SQLException {
        return getLocalDateTimeOptional(field).orElse(null);
    }

    public Optional<LocalDateTime[]> getArrayOfLocalDateTimesOptional(String field) throws SQLException {
        return Optional.ofNullable(rs.getArray(field))
                .map(o -> {
                    try {
                        final var array = (java.sql.Timestamp[]) o.getArray();
                        return Arrays.stream(array)
                                .map(java.sql.Timestamp::toLocalDateTime)
                                .toArray(LocalDateTime[]::new);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public LocalDateTime[] getArrayOfLocalDateTimes(String field) throws SQLException {
        return getArrayOfLocalDateTimesOptional(field).orElse(null);
    }

    public Optional<List<LocalDateTime>> getListOfLocalDateTimesOptional(String field) throws SQLException {
        return getArrayOfLocalDateTimesOptional(field).map(List::of);
    }

    public List<LocalDateTime> getListOfLocalDateTimes(String field) throws SQLException {
        return getListOfLocalDateTimesOptional(field).orElse(null);
    }

}
