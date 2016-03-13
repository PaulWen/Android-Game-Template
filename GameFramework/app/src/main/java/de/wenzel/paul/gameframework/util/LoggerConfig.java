package de.wenzel.paul.gameframework.util;

import de.wenzel.paul.gameframework.BuildConfig;


/**
 * Die Klasse {@link LoggerConfig} dient zum Verwalten der Logeinträge.<br/><br/>
 * 
 * <strong>Beispiel Log-Einträge:</strong><br/>
 * Format: Log.d(tag, msg);<br/><br/>
 *  
 * if (LoggerConfig.DEBUG) Log.d(ClassName.class + "", "Debug");<br/>
 * if (LoggerConfig.ERROR) Log.e(ClassName.class + "", "Error");<br/>
 * if (LoggerConfig.INFO) Log.i(ClassName.class + "", "Info");<br/>
 * if (LoggerConfig.WARN) Log.w(ClassName.class + "", "Warn");<br/>
 * if (LoggerConfig.VERBOSE) Log.v(ClassName.class + "", "Verbose");<br/>
 * if (LoggerConfig.ASSERT) Log.wtf(ClassName.class + "", "Assert");<br/>
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class LoggerConfig {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////

	/** das derzeitige Log-Level */
	private static final int LOG_LEVEL = (BuildConfig.DEBUG) ? 5 /*<= Log-Level während der Entwicklung */ : 2 /*<= Log-Level wenn App im Store */;
	
	/** einfache Nachricht */
	public static final boolean VERBOSE = LOG_LEVEL >= 5;
	/** Debug-Meldung, um Fehler zu finden */
	public static final boolean DEBUG = LOG_LEVEL >= 4;
	/** Informations-Meldung, um nützliche Informationen zu loggen */
	public static final boolean INFO = LOG_LEVEL >= 3;
	/** Warn-Meldung, wenn etwas unerwartetes passiert, was aber noch kein Error ist */
	public static final boolean WARN = LOG_LEVEL >= 2;
	/** Fehler-Meldung, wenn ein Fehler auftritt */
	public static final boolean ERROR = LOG_LEVEL >= 1;
	/** Super-Fehler-Meldung, wenn ein Fehler auftritt der gar nicht auftreten kann */
	public static final boolean ASSERT = LOG_LEVEL >= 0;

}
