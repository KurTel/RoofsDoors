package whail.trail.hueil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import whail.trail.hueil.GameAssetManager;

/**
 * Created by User on 06.04.2015.
 */
public class LoadScreen implements Screen {

    public void loadAssets(){
        Gdx.app.debug("screens.log", "LoadScreen LoadAssets");
        GameAssetManager.getInstance().load("textures/textures.atlas",TextureAtlas.class);
    }

    public LoadScreen(){}

    @Override
    public void show() {
        GameAssetManager.getInstance().init();
        loadAssets();
    }

    @Override
    public void render(float delta) {
        if(GameAssetManager.getInstance().update()) {
            ScreenManager.getInstance().show(CustomScreen.GAME);
        }else {Gdx.app.debug("screens.log", "LoadScreen, can't load GameScreen");}
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
