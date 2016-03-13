package de.wenzel.paul.gameframework.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Die Klasse {@link ObjectSaver} [...]
 * Alle Klassen die von diser Klasse gespeichert werden sollen müssen lediglich das Interface {@link Serializable}
 * implementieren!
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class ObjectSaver {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	
	
	
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link ObjectSaver}. 
	 */
	public ObjectSaver() {
		// Datenfelder initialisieren
		
	}
	
	
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	
	
	
	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	public static void saveObjectToFile(Object object, String fileName) {
		try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
	}
	
	public static Object loadObjectFromFile(String fileName) {
		Object deserializedObject = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("userDetails.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            deserializedObject = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        
        return deserializedObject;
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
