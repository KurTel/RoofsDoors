package whail.trail.hueil.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.InputControlComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;

/**
 * Created by User on 06.04.2015.
 */
public class InputSystem extends IteratingSystem implements InputProcessor {

    private Vector2 accel;

    private InputControlComponent inputControl;
    private MovementComponent movement;
    private BodyComponent body;
    private PositionComponent position;

    private ComponentMapper<InputControlComponent> ic = ComponentMapper.getFor(InputControlComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<BodyComponent> bd = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<PositionComponent> po = ComponentMapper.getFor(PositionComponent.class);

    private enum Keys {LEFT, RIGHT, UP, DOWN}
    private HashMap<Keys,Boolean> keys = new HashMap<Keys, Boolean>();

    public InputSystem() {
        super(Family.all(InputControlComponent.class,MovementComponent.class,BodyComponent.class,PositionComponent.class).get());
        keys.put(Keys.UP,false);
        keys.put(Keys.RIGHT,false);
        keys.put(Keys.DOWN,false);
        keys.put(Keys.LEFT,false);
    }

    @Override
    public boolean keyDown(int keycode) {
        //получаем нажатые клавиши и выставляем соответствующие члены словаря keys
        switch (keycode){
            case Input.Keys.LEFT:
                //нажата кнопка "влево"
                keys.put(Keys.LEFT, true);
                //Gdx.app.log("InputSystem", "key LEFT pressed");
                break;
            case Input.Keys.RIGHT:
                //нажата кнопка "вправо"
                keys.put(Keys.RIGHT, true);
                //Gdx.app.log("InputSystem", "key RIGHT pressed");
                break;
            case Input.Keys.UP:
                //нажата кнопка "вверх"
                keys.put(Keys.UP, true);
                //Gdx.app.log("InputSystem", "key UP pressed");
                break;
            case Input.Keys.DOWN:
                //нажата кнопка "вниз"
                keys.put(Keys.DOWN, true);
                //Gdx.app.log("InputSystem", "key DOWN pressed");
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //получаем нажатые клавиши и выставляем соответствующие члены словаря keys
        switch (keycode){
            case Input.Keys.LEFT:
                //нажата кнопка "влево"
                keys.put(Keys.LEFT, false);
                break;
            case Input.Keys.RIGHT:
                //нажата кнопка "вправо"
                keys.put(Keys.RIGHT, false);
                break;
            case Input.Keys.UP:
                //нажата кнопка "вверх"
                keys.put(Keys.UP, false);
                break;
            case Input.Keys.DOWN:
                //нажата кнопка "вниз"
                keys.put(Keys.DOWN, false);
                break;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        inputControl = ic.get(entity);
        movement = mm.get(entity);
        body = bd.get(entity);
        position = po.get(entity);
        accel = new Vector2();

        if(inputControl.isControllable()){
            if(keys.get(Keys.UP)){
                //movement.acceleration.y = 0.1f;
                if(position.isGrounded)
                    body.body.applyLinearImpulse(new Vector2(0,0.7f),body.body.getPosition(),true);

            }
            if(keys.get(Keys.RIGHT)){
                movement.acceleration.x = 10f;
                //body.body.getFixtureList().get(0).setFriction(0.2f);
                //body.body.applyLinearImpulse(new Vector2(1f,0),body.body.getPosition(),true);
            }
            if(keys.get(Keys.DOWN)){
                //movement.acceleration.y = -0.1f;
                //body.body.applyLinearImpulse(new Vector2(0,10),body.body.getPosition(),true);
            }
            if(keys.get(Keys.LEFT)){
                //body.body.getFixtureList().get(0).setFriction(0.2f);
                movement.acceleration.x = -10f;
                //body.body.applyLinearImpulse(new Vector2(-1f,0),body.body.getPosition(),true);
            }
            if((!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)) || (keys.get(Keys.LEFT) && keys.get(Keys.RIGHT))){
                //movement.acceleration.x = 0;
                body.body.setLinearVelocity(0,body.body.getLinearVelocity().y);

                //if(  Math.abs(body.body.getLinearVelocity().x) > 0){
                //    body.body.getFixtureList().get(0).setFriction(200);
                //}
                //в случае, когда кнопки "влево" и "вправо" нажаты одновременно или не нажаты обе, обнуляем x составляющую ускорения
            }
            if((!keys.get(Keys.UP) && !keys.get(Keys.DOWN)) || (keys.get(Keys.UP) && keys.get(Keys.DOWN))){
                //movement.acceleration.y = 0;
                //аналогично проверяем "вверх" и "вниз"
            }
            position.isGrounded = false;
            //movement.acceleration.nor().scl(movement.accelerationValue);
        }
    }
}
