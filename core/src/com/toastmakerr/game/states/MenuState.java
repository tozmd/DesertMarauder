package com.toastmakerr.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.DesertMarauderMain;

public class MenuState extends State{
    private Texture background, background2, title;
    private final static float SCROLLING_SPEED = 288;
    private float cameraPosX = DesertMarauderMain.WIDTH / 2;
    private float destinationX;
    private float lerp;

    public MenuState(GameStateManager manager, AssetManager assetManager) {
        super(manager, assetManager);
        background = assetManager.get(Assets.DESERT_BG);
        background2 = assetManager.get(Assets.DESERT_BG);
        title = assetManager.get(Assets.TITLE);
        camera.setToOrtho(false,  DesertMarauderMain.WIDTH, DesertMarauderMain.HEIGHT);
        destinationX = 3 * DesertMarauderMain.WIDTH / 2;
        lerp = 0.1f;
    }

    @Override
    public void inputHandler() {
        if(Gdx.input.justTouched()){
            manager.set(new PlayState(manager, assetManager));
            dispose();
        }
    }

    @Override
    public void update(float delta) {
        System.out.println(camera.position.x);
        if(cameraPosX >= destinationX){
            cameraPosX = DesertMarauderMain.WIDTH / 2;
        }
        cameraPosX += SCROLLING_SPEED * lerp * delta;
        camera.position.x = cameraPosX;
        inputHandler();
    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();;
        batch.draw(background,0,0,DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.draw(background2,DesertMarauderMain.WIDTH,0,DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.draw(title,cameraPosX-DesertMarauderMain.WIDTH / 2,0);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        background2.dispose();
        title.dispose();
    }
}
