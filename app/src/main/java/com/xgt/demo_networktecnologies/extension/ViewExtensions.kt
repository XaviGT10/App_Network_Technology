package com.xgt.demo_networktecnologies.extension

import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso


fun ImageView.imageUrl(imageUrl: String?) {
    if (imageUrl.isNullOrEmpty()) return
    Picasso.get().load(imageUrl).into(this)
}

fun View.visibleOrGone(visible: Boolean) {

    this.visibility = if (visible) View.VISIBLE else View.GONE

/* Es lo mismo que:


    if (visible){
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
 */
}