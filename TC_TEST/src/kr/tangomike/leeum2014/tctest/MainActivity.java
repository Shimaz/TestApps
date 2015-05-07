package kr.tangomike.leeum2014.tctest;

import tuioDroid.osc.OSCInterface;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class MainActivity extends Activity {
	
	private RelativeLayout rlMain;
	private TextView tvDebug;
	
	private static final int MODE_PAN = 0x00;
	private static final int MODE_ZOOM = 0x01;
	
	private float posX, posY, posCenterX, posCenterY;
	private double distOld, distNow, distDiff;
	
	private int touchCount, mode;
	
	private double scale;
	
	private boolean isOSCConnected;
	
	private InputMethodManager imm;

	
	private OSCInterface oscInterface;
	
	private EditText etIP;
	private EditText etPort;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*Disable Sleep Mode */
        super.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		
		setContentView(R.layout.layout_main);

		isOSCConnected = false;
		scale = 1;
		
		tvDebug = (TextView)findViewById(R.id.tv_debug);
		
		etIP = (EditText)findViewById(R.id.et_ip);
		etPort = (EditText)findViewById(R.id.et_port);
		
		rlMain = (RelativeLayout)findViewById(R.id.rl_main);
		rlMain.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				mode = MODE_PAN;
				
				touchCount = event.getPointerCount();
				if(touchCount  == 1) mode = MODE_PAN;
				else if(touchCount >= 2) mode = MODE_ZOOM;
				
				switch(mode){
				case MODE_PAN:
					
					posX = event.getX(0);
					posY = event.getY(0);
					posCenterX = posCenterY = 0;
					
					
					break;
					
				case MODE_ZOOM:
					posCenterX = (event.getX(0) + event.getX(1)) / 2;
					posCenterY = (event.getY(0) + event.getY(1)) / 2;
					
					distNow = Math.sqrt( Math.pow(event.getX(0) - event.getX(1), 2) + Math.pow(event.getY(0) - event.getY(1), 2) );
					distDiff = distNow - distOld;
					distOld = distNow;
					
					
					
					break;
					
				default:
					break;
				}
				
				
				
				
				setText();
				if(isOSCConnected)	sendOSC();
				
				
				return true;
			}
		});


		
		
		Button btnConnect = (Button)findViewById(R.id.btn_connect);
		btnConnect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				
				if(etPort.getText().toString() != "" && etIP.getText().toString() != ""){
					
					Toast toast = Toast.makeText(getApplicationContext(), "fill ip and port", Toast.LENGTH_SHORT);
					toast.show();
					
				}else{
				
					String serverIP = "192.168.0." + etIP.getText().toString();
					int serverPort = Integer.parseInt(etPort.getText().toString());
					
					oscInterface = new OSCInterface(serverIP, serverPort);
				
					if(oscInterface.isReachable()) isOSCConnected = true;
				
					
				}
				
					
			}
		});
		
		
		
		
		
		
	}
	
	
	
	private void setText(){
		
		String strTmp = 
				"Touch Count: " + touchCount + "\n\n"
				+ "Position: \n" + posX + "\n" + posY + "\n\n" 
				+ "Center: \n" + posCenterX + "\n" + posCenterY + "\n\n"
				+ "Distance: " + distNow + "\n\n" 
				+ "Difference: " + distDiff;
		
		if(mode == MODE_PAN) strTmp += "\n\nMode: PAN";
		else strTmp += "\n\nMODE: ZOOM";
		
		if(isOSCConnected) strTmp += "\n\nOSC Connected";
		else strTmp += "\n\nOSC Disconnected";
		
		tvDebug.setText(strTmp);
		
		
		
	}
	
	
	private void sendOSC(){
		
		
	}
}
