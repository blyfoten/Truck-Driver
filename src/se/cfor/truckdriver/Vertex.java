package se.cfor.truckdriver;

import android.graphics.PointF;


public class Vertex {
	protected PointF position;
	protected PointF oldPosition;
	protected PointF acceleration;
	protected PhysicsBody parent;

	public Vertex(Physics world, PhysicsBody body, float posX, float posY ) {
		position    = new PointF( posX, posY );
		oldPosition = new PointF( posX, posY );

		parent = body;

		body.addVertex( this ); //Add the vertex to the given body and to the physics simulator
		world.addVertex( this );
	}
};

