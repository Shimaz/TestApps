package kr.tangomike.tc_test_002;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;

public class TestActivity extends Activity {
	

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
        
        GLCubeView view = new GLCubeView(this);
        
		setContentView(view);
		
		
		
	}
	

}
