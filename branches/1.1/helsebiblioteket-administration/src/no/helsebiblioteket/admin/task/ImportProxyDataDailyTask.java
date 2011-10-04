package no.helsebiblioteket.admin.task;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImportProxyDataDailyTask extends ImportProxyDataTask {
	private final Log logger = LogFactory.getLog(getClass());
	private String readLogFileNamePrefix;
	
	public void setReadLogFileNamePrefix(String readLogFileNamePrefix) {
		this.readLogFileNamePrefix = readLogFileNamePrefix;
	}
	
	public void importProxyData() {
		logger.info("Quartz task 'no.helsebiblioteket.admin.task.ImportProxyDataTask' is running. This is only supposed to happen ONCE each time DAILY cronjob is triggered.");
		String todayFormatterString = "yyyy-MM-dd";
		SimpleDateFormat todayFormatter = new SimpleDateFormat(todayFormatterString);
	    Calendar stop = Calendar.getInstance();
	    String startOfToday = todayFormatter.format(stop.getTime()) + " 00:00:00.000000000";
		try {
			stop.setTime(todayFormatter.parse(startOfToday));
		} catch (ParseException e) {
			logger.error("Unable to create new SimpleDateFormat based on simpledateformat + '" + todayFormatterString + "' and parse string '" + startOfToday + "'");
		}
		Calendar start = Calendar.getInstance();
		start.setTime(stop.getTime());
		start.add(Calendar.DATE, -1);
		File logFile = new File(createReadLogFileName());
		super.importProxyData(start, stop, logFile);
		logger.info("Quartz task 'no.helsebiblioteket.admin.task.ImportProxyDataTask' finished");
	}
		
	private String createReadLogFileName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat todayFormatter = new SimpleDateFormat("yyyyMM");
		String date = todayFormatter.format(cal.getTime());
		return readLogFileNamePrefix + date + ".log";
	}
	
	public static void main(String[] args) throws ParseException {
		ImportProxyDataDailyTask dataTask = new ImportProxyDataDailyTask();
		dataTask.readLogFileNamePrefix = "/var/log/ezproxy/ezproxy_";
		String name = dataTask.createReadLogFileName();
		System.out.println(name);
	}
}