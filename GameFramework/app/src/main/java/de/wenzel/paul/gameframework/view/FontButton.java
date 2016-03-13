package de.wenzel.paul.gameframework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import de.wenzel.paul.gameframework.Config;
import de.wenzel.paul.gameframework.R;

/**
 * Die Klasse {@link FontButton} [...]
 * 
 * 
 * @author Paul Wenzel
 *
 */
public class FontButton extends Button {
	
/////////////////////////////////////////////////Datenfelder/////////////////////////////////////////////////
	
	
	
	
	
/////////////////////////////////////////////////Konstruktor/////////////////////////////////////////////////
	
	/**
	 * Der Konstruktor der Klasse {@link FontButton}. 
	 */
	public FontButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}
	
	/**
	 * Der Konstruktor der Klasse {@link FontButton}. 
	 */
	public FontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}
	
	/**
	 * Der Konstruktor der Klasse {@link FontButton}. 
	 */
	public FontButton(Context context) {
		super(context);
		init(null);
	}
	
//////////////////////////////////////////////Getter und Setter//////////////////////////////////////////////
	
	
	

	
///////////////////////////////////////////////geerbte Methoden//////////////////////////////////////////////
	
	

	
	
	
//////////////////////////////////////////////////Methoden///////////////////////////////////////////////////
	
	/**
	 * Die Methode übernimmt die selbst angelegten Attribute.
	 * 
	 * @param attrs die Attribute mit den Werten
	 */
	private void init(AttributeSet attrs) {
		if (attrs!=null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Font);
			String fontName = a.getString(R.styleable.Font_customFont);
			if (fontName!=null) {
				Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), Config.FONTS_FOLDER + "/" +fontName);
				setTypeface(myTypeface);
			}
			a.recycle();
		}
	}
	
///////////////////////////////////////////////Innere Klassen////////////////////////////////////////////////	
	
	
	
	
}
