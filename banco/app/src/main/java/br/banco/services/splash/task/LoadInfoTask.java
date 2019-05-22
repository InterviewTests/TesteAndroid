package br.banco.services.splash.task;


import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class LoadInfoTask extends AsyncTask<Void, Void, Void> {
	
	public OnAsyncResult onAsyncResult;
	
	public void setOnResultListener(OnAsyncResult onAsyncResult) {
		if (onAsyncResult != null) {
			this.onAsyncResult = onAsyncResult;
			
		}
	}
	
	@Override
	protected Void doInBackground(Void... params) {



		if (onAsyncResult != null) {


			if (2==2) {
				onAsyncResult.onResultSuccess(0, "SUCESSO");
			} else {
				onAsyncResult.onResultFail(1, "ERRO");
			}
		}
		
		return null;
	}

	protected void onPostExecute(Integer results) {

		try {


			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {

				Log.d("FUND","SUCESSO");

				}
			}, 5000);

		} catch (Exception e) {
			Log.d("FUND","ERRO");
			e.printStackTrace();
		}

	}




	public interface OnAsyncResult {
		
		  void onResultSuccess(int resultCode, String message);

		  void onResultFail(int resultCode, String errorMessage);
	}
	
}


/*


			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {



				}
			}, 3000);

 */