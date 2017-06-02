package com.example.try_hit_animal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MAction2;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_hit_animal.model.Background;
import com.example.try_hit_animal.model.ObstracFactory;
import com.example.try_hit_animal.model.ProtocolMainscreen;
import com.example.try_hit_animal.utils.AudioUtil;
import com.example.try_hit_animal.utils.CommonUtil;

public class GameScene extends EasyScene implements
		ButtonLayer.OnClickListener, ProtocolMainscreen {
	GameView gameView;
	Background bg = new Background(0, 0, false);
	ObstracFactory obstractFactory;
	LabelLayer scoreLab = new LabelLayer(0, 0, false);
	LabelLayer appLab = new LabelLayer(0, 0, false);
	LabelLayer myLabel = new LabelLayer(0, 0, false);
	int appleNum = 0;

	float moveSpeed = 15.0f;
	float maxSpeed = 40.0f;
	float distance = 0.0f;
	float lastDis = 0.0f;
	float theY = 0.0f;
	boolean isLose = false;
	boolean isReadyToJump = false;
	boolean isStartToDisapearPlatform = false;

	public GameScene(Context context, String id, int level) {
		super(context, id, level);
		// TODO Auto-generated constructor stub
		this.addAutoDraw(bg);

		int skyColor = Color.argb(255, 113, 197, 207);
		this.setBackgroundColor(skyColor);
		scoreLab.getPaint().setTextAlign(Align.LEFT);
		scoreLab.setPosition(20, 150);
		scoreLab.setText("run: 0 km");
		this.addAutoDraw(scoreLab);

		appLab.getPaint().setTextAlign(Align.LEFT);
		appLab.setPosition(400, 150);
		appLab.setText("eat: apple");
		this.addAutoDraw(appLab);

		myLabel.setText("");
		myLabel.setTextSize(100);
		myLabel.setzPosition(100);
		myLabel.setAutoHWByText();
		LayerParam layerParam = new LayerParam();
		layerParam.setPercentageX(0.5f);
		layerParam.setEnabledPercentagePositionX(true);
		myLabel.setLayerParam(new LayerParam());
		myLabel.setPosition(CommonUtil.screenWidth / 2,
				CommonUtil.screenHeight / 2);
		myLabel.setAnchorPoint(0.5f, 0);
		this.addAutoDraw(myLabel);

		obstractFactory = new ObstracFactory(0, 0, CommonUtil.screenWidth,
				false);
		this.addAutoDraw(obstractFactory);

		AudioUtil.playBackgroundMusic();

		isEnableRemoteController(false);
	}

	void checkCollistion() {

	}

	public void downAndUp(final Sprite sprite, float down, float downTime,
			float up, float upTime, boolean isRepeat) {
		MovementAction downAct = MAction
				.moveByY(down, (long) (downTime * 1000));
		MovementAction upAct = MAction.moveByY(up, (long) (upTime * 1000));
		MovementAction downUpAct = MAction2.sequence(new MovementAction[] {
				downAct, upAct });
		downUpAct.setMovementActionController(new MovementAtionController());
		if (isRepeat) {
			sprite.runMovementActionAndAppend(MAction.repeatForever(downUpAct));
		} else {
			sprite.runMovementActionAndAppend(downUpAct);
		}
	}

	@Override
	public GameView initGameView(Activity activity,
			IGameController gameController, IGameModel gameModel) {
		// TODO Auto-generated method stub
		return gameView = new GameView(activity, gameController, gameModel);
	}

	public void action() {
		// gameDog.alone();
	}

	@Override
	public boolean onSceneTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (isLose) {
				reSet();
			} else {
				// if (panda.status != Status.jump2) {
				// AudioUtil.playJump();
				// }
				isReadyToJump = true;
			}
		}

		return super.onSceneTouchEvent(event);
	}

	private void checkGameOver() {

	}

	private void gameover() {
		System.out.println("game over");
		myLabel.setText("game over");
		AudioUtil.playDead();
		isLose = true;
		AudioUtil.stopBackgroundMusic();
	}

	// 重新开始游戏
	public void reSet() {
		isLose = false;
		// panda.setPosition(200, 400);
		// panda.reset();
		myLabel.setText("");
		moveSpeed = 15.0f;
		distance = 0.0f;
		lastDis = 0.0f;
		appleNum = 0;
		// platformFactory.reset();
		obstractFactory.reSet();
		AudioUtil.playBackgroundMusic();
		isStartToDisapearPlatform = false;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		if (isLose) {
			// do nothing
		} else {
			LayerManager.getInstance().processLayers();

			checkGameOver();
		}
	}

	@Override
	public void onGetData(float dist, float theY) {
		// TODO Auto-generated method stub
		this.lastDis = dist;
		this.theY = theY;
		obstractFactory.theY = theY;
	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		LayerManager.getInstance().drawLayers(canvas, null);

		Paint paint = new Paint();
		paint.setColor(Color.RED);
		// canvas.drawRect(panda.getCollisionRectF(), paint);
		// fight.drawSelf(canvas, null);
	}

	@Override
	public void beforeGameStart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void arrangeView(Activity activity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActivityContentView(Activity activity) {
		// TODO Auto-generated method stub
		activity.setContentView(gameView);
	}

	@Override
	public void afterGameStart() {
		// TODO Auto-generated method stub
		Log.e("game scene", "game start");
		AudioUtil.playBackgroundMusic();
	}

	@Override
	protected void beforeGameStop() {
		// TODO Auto-generated method stub
		Log.e("game scene", "game stop");
		AudioUtil.stopBackgroundMusic();
	}

	@Override
	protected void afterGameStop() {
		// TODO Auto-generated method stub
		// AudioUtil.stopBackgroundMusic();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(ButtonLayer buttonLayer) {
		// TODO Auto-generated method stub

	}

}
