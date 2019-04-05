package com.example.tinder2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This activity displays an image on the screen.
 * The image has three different regions that can be clicked / touched.
 * When a region is touched, the activity changes the view to show a different
 * image.
 *
 */

public class MapsActivity extends Activity

        implements View.OnTouchListener

{
int i=0;String Area;
    /**
     * Create the view for the activity.
     *
     */

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ImageView iv = (ImageView) findViewById (R.id.image);
        if (iv != null) {
            iv.setOnTouchListener (this);
        }

        toast ("Touch the screen to discover where the regions are.");
    }

    /**
     * Respond to the user touching the screen.
     * Change images to make things appear and disappear from the screen.
     *
     */

    public boolean onTouch (View v, MotionEvent ev)
    {
        boolean handledHere = false;

        final int action = ev.getAction();

        final int evX = (int) ev.getX();
        final int evY = (int) ev.getY();
        int nextImage = -1;			// resource id of the next image to display

        // If we cannot find the imageView, return.
        ImageView imageView = (ImageView) v.findViewById (R.id.image);
        if (imageView == null) return false;

        // When the action is Down, see if we should show the "pressed" image for the default image.
        // We do this when the default image is showing. That condition is detectable by looking at the
        // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
        Integer tagNum = (Integer) imageView.getTag ();
        int currentResource = (tagNum == null) ? R.drawable.map_default : tagNum.intValue ();

        // Now that we know the current resource being displayed we can handle the DOWN and UP events.

        switch (action) {
            case MotionEvent.ACTION_DOWN :
                if (currentResource == R.drawable.map_default) {
                    nextImage = R.drawable.map_default;
                    handledHere = true;
       /*
       } else if (currentResource != R.drawable.p2_ship_default) {
         nextImage = R.drawable.p2_ship_default;
         handledHere = true;
       */
                } else handledHere = true;
                break;

            case MotionEvent.ACTION_UP :
                // On the UP, we do the click action.
                // The hidden image (image_areas) has three different hotspots on it.
                // The colors are red, blue, and yellow.
                // Use image_areas to determine which region the user touched.
                int touchColor = getHotspotColor (R.id.image_areas, evX, evY);

                // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
                // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
                // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
                // varying pixel density.
                ColorTool ct = new ColorTool ();
                int tolerance = 0;
                nextImage = R.drawable.map_default;




                if (ct.closeMatch (Color.RED, touchColor, tolerance))
                {
                    Toast.makeText(this,"Latin America",Toast.LENGTH_SHORT).show();i=1;Area="Latin America";
                }
                if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)){
                    Toast.makeText(this,"North America",Toast.LENGTH_SHORT).show();i=2;Area="North America";
                }

                if (ct.closeMatch (Color.GRAY, touchColor, tolerance))
                {
                    Toast.makeText(this,"Australia",Toast.LENGTH_SHORT).show();i=3;Area="Australia";
                }
                if (ct.closeMatch (Color.DKGRAY, touchColor, tolerance))
                {
                    Toast.makeText(this,"Asia",Toast.LENGTH_SHORT).show();i=4;Area="Asia";
                }
                if (ct.closeMatch (Color.CYAN, touchColor, tolerance))
                {
                    Toast.makeText(this,"Africa",Toast.LENGTH_SHORT).show();i=5;Area="Africa";
                }
                if (ct.closeMatch (Color.GREEN, touchColor, tolerance))
                {
                    Toast.makeText(this,"Arab",Toast.LENGTH_SHORT).show();i=6;Area="Arab";
                }
                if (ct.closeMatch (Color.MAGENTA, touchColor, tolerance)) {
                    Toast.makeText(this,"Europe",Toast.LENGTH_SHORT).show();i=7;Area="Europe";
                }

                else
                if (ct.closeMatch (Color.WHITE, touchColor, tolerance))
                {
                    Toast.makeText(this,"Ocean",Toast.LENGTH_SHORT).show();i=0;
                }


             if(i!=0) {
                 AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                 builder1.setMessage("Are you sure you want to proceed with " + Area + " ?");
                 builder1.setCancelable(true);

                 builder1.setPositiveButton(
                         "Yes",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 dialog.cancel();
                                 Intent intent = new Intent(MapsActivity.this, ChooseTask.class);
                                 intent.putExtra("mapid", i);
                                 startActivity(intent);



                             }
                         });

                 builder1.setNegativeButton(
                         "No",
                         new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 dialog.cancel();
                             }
                         });

                 AlertDialog alert11 = builder1.create();
                 alert11.show();

             }




                // If the next image is the same as the last image, go back to the default.
                // toast ("Current image: " + currentResource + " next: " + nextImage);
                if (currentResource == nextImage) {
                    nextImage = R.drawable.map_default;
                }
                handledHere = true;
                break;

            default:
                handledHere = false;
        } // end switch

        if (handledHere) {

            if (nextImage > 0) {
                imageView.setImageResource (nextImage);
                imageView.setTag (nextImage);
            }
        }
        return handledHere;
    }

    /**
     * Resume the activity.
     */

//    @Override protected void onResume() {
//        super.onResume();
//
//        View v  = findViewById (R.id.wglxy_bar);
//        if (v != null) {
//            Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//            //anim1.setAnimationListener (new StartActivityAfterAnimation (i));
//            v.startAnimation (anim1);
//        }
//    }
//
//    /**
//     * Handle a click on the Wglxy views at the bottom.
//     *
//     */
//
//    public void onClickWglxy (View v) {
//        Intent viewIntent = new Intent ("android.intent.action.VIEW",
//                Uri.parse("http://double-star.appspot.com/blahti/ds-download.html"));
//        startActivity(viewIntent);
//
//    }


/**
 */
// More methods

    /**
     * Get the color from the hotspot image at point x-y.
     *
     */

    public int getHotspotColor (int hotspotId, int x, int y) {
        ImageView img = (ImageView) findViewById (hotspotId);
        if (img == null) {
            Log.d ("ImageAreasActivity", "Hot spot image not found");
            return 0;
        } else {
            img.setDrawingCacheEnabled(true);
            Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
            if (hotspots == null) {
                Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
                return 0;
            } else {
                img.setDrawingCacheEnabled(false);
                return hotspots.getPixel(x, y);
            }
        }
    }

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
    } // end toast

} // end class
