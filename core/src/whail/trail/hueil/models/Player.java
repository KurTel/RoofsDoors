package whail.trail.hueil.models;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import whail.trail.hueil.GameAssetManager;
import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.InputControlComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;
import whail.trail.hueil.components.RenderComponent;
import whail.trail.hueil.components.SizeComponent;
import whail.trail.hueil.screens.GameScreen;

/**
 * Created by User on 08.04.2015.
 */
public class Player {

    private Entity entity;

    public Player(World world,float x,float y){

        entity = new Entity();
        TextureRegion region;

        //задаем компонент позиции
        PositionComponent position = new PositionComponent(new Vector2(x,y));

        //задаем компонент движения
        MovementComponent movement = new MovementComponent(10f,3f,new Vector2(0,0),new Vector2(0,0));

        //задаем компонент графики, рендера
        region = GameAssetManager.getInstance().get("textures/textures.atlas",TextureAtlas.class).findRegion("gg");
        RenderComponent render = new RenderComponent(region,new Vector2());

        //задаем размер по X, потом пересчитывается по Y, можно задавать не здесь, а напрямую
        float SIZE_X = 0.7f;
        float SIZE_Y = render.frame.getRegionHeight()/render.frame.getRegionWidth()*SIZE_X;
        SizeComponent size = new SizeComponent( SIZE_X, SIZE_Y);

        //задаем компоненту управления
        InputControlComponent control = new InputControlComponent(true);

        //create Body + Fixtures
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        Body box = world.createBody(def);
        box.setTransform(
                position.position.x,
                position.position.y,
                0
        );
        box.setFixedRotation(true);
        box.setBullet(true);
        box.setUserData("player");

        Fixture fixture;
        Vector2[] vertices = {
                new Vector2(0.2f,0f),
                new Vector2(0.5f,0f),
                new Vector2(0.7f, 0.6f),
                new Vector2(0.5f, 1.4f),
                new Vector2(0.2f, 1.4f),
                new Vector2(0f, 0.6f)
        };

        PolygonShape poly = new PolygonShape();
        poly.set(vertices);
        //poly.setAsBox(size.sizeX/2, size.sizeY/2,new Vector2(size.sizeX/2,size.sizeY/2),0);
        FixtureDef boxFixtureDef = new FixtureDef();
        boxFixtureDef.shape = poly;
        boxFixtureDef.friction = 0.2f;
        boxFixtureDef.density = 0.5f;
        //boxFixtureDef.restitution = 0.1f;
        box.createFixture(boxFixtureDef);
        poly.dispose();

        BodyComponent body = new BodyComponent(box);
        //end create body

        entity.add(position);
        entity.add(size);
        entity.add(movement);
        entity.add(render);
        entity.add(control);
        entity.add(body);
    }

    public Entity getEntity(){
        return entity;
    }
}
