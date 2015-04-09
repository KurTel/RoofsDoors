package whail.trail.hueil.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by User on 06.04.2015.
 */
public class ScreenManager {
    private static ScreenManager instance;

    private Game game;

    private IntMap<Screen> screens;

    private ScreenManager() {
        screens = new IntMap<Screen>();
    }

    public static ScreenManager getInstance() {
        if (null == instance) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void init(Game game) {
        this.game = game;
    }

    public void show(CustomScreen screen) {
        if (null == game) return;
        if (!screens.containsKey(screen.ordinal())) {
            screens.put(screen.ordinal(), screen.getScreenInstance());
        }
        game.setScreen(screens.get(screen.ordinal()));
    }

    public void dispose(CustomScreen screen) {
        if (!screens.containsKey(screen.ordinal())) return;
        screens.remove(screen.ordinal()).dispose();
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
        instance = null;
    }
}