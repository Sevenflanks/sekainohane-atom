package tw.org.sekainohane.common.logger;

import java.util.List;

import org.joda.time.LocalDateTime;

import com.google.common.base.Splitter;

public class Log {
	
	private static final String INFO = "INFO";
	private static final String WARN = "WARN";
	private static final String ERROR = "ERROR";
	
	private static String logString(String logType, String log, Object[] objs) {
		List<String> logElements = Splitter.on("{}").splitToList(log);
		
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(logType).append("][").append(LocalDateTime.now().toString("yyyy-MM-dd HH:mm:ss.SSS")).append("]: ");
		for (int i = 0; i < logElements.size(); i++) {
			sb.append(logElements.get(i));
			if (i < objs.length) {
				sb.append(objs[i].toString());
			}
		}
		
		return sb.toString();
	}
	
	private static void showLog(String logType, String log, Object[] objs) {
		System.out.println(logString(logType, log, objs));
	}
	
	public static void info(String log) {
		info(log, new Object[0]);
	}
	
	public static void info(String log, Object... objs) {
		showLog(INFO, log, objs);
	}
	
	public static void warn(String log) {
		warn(log, new Object[0]);
	}
	
	public static void warn(String log, Object... objs) {
		showLog(WARN, log, objs);
	}
	
	public static void error(String log) {
		error(log, new Object[0]);
	}
	
	public static void error(String log, Object... objs) {
		showLog(ERROR, log, objs);
	}

}
