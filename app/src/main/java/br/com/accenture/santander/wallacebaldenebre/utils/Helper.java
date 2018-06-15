package br.com.accenture.santander.wallacebaldenebre.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import br.com.accenture.santander.wallacebaldenebre.R;

public class Helper {
    public static void toast(Activity a, String msg) {
        Toast.makeText(a, msg, Toast.LENGTH_LONG).show();
    }

    public static void openClass(Activity a, Class clazz) {
        a.startActivity(new Intent(a, clazz));
    }

    public static void loadFragment(Fragment f, boolean addToBackStack, Bundle args, Activity activity) {
        f.setArguments(args);
        android.support.v4.app.FragmentTransaction transaction = ((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(R.id.layout_base_contact, f);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static boolean isPhone(String phone) {
        return phone.trim().matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$");
    }

    public static LinearLayout.LayoutParams margins(int left, int top, int right, int bottom) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, top, right, bottom);
        return lp;
    }

    public static RelativeLayout.LayoutParams marginsRL(int left, int top, int right, int bottom) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(left, top, right, bottom);
        lp.alignWithParent = true;
        return lp;
    }

    public static int doubleToInt(double number) {
        return (int) number;
    }

    public static void hideSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //  ==================== req WebService
    private static String streamToString(InputStream input) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) sb.append(line).append('\n');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        return sb.toString();
    }

    public static String callAPI(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //  lê o response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = streamToString(in);
        } catch (MalformedURLException e) {
            Log.e("LOG", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("LOG", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("LOG", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("LOG", "Exception: " + e.getMessage());
        }
        return response;
    }

}
