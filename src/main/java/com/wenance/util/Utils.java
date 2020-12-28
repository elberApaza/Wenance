package com.wenance.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	
	public static synchronized String convertDateToString(Date dDate, String format) {

		String sResult = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sResult = sdf.format(dDate);

		} catch (Exception e) {
			log.error("Error al convertir la fecha {}", e, sResult);
		}

		return sResult;
	}

	public static synchronized Date convertStringToDate(String sDate, String format) {

		Date dResult = null;

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.setLenient(false);
			dResult = sdf.parse(sDate);

		} catch (Exception e) {
			log.error("Error al convertir la fecha {}", e, dResult);
		}

		return dResult;
	}
}
