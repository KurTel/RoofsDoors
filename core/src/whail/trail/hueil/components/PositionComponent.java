package whail.trail.hueil.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by User on 06.04.2015.
 */
public class PositionComponent extends Component implements Json.Serializable {

    public Vector2 position;
    public boolean isGrounded;

    public PositionComponent(){
        position = new Vector2();
    }

    public PositionComponent(Vector2 vector){
        position = vector;
        this.isGrounded = false;
    }

    boolean isGrounded(){
        return this.isGrounded;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "position", jsonData);
    }
}
