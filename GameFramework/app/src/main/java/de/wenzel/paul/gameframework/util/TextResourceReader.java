package de.wenzel.paul.gameframework.util;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Die Klasse {@link TextResourceReader} dient dazu den Text aus einer Datei auszulesen.
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class TextResourceReader {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	
	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode list einen Text aus einer Datei und gibt ihn aus.
	 * 
	 * @param context zu welcher Activity die gewünschte Aktion gehört, um auf die gewünschte Datei zugreifen zu können
	 * @param resourceId die ID der Datei die ausgelesen werden soll (R.*)
	 * 
	 * @return der Text aus der gewünschten Datei
	 */
	public static String readTextFileFromResource(Context context, int resourceId) {
		StringBuilder body = new StringBuilder();
		
		try {
			InputStream inputStream = context.getResources().openRawResource(resourceId);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			String nextLine = "";
			
			while ((nextLine = bufferedReader.readLine()) != null) {
				body.append(nextLine);
				body.append('\n');
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not open resource: " + resourceId, e);
		} catch (Resources.NotFoundException nfe) {
			throw new RuntimeException("Resource not found: " + resourceId, nfe);
		}
		
		return body.toString();
	}
}
