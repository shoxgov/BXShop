package com.sq.bxstore.utils;

import android.os.Environment;
/**
 * 
 * @author 
 */
public class Tools {
	/**
	 * 是否有SDCard
	 * @return
	 */
	public static boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
}
