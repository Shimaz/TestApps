package kr.tangomike.tc_test_002;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

@SuppressLint("ClickableViewAccessibility")
public class ControlView extends GLSurfaceView {
	
	private OGLRenderer myRndr;
	
	public ControlView (Context context){
		super(context);
		
	}

	


	float prevX, prevY, curX, curY;

	float angleY, angleX;

	@Override

	public boolean onTouchEvent(MotionEvent event) {

		//Log.i("터치 이벤트", "터치 이벤트발생");

		int action = event.getAction();

		if(action==MotionEvent.ACTION_UP) {

			//prevX=prevY=curX=curY=angleX=angleY=0;

			prevX=prevY = 0;

		}else if(action==MotionEvent.ACTION_MOVE) {

			//Log.i("터치 이벤트", "Move 이벤트발생");

			if(prevX==0 && prevY==0) {

				prevX = event.getX();

				prevY = event.getY();

				return true;

			}

			curX = event.getX();

			curY = event.getY();

			if(curX - prevX > 0) {

				this.myRndr.angleY=(angleY+=5);

			}else if(curX - prevX < 0) {

				this.myRndr.angleY=(angleY-=5);

			}/*

			if(curY - prevY >0) {

				this.renderer.angleX=(angleX+=2);

			}else if(curY - prevY <0) {// 위로

				this.renderer.angleX=(angleX-=2);

			}*/

		}

		return true;

	}

	@Override

	public void setRenderer(Renderer renderer) {

		super.setRenderer(renderer);

		this.myRndr = (OGLRenderer)renderer;

	}

}
