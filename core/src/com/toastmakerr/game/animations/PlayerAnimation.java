package com.toastmakerr.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.AssetsManager;

public class PlayerAnimation {
    private Texture texture;
    private TextureRegion[] spriteFrames;
    private TextureRegion currentFrame;
    private Animation<TextureRegion> animation;
    private static PlayerAction action;
    private AssetsManager assetManager;
    private PlayerAction previousAction;
    private int rows;
    private int cols;
    private static float stateTime;
    private static boolean flip;
    private static float frameDuration;


    public PlayerAnimation(AssetsManager assetsManager){
        this.assetManager = assetsManager;
        flip = false;
        previousAction = null;
        action = PlayerAction.IDLE;
        frameDuration = 0.25f;
        setAnimation();
    }

    public void draw(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime();
        if(stateTime > animation.getAnimationDuration()){
            stateTime -= animation.getAnimationDuration();
        }
        currentFrame = animation.getKeyFrame(stateTime,true);
        batch.draw(currentFrame, 50, 65, 90,65,250, 250, (flip ? -1 : 1) ,1,0);
    }

    public void flipSprite(boolean bool){
        flip = bool;
    }

    public PlayerAction getAction(){
        return action;
    }

    public float getFrameDuration(){
        return frameDuration;
    }

    public void newAnimation(Texture spriteSheet, int row, int col){
        rows = row;
        cols = col;
        texture = spriteSheet;
        TextureRegion[][] temp = new TextureRegion(texture).split(texture.getWidth() / cols, texture.getHeight() / rows);
        spriteFrames = new TextureRegion[rows * cols];
        int index = 0;
        for(int i = 0; i < rows; i++){;
            for(int j = 0; j < cols; j++){
                spriteFrames[index++] = temp[i][j];
            }
        }
        animation = new Animation<>(frameDuration, spriteFrames);
        stateTime = 0f;
    }

    public void setAction(PlayerAction act){
        action = act;
    }

    public void setAnimation(){
        switch(action){
            case IDLE:
                if(previousAction != PlayerAction.IDLE) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_IDLE), 1, 4);
                    previousAction = PlayerAction.IDLE;
                }
                break;
            case WALK:
                if(previousAction != PlayerAction.WALK) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_WALK), 1, 6);
                    previousAction = PlayerAction.WALK;
                }
                break;
            case RUN:
                if(previousAction != PlayerAction.RUN) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_RUN), 1, 6);
                    previousAction = PlayerAction.RUN;
                }
                break;
            case JUMP:
                if(previousAction != PlayerAction.JUMP) {
                    newAnimation(assetManager.am.get(Assets.PLAYER_JUMP), 1, 6);
                    previousAction = PlayerAction.JUMP;
                }
                break;
        }
    }

    public void setFrameDuration(float duration){
        frameDuration = duration;
    }

}