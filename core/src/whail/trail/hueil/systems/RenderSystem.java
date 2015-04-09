package whail.trail.hueil.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;

import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;
import whail.trail.hueil.components.RenderComponent;
import whail.trail.hueil.components.SizeComponent;

/**
 * Created by User on 06.04.2015.
 */
public class RenderSystem extends EntitySystem {

    public ImmutableArray<Entity> entities;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    public float APP_WIDTH = Gdx.graphics.getWidth();
    public float APP_HEIGHT = Gdx.graphics.getHeight();

    public float camera_width = 15f;
    public float camera_height  = camera_width*APP_HEIGHT/APP_WIDTH;

    public float ppuX = APP_WIDTH/camera_width;
    public float ppuY = APP_HEIGHT/camera_height;

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<RenderComponent> rm = ComponentMapper.getFor(RenderComponent.class);
    private ComponentMapper<SizeComponent> sz = ComponentMapper.getFor(SizeComponent.class);
    private ComponentMapper<BodyComponent> bd = ComponentMapper.getFor(BodyComponent.class);

    public RenderSystem(){
        batch = new SpriteBatch();

        camera = new OrthographicCamera(camera_width,camera_height);
        camera.position.set(camera_width/2,camera_height/2,0);
        Gdx.app.log("RenderSystem","APP_WIDTH = " + APP_WIDTH + " APP_HEIGHT = " + APP_HEIGHT);
        Gdx.app.log("RenderSystem","camera_width = " + camera_width + " camera_height = " + camera_height);
        Gdx.app.log("RenderSystem","ppuX = " + ppuX + " ppuY = " + ppuY );
    }

    public OrthographicCamera getCamera (){
        return camera;
    }

    @Override
    public void addedToEngine(Engine engine){
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, MovementComponent.class,SizeComponent.class,
                BodyComponent.class).get());
    }

    @Override
    public void removedFromEngine (Engine engine) {

    }

    @Override
    public void update(float deltaTime){
        PositionComponent position;
        RenderComponent render;
        SizeComponent size;
        BodyComponent body;

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        for(int i=0; i<entities.size();i++ ){
            Entity e = entities.get(i);

            position = pm.get(e);
            render = rm.get(e);
            size = sz.get(e);
            body = bd.get(e);

            float sizeX = size.sizeX;
            float sizeY = size.sizeY;
            float angle = body.body.getAngle()*180/3.1415f;

            batch.draw(
                    render.frame,
                    position.position.x,
                    position.position.y,
                    0,
                    0,
                    sizeX,
                    sizeY,
                    1,
                    1,
                    angle
            );
        }
        batch.end();
    }
}
