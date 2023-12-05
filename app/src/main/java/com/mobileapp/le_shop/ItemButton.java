package com.mobileapp.le_shop;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemButton extends ConstraintLayout {
    private ImageView itemImage;
    private TextView itemName;
    private TextView itemPrice;
    private int itemIDNum;


    public ItemButton(Context context, int action) {
        super(context);
        init(context, action);
    }

    public ItemButton(Context context, AttributeSet attrs, int action) {
        super(context, attrs);
        init(context, action);
    }

    /**
     *
     * @param context pass in context
     * @param attrs pass in attributes, can use null if none needed
     * @param imageResource pass in imageResource number, use R.id etc
     * @param itemName  Set item name
     * @param itemPrice Set item price
     * @param itemIDNum Set itemIdNumber, will be passed in action
     * @param action action when declaring button, then pass action
     */
    //you can use null for attrs if you want, just declare an itemButton as new ItemButton, pass in the resource value as for imgResource
    //then do currentLayout.addView(itemButton) after initializing
    public ItemButton(Context context, AttributeSet attrs, int imageResource, String itemName,
                      String itemPrice, int itemIDNum, int action) {
        super(context, attrs);
        init(context, action);
        setItemImage(imageResource);
        setItemName(itemName);
        setItemPrice(itemPrice);
        setItemIDNum(itemIDNum);
    }

    private void init(Context context, int action) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_button, this, true);

        itemImage = findViewById(R.id.itemImage);
        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        Log.d("ITEMBUTTON_DEBUG", "before onClick Called");
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("ITEM_ID", itemIDNum);
                Log.d("ITEMBUTTON_DEBUG", "onClick Called");
                Navigation.findNavController(v).navigate(R.id.action_catalogFragment_to_itemFragment, bundle);
            }
        });
    }

    public void setItemImage(int resourceId) {
        itemImage.setImageResource(resourceId);
    }

    public void setItemName(String text) {
        itemName.setText(text);
    }

    public void setItemPrice(String text) {
        itemPrice.setText(text);
    }

    public void setItemIDNum(int num){
        this.itemIDNum = num;
    }

    public int getItemIDNum(){
        return itemIDNum;
    }
}
