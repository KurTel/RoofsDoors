package whail.trail.hueil.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

/**
 * Created by User on 06.04.2015.
 */
public class MovementComponent extends Component implements Json.Serializable {

    public Vector2 velocity = new Vector2();
    public Vector2 acceleration = new Vector2();
    public float accelerationValue;
    public float maxVelocity;

    public MovementComponent(float accelerationValue, float maxVelocity, Vector2 velocity, Vector2 acceleration){
        this.accelerationValue = accelerationValue;
        this.maxVelocity = maxVelocity;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "accelerationValue", jsonData);
        json.readField(this, "maxVelocity", jsonData);
    }
}
