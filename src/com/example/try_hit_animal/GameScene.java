package com.example.try_hit_animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MAction2;
import com.example.try_gameengine.action.MAction3;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.ReturnBackDecorator;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_hit_animal.model.Background;
import com.example.try_hit_animal.model.BeHitNode;
import com.example.try_hit_animal.model.ObstracFactory;
import com.example.try_hit_animal.model.ProtocolMainscreen;
import com.example.try_hit_animal.utils.AudioUtil;
import com.example.try_hit_animal.utils.BitmapUtil;
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
	
	final int DEFAULT_HP = 1;
	int catMaxHp = DEFAULT_HP;

	Boolean isGameRun, isGameStart;
	
	int score;
    float upSpeed;
    float downSpeed;
    int createCatFrequenceRate;
    int delaytime;
    int randomMaxCatLimit;
    int randomMinCatLimit;
    float eatedCoinFrequence;
    float eatedFrequenceFasterFactor;
    float eatedFrequenceSlowerFactor;
    int coin10Num;
    int coin30Num;
    int coin50Num;
    List<Sprite> catArray, catInRunActionArray;
    List<Integer> catOriganalPositionY;
    Sprite textureBox;
    LabelLayer scoreLabel, scoreByBreakGameModeLabel;
    LabelLayer coin10NumNode, coin30NumNode, coin50NumNode;
    Sprite hamer;
    Sprite modeBtn;
    List<Bitmap> musicBtnTextures;
    LabelLayer gameTimeNode;
    
    Sprite hpBar;
    Bitmap[] currentCatTextures;
    
    float lastSpawnTimeInterval, lastSpawnEatedCoinTimeInterval, lastCreateCatTimeInterval;
    int currentMoneyLevel;
    int catCurrentHp;
    int ccount;
    
    {
    	  upSpeed = 1;
    	  downSpeed = 1;
    	  createCatFrequenceRate = 2;
          delaytime = 1;
          randomMaxCatLimit = 3;
          randomMinCatLimit = 1;
          eatedCoinFrequence = 3.0f;
          eatedFrequenceFasterFactor = 0.9f;
          eatedFrequenceSlowerFactor = 1.1f;
          BitmapUtil.initTextures();
          BitmapUtil.initCatTextures();
          currentCatTextures = randomCatTextures();
          catArray = new ArrayList<Sprite>();
          catInRunActionArray = new ArrayList<Sprite>();
          catOriganalPositionY = new ArrayList<Integer>();
          initWithBg();
          textureBox = new Sprite(BitmapUtil.timeScoresImages[0], 100, 100, false);
          textureBox.setPosition(200, 400);
          addChild(textureBox);
          
//          self.backgroundColor = [SKColor colorWithRed:0.15 green:0.15 blue:0.3 alpha:1.0];
          
          initWithScoreLabelAndScoreByBreakGameModeLabel();
          
          initBoard();
          
          hamer = new Sprite();
          hamer.setSize(50, 50);
          hamer.setBitmap(BitmapUtil.hamer);
          addChild(hamer);
          
          initButtons();
    }
    
    private void initWithBg(){
    	
    }
    
    private void initWithScoreLabelAndScoreByBreakGameModeLabel(){
    	scoreLabel = new LabelLayer(CommonUtil.screenWidth/2f, CommonUtil.screenHeight/2f - 135, false);
        scoreLabel.setText("0");
        scoreLabel.setTextSize(70);
        scoreLabel.setTextColor(Color.RED);
        
        addChild(scoreLabel);
        
        scoreByBreakGameModeLabel = new LabelLayer(CommonUtil.screenWidth/2f, CommonUtil.screenHeight/2f - 50, false);
        scoreByBreakGameModeLabel.setText("0");
        scoreByBreakGameModeLabel.setTextSize(50);
        scoreByBreakGameModeLabel.setTextColor(Color.RED);
        
        addChild(scoreByBreakGameModeLabel);
        scoreByBreakGameModeLabel.setHidden(true);
    }
    
    public void initBoard(){
        for (int i = 0; i<3; i++) {
            for(int j = 0; j <3; j++){
                BeHitNode cat = new BeHitNode();
                cat.setPosition(i*120+40, j*110+50);
                cat.setHited(true);
                addChild(cat);
                catArray.add(cat);
                catOriganalPositionY.add((int) cat.getPosition().y);
                
                Sprite obstac = new Sprite(BitmapUtil.treatureBox01, (int)(cat.getSize().x*1.3f), (int) (cat.getSize().y*1.3f), false);
                obstac.setPosition(cat.getPosition().x, cat.getPosition().y);
                addChild(obstac);
            }
        }
    }
    
    private void initButtons(){
    	modeBtn = new Sprite();
    	modeBtn.setSize(42, 42);
    	modeBtn.setPosition(CommonUtil.screenWidth - modeBtn.getSize().x, CommonUtil.screenHeight/2 - modeBtn.getSize().y*2);
    	addChild(modeBtn);
    	
    	musicBtnTextures = new ArrayList<Bitmap>();
    	musicBtnTextures.add(BitmapFactory.decodeResource(context.getResources(),
		 R.drawable.green_point));
    	musicBtnTextures.add(BitmapFactory.decodeResource(context.getResources(),
    			 R.drawable.green_point));
    	
        gameTimeNode = new LabelLayer("00:00", 0, 0, false);
        gameTimeNode.setTextStyle(Typeface.DEFAULT_BOLD);
        gameTimeNode.setTextSize(25);
        gameTimeNode.setPosition(CommonUtil.screenWidth - gameTimeNode.getSize().x/2, CommonUtil.screenHeight - gameTimeNode.getSize().y - 50);
        addChild(gameTimeNode);
        gameTimeNode.setHidden(true);
        gameTimeNode.setzPosition(20);
        
//        [MyUtils preparePlayBackgroundMusic:@"am_white.mp3"];
//        
//        id isPlayMusicObject = [[NSUserDefaults standardUserDefaults] objectForKey:@"isPlayMusic"];
//        
//        BOOL isPlayMusic = true;
//        if(isPlayMusicObject==NULL){
//            isPlayMusicObject = false;
//        }else{
//            isPlayMusic = [isPlayMusicObject boolValue];
//        }
//        
//        if(isPlayMusic){
//            [MyUtils backgroundMusicPlayerPlay];
//            musicBtn.texture = musicBtnTextures[0];
//        }else{
//            [MyUtils backgroundMusicPlayerPause];
//            musicBtn.texture = musicBtnTextures[1];
//        }
//        
//        cloudMoveSpeed = CLOUD_DEFAULT_MOVE_SPEED;
//        
//        if(isPlayMusic){
//            [MyUtils backgroundMusicPlayerPlay];
//            musicBtn.texture = musicBtnTextures[0];
//        }else{
//            [MyUtils backgroundMusicPlayerPause];
//            musicBtn.texture = musicBtnTextures[1];
//        }
//        
//        cloudMoveSpeed = CLOUD_DEFAULT_MOVE_SPEED;
//        
//        if(self.mode==MyScene.GAME_BREAK){
//            isCanPressStartBtn = true;
//            [self initWithLabaHoleAndCoin];
//            scoreLabel.hidden = NO;
//            scoreByBreakGameModeLabel.hidden = NO;
//            self.hpBar = [SKSpriteNode spriteNodeWithImageNamed:@"hp_bar"];
//            
//            self.hpBar.zPosition = 2;
//            
//            SKSpriteNode * hpFrame = [SKSpriteNode spriteNodeWithImageNamed:@"hp_frame"];
//            hpFrame.size = CGSizeMake(self.frame.size.width, 42);
//            
//            hpFrame.position = CGPointMake(CGRectGetMidX(self.frame),
//                                           CGRectGetMaxY(self.frame) - hpFrame.size.height/2);
//            
//            hpFrame.zPosition = 2;
//            
//            [self addChild:hpFrame];
//            [self addChild:self.hpBar];
//            [self changeCatHpBar];
//        }else if(self.mode==MyScene.GAME_INFINITY){
////            [self initWithLabaHoleAndCoin];
//            isGameStart = true;
//            scoreLabel.hidden = NO;
//        }else{
//            //            [self initWithLabaHoleAndCoin];
//            gameTimeNode.hidden = NO;
//            [self initGameTimer];
//            isGameStart = true;
//            scoreLabel.hidden = YES;
//            scoreByBreakGameModeLabel.hidden = NO;
//        }
        
        loadScore();
        updateLoadScore();
        
        loadCoin();
        updateCoinNumNode();
    }
    

