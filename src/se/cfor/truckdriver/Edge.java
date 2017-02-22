package se.cfor.truckdriver;

public class Edge {
	Vertex v1;
	Vertex v2;
	float length;
	boolean boundary; //Value used for optimization - see Physics::DetectCollision for more information
	PhysicsBody parent;

	public Edge( Physics world, PhysicsBody body, Vertex pV1, Vertex pV2) {
		this(world, body, pV1, pV2, true);
	}

	public Edge( Physics world, PhysicsBody body, Vertex pV1, Vertex pV2, boolean pBoundary) {
		v1 = pV1; //Set boundary vertices
		v2 = pV2;

		length   = (float)Math.hypot(pV2.position.x - pV1.position.x, pV2.position.y - pV1.position.y); //Calculate the original length
		boundary = pBoundary;

		parent = body;

		body.addEdge( this ); //Add the edge to the given body and to the physics simulator
		world.addEdge( this );
	}
};
