package com.mobileapp.le_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class CartItemListing extends ConstraintLayout {
    private ImageView itemImage;
    private TextView itemName;
    private TextView itemPrice;
    private TextView itemQuantity;
    private int itemIDNum;
    private TextView itemSize;

    /**
     *
     * @param context
     * @param item A ShopItem object
     */
    public CartItemListing(Context context, ShopItem item) {
        super(context);
        init(context, item);

        setItemName(item.getName());
        setItemSize(item.getSize());
        setItemPrice(item.getPrice() + ""); //TODO: Maybe reflect total price
        setItemImage(item.getImageResourceId(context));
        setItemQuantity(1);
    }

    public CartItemListing(Context context, ShopItem item, int quantity) {
        super(context);
        init(context, item);

        setItemName(item.getName());
        setItemSize(item.getSize());
        setItemPrice(String.format("$%.2f", item.getPrice())); //TODO: Maybe reflect total price
        setItemImage(item.getImageResourceId(context));
        setItemQuantity(quantity);
    }

    private void init(Context context, ShopItem item) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cart_item_listing, this, true);

        itemImage = findViewById(R.id.cartItemImage);
        itemName = findViewById(R.id.cartNameText);
        itemPrice = findViewById(R.id.cartPriceText);
        itemQuantity = findViewById(R.id.cartQuantityText);
        itemSize = findViewById(R.id.cartSizeText);
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
    public void setItemQuantity(int q) {
        itemQuantity.setText(q + "");
    }

    public void setItemIDNum(int num){
        this.itemIDNum = num;
    }
    public void setItemSize(String size) {
        this.itemSize.setText(size);

    }

    public int getItemIDNum(){
        return itemIDNum;
    }
}
