package whail.trail.hueil.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by User on 06.04.2015.
 */
public class InputControlComponent extends Component implements Json.Serializable {

    private boolean controllable;

    public boolean isControllable(){
        return controllable;
    }

    public InputControlComponent(boolean control){
        this.controllable = control;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "controllable", jsonData);
    }
}
