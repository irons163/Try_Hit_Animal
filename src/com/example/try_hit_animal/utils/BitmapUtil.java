package com.example.try_hit_animal.utils;

import java.lang.reflect.Array;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.try_hit_animal.R;

public class BitmapUtil {

	static Context context;

	public static Bitmap bg1;
	public static Bitmap chair02;
	public static Bitmap chair02_1;
	public static Bitmap chair02_2;
	public static Bitmap chair02_3;
	public static Bitmap chair02_4;
	public static Bitmap lemon;
	public static Bitmap grapes;
	public static Bitmap orange;
	public static Bitmap watermelon;

	public static Bitmap redPoint, bluePoint;
	public static Bitmap yellowPoint;
	public static Bitmap greenPoint;
	public static Bitmap mapBg1;

	public static Bitmap[] jewelBitmaps;
	
	public static Bitmap hamster;
	
	public static Bitmap bg;
	public static Bitmap flower;
	public static Bitmap fireball;
	public static Bitmap cloud1;
	public static Bitmap cloud2;
	public static Bitmap cloud3;
	
	public static Bitmap restartBtn01;
	public static Bitmap restartBtn02;
	public static Bitmap gameover;
	
	public static Bitmap sheep;
	public static Bitmap sheepJump;
	public static Bitmap sheepJump2;
	public static Bitmap sheepJump3;
	
	public static int sheepHW = 250;
	
	public static List<Bitmap> runBitmaps;
	
	public static Bitmap[]timeScoresImages;
	public static Bitmap[] bgs;
	public static Bitmap[] cat1Textures, cat2Textures, cat3Textures, cat4Textures ,cat5Textures;
	public static Bitmap treatureBox01, hamer;
	public static Bitmap coin10, coin30, coin50;
	
	public static void initBitmap(Context context) {
		if(BitmapUtil.context==null){
			BitmapUtil.context = context;
			initBitmap();
		}	
	}

	private static void initBitmap() {
		 greenPoint = BitmapFactory.decodeResource(context.getResources(),
		 R.drawable.green_point);
		 
		 treatureBox01 = BitmapFactory.decodeResource(context.getResources(),
				 R.drawable.treaturebox01);
		 hamer = BitmapFactory.decodeResource(context.getResources(),
				 R.drawable.images);
		 coin10 = BitmapFactory.decodeResource(context.getResources(),
				 R.drawable.coin_10_btn01);
		 coin30 = BitmapFactory.decodeResource(context.getResources(),
				 R.drawable.coin_30_btn01);
		 coin50 = BitmapFactory.decodeResource(context.getResources(),
				 R.drawable.coin_50_btn01);

		BitmapFactory.Options options = new BitmapFactory.Options();
		// Make sure it is 24 bit color as our image processing algorithm
		// expects this format
		options.inPreferredConfig = Config.ARGB_8888;

		options.inScaled = false;
		
		mapBg1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.ic_launcher, options);
		redPoint = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.red_point, options);
		bluePoint = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.blue_point, options);
		yellowPoint = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.yellow_point, options);	
	}

	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width,
			int height) {
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); 
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}

	public static void createJewelBitmaps(int w, int h) {
		jewelBitmaps = new Bitmap[] {
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.orange_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.yellow_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.green_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.blue_point), w, h),
				BitmapUtil.yellowPoint = BitmapUtil.createSpecificSizeBitmap(
						context.getResources().getDrawable(
								R.drawable.brown_point), w, h) };
	}
	
	public static void  initTextures(){
//	    hamster_injure = [SKTexture textureWithImageNamed:@"hamster_injure"];
	    
//	    time01  = [SKTexture textureWithImageNamed:@"s1"];
//	    time02  = [SKTexture textureWithImageNamed:@"s2"];
//	    time03  = [SKTexture textureWithImageNamed:@"s3"];
//	    time04  = [SKTexture textureWithImageNamed:@"s4"];
//	    time05  = [SKTexture textureWithImageNamed:@"s5"];
//	    time06  = [SKTexture textureWithImageNamed:@"s6"];
//	    time07  = [SKTexture textureWithImageNamed:@"s7"];
//	    time08  = [SKTexture textureWithImageNamed:@"s8"];
//	    time09  = [SKTexture textureWithImageNamed:@"s9"];
//	    time00  = [SKTexture textureWithImageNamed:@"s0"];
//	    timeQ  = [SKTexture textureWithImageNamed:@"dot"];
//	    
//	    timeScores = @[time00, time01, time02, time03, time04, time05,time06, time07, time08, time09, timeQ];
	    
	    Bitmap image01 = BitmapFactory.decodeResource(context.getResources(),
	   		 R.drawable.s1);
	    Bitmap image02 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image03 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image04 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image05 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image06 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image07 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image08 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image09 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap image00 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    Bitmap imageQ = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.s1);
	    timeScoresImages = new Bitmap[]{image00, image01, image02, image03, image04, image05, image06, image07, image08, image09, imageQ};
	    
	    Bitmap bg01 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg01);
	    Bitmap bg02 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg02);
	    Bitmap bg03 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg03);
	    Bitmap bg04 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg04);
	    Bitmap bg05 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg05);
	    Bitmap bg06 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg06);
	    Bitmap bg07 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg07);
	    Bitmap bg08 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg08);
	    Bitmap bg09 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg09);
	    Bitmap bg10 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg10);
	    Bitmap bg11 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg11);
	    Bitmap bg12 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg12);
	    Bitmap bg13 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg13);
	    Bitmap bg14 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg14);
	    Bitmap bg15 = BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.bg15);
	    
	    bgs = new Bitmap[]{bg01, bg02, bg03 ,bg04, bg05, bg06,bg07, bg08, bg09, bg10, bg11, bg12, bg13, bg14, bg15};
	}
	
	public static void initCatTextures(){
	    cat1Textures = new Bitmap[]{BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.cat01_1), BitmapFactory.decodeResource(context.getResources(),
				   		 R.drawable.cat01_2), BitmapFactory.decodeResource(context.getResources(),
						   		 R.drawable.cat01_3), BitmapFactory.decodeResource(context.getResources(),
								   		 R.drawable.cat01_4)};
	    cat2Textures = new Bitmap[]{BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.cat02_1), BitmapFactory.decodeResource(context.getResources(),
				   		 R.drawable.cat02_2), BitmapFactory.decodeResource(context.getResources(),
						   		 R.drawable.cat02_3), BitmapFactory.decodeResource(context.getResources(),
								   		 R.drawable.cat02_4)};
	    cat3Textures = new Bitmap[]{BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.cat03_1), BitmapFactory.decodeResource(context.getResources(),
				   		 R.drawable.cat03_2), BitmapFactory.decodeResource(context.getResources(),
						   		 R.drawable.cat03_3), BitmapFactory.decodeResource(context.getResources(),
								   		 R.drawable.cat03_4)};
	    cat4Textures = new Bitmap[]{BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.cat04_1), BitmapFactory.decodeResource(context.getResources(),
				   		 R.drawable.cat04_2), BitmapFactory.decodeResource(context.getResources(),
						   		 R.drawable.cat04_3), BitmapFactory.decodeResource(context.getResources(),
								   		 R.drawable.cat04_4)};
	    cat5Textures = new Bitmap[]{BitmapFactory.decodeResource(context.getResources(),
		   		 R.drawable.cat05_1), BitmapFactory.decodeResource(context.getResources(),
				   		 R.drawable.cat05_2), BitmapFactory.decodeResource(context.getResources(),
						   		 R.drawable.cat05_3), BitmapFactory.decodeResource(context.getResources(),
								   		 R.drawable.cat05_4)};
	}
}
