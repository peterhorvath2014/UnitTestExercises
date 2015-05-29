package com.epam.suhuj5.trender.converter;

import org.joda.time.Period;
import org.springframework.core.convert.converter.Converter;

public class PeriodWriteConverter implements Converter<Period, String> {
	@Override
	public String convert(Period p) {
		return p.toString();
	}
}
