package whail.trail.hueil.models;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import whail.trail.hueil.GameAssetManager;
import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.InputControlComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;
import whail.trail.hueil.components.RenderComponent;
import whail.trail.hueil.components.SizeComponent;

/**
 * Created by User on 08.04.2015.
 */
public class Ground {
    private Entity entity;

    public Ground(World world,float x,float y){

        entity = new Entity();

        PositionComponent position = new PositionComponent(new Vector2(0,0));

        //create Body + Fixtures
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.StaticBody;
        Body box = world.createBody(def);
        box.setTransform(
                position.position.x,
                position.position.y,
                0
        );
        box.setFixedRotation(true);
        box.setUserData("ground");

        PolygonShape poly = new PolygonShape();
        poly.setAsBox(10f, 0.01f,new Vector2(10f,0.05f),0);
        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.friction = 1f;
        boxFixtureDef.shape = poly;
        boxFixtureDef.density = 0.5f;
        //boxFixtureDef.restitution = 0.f;
        box.createFixture(boxFixtureDef);
        poly.dispose();

        BodyComponent body = new BodyComponent(box);
        //end create body

        entity.add(position);;
        entity.add(body);
    }

    public Entity getEntity(){
        return entity;
    }
}
