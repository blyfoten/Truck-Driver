package se.cfor.truckdriver;


import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class ThaGLSurfaceView extends GLSurfaceView {
	
    private ThaGLRenderer mRenderer;
    private float startAngle = 200;
	private double wheelAngle;

    public ThaGLSurfaceView(Context context){
        super(context);
        // Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);
            
        // set the mRenderer member
        mRenderer = new ThaGLRenderer(context);
        setRenderer(mRenderer);
        
        // Render the view only when there is a change
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
    @Override 
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input 

       // and other input controls. In this case, you are only
       // interested in events where the touch position changed.
    	for (int i=0;i<e.getPointerCount();i++) {
	        float x = e.getX(i);
	        float y = e.getY(i);
	        float xn;
	        float yn,hyp;
	        float newAngle;
	        
	        switch (e.getAction()) {
	        case MotionEvent.ACTION_MOVE:
	        case MotionEvent.ACTION_DOWN:
	        	yn = Math.max(8.0f*(float)y/getHeight(),6.0f)-7.0f;
            	xn = Math.max(8.0f*(float)x/getWidth() ,6.0f)-7.0f;
            	
            		
	                if (x < getWidth()/2 && y > 2*getHeight()/3)
	                	mRenderer.setAccel(((yn<0)?-0.005f:-0.002f)*yn);
	                
	                if (x > (getWidth()/2)) {
	                	hyp = Math.max(0.1f,(float)Math.sqrt(xn*xn+yn*yn));
		            	if (startAngle > 10.0f) {
		            		startAngle = (yn<0)?(float)Math.acos((float)xn/hyp):(-(float)Math.acos((float)xn/hyp));
		            	} else {
		            		newAngle = (yn<0)?(float)Math.acos((float)xn/hyp):(-(float)Math.acos((float)xn/hyp));
		                    float delta = (newAngle-startAngle);
		                    if (delta > 180) 
		                    	delta = 2*(float)Math.PI - delta;
		                    if (delta < -Math.PI)
		                    	delta = 2*(float)Math.PI + delta;
		                    wheelAngle = mRenderer.getWheelAngle();
		            		wheelAngle+= delta; 
		                    startAngle = newAngle;
		                    wheelAngle = (wheelAngle+Math.PI)%(2*Math.PI)-Math.PI;
			                wheelAngle = (wheelAngle>2)?2:wheelAngle;
			                wheelAngle = (wheelAngle<-2)?-2:wheelAngle;
			                mRenderer.setWheelAngle(wheelAngle);
		            	}
	                }
	                
	                //requestRender();
	                break;
	        case MotionEvent.ACTION_UP:
	        case MotionEvent.ACTION_CANCEL:
	        case MotionEvent.ACTION_POINTER_UP:
		            if (x <getWidth()/2) {
	            		mRenderer.setAccel(0);
		            } else 
	            		startAngle = 200;
	        }
    	}
        return true;
    }
    
    
}
