package com.example.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity {

	private int mRadioId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText firstNumTxt = (EditText) findViewById(R.id.first_number);
		final EditText secNumTxt = (EditText) findViewById(R.id.second_number);
		final TextView resultTxt = (TextView) findViewById(R.id.result_text);

		RadioGroup operators = (RadioGroup) findViewById(R.id.operator_group);
		mRadioId = operators.getCheckedRadioButtonId();
		operators.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				mRadioId = checkedId;
				RadioButton newOperator = (RadioButton) findViewById(checkedId);
				TextView operatorText = (TextView) findViewById(R.id.operator);
				operatorText.setText(newOperator.getText());

			}
		});

		final Button evaluate = (Button) findViewById(R.id.evaluate_btn);
		evaluate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				try{

					int num1 = Integer.parseInt(firstNumTxt.getText()
							.toString());
					int num2 = Integer.parseInt(secNumTxt.getText().toString());
					float result = 0;

					switch (mRadioId) {
					case R.id.add:
						result = (float) add(num1, num2);
						break;
					case R.id.subtract:
						result = (float) subtract(num1, num2);
						break;
					case R.id.multiply:
						result = (float) multiply(num1, num2);
						break;
					case R.id.divide:
						result = divide(num1, num2);
						break;
					}

					resultTxt.setText(String.valueOf(result));
				}catch (NumberFormatException ex) {
					resultTxt.setText("Enter values in boxes above");
				}
			}
		});

		secNumTxt.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))
						|| (actionId == EditorInfo.IME_ACTION_DONE)) {
					evaluate.performClick();
				}
				return false;
			}
		});

	}

	public int add(int x, int y) {

		return x + y;
	}

	public int subtract(int x, int y) {

		return x - y;
	}

	public int multiply(int x, int y) {

		return x * y;
	}

	public float divide(int x, int y) {

		return ((float) x) / y;
	}

}
