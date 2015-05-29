package com.epam.suhuj5.trender.converter;

import org.joda.time.Period;
import org.springframework.core.convert.converter.Converter;

public class PeriodReadConverter implements Converter<String, Period> {
	@Override
	public Period convert(String s) {
		String[] periods = s.split(":");
		return new Period(Integer.valueOf(periods[0]), Integer.valueOf(periods[1]), Integer.valueOf(periods[2]), 0);
	}
}
