package lzacheu.com.br.santanderinvestimento.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;

import lzacheu.com.br.santanderinvestimento.R;

public class TypeFaceBuilder {

	public TypeFaceBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static Typeface getDinpRegular(Context context){
		return  ResourcesCompat.getFont(context, R.font.dinproregular);
		
	}
	
	public static Typeface getDinpMedium(Context context){
		return  ResourcesCompat.getFont(context, R.font.dinpromedium);
				
	}
	
	public static Typeface getDinpLight(Context context){
		return  ResourcesCompat.getFont(context, R.font.dinprolight);
	}

	public static Typeface getDineStd(Context context){
		return  ResourcesCompat.getFont(context, R.font.din_engschrift_std);
	}
}
