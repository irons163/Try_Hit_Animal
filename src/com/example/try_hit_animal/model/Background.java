package com.example.try_hit_animal.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.stage.StageManager;
import com.example.try_hit_animal.R;

public class Background extends Layer {

	public Background(float x, float y, boolean autoAdd) {
		super(x, y, autoAdd);

		Context context = StageManager.getCurrentStage();
		Bitmap bg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.bg01);

		setBitmapAndAutoChangeWH(bg);
	}
}
