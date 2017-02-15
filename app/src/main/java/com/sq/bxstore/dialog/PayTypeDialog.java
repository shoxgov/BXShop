/**************************************************************************************
 * [Project]
 *       MyProgressDialog
 * [Package]
 *       com.lxd.widgets
 * [FileName]
 *       CustomProgressDialog.java
 * [Copyright]
 *       Copyright 2012 LXD All Rights Reserved.
 * [History]
 *       Version          Date              Author                        Record
 *--------------------------------------------------------------------------------------
 *       1.0.0           2012-4-27         lxd (rohsuton@gmail.com)        Create
 **************************************************************************************/

package com.sq.bxstore.dialog;

import com.sq.bxstore.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/********************************************************************
 *******************************************************************/

public class PayTypeDialog extends Dialog {
	private Context context = null;

	public PayTypeDialog(Context context) {
		super(context);
		this.context = context;
	}

	public PayTypeDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

}
