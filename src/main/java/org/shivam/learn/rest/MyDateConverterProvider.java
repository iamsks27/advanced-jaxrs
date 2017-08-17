package org.shivam.learn.rest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

	public <T> ParamConverter<T> getConverter(final Class<T> arg0, Type arg1, Annotation[] arg2) {
		if (arg0.getName().equals(MyDate.class.getName())) {
			return new ParamConverter<T>() {

				public T fromString(String value) {
					Calendar requestedDate = Calendar.getInstance();
					if ("tomorrow".equalsIgnoreCase(value)) {
						requestedDate.add(Calendar.DATE, 1);
					} else if ("yesterday".equalsIgnoreCase(value)) {
						requestedDate.add(Calendar.DATE, -1);
					}
					MyDate date = new MyDate();
					date.setDay(requestedDate.get(Calendar.DATE));
					date.setMonth(requestedDate.get(Calendar.MONTH));
					date.setYear(requestedDate.get(Calendar.YEAR));
					return arg0.cast(date);
				}

				public String toString(T arg0) {
					// TODO Auto-generated method stub
					if (arg0 == null) {
						return null;
					}
					return arg0.toString();
				}
			};
		}  
		return null;
	}

}
