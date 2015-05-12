package kr.tangomike.tc_test_002;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.TextView;

@SuppressLint("ClickableViewAccessibility")
public class TestActivity extends Activity {
	
	private GLCubeView view;
	private TextView tvDebug;
	@Override
	protected void onCreate(Bundle sis){
		super.onCreate(sis);
		

        /*Disable Sleep Mode */
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
		
//		ControlView view = new ControlView(this);
//		OGLRenderer rndr = new OGLRenderer(this);
//		view.setRenderer(rndr);
//		
        
        
        
        FrameLayout frm = new FrameLayout(this);
        frm.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        
        
        
        
        view = new GLCubeView(this);
        
        frm.addView(view);
        
        
        tvDebug = new TextView(this);
        tvDebug.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        tvDebug.setTextColor(Color.CYAN);
        tvDebug.setX(10);
        tvDebug.setY(10);
        
        frm.addView(tvDebug);
        
		setContentView(frm);
		
		setText();
		
		frm.setOnTouchListener(new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				
				view.retriveTouchEvent(event);
				setText();
				return true;
			}
		});
		
		
		
	}
	
	private void setText(){
		
		String str = "";
		for (int i = 0; i < view.mRenderer.m_fVPMatrix.length; i++){
		
			str += i + "\t\t" + view.mRenderer.m_fVPMatrix[i] + "\n";
			
		}
		
		tvDebug.setText(str);
	}
	

}
