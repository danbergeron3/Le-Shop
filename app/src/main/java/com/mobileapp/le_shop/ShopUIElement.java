package com.mobileapp.le_shop;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Class to hold the button that will display the item,
 * price, and title.
 */
public class ShopUIElement {
    final private ImageButton buttonRef;
    final private TextView titleView, priceView;
    private String title;
    private float price;
    private Bitmap image;

    /**
     * Default constructor.
     * @param c Context
     */
    ShopUIElement(Context c) {
        buttonRef = new ImageButton(c);
        titleView = new TextView(c);
        priceView = new TextView(c);
    }

    /**
     * Constructor with image location for image. (Not Done)
     * @param c Context
     * @param title Title
     * @param price Price
     * @param imageLocation imageLocation (might change to id)
     */
    ShopUIElement(Context c, String title, float price, String imageLocation) {
        buttonRef = new ImageButton(c);
        titleView = new TextView(c);
        priceView = new TextView(c);

        this.title = title;
        this.price = price;
        // TODO: Import image from location into bitmap

        UpdateUIElements();
    }

    /**
     * Constructor with Bitmap reference for image.
     * @param c Context
     * @param title Title
     * @param price Price
     * @param image Image Bitmap
     */
    ShopUIElement(Context c, String title, float price, Bitmap image) {
        buttonRef = new ImageButton(c);
        titleView = new TextView(c);
        priceView = new TextView(c);

        this.title = title;
        this.price = price;
        this.image = image;

        UpdateUIElements();
    }

    /**
     * Sets the image with Bitmap image.
     * @param image Image Bitmap
     */
    void setImage(Bitmap image) {
        this.image = image;
    }

    /**
     * Sets the title.
     * @param title Title
     */
    void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the price.
     * @param price Price
     */
    void setPrice(float price) {
        this.price = price;
    }

    /**
     * Updates the UI elements display. Should be
     * used anytime the title, price, or image is
     * changed.
     */
    private void UpdateUIElements() {
        buttonRef.setImageBitmap(image);
        titleView.setText(title);
        priceView.setText(String.format("%.2f", price));
    }
}
