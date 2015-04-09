package whail.trail.hueil.screens;

import com.badlogic.gdx.Screen;

/**
 * Created by User on 06.04.2015.
 */
public enum CustomScreen {

    LOAD_SCREEN {
        @Override
        protected Screen getScreenInstance() {
            return new LoadScreen();
        }
    },

    MAIN_MENU {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new MenuScreen();
        }
    },

    GAME {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new GameScreen();
        }
    };

    /*BATTLE {
        @Override
        protected com.badlogic.gdx.Screen getScreenInstance() {
            return new BattleScreen();
        }
    };*/

    protected abstract Screen getScreenInstance();

}
