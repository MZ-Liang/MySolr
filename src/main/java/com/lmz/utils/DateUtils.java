package com.lmz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 */
public class DateUtils {
	
	/**
	 * 根据当前时间获取id
	 * @return id
	 */
	public static Long getIDByDate() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddmmss");
		String idStr=sdf.format(new Date());
		return new Long(idStr);
	}
}
