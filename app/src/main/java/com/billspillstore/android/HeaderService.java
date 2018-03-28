package com.billspillstore.android;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class HeaderService extends Service {


    private WindowManager mWindowManager;
    private View mChatHeadView;
    String company="";

    public HeaderService() {}

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public void startpopup(){
        //stopSelf();
        company=MyAccessibilityService.appname;
       // String packagename=MyAccessibilityService.classname;
       //if(company.equals("Flipkart")|| company.equals("Ebay")||company.equals("Shopclues")||company.equals("Snapdeal")){

            Intent dialogIntent = new Intent(this.getApplicationContext(), PopupListActivity.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //dialogIntent.clone();
        //int id= android.os.Process.myPid();
       // android.os.Process.killProcess(id);
            startActivity(dialogIntent);
        }
       // }
       // else {
           // Intent dialogIntent = new Intent(this, CustomizedPopupActivity.class);
           // dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //  startActivity(dialogIntent);
        //}

    @Override
    public void onCreate() {
        mChatHeadView = LayoutInflater.from(this).inflate(R.layout.layout_chat_header, null);
        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        //Specify the chat head position
        params.gravity = Gravity.CENTER | Gravity.END;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;
        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mChatHeadView, params);




        ImageView chatHeadImage = (ImageView) mChatHeadView.findViewById(R.id.chat_head_profile_iv);
        chatHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startpopup();
            }
        });
     /*  chatHeadImage.setOnTouchListener(new View.OnTouchListener() {
            private int lastAction;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;



            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;
                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        startpopup();
                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_UP:
                        //As we implemented on touch listener with ACTION_MOVE,
                        //we have to check if the previous action was ACTION_DOWN
                        //to identify if the user clicked the view or not.
                        if (lastAction == MotionEvent.ACTION_DOWN) {   //Open the chat conversation click.
                            startpopup();
                        }
                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mChatHeadView, params);
                        lastAction = event.getAction();
                        return true;
                }
                return false;
            }
        });*/

    }

    @Override
    public void onDestroy() {
        if (mChatHeadView != null) mWindowManager.removeView(mChatHeadView);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }


}



