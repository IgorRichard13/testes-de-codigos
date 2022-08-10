package com.grupo7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class FishingDerbyClone extends ApplicationAdapter {
	private Texture backgroundTexture;
	private Sprite backgroundSprite;

	private Texture fish2lbTexture;
	private Texture fish4lbTexture;
	private Texture fish6lbTexture;
	private Texture sharkTexture;
	private Texture hookTexture;
	private Sprite hookSprite;
	private Sprite hookFlipSprite;
	private Texture boatTexture;
	private Sprite boatSprite;
	private Sprite boatFlipledSprite;
	private Texture fishermanTexture;
	private Texture fishingRodTexture;
	private Sprite fishingRodSprite;
	private Sprite fishingRodFlippedSprite;
	private Texture fishermanHookTexture;

	GameObjectController fishAnimator;
	GameObjectController sharkAnimator;
	GameObjectController fishermanAnimator;
	GameObjectController fishermanHookAnimation;
	ArrayList<GameObjectController> animations;

	private Sound hit;
	private Sound shark;
	private Sound point;
	private Sound pick;
	private Music music;
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;
	private int score_p1, score_p2;
	private FishingHook fishingHook;
	private FishingRod rod;
	private long lastSharkTime, lastRunTime, AIThreadLimit;
	private boolean win = false;
	private int winnerPlayer;
	private int aiTarget;

	ArrayList<FishingHook> hooks;
	ArrayList<FishingRod> rods;

	public boolean PlayervsAI;

	public FishingDerbyClone(Object escolha){
		if(escolha.equals("1 Jogador - Plaver VS IA")){
			PlayervsAI = true;
		}else if(escolha.equals("2 Jogadores - Plaver VS Player")){
			PlayervsAI = false;
		} else{
			System.exit(0);
		}
	}

	private void loadTextures() {
		backgroundTexture = new Texture("assets/backgroundTexture.png");
		backgroundSprite = new Sprite(backgroundTexture);
		fishermanTexture = new Texture("assets/Fisherman_fish.png");
		fishermanHookTexture = new Texture("assets/Fisherman_hook.png");
		fish2lbTexture = new Texture("assets/fish2lb.png");
		fish4lbTexture = new Texture("assets/fish4lb.png");
		fish6lbTexture = new Texture("assets/fish6lb.png");
		sharkTexture = new Texture("assets/shark.png");
		boatTexture = new Texture("assets/boat.png");
		hookTexture = new Texture("assets/hook.png");
		hookSprite = new Sprite(hookTexture);
		boatSprite = new Sprite(boatTexture);
		boatFlipledSprite = new Sprite(boatTexture);
		boatFlipledSprite.flip(true, false);
		hookFlipSprite = new Sprite(hookSprite);
		hookFlipSprite.flip(true, false);
		fishingRodTexture = new Texture("assets/varapesca.png");
		fishingRodSprite = new Sprite(fishingRodTexture);
		fishingRodFlippedSprite = new Sprite(fishingRodTexture);
		fishingRodFlippedSprite.flip(true, false);
	}

	private void loadSounds() {
		hit = Gdx.audio.newSound(Gdx.files.internal("assets/hit_sound.mp3"));
		shark = Gdx.audio.newSound(Gdx.files.internal("assets/sharkBite.mp3"));
		point = Gdx.audio.newSound(Gdx.files.internal("assets/pickupFish.mp3"));
		pick = Gdx.audio.newSound(Gdx.files.internal("assets/blipSelect.mp3"));
	}

	private void loadMusic() {
		music = Gdx.audio.newMusic(Gdx.files.internal("assets/lonely.mp3"));
	}

	private void renderBackground() {
		backgroundSprite.draw(batch);
	}

	private void startMusic() {
		music.play();
		music.setLooping(true);
		music.setVolume(0.2f);
	}

	private void startCamera() {
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		this.batch = new SpriteBatch();
	}

	private void startFont() {
		font = new BitmapFont();
		font.setColor(Color.RED);
	}

	private GameObjectController createFish(int h) {
		Random r = new Random();
		int x = r.nextInt(800);
		switch (h) {
			case 1:
				fishAnimator = new Fish(fish2lbTexture, 2, 1, hit,
						new Rectangle(x, 320, fish2lbTexture.getWidth() * 2.0f, fish2lbTexture.getHeight() * 2.0f * 2),
						100f, 2, 320, h);
				break;
			case 2:
				fishAnimator = new Fish(fish2lbTexture, 2, 1, hit,
						new Rectangle(x, 260, fish2lbTexture.getWidth() * 2.0f, fish2lbTexture.getHeight() * 2.0f * 2),
						105f, 2, 260, h);
				break;
			case 3:
				fishAnimator = new Fish(fish4lbTexture, 2, 1, hit,
						new Rectangle(x, 200, fish4lbTexture.getWidth() * 1.7f, fish4lbTexture.getHeight() * 1.7f * 2),
						75f, 4, 200, h);
				break;
			case 4:
				fishAnimator = new Fish(fish4lbTexture, 2, 1, hit,
						new Rectangle(x, 140, fish4lbTexture.getWidth() * 1.7f, fish4lbTexture.getHeight() * 1.7f * 2),
						70f, 4, 140, h);
				break;
			case 5:
				fishAnimator = new Fish(fish6lbTexture, 2, 1, hit,
						new Rectangle(x, 80, fish6lbTexture.getWidth() * 1.5f, fish6lbTexture.getHeight() * 1.5f * 2),
						50f, 6, 80, h);
				break;
			case 6:
				fishAnimator = new Fish(fish6lbTexture, 2, 1, hit,
						new Rectangle(x, 20, fish6lbTexture.getWidth() * 1.5f, fish6lbTexture.getHeight() * 1.5f * 2),
						55f, 6, 20, h);
				break;
		}
		return fishAnimator;
	}

	private GameObjectController createShark() {
		Random r = new Random();
		int x = r.nextInt(800);
		sharkAnimator = new Shark(sharkTexture, 2, 1, hit,
				new Rectangle(x, 350, sharkTexture.getWidth() * 1.8f, sharkTexture.getHeight() * 3.6f), 120f);
		return sharkAnimator;
	}

	private FishingHook createHook(int x, int y, int player) {
		Rectangle hitbox = new Rectangle();
		hitbox.x = x;
		hitbox.y = y;
		hitbox.width = hookTexture.getWidth() * 3f;
		hitbox.height = hookTexture.getHeight() * 3f;
		fishingHook = new FishingHook(hitbox, player);
		return fishingHook;

	}

	private void loadDefaultGameObjects() {
		animations = new ArrayList<GameObjectController>();
		hooks = new ArrayList<FishingHook>();
		rods = new ArrayList<FishingRod>();
		for(int i = 1; i <= 6; i++) {
			animations.add(createFish(i));
		}
		animations.add(createShark());

		fishermanAnimator = new Player(fishermanTexture, 4, 1, hit, new Rectangle(135, 450, fishermanTexture.getWidth() * 0.5f, fishermanTexture.getHeight() * 2.5f));
		animations.add(fishermanAnimator);
		fishermanAnimator = new Player(fishermanTexture, 4, 1, hit, new Rectangle(650, 450, fishermanTexture.getWidth() * 0.5f * -1, fishermanTexture.getHeight() * 2.5f));
		animations.add(fishermanAnimator);
		fishermanHookAnimation = new Player(fishermanHookTexture, 6, 1, shark, new Rectangle(135, 450, fishermanTexture.getWidth() * 0.5f, fishermanTexture.getHeight() * 2.5f));
		animations.add(fishermanAnimator);

		rod = new FishingRod(fishingRodSprite, new Rectangle(187, 495, fishingRodTexture.getWidth()*2 , fishingRodTexture.getHeight()*2), 0);
		rods.add(rod);
		rod = new FishingRod(fishingRodSprite, new Rectangle(565, 495, fishingRodTexture.getWidth()*2 , fishingRodTexture.getHeight()*2), 1);
		rods.add(rod);

		score_p1 = 0;
		score_p2 = 0;

		hooks.add(createHook(220, 430, 0));
		hooks.add(createHook(550, 430, 1));

		lastSharkTime = TimeUtils.millis();
		lastRunTime = TimeUtils.millis();
		AIThreadLimit = TimeUtils.millis();

		aiTarget = 0;
	}

	private void renderBoats() {
		batch.draw(boatSprite, 575, 450, boatTexture.getWidth() * 2.5f, boatTexture.getHeight() * 2.5f);
		batch.draw(boatFlipledSprite, 25, 450, boatTexture.getWidth() * 2.5f, boatTexture.getHeight() * 2.5f);
	}

	private void renderPoints() {
		font.draw(batch, score_p1 + " lbs", 30, 580);
		font.draw(batch, score_p2 + " lbs", 700, 580);
		font.draw(batch, Gdx.graphics.getFramesPerSecond() + " FPS", 400, 580);
	}

	private void moveAnimations() {
		for (GameObjectController animator : animations) {
			animator.renderAnimation(batch);
			animator.move();
		}
	}

	private void renderHooks() {
		for (FishingHook hook : hooks) {
			if(hook.getPlayer() == 0) {
				batch.draw(hookFlipSprite, hook.getHitbox().x, hook.getHitbox().y, hook.getHitbox().width, hook.getHitbox().height);
			} else {
				batch.draw(hookSprite, hook.getHitbox().x, hook.getHitbox().y, hook.getHitbox().width, hook.getHitbox().height);
			}
			
		}
	}

	private void renderRods(){
		for (FishingRod rod : rods) {
			if(rod.getPlayer() == 0){
				batch.draw(fishingRodSprite, rod.getHitbox().x, rod.getHitbox().y, rod.getHitbox().width, rod.getHitbox().height);
			}else if (rod.getPlayer() == 1){
				batch.draw(fishingRodFlippedSprite, rod.getHitbox().x, rod.getHitbox().y, rod.getHitbox().width, rod.getHitbox().height);
			}	
		}
	}

	private void resetFish(GameObjectController animator, int random, FishingHook hook) {
		if(animator instanceof Fish) {
			((Fish) animator).setHitbox(new Rectangle(random, ((Fish) animator).getHeight(), ((Fish) animator).hitbox.width, ((Fish) animator).hitbox.height));
			((Fish) animator).setIsCatchedBy(2);
			((Fish) animator).setIsCatched(false);
			hook.setIsCatching(false);
		}
	}
	
	private void hookHandler(){
		Iterator<FishingHook> it = hooks.iterator();
		while (it.hasNext()) {
			FishingHook hook = it.next();
			for (GameObjectController animator : animations) {
				if (!hook.getIsCatching()) {
					Rectangle r = new Rectangle(animator.getHitbox().x + animator.getHitbox().width, animator.getHitbox().y, animator.getHitbox().width * -1 , animator.getHitbox().height);
					if (hook.getHitbox().overlaps(animator.getHitbox()) || hook.getHitbox().overlaps(r)) {
						if (animator instanceof Fish) {
							if(!((Fish)animator).getIsCatched()){
								((Fish) animator).setIsCatched(true);
								((Fish) animator).setIsCatchedBy(hook.getPlayer());
								hook.setIsCatching(true);
								pick.play();
							}
						}
					}
				}

				if (animator instanceof Fish) {
					if (((Fish) animator).getIsCatched()) {
						Random r = new Random();
						int random = r.nextInt(800);

						if (((Fish) animator).getIsCatchedBy() == hook.getPlayer()) {
							((Fish) animator)
									.setHitbox(new Rectangle(hook.getHitbox().x - (((Fish) animator).hitbox.width) / 2,
											hook.getHitbox().y - ((Fish) animator).hitbox.height,
											((Fish) animator).hitbox.width,
											((Fish) animator).hitbox.height));

							if (((Fish) animator).hitbox.y + ((Fish) animator).hitbox.height >= 401) {
									if (((Fish) animator).getIsCatchedBy() == 0) {
										score_p1 += ((Fish) animator).getLb();
									} else if (((Fish) animator).getIsCatchedBy() == 1) {
										score_p2 += ((Fish) animator).getLb();
									}
									resetFish(((Fish) animator), random, hook);
									point.play();
							}
						}

						if (((Fish) animator).getHitbox().overlaps(
							new Rectangle(sharkAnimator.getHitbox().x, sharkAnimator.getHitbox().y, sharkAnimator.getHitbox().width, sharkAnimator.getHitbox().height)) ||
							((Fish) animator).getHitbox().overlaps(
								new Rectangle(sharkAnimator.getHitbox().x + sharkAnimator.getHitbox().width,
								sharkAnimator.getHitbox().y,
								sharkAnimator.getHitbox().width * -1,
								sharkAnimator.getHitbox().height))
							) {
								resetFish(((Fish) animator), random, hook);
								shark.play();
						}
					}
				}
			}
		}
	}

	private void sharkHandler() {
		if (TimeUtils.millis() - lastSharkTime > 2000) {
			Random r = new Random();
			for (GameObjectController shark : animations) {
				if (shark instanceof Shark) {
					if (r.nextBoolean()) {
						((Shark) shark).startAttack();
					} else {
						if (((Shark) shark).getIsAttacking()) {
							((Shark) shark).stopAttack();
						}
					}
				}
			}
			lastSharkTime = TimeUtils.millis();
		}
	}

	private void fishHandler() {
		if(TimeUtils.millis() - lastRunTime > 2000){
			Random r = new Random();
			for(GameObjectController fish : animations){
				if(fish instanceof Fish){
					if(r.nextBoolean()){
						if(!((Fish)fish).isCatched){
							Rectangle fishPov = new Rectangle(((Fish)fish).getHitbox().x-75, ((Fish)fish).getHitbox().y-75, ((Fish)fish).getHitbox().width+150, ((Fish)fish).getHitbox().height+150);
							for(FishingHook hook : hooks){
								if(fishPov.overlaps(hook.getHitbox())){
									if(r.nextInt(100) <= 68.897f){
										((Fish)fish).runAway();
										lastRunTime = TimeUtils.millis();
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public void renderLine(float xInicial, float yInicial, float xFinal, float yFinal){
		ShapeRenderer sp = new ShapeRenderer();
		sp.setProjectionMatrix(camera.combined);
		sp.setColor(Color.BLACK);
		sp.begin(ShapeType.Line);
		sp.line(xInicial, yInicial, xFinal, yFinal);
		sp.end();
	}

	private void drawFishingLines(){
		for(FishingHook hook : hooks){
			for(FishingRod rod : rods){
				if(hook.getPlayer() == 0 && rod.getPlayer() == 0){
					renderLine(rod.getHitbox().x + rod.getHitbox().width, rod.getHitbox().y + rod.getHitbox().height, hook.getHitbox().x, hook.getHitbox().y + hook.getHitbox().height);
				}else if (hook.getPlayer() == 1 && rod.getPlayer() == 1){
					renderLine(rod.getHitbox().x, rod.getHitbox().y + rod.getHitbox().height, hook.getHitbox().x, hook.getHitbox().y + hook.getHitbox().height);
				}
			}
				
		}
	}

	private void ifWin(){
		if(score_p1 >= 99){
			win = true;
			winnerPlayer = 0;
		}else if(score_p2 >= 99){
			win = true;
			winnerPlayer = 1;
		}
	}

	private void aiHandler(){
		for (FishingHook hook : hooks) {
			if(hook.getPlayer() == 0){
				hook.handleEventAI(camera, -1);
			}else if (hook.getPlayer() == 1){
				if(!hook.getIsCatching()){
					if(aiTarget == 0){
						ArrayList<GameObjectController> temp = new ArrayList<GameObjectController>();
						for(GameObjectController fish : animations){
							if(fish instanceof Fish){
								if(!((Fish)fish).isCatched){
								// 	temp.add(fish);
								// }
									if(	((Fish)fish).getHitbox().x + ((Fish)fish).getHitbox().width > 400 ||
										((Fish)fish).getHitbox().x + ((Fish)fish).getHitbox().width*-1 > 400){
											temp.add(fish);
									}
								}
							}
						}
						Random r = new Random();
						aiTarget = r.nextInt(temp.size())+1;
						System.out.println(aiTarget);
					}else{
						for(GameObjectController fish : animations){
							if(fish instanceof Fish){
								if(aiTarget == ((Fish)fish).getId()){
									if (TimeUtils.millis() - AIThreadLimit < 5000){
										if(!((Fish)fish).isCatched){ // 0 esq; 1 dir; 2 cima; 3 baixo
											if (hook.getHitbox().y > ((Fish)fish).getHitbox().y){
															hook.handleEventAI(camera, 3);
											}
											if (hook.getHitbox().y < ((Fish)fish).getHitbox().y){
												hook.handleEventAI(camera, 2);
											}
											if(hook.getHitbox().x > ((Fish)fish).getHitbox().x){
															rod.handleEventAI(camera, 0);
															hook.handleEventAI(camera, 0);
											}
											if (hook.getHitbox().x < ((Fish)fish).getHitbox().x){
															rod.handleEventAI(camera, 1);
															hook.handleEventAI(camera, 1);
											}
										}else{
											this.aiTarget = 0;
											AIThreadLimit = TimeUtils.millis();
										}
									}else{
										this.aiTarget = 0;
										AIThreadLimit = TimeUtils.millis();
									}
								}
							}
						}		
					}
				}
				if(hook.getIsCatching()){
					hook.getHitbox().y += 55.98f * Gdx.graphics.getDeltaTime();
					hook.getHitbox().y += 55.98f * Gdx.graphics.getDeltaTime(); //Corrigir funções de isCatching
					if (hook.getHitbox().y > 425 - hook.getHitbox().height) {
						hook.setIsCatching(false);
						this.aiTarget = 0;
					}
				}
			}
		}
	}
	
	private void handleEvents(){
		if(PlayervsAI){
			aiHandler();
		}else if(!PlayervsAI){
			for (FishingHook hook : hooks) {
				hook.handleEvent(camera);
			}	
			for(FishingRod rod : rods){
				rod.handleEvent(camera);
			}
		}
	}

	@Override
	public void create() {
		loadTextures();
		loadSounds();
		loadMusic();
		loadDefaultGameObjects();
		startCamera();
		startMusic();
		startFont();
	}

	@Override
	public void render() {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			if(!win){
				ifWin();
				renderBackground();
				moveAnimations();
				renderBoats();
				renderPoints();
				renderHooks();
				renderRods();
				hookHandler();
				sharkHandler();
				fishHandler();
				handleEvents();
			}else{
      			JOptionPane.showMessageDialog(null,  "Player " + (winnerPlayer + 1) + " wins!", "We have a winner!",
				JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		batch.end();
		drawFishingLines();
	}

	@Override
	public void dispose() {
		backgroundTexture.dispose();
		fishermanTexture.dispose();
		fish2lbTexture.dispose();
		fish4lbTexture.dispose();
		fish6lbTexture.dispose();
		sharkTexture.dispose();
		boatTexture.dispose();
		hookTexture.dispose();
		batch.dispose();
	}
}

abstract class Movement {
	public abstract void move();
	public abstract void handleEvent(OrthographicCamera camera);
}

class GameObjectController extends Movement {
	private Animation<TextureRegion> animation;
	private float stateTime;
	protected Rectangle hitbox;
	protected Sound sound;

	public GameObjectController(Texture sheetTexture, int columns, int rows, Sound sound, Rectangle hitbox) {
		TextureRegion[][] tmp = TextureRegion.split(sheetTexture,
				sheetTexture.getWidth() / columns,
				sheetTexture.getHeight() / rows);

		TextureRegion[] frames = new TextureRegion[columns * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				frames[index++] = tmp[i][j];
			}
		}

		animation = new Animation<TextureRegion>(0.1f, frames);
		stateTime = 0f;
		this.sound = sound;
		this.hitbox = hitbox;
	}

	public void renderAnimation(SpriteBatch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
		batch.draw(currentFrame, this.hitbox.x, this.hitbox.y, this.hitbox.width, this.hitbox.height);
	}

	@Override
	public void move() {

	}

	@Override
	public void handleEvent(OrthographicCamera camera) {

	}

	public void PlaySound() {
		if (sound != null) {
			sound.play();
		}
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public void setHitbox(Rectangle hitbox) {
		this.hitbox = hitbox;
	}

	public Sound getSound() {
		return sound;
	}

	public void setSound(Sound sound) {
		this.sound = sound;
	}
}

class Fish extends GameObjectController {

	float speed;
	boolean isCatched = false;
	int isCatchedBy = 2, lb;
	int height, id;

	public Fish(Texture sheetTexture, int columns, int rows, Sound sound, Rectangle hitbox, float speed, int lb,
			int height, int id) {
		super(sheetTexture, columns, rows, sound, hitbox);
		Random r = new Random();
		if(r.nextBoolean()){
			this.speed = speed;
		}else{
			this.speed = -speed;
			this.hitbox.width = -this.hitbox.width;
		}
		this.lb = lb;
		this.height = height;
		this.id = id;
	}

	public void move() {
		if (getHitbox().x + getHitbox().width >= Gdx.graphics.getWidth()) {
			this.hitbox.width = -this.hitbox.width;
			this.hitbox.x = Gdx.graphics.getWidth();
			speed = -speed;
		} else if (getHitbox().x + getHitbox().width <= 0) {
			this.hitbox.width = -this.hitbox.width;
			this.hitbox.x = 0;
			speed = -speed;
		}
		if (!isCatched) {
			getHitbox().x += speed * Gdx.graphics.getDeltaTime();
		}
	}

	public void setIsCatched(boolean isCatched) {
		this.isCatched = isCatched;
	}

	public boolean getIsCatched() {
		return isCatched;
	}

	public int getIsCatchedBy() {
		return isCatchedBy;
	}

	public void setIsCatchedBy(int isCatchedBy) {
		this.isCatchedBy = isCatchedBy;
	}

	public int getLb() {
		return lb;
	}

	public int getHeight() {
		return height;
	}

	public int getId(){
		return id;
	}

	public void runAway(){
		this.speed = -speed;
		this.hitbox.width = -this.hitbox.width;
	}
}

class Shark extends GameObjectController {

	private float speed;
	private boolean IsAttacking;

	public Shark(Texture sheetTexture, int columns, int rows, Sound sound, Rectangle hitbox, float speed) {
		super(sheetTexture, columns, rows, sound, hitbox);
		this.speed = speed;
	}

	public boolean getIsAttacking() {
		return IsAttacking;
	}

	public void move() {
		if (getHitbox().x + getHitbox().width >= Gdx.graphics.getWidth()) {
			this.hitbox.width = -this.hitbox.width;
			this.hitbox.x = Gdx.graphics.getWidth();
			speed = -speed;
		} else if (getHitbox().x + getHitbox().width <= 0) {
			this.hitbox.width = -this.hitbox.width;
			this.hitbox.x = 0;
			speed = -speed;
		}
		getHitbox().x += speed * Gdx.graphics.getDeltaTime();
	}

	public void startAttack() {
		IsAttacking = true;
		if(speed>=0)
			speed = 245.98f;
		if(speed<0)
			speed = -245.98f;
	}

	public void stopAttack() {
		IsAttacking = false;
		if(speed>=0)
			speed = 120f;
		if(speed<0)
			speed = -120f;
	}
}

class FishingRod extends Movement{
	private Sprite sprite;
	private Rectangle hitbox, backupHitbox;
	private int player;
	public FishingRod(Sprite sprite, Rectangle hitbox, int player) {
		this.sprite = sprite;
		this.hitbox = hitbox;
		this.player = player;
		this.backupHitbox = new Rectangle(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}

	public Rectangle getHitbox() {
		return this.hitbox;
	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
		if(this.player == 0){
			if(Gdx.input.isKeyPressed(Input.Keys.A)){
				if(this.hitbox.width > this.backupHitbox.width){
					this.hitbox.width -= 1.5f;
				}
			}
			if(Gdx.input.isKeyPressed(Input.Keys.D)){
				if(this.hitbox.width + this.hitbox.x < 800 / 2 - 10){
					this.hitbox.width += 1.5f;
				}
			}
		} else if (this.player == 1){
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				if(this.hitbox.width + (800 - this.hitbox.x - this.hitbox.width) < 800/2 - 10){
					this.hitbox.x -= 1.5f;
					this.hitbox.width += 1.5f;
				}
			}
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				if(this.hitbox.width > this.backupHitbox.width){
					this.hitbox.x += 1.5f;
					this.hitbox.width -= 1.5f;
				}
			}
		}
	}

	public void handleEventAI(OrthographicCamera camera, int movement) { // movement => 0 = left; 1 = right
		if(this.player == 1){
			if(movement == 0){
				if(this.hitbox.width + (800 - this.hitbox.x - this.hitbox.width) < 800/2 - 10){
					this.hitbox.x -= 1.5f;
					this.hitbox.width += 1.5f;
				}
			}
			if(movement == 1){
				if(this.hitbox.width > this.backupHitbox.width){
					this.hitbox.x += 1.5f;
					this.hitbox.width -= 1.5f;
				}
			}
		}
	}

	@Override
	public void move() {

	}

	public Sprite getSprite(){
		return this.sprite;
	}

	public int getPlayer(){
		return this.player;
	}
}

class FishingHook extends Movement {
	private Rectangle hitbox;
	private int player;
	private boolean isCatching = false;

	public FishingHook(Rectangle hitbox, int player) {
		this.hitbox = hitbox;
		this.player = player;
	}

	public void setIsCatching(boolean isCatching) {
		this.isCatching = isCatching;
	}

	public boolean getIsCatching() {
		return isCatching;
	}

	@Override
	public void move() {

	}

	@Override
	public void handleEvent(OrthographicCamera camera) {
		int keyLeft, keyRight, keyUp, keyDown;
		float speed = 55.98f;

		if (this.player == 0) {
			keyRight = Input.Keys.D;
			keyUp = Input.Keys.W;
			keyDown = Input.Keys.S;
			keyLeft = Input.Keys.A;

			if(hitbox.x < 0) {
				hitbox.x = 0;
			}
			if (hitbox.x > 800 / 2 - hitbox.width) {
				hitbox.x = 800 / 2 - hitbox.width;
			}
		} else {
			keyLeft = Input.Keys.LEFT;
			keyRight = Input.Keys.RIGHT;
			keyUp = Input.Keys.UP;
			keyDown = Input.Keys.DOWN;

			if (hitbox.x < 800 / 2 + hitbox.width) {
				hitbox.x = 800 / 2 + hitbox.width;
			}
			if (hitbox.x > 800 - hitbox.width) {
				hitbox.x = 800 - hitbox.width;
			}
		}
		if (!isCatching) {
			if (Gdx.input.isKeyPressed(keyLeft)) {
				this.hitbox.x -= 1.5f;
			} else if (Gdx.input.isKeyPressed(keyRight)) {
				this.hitbox.x += 1.5f;
			} else if (Gdx.input.isKeyPressed(keyUp)) {
				this.hitbox.y += 1.5f;
			} else if (Gdx.input.isKeyPressed(keyDown)) {
				this.hitbox.y -= 1.5f;
			}
		} else {
			getHitbox().y += speed * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(keyUp)) {
				getHitbox().y += speed * Gdx.graphics.getDeltaTime();
			}
			if (getHitbox().y > 425 - hitbox.height) {
				this.isCatching = false;
			}
		}
		if (hitbox.y < 0) {
			hitbox.y = 0;
		}
		if (hitbox.y > 425 - hitbox.height) {
			hitbox.y = 425 - hitbox.height;
		}
	}

	public void handleEventAI(OrthographicCamera camera, int movementDirection){
		int keyLeft, keyRight, keyUp, keyDown;
		float speed = 55.98f;

		keyRight = Input.Keys.D;
		keyUp = Input.Keys.W;
		keyDown = Input.Keys.S;
		keyLeft = Input.Keys.A;

		if(this.player == 0){
			if(hitbox.x < 0) {
			hitbox.x = 0;
			}
			if (hitbox.x > 800 / 2 - hitbox.width) {
				hitbox.x = 800 / 2 - hitbox.width;
			}
		}else{
			if (hitbox.x < 800 / 2 + hitbox.width) {
				hitbox.x = 800 / 2 + hitbox.width;
			}
			if (hitbox.x > 800 - hitbox.width) {
				hitbox.x = 800 - hitbox.width;
			}
		}

		if (!this.isCatching) {
			if(this.player == 0){
				if (Gdx.input.isKeyPressed(keyLeft)) {
					this.hitbox.x -= 1.5f;
				} else if (Gdx.input.isKeyPressed(keyRight)) {
					this.hitbox.x += 1.5f;
				} else if (Gdx.input.isKeyPressed(keyUp)) {
					this.hitbox.y += 1.5f;
				} else if (Gdx.input.isKeyPressed(keyDown)) {
					this.hitbox.y -= 1.5f;
				}
			}
			if(this.player == 1){
				if(movementDirection == 0){ 
					this.hitbox.x -= 1.5f;
				} else if(movementDirection == 1){
					this.hitbox.x += 1.5f;
				} else if(movementDirection == 2){
					this.hitbox.y += 1.5f;
				} else if(movementDirection == 3){
					this.hitbox.y -= 1.5f;
				}
			}
		} else {
			this.hitbox.y += speed * Gdx.graphics.getDeltaTime();
			if(this.player == 0){
				if (Gdx.input.isKeyPressed(keyUp)) {
					getHitbox().y += speed * Gdx.graphics.getDeltaTime();
				}
			}

			if (getHitbox().y > 425 - hitbox.height) {
				this.isCatching = false;
			}
		}
		if (hitbox.y < 0) {
			hitbox.y = 0;
		}
		if (hitbox.y > 425 - hitbox.height) {
			hitbox.y = 425 - hitbox.height;
		}
		
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public int getPlayer() {
		return player;
	}
}

class Player extends GameObjectController {

	GameObjectController fishingRod;
	
	public Player(Texture sheetTexture, int columns, int rows, Sound sound, Rectangle hitbox) {
		super(sheetTexture, columns, rows, sound, hitbox);
	}
	
	@Override
	public void move() {

	}
}
