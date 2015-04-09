package whail.trail.hueil;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;

/**
 * Created by User on 09.04.2015.
 */
public class MyQueryCallback implements QueryCallback{
    private Body fBody;
    private Fixture fFixture;
    @Override
    public boolean reportFixture(Fixture fixture) {
        fBody=fixture.getBody();
        return false;
    }
    public Body getBody() {
        return fBody;
    }
}
