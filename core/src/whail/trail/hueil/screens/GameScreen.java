package whail.trail.hueil.screens;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;


import java.util.Random;

import whail.trail.hueil.GameAssetManager;
import whail.trail.hueil.MyQueryCallback;
import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.InputControlComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;
import whail.trail.hueil.components.RenderComponent;
import whail.trail.hueil.components.SizeComponent;
import whail.trail.hueil.models.Box;
import whail.trail.hueil.models.Ground;
import whail.trail.hueil.models.Player;
import whail.trail.hueil.systems.CollisionSystem;
import whail.trail.hueil.systems.InputSystem;
import whail.trail.hueil.systems.MovementSystem;
import whail.trail.hueil.systems.RenderSystem;

/**
 * Created by User on 06.04.2015.
 */
public class GameScreen implements Screen {

    Box2DDebugRenderer renderer;
    World world;
    MyQueryCallback myQueryCallback;
    Engine engine;

    static int count = 0;

    long time;

    public TextureRegion region;

    @Override
    public void show() {
        Gdx.app.debug("screens.log", "GameScreen");

        renderer = new Box2DDebugRenderer();

        engine = new Engine();

        InputSystem inputSystem = new InputSystem();
        Gdx.input.setInputProcessor(inputSystem);

        RenderSystem renderSystem = new RenderSystem();

        engine.addSystem(new MovementSystem());
        engine.addSystem(renderSystem);
        engine.addSystem(inputSystem);

        CollisionSystem collisionSystem = new CollisionSystem();
        engine.addSystem(collisionSystem);
        Box2DCreateWorld(collisionSystem);
        collisionSystem.setWorld(world);

        addGround(0,0);
        addPlayer(1,1);
        //addBox(0.8f, 3f);
        addBoxRandom();

        time = TimeUtils.millis();
    }

    void Box2DCreateWorld(CollisionSystem collisionSystem){
        world = new World(new Vector2(0,-15),true);
        world.setContactListener(collisionSystem);
        //myQueryCallback = new MyQueryCallback();
        //world.QueryAABB(myQueryCallback, 1, 1, 19, 19);
    }

    void addGround(float x,float y){
        Ground ground = new Ground(world,x,y);
        engine.addEntity(ground.getEntity());
    }

    void addPlayer(float x, float y){
        Player player = new Player(world,x,y);
        engine.addEntity(player.getEntity());
    }

    void addBox(float x,float y){
        Box box = new Box(world,x,y,0);
        engine.addEntity(box.getEntity());
    }
    void addBoxRandom(){
        float x = MathUtils.random(engine.getSystem(RenderSystem.class).camera_width);
        float y = MathUtils.random(2,engine.getSystem(RenderSystem.class).camera_height);
        float angle = MathUtils.random(360);
        Box box = new Box(world,x,y,angle);
        engine.addEntity(box.getEntity());
    }

    @Override
    public void render(float delta) {
        //world.QueryAABB(myQueryCallback, 1, 1, 1,0 );
        //Body findbody = myQueryCallback.getBody();
        //if(findbody != null){
         //   Gdx.app.log("GameScreen","Body delete!");
         //   world.destroyBody(myQueryCallback.getBody());
       // }
        if (TimeUtils.millis()-time > 500){
            addBoxRandom();
            count++;
           // Gdx.app.log("GameScreen","Boxes on GameScreen = " + count);
            time = TimeUtils.millis();
        }

        engine.update(delta);
        renderer.render(world,engine.getSystem(RenderSystem.class).getCamera().combined);
        world.step(delta,4,4);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen","Resize!");
        GameAssetManager.getInstance().load("textures/textures.atlas",TextureAtlas.class);
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen","Pause!");
        //GameAssetManager.getInstance().dispose();
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen","Resume!");
        //GameAssetManager.getInstance().init();
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen","Hide!");
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen","Dispose!");
        GameAssetManager.getInstance().dispose();
    }
}


