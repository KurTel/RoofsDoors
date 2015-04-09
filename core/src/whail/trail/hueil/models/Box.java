package whail.trail.hueil.models;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
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
public class Box {

    private Entity entity;
    static int count = 0 ;

    public Box (World world,float x,float y,float angle){

        count ++;

        entity = new Entity();
        TextureRegion region;

        //задаем компонент позиции
        PositionComponent position = new PositionComponent(new Vector2(x,y));

        //задаем компонент движения
        MovementComponent movement = new MovementComponent(100,1f,new Vector2(0,0),new Vector2(0,0));

        //задаем компонент графики, рендера
        region = GameAssetManager.getInstance().get("textures/textures.atlas",TextureAtlas.class).findRegion("box");
        RenderComponent render = new RenderComponent(region,new Vector2());

        //задаем размер по X, потом пересчитывается по Y, можно задавать не здесь, а напрямую
        float SIZE_X = 1f;
        float SIZE_Y = render.frame.getRegionHeight()/render.frame.getRegionWidth()*SIZE_X;
        SizeComponent size = new SizeComponent( SIZE_X, SIZE_Y);

        //create Body + Fixtures
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        Body box = world.createBody(def);
        box.setTransform(
                position.position.x,
                position.position.y,
                angle
        );
        box.setFixedRotation(false);
        box.setBullet(false);
        box.setUserData("box");

        Fixture fixture;
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(size.sizeX/2, size.sizeY/2,new Vector2(size.sizeX/2,size.sizeY/2),0);
        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = poly;
        boxFixtureDef.friction = 0.3f;
        boxFixtureDef.density = 2f;
        boxFixtureDef.restitution = 0.1f;
        box.createFixture(boxFixtureDef);
        poly.dispose();

        BodyComponent body = new BodyComponent(box);
        //end create body

        entity.add(position);
        entity.add(size);
        entity.add(movement);
        entity.add(render);
        entity.add(body);

        Gdx.app.log("Box","Boxes: " + count);
    }

    protected void finalize ( ) {
        count--;
    }

    public Entity getEntity(){
        return entity;
    }
}
