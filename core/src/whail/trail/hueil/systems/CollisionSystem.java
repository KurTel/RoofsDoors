package whail.trail.hueil.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.Manifold.ManifoldPoint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;

import whail.trail.hueil.components.BodyComponent;
import whail.trail.hueil.components.MovementComponent;
import whail.trail.hueil.components.PositionComponent;
import whail.trail.hueil.components.SizeComponent;

/**
 * Created by User on 09.04.2015.
 */
public class CollisionSystem extends EntitySystem implements ContactListener {

    public ImmutableArray<Entity> entities;
    private ComponentMapper<PositionComponent> po = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<BodyComponent> bd = ComponentMapper.getFor(BodyComponent.class);

    World world;

    Vector2 normal;
    Vector2 localpoint;
    Vector2[] points;

    ManifoldPoint[] manifoldPoints;

    ImmediateModeRenderer20 r;

    public CollisionSystem() {
    }

    public void setWorld(World world){
        this.world = world;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class, PositionComponent.class).get());
    }

    @Override
    public void beginContact(Contact contact) {
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }


    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    @Override
    public void update(float delta) {
        boolean isGrounded = isGrounded();
        if (isGrounded) {
            Entity e;
            BodyComponent bodyC;
            PositionComponent posC;
            for (int j = 0; j < entities.size(); j++) {
                e = entities.get(j);
                posC = po.get(e);
                bodyC = bd.get(e);
                if (bodyC.body.getUserData().equals("player")) {
                    posC.isGrounded = isGrounded;
                }
            }
        }
    }

    boolean isGrounded() {
        //проверка на контакт с поверхностью
        Vector2 pos;
        Body body;

        Array<Contact> contactList = world.getContactList();
        for (int i = 0; i < contactList.size; i++) {
            Contact contact = contactList.get(i);
            if ((contact.isTouching())) {
                if ((contact.getFixtureA().getBody().getUserData().equals("player")) ||
                        (contact.getFixtureB().getBody().getUserData().equals("player"))) {
                    if ((contact.getFixtureA().getBody().getUserData().equals("player"))) {
                        pos = contact.getFixtureA().getBody().getPosition();
                    } else {
                        pos = contact.getFixtureB().getBody().getPosition();
                    }
                    WorldManifold manifold = contact.getWorldManifold();
                    for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                        if (manifold.getPoints()[j].y < pos.y + 0.4f) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}













/*//Gdx.gl.glEnable(GL20.GL_BLEND);
        //Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        //r.begin(camera.combined,GL20.GL_TRIANGLE_FAN);

        Body bodyA = contact.getFixtureA().getBody();
        Body bodyB = contact.getFixtureB().getBody();
        Object userdataA = bodyA.getUserData();
        Object userdataB = bodyB.getUserData();
        if (  (userdataA.equals("player")) ||
              (userdataB.equals("player"))     ){
            localpoint = oldManifold.getLocalPoint();
            oldManifold.getPointCount();
            manifoldPoints = oldManifold.getPoints();

            //normal = oldManifold.getLocalNormal();
            //Gdx.app.log("CollisionSystem","normal = " + normal.x + " " + normal.y);
            //Gdx.app.log("CollisionSystem","localpoint = " + localpoint.x + " " + localpoint.y);
            Gdx.app.log("CollisionSystem","pointcount = " + oldManifold.getPointCount());
            Gdx.app.log("CollisionSystem","point0  = " + manifoldPoints[0].localPoint.x + " " + manifoldPoints[0].localPoint.y);
            Gdx.app.log("CollisionSystem","point1  = " + manifoldPoints[1].localPoint.x + " " + manifoldPoints[1].localPoint.y);

            /*Manifold.ManifoldType type = oldManifold.getType();
            Vector2 localPoint = oldManifold.getLocalPoint();
            Vector2 localNormal = oldManifold.getLocalNormal();
            int pointCount = oldManifold.getPointCount();
            ManifoldPoint[] points = oldManifold.getPoints();
            System.out.println("pre solve, " + type +
            ", point: " + localPoint +
            ", local normal: " + localNormal +
            ", #points: " + pointCount +
            ", [" + points[0] + ", " + points[1] + "]");*/
/*
            WorldManifold manifold = contact.getWorldManifold();
            boolean below = true;
            for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                below &= (manifold.getPoints()[j].y < pos.y - 0.4f);
            }


            //r.color(Color.WHITE);
            //r.vertex(manifoldPoints[0].localPoint.x, manifoldPoints[0].localPoint.y,3f);
            //r.vertex(manifoldPoints[1].localPoint.x,manifoldPoints[1].localPoint.y,1);
        }
        //r.end();*/