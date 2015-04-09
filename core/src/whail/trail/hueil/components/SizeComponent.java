package whail.trail.hueil.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by User on 08.04.2015.
 */
public class SizeComponent extends Component implements Json.Serializable {

    public float sizeX = 0;
    public float sizeY = 0;

    public SizeComponent(float x,float y){
        this.sizeX = x;
        this.sizeY = y;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
