package br.banco.services.splash.task;
 // executar tarefa caso o app esteja diferente
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


import br.banco.services.R;

public class LoadInfo extends Activity {
	
	public TextView textView;
	LoadInfoTask asyncTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView) findViewById(R.id.mTextView);

		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}
	
	LoadInfoTask.OnAsyncResult asynResult = new LoadInfoTask.OnAsyncResult() {
		
		@Override
		public void onResultSuccess(final int resultCode, final String message) {
			
			runOnUiThread(new Runnable() {
				public void run() {
					textView.setText("Code : " + resultCode + "\nMessage : " + message);
				}
			});
		}
		
		@Override
		public void onResultFail(final int resultCode, final String errorMessage) {

			runOnUiThread(new Runnable() {
				public void run() {
					textView.setText("Code : " + resultCode + "\nMessage : " + errorMessage);
				}
			});
			
		}
	};
	
}
