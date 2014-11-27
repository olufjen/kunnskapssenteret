package no.kith.xmlstds.emok.msghead.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

public class CustomXMLGregorianCalendar extends XMLGregorianCalendar {
	private XMLGregorianCalendar calendar;   

	public CustomXMLGregorianCalendar(XMLGregorianCalendar calendar) {
		super();
		
		this.calendar = calendar;
		if (this.calendar == null){
			Date now = new Date();
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(now);
			System.out.println("Custom XML date setter dagens dato");
			try {
				this.calendar =  DatatypeFactory.newInstance().newXMLGregorianCalendarTime(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), c.get(Calendar.SECOND),0);
			} catch (DatatypeConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Custom XML date Feil for angitt dato "+e.getMessage());
			} 
		}
			
		int hour = this.calendar.getHour();
		if (hour >= 2)
			hour = hour - 2;
		if (hour == 1)
			hour = 25 - 2;
		if (hour == 0)
			hour = 24 - 2;
		
		this.calendar.setHour(hour);
		this.calendar.setTimezone(120);
	}



	@Override
	public String toXMLFormat() {
	       String text = calendar.toXMLFormat();   
	        int pos = text.indexOf('Z');   
	        
	        return pos < 0 ? text : text.substring(0,pos) + "+00:00";   

	}



	@Override
	public void add(Duration arg0) {
		this.calendar.add(arg0);
		
	}



	@Override
	public void clear() {
		this.calendar.clear();
		
	}



	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return this.calendar.clone();
	}



	@Override
	public int compare(XMLGregorianCalendar arg0) {
		// TODO Auto-generated method stub
		return this.calendar.compare(arg0);
	}



	@Override
	public int getDay() {
		// TODO Auto-generated method stub
		return this.calendar.getDay();
	}



	@Override
	public BigInteger getEon() {
		// TODO Auto-generated method stub
		return this.calendar.getEon();
	}



	@Override
	public BigInteger getEonAndYear() {
		// TODO Auto-generated method stub
		return this.calendar.getEonAndYear();
	}



	@Override
	public BigDecimal getFractionalSecond() {
		// TODO Auto-generated method stub
		return this.calendar.getFractionalSecond();
	}



	@Override
	public int getHour() {
		// TODO Auto-generated method stub
		return this.calendar.getHour();
	}



	@Override
	public int getMinute() {
		// TODO Auto-generated method stub
		return this.calendar.getMinute();
	}



	@Override
	public int getMonth() {
		// TODO Auto-generated method stub
		return this.calendar.getMonth();
	}



	@Override
	public int getSecond() {
		// TODO Auto-generated method stub
		return this.calendar.getSecond();
	}



	@Override
	public TimeZone getTimeZone(int arg0) {
		// TODO Auto-generated method stub
		return this.calendar.getTimeZone(arg0);
	}



	@Override
	public int getTimezone() {
		// TODO Auto-generated method stub
		return this.calendar.getTimezone();
	}



	@Override
	public QName getXMLSchemaType() {
		// TODO Auto-generated method stub
		return this.calendar.getXMLSchemaType();
	}



	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return this.calendar.getYear();
	}



	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return this.calendar.isValid();
	}



	@Override
	public XMLGregorianCalendar normalize() {
		// TODO Auto-generated method stub
		return this.calendar.normalize();
	}



	@Override
	public void reset() {
		// TODO Auto-generated method stub
		this.calendar.reset();
	}



	@Override
	public void setDay(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setDay(arg0);
	}



	@Override
	public void setFractionalSecond(BigDecimal arg0) {
		// TODO Auto-generated method stub
		this.calendar.setFractionalSecond(arg0);
	}



	@Override
	public void setHour(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setHour(arg0);
	}



	@Override
	public void setMillisecond(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setMillisecond(arg0);
	}



	@Override
	public void setMinute(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setMinute(arg0);
	}



	@Override
	public void setMonth(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setMonth(arg0);
	}



	@Override
	public void setSecond(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setSecond(arg0);
	}



	@Override
	public void setTimezone(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setTimezone(arg0);
	}



	@Override
	public void setYear(BigInteger arg0) {
		// TODO Auto-generated method stub
		this.calendar.setYear(arg0);
	}



	@Override
	public void setYear(int arg0) {
		// TODO Auto-generated method stub
		this.calendar.setYear(arg0);
	}



	@Override
	public GregorianCalendar toGregorianCalendar() {
		// TODO Auto-generated method stub
		return this.calendar.toGregorianCalendar();
	}



	@Override
	public GregorianCalendar toGregorianCalendar(TimeZone arg0, Locale arg1,
			XMLGregorianCalendar arg2) {
		// TODO Auto-generated method stub
		return this.calendar.toGregorianCalendar(arg0, arg1, arg2);
	}

}
