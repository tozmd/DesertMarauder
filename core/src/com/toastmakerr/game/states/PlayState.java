package com.toastmakerr.game.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.toastmakerr.game.Assets;
import com.toastmakerr.game.DesertMarauderMain;

public class PlayState extends State{
    private Texture BG1, BG2, BG3, BG4, BG5;
    private World world;
    private static final float STEP_TIME = 1f/60f;
    private float accumulator;

    public PlayState(GameStateManager manager, AssetManager assetManager) {
        super(manager, assetManager);
        world = new World(new Vector2(0,-9.8f), true);
        accumulator = 0;
        BG1 = assetManager.get(Assets.DESERT_BG_1);
        BG2 = assetManager.get(Assets.DESERT_BG_2);
        BG3 = assetManager.get(Assets.DESERT_BG_3);
        BG4 = assetManager.get(Assets.DESERT_BG_4);
        BG5 = assetManager.get(Assets.DESERT_BG_5);
        camera.setToOrtho(false,  DesertMarauderMain.WIDTH, DesertMarauderMain.HEIGHT);
    }

    @Override
    public void inputHandler() {

    }

    @Override
    public void update(float delta) {
        accumulator += Math.min(delta, 0.25f);
    }

    @Override
    public void render(SpriteBatch batch) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(BG1,0,0, DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.draw(BG2,0,0, DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.draw(BG3,0,0, DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.draw(BG4,0,0, DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.draw(BG5,0,0, DesertMarauderMain.WIDTH,DesertMarauderMain.HEIGHT);
        batch.end();
        stepWorld();
    }

    @Override
    public void dispose() {

    }
    private void stepWorld() {
        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
            world.step(STEP_TIME, 6, 2);
        }
    }
}
