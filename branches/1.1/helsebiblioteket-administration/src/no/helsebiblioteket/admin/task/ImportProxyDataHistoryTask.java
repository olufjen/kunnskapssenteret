package no.helsebiblioteket.admin.task;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImportProxyDataHistoryTask extends ImportProxyDataTask {
	private final Log logger = LogFactory.getLog(getClass());
	private String historyLogFileURIs;
	private String startDateAsString;
	private String endDateAsString;

	public void setHistoryLogFileURIs(String historyLogFileURIs) {
		this.historyLogFileURIs = historyLogFileURIs;
	}
	
	public void setStartDateAsString(String startDateAsString) {
		this.startDateAsString = startDateAsString;
	}
	public void setEndDateAsString(String endDateAsString) {
		this.endDateAsString = endDateAsString;
	}

	public void importProxyData() {
		logger.info("Quartz task 'no.helsebiblioteket.admin.task.ImportProxyDataHistoryTask' is running. This is only supposed to happen ONCE each time HISTORY cronjob is triggered.");
		logger.info("Import file URIs: " + this.historyLogFileURIs);
		logger.info("Import interval from " + this.startDateAsString + " to " + this.endDateAsString);
		String dateFormatterString = "yyyy-MM-dd hh.mm.ss";
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatterString);
			
			Date dateStart = dateFormatter.parse(this.startDateAsString);
		    Calendar start = Calendar.getInstance();
		    start.setTime(dateStart);
		    
		    Date dateStop = dateFormatter.parse(this.endDateAsString);
		    Calendar stop = Calendar.getInstance();
		    stop.setTime(dateStop);
		    
			File logFile = null;
			URI logFileURI = null;
			String[] logFileURIs = this.historyLogFileURIs.split(",");
			for (String logFileURIString : logFileURIs) {
				try {
					logFileURI = new URI(logFileURIString);
				} catch (URISyntaxException e) {
					logger.error("Illegal URI for logfile: '" + logFileURIString + "'", e);
				}
				logFile = new File(logFileURI);
				super.importProxyData(start, stop, logFile);
			}
		} catch (ParseException e) {
			logger.error("Unable to parse date based on formatter string '" + dateFormatterString + "'", e);
		}
		logger.info("Quartz task 'no.helsebiblioteket.admin.task.ImportProxyDataHistoryTask' finished");
	}
	
	public static void main(String[] args) {
		String historyLogFileURIs = 
				"file:///d:/data/dev/log/ezproxy/hist/ezproxy_2009.log," +
				"file:///d:/data/dev/log/ezproxy/hist/ezproxy_201001.log," +
				"file:///d:/data/dev/log/ezproxy/hist/ezproxy_201002.log," +
				"file:///d:/data/dev/log/ezproxy/hist/ezproxy_201003.log";
		File logFile = null;
		URI logFileURI = null;
		String[] logFileURIs = historyLogFileURIs.split(",");
		for (String logFileURIString : logFileURIs) {
			try {
				logFileURI = new URI(logFileURIString);
			} catch (URISyntaxException e) {
				System.out.println("Illegal URI for logfile: '" + logFileURIString + "'");
				e.printStackTrace();
			}
			logFile = new File(logFileURI);
			System.out.println(logFile.getAbsolutePath() + " " + logFile.getName() + " " + logFile.length());
		}
		
		String dateFormatterString = "yyyy-MM-dd hh.mm.ss";
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatterString);
			
			Date dateStart = dateFormatter.parse("2009-01-01 00.00.01");
		    Calendar start = Calendar.getInstance();
		    start.setTime(dateStart);
		    System.out.println("start date: " + start.getTime());
		    
		    Date dateStop = dateFormatter.parse("2011-01-01 00.00.01");
		    Calendar stop = Calendar.getInstance();
		    stop.setTime(dateStop);
		    System.out.println("stop date: " + stop.getTime());
		} catch (ParseException e) {
			System.out.println("Unable to parse date based on formatter string '" + dateFormatterString + "'");
			e.printStackTrace();
		}
	}
}