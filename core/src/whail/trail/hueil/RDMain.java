package whail.trail.hueil;

import whail.trail.hueil.screens.CustomScreen;
import whail.trail.hueil.screens.ScreenManager;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class RDMain extends Game {

	
	@Override
	public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Gdx.app.debug("screens.log", "StartApp");
        ScreenManager.getInstance().init(this);
        ScreenManager.getInstance().show(CustomScreen.LOAD_SCREEN);
	}

	@Override
	public void render () {
        super.render();
	}
}
