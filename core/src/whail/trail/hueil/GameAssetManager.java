package whail.trail.hueil;

import com.badlogic.gdx.assets.AssetManager;

/**
 * Created by User on 06.04.2015.
 */
public class GameAssetManager extends AssetManager {

    private static GameAssetManager instance;

    public static GameAssetManager getInstance(){
        if(null == instance){
            instance = new GameAssetManager();
        }
        return instance;
    }

    public void init(){}

    @Override
    public void dispose(){
        instance.dispose();
    }
}
