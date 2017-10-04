package com.okapp.picasso;

import android.content.Context;
import android.widget.ImageView;

import com.okapp.domain.helpers.ImageHelper;
import com.squareup.picasso.Picasso;


/**
 * @author John Piser johnpiser@yahoo.com
 */

public class PicassoImageHelper implements ImageHelper {

    private Picasso picasso;

    public PicassoImageHelper(Context applicationContext, boolean indicatorsEnabled) {
        picasso = Picasso.with(applicationContext);
        picasso.setIndicatorsEnabled(indicatorsEnabled);
    }
    @Override
    public void loadImage(Object imageView, String url) {

        if(imageView instanceof ImageView && url != null){

            picasso.load(url).into((ImageView) imageView);
        }
    }
}