private void loadScore(){
//    score = [[[NSUserDefaults standardUserDefaults] objectForKey:@"score"] intValue];
}

private void updateLoadScore(){
    scoreLabel.setText(score+"");
}

private void loadCoin(){
//    coin10Num = [[[NSUserDefaults standardUserDefaults] objectForKey:@"savedcoin10Num"] intValue];
//    coin30Num = [[[NSUserDefaults standardUserDefaults] objectForKey:@"savedcoin30Num"] intValue];
//    coin50Num = [[[NSUserDefaults standardUserDefaults] objectForKey:@"savedcoin50Num"] intValue];
}

private void updateCoinNumNode(){
    coin10NumNode.setText(coin10Num + "");
    coin30NumNode.setText(coin30Num + "");
    coin50NumNode.setText(coin50Num + "");
}

//-(void)didMoveToView:(SKView *)view {
//      catCurrentHp = DEFAULT_HP;
//      
//
//  
////  }
////  return self;
//}

private void randomCatApearAction(){
    Random random = new Random();
    int randomCatNum = random.nextInt(randomMaxCatLimit - randomMinCatLimit + 1) + randomMinCatLimit;
    
    for(int i = 0; i < randomCatNum ; i++){
        int catIndex = random.nextInt(catArray.size());
        
        int goodPercent = 5;
        boolean isGood = random.nextInt(10)<goodPercent?true:false;
        
        if(!catInRunActionArray.contains(catArray.get(catIndex))){
        
            final BeHitNode cat = (BeHitNode) catArray.get(catIndex);
//            cat.isHited = NO;
            
            if(isGood){
                if (mode == GAME_INFINITY || mode == GAME_LIMIT){
                    int r = random.nextInt(3);
                    if(r==0){
                        cat.setBitmap(BitmapUtil.coin10);
                        cat.setBeHitedTextture(cat.getBitmap());
                        cat.setMoney(10);
                    }else if(r==1){
                        cat.setBitmap(BitmapUtil.coin30);
                        cat.setBeHitedTextture(cat.getBitmap());
                        cat.setMoney(30);
                    }else if(r==2){
                        cat.setBitmap(BitmapUtil.coin50);
                        cat.setBeHitedTextture(cat.getBitmap());
                        cat.setMoney(50);
                    }
                }else{
                    if(currentMoneyLevel==10){
                    	cat.setBitmap(BitmapUtil.coin10);
                        cat.setBeHitedTextture(cat.getBitmap());
                        cat.setMoney(10);
                    }else if(currentMoneyLevel==30){
                    	cat.setBitmap(BitmapUtil.coin30);
                        cat.setBeHitedTextture(cat.getBitmap());
                        cat.setMoney(30);
                    }else if(currentMoneyLevel==50){
                    	cat.setBitmap(BitmapUtil.coin50);
                        cat.setBeHitedTextture(cat.getBitmap());
                        cat.setMoney(50);
                    }
                }
                
                cat.setType(BeHitNode.Type.COIN);
//                cat.money = 10;
            }else{
                Bitmap[] tmp = randomCatTextures();
                cat.setBitmap(tmp[0]);
                cat.setBeHitedTextture(tmp[3]);
                cat.setType(Type.CAT);
                cat.setBeHitedTextturesArray(tmp);
            }
            
            MovementAction a = createCatUpDownAction(catArray.get(catIndex), delaytime);
            
            cat.setHited(false);
            
            MovementAction end = MAction.runBlockNoDelay(new MAction.MActionBlock() {
				
				@Override
				public void runBlock() {
					catInRunActionArray.remove(cat);
	                cat.setHited(true);
				}
			});
            
            PointF oraginalCatPosition = new PointF(cat.getPosition().x, catOriganalPositionY.get(catIndex));
            cat.setPosition(oraginalCatPosition.x, oraginalCatPosition.y);
            cat.runMovementAction(MAction2.sequence(new MovementAction[]{a, end}));
            
            catInRunActionArray.add(cat);
        }
    }
}
    
    private void update(float currentTime) {
        /* Called before each frame is rendered */
        
        if(!isGameRun)
            return;
        
        /* Called before each frame is rendered */
        // 获取时间增量
        // 如果我们运行的每秒帧数低于60，我们依然希望一切和每秒60帧移动的位移相同
        CFTimeInterval timeSinceLast = currentTime - self.lastUpdateTimeInterval;
        self.lastUpdateTimeInterval = currentTime;
        if (timeSinceLast > 1) { // 如果上次更新后得时间增量大于1秒
            timeSinceLast = 1.0 / 60.0;
            self.lastUpdateTimeInterval = currentTime;
        }
        
        updateWithTimeSinceLastUpdate(timeSinceLast);
        
        if(checkBigCatEnoughBig()){
            //game over
            isGameRun = false;
            gameOverInBreakGameMode();
        }
        
        
    }

    private void updateWithTimeSinceLastUpdate(float timeSinceLast) {
        lastSpawnTimeInterval += timeSinceLast;
        lastSpawnEatedCoinTimeInterval += timeSinceLast;
        lastCreateCatTimeInterval += timeSinceLast;
        
//        [self processContactsForUpdate];
        
        if(isGameStart && self.mode==MyScene.GAME_BREAK && self.lastSpawnEatedCoinTimeInterval >= eatedCoinFrequence){
            self.lastSpawnEatedCoinTimeInterval = 0;
//            [self bigCatEatCoin];
            [self bigCatBigger];
            [self updateCreateFrequence];
        }
        
        if (self.lastSpawnTimeInterval > 0.5) {
            self.lastSpawnTimeInterval = 0;
            
            if (willChangeGameMode != NONE_MODE) {
                self.mode = willChangeGameMode;
                switch (self.mode) {
                    case GAME_INFINITY:
                        [self changeToInfiniteMode];
                        break;
                    case GAME_BREAK:
                        [self initWithLabaHoleAndCoin];
                        [self changeToBreakGameMode];
                        break;
                    case GAME_LIMIT:
                        [self changeToTimeLimitMode];
                        break;
                    default:
                        break;
                }
                willChangeGameMode = NONE_MODE;
                return;
            }
            
        }else if(lastCreateCatTimeInterval > 0.1){
            lastCreateCatTimeInterval = 0;
            
            if(isGameStart){
                
                ccount++;
                
                if(ccount>=createCatFrequenceRate)    {
                    
                    int continueAttackCounter = 0;
                    
                    int r = arc4random_uniform(40);
                    
                    
                    if(catInRunActionArray.count==0){
//                        [self setCatSequenceDelay:0.5];
                        [self randomCatApearAction];
                    }
                    
                    //            [self randomNewCoin];
                    
                    ccount = 0;
                }
                
            }
        }
        
        
    }

