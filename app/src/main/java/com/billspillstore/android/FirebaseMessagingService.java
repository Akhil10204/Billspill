package com.billspillstore.android;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    String summary;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if(remoteMessage.getData().size()>0){

            final String title,message,image,link,click_action;
            Intent intent;
            title=remoteMessage.getData().get("title");
            message=remoteMessage.getData().get("message");
            image=remoteMessage.getData().get("image");
            link=remoteMessage.getData().get("offerlink");
            summary=remoteMessage.getData().get("summary");
            click_action=remoteMessage.getData().get("click_action");
            Log.d("Extrasare",title+"    "+message+"    "+image);
            if(click_action.trim().equals("WEBLINK")) {
                intent = new Intent(this, NotificationActivity.class);
                intent.putExtra("URL", link);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("TOPOFFERS")){
                intent = new Intent(this, MenuOffers.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("TOPPRODUCTS")){
                intent = new Intent(this, MenuProducts.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("TRAVELSTAY")){
                intent = new Intent(this, TabbedFlightActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("CLOTHINGS")){
                intent = new Intent(this, ClothingTabbedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("GLOBALSTORES")){
                intent = new Intent(this, GlobalTabbed.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("OTHERSTORES")){
                intent = new Intent(this, OtherTabbedActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("BLOG")){
                intent = new Intent(this, BlogWebActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else if(click_action.trim().equals("CHOOSESTORES")){
                intent = new Intent(this, SetStoreActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            else
            {
                intent = new Intent(this, TabbedMainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            }
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
            final NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setAutoCancel(true);
            builder.setContentTitle(title);
            builder.setContentText(message);
            builder.setContentIntent(pendingIntent);
            builder.setSound(sound);
            builder.setSmallIcon(R.drawable.ic_notification);
            Drawable drawable= ContextCompat.getDrawable(this,R.drawable.billspill);

            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
            builder.setLargeIcon(bitmap);
            ImageRequest imageRequest=new ImageRequest(image, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    NotificationCompat.BigPictureStyle style =
                            new NotificationCompat.BigPictureStyle(builder);
                    style.setBigContentTitle(title);
                    style.setSummaryText(summary);
                    style.bigPicture(response);
                    builder.setStyle(style);
                    //  builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0,builder.build());
                }
            }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            MySingleton.getmInstance(this).addToRequestQueue(imageRequest);
        }





    }
}
