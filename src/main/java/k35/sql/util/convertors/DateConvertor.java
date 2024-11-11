package k35.sql.util.convertors;

import java.util.Optional;

public final class DateConvertor {

	private DateConvertor() {
	}

	public static Optional<java.sql.Time> dateToSqlTime(java.util.Date date) {
		return Optional.ofNullable(date).map(sql -> sql.getTime()).map(java.sql.Time::new);
	}

	public static Optional<java.sql.Date> dateToSqlDate(java.util.Date date) {
		return Optional.ofNullable(date).map(sql -> sql.getTime()).map(java.sql.Date::new);
	}

	public static Optional<java.sql.Timestamp> dateToSqlTimestamp(java.util.Date date) {
		return Optional.ofNullable(date).map(sql -> sql.getTime()).map(java.sql.Timestamp::new);
	}

	public static Optional<java.util.Date> sqlToDate(java.sql.Time sql) {
		return Optional.ofNullable(sql).map(obj -> obj.getTime()).map(java.util.Date::new);
	}

	public static Optional<java.util.Date> sqlToDate(java.sql.Date sql) {
		return Optional.ofNullable(sql).map(obj -> obj.getTime()).map(java.util.Date::new);
	}

	public static Optional<java.util.Date> sqlToDate(java.sql.Timestamp sql) {
		return Optional.ofNullable(sql).map(obj -> obj.getTime()).map(java.util.Date::new);
	}

}
