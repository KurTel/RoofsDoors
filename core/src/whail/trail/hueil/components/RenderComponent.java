package whail.trail.hueil.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import whail.trail.hueil.GameAssetManager;

/**
 * Created by User on 06.04.2015.
 */
public class RenderComponent extends Component implements Json.Serializable {

    public TextureRegion frame = GameAssetManager.getInstance().get("textures/textures.atlas",TextureAtlas.class).findRegion(null);
    public Vector2 frameOffset = new Vector2();

    public RenderComponent(){}

    public RenderComponent(Vector2 offset){
        this.frameOffset = offset;
    }

    public RenderComponent(TextureRegion texture, Vector2 offset){
        this.frame = texture;
        this.frameOffset = offset;
    }

    public Vector2 getSize(){
        return new Vector2(frame.getRegionWidth(),frame.getRegionHeight());
    }

    @Override
    public void write(Json json) {

    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        json.readField(this, "frameOffset", jsonData);
    }
}
