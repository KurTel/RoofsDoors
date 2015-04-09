package whail.trail.hueil.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by User on 06.04.2015.
 */
public class BodyComponent extends Component implements Json.Serializable {

    public Body body;
    private boolean isGrounded;

    public BodyComponent(Body body){
        this.body = body;
    }

    public boolean isGrounded(){
        return this.isGrounded;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {

    }
}
