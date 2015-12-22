package edu.uic.cs478.Muthiah;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TextDial extends Activity {

	private EditText inputText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		inputText = (EditText)findViewById(R.id.inputText);
	}
	
	/**
	 * This method handles the function of parsing the text entered by the user and compares
	 * it with the regular expression to find a match. If there is a match then the dialer
	 * is opened with the entered number. The format (123) 456-7890
	 * @param v - Its a view returned by the system indicating the click of the button
	 */
	public void processInputText(View v)
	{
		String enteredText = inputText.getText().toString();
		
		// Regular expression to find number in the format (123) 456-7890
		String pattern = "([(]\\d{3}[)] ?\\d{3}-?\\d{4})";
		String intentData;
		Matcher m = Pattern.compile(pattern).matcher(enteredText);
		if(m.find())
		{
			//Gets only the first number, if there are several matches
			intentData = "tel:" + m.group(0);
			Intent diallerIntent = new Intent(Intent.ACTION_DIAL);
			diallerIntent.setData(Uri.parse(intentData));
			startActivity(diallerIntent);
		}		
	}
}