//    +(int)GAME_BREAK{
//        return GAME_BREAK;
//    }
//
//    +(int)GAME_LIMIT{
//        return GAME_LIMIT;
//    }
//
//    +(int)GAME_INFINITY{
//        return GAME_INFINITY;
//    }
//
//    -(int)getGameTime{
//        return gameTime;
//    }
//
//    -(MODE)getMode{
//        return self.mode;
//    }
//
//    -(int)getScore{
//        return score;
//    }
//
//    -(int)getScoreInBreakGameMode{
//        return scoreInBreakGameMode;
//    }
//
//    -(int)getCoin10ForReward{
//        return coin10NumForReward;
//    }
//
//    -(int)getCoin30ForReward{
//        return coin30NumForReward;
//    }
//
//    -(int)getCoin50ForReward{
//        return coin50NumForReward;
//    }

    private boolean checkBigCatEnoughBig(){
        boolean isBig = false;
        if(bigCat && bigCat.position.y - bigCat.size.height/2 <= scoreLabel.position.y){
            isBig = true;
        }
        return isBig;
    }
    
    private void changeCatHpBar() {
        float hpBarWidth = CommonUtil.screenWidth/((float)catMaxHp/catCurrentHp);
        hpBar.setSize((int) hpBarWidth, 42);
        hpBar.setAnchorPoint(0.5f, 0.5f);
        float hpBarOffsetX = CommonUtil.screenWidth/10 - hpBarWidth/10;
        
        hpBar.setPosition(hpBar.getSize().x/2 + hpBarOffsetX, CommonUtil.screenHeight - hpBar.getSize().y/2);
    }
    
    public Bitmap[] randomCatTextures(){
    	Random random = new Random();
        int r = random.nextInt(5);
        Bitmap[] randomCatTextures = null;
        
        switch (r) {
            case 0:
                randomCatTextures = BitmapUtil.cat1Textures;
                break;
            case 1:
                randomCatTextures = BitmapUtil.cat2Textures;
                break;
            case 2:
                randomCatTextures = BitmapUtil.cat3Textures;
                break;
            case 3:
                randomCatTextures = BitmapUtil.cat4Textures;
                break;
            case 4:
                randomCatTextures = BitmapUtil.cat5Textures;
                break;
            default:
                break;
        }
        
        return randomCatTextures;
    }
    
    public MovementAction createCatUpDownAction(Sprite cat, float time){
        MovementAction up = MAction3.moveByY(-50, (long) (upSpeed*1000));
        MovementAction duration = MAction.waitAction((long) (time*1000));
        MovementAction down = MAction.moveByY(50, (long) (downSpeed*1000));
        return MAction2.sequence(new MovementAction[]{up, duration, down});
    }
    
    private void gameOverInBreakGameMode(){
        reportBreakGameModeScore(scoreInBreakGameMode);
        [self.gameDelegate showLoseView];
    }
}
