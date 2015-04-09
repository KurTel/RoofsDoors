package whail.trail.hueil.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;

import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;

/**
 * Created by User on 06.04.2015.
 */
public class MovementSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<BodyComponent> bd = ComponentMapper.getFor(BodyComponent.class);

    public MovementSystem(){
        super(Family.all(PositionComponent.class, MovementComponent.class,BodyComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent position = pm.get(entity);
        MovementComponent movement = mm.get(entity);
        BodyComponent body = bd.get(entity);

        if(body!=null){
            if( Math.abs(body.body.getLinearVelocity().x) <= movement.maxVelocity) {
                body.body.setLinearVelocity(body.body.getLinearVelocity().add(movement.acceleration.cpy().scl(deltaTime)));
            }
            if( body.body.getLinearVelocity().x >= movement.maxVelocity) {
                body.body.setLinearVelocity(movement.maxVelocity,body.body.getLinearVelocity().y);
            }
            if( body.body.getLinearVelocity().x <= -movement.maxVelocity) {
                body.body.setLinearVelocity(-movement.maxVelocity,body.body.getLinearVelocity().y);
            }
        }
        //if( body.body.getLinearVelocity().len() >= movement.maxVelocity){
            //Gdx.app.log("MovementSystem","limit velocity" + body.body.getLinearVelocity().len());
            //body.body.getLinearVelocity().limit(movement.maxVelocity);
        //}
        //position.position.add(movement.velocity.cpy().scl(deltaTime));
        position.position = body.body.getPosition();
    }
}
