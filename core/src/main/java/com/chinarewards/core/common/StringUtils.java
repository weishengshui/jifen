package com.chinarewards.core.common;

import java.util.LinkedHashSet;
import java.util.Set;


public class StringUtils {
	/**
	 * 将 "1,2,3,3,5" 字符转为Integer数组
	 * @param value
	 * @return
	 */
	public static Integer[] getIntegers(String value) {
		try {
			if (value != null) {
				String[] param = value.split(",");
				Set<Integer> set = new LinkedHashSet<Integer>();
				for (int i = 0; i < param.length; i++)
					set.add(Integer.valueOf(param[i]));
				Integer[] rs = new Integer[set.size()];
				return set.toArray(rs);
			}
		} catch (Exception e) {}
		return null;
	}
}
