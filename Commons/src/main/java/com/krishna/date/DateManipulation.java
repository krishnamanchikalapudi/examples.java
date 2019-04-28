package com.krishna.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Krishna Manchikalapudi
 */
public class DateManipulation  {

	/**
	 * Number of days between TODAY & input date
	 * 
	 * @param date
	 * @return
	 */
	public static int diffWithToday(Date date) {
		if (date != null)
			return (int) (((new Date()).getTime() - date.getTime()) / IDateFormat.MILLISECS_PER_DAY);
		return 0;
	}

	/**
	 * @param dateStr
	 * @param format
	 * @return java.util.Date
	 */
	public static Date toUtilDate(String dateStr, String format) {
		Date today = null;
		if (StringUtils.isNotBlank(dateStr)) {
			try {
				DateFormat df = new SimpleDateFormat(format);
				today = df.parse(dateStr);
				System.out.println("parsed Date = " + df.format(today));
			} catch (Exception ex) {
				ex.getStackTrace();
			} // TRY - CATCH
		}
		return today;
	}

	/**
	 * @param dateStr
	 * @return java.util.Date MM-dd-yyyy
	 */
	public static Date parseUtilMM_dd_yyyy(String dateStr) {
		if (StringUtils.isNotBlank(dateStr))
			return toUtilDate(dateStr, IDateFormat.MM_dd_yyyy);
		return null;
	}

	/**
	 * @param dateStr
	 * @return java.util.Date MM/dd/yyyy
	 */
	public static Date parseUtilMMddyyyy(String dateStr) {
		if (StringUtils.isNotBlank(dateStr))
			return toUtilDate(dateStr, IDateFormat.MMddyyyy);
		return null;
	}

	/**
	 * @param dateStr
	 * @return java.util.Date MM-dd-yyyy hh:mm aa
	 */
	public static Date parseUtilMMddyyyyHHMMmeridiem(String dateStr) {
		if (StringUtils.isNotBlank(dateStr))
			return toUtilDate(dateStr, IDateFormat.MM_dd_yyyy_hh_mm_aa);
		return null;
	}

	public static java.sql.Date toSqlDate(String dateStr, String format) {
		java.sql.Date sqlDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date utilDate = sdf.parse(dateStr);
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException pe) {
			pe.getStackTrace();
		} // TRY - CATCH
		return sqlDate;
	}

	/**
	 * @param dateStr
	 * @return dd/MM/yyyy
	 */
	public static java.sql.Date toSqlddMMyyyy(String dateStr) {
		if (StringUtils.isNotBlank(dateStr))
			return toSqlDate(dateStr, IDateFormat.ddMMyyyy);
		return null;
	}

	/**
	 * @param dateStr
	 * @return dd-MM-yyyy
	 */
	public static java.sql.Date toSqldd_MM_yyyy(String dateStr) {
		if (StringUtils.isNotBlank(dateStr))
			return toSqlDate(dateStr, IDateFormat.dd_MM_yyyy);
		return null;
	}
}
interface IDateFormat {
    public static final String MM_dd_yyyy = "MM-dd-yyyy";
    public static final String MMddyyyy = "MM/dd/yyyy";
    public static final String dd_MM_yyyy = "dd-MM-yyyy";
    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String yy_MM_dd = "yy-MM-dd";
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    public static final String yyMMdd = "yyMMdd";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String MM_dd_yyyy_hh_mm_aa = "MM-dd-yyyy hh:mm aa";
    public static final String MM_dd_yyyy_hh_mm_ss_aa = "MM-dd-yyyy hh:mm:ss aa";
    public final static int MILLISECS_PER_DAY = (1000 * 60 * 60 * 24);
    public final static int MILLISECS_PER_WEEK = (MILLISECS_PER_DAY * 7);
}
