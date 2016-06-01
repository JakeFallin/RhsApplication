package mehdi.sakout.aboutpage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by medyo on 3/25/16.
 * modified by Will Botto 05/17/16
 */
public class AboutPage {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final View mView;
    private String mDescription;
    private int mImage = 0;
    private boolean mIsRTL = false;
    private Typeface mCustomFont;
    private Dialog dialog = null;


    public AboutPage(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mView = mInflater.inflate(R.layout.about_page, null);

    }

    public AboutPage setCustomFont(String path) {
        mCustomFont = Typeface.createFromAsset(mContext.getAssets(), path);
        return this;
    }



    /*
        Add Facebook Element
     */
    /*
        Add Zack Element
     */
    public AboutPage addZack(String id) {
        Element ZackElement = new Element();
        ZackElement.setTitle("Zack Rubenstein");
        ZackElement.setStaffIcon(R.drawable.zach);
        ZackElement.setJob("2016");
        //ZackElement.setColor(ContextCompat.getColor(mContext, R.color.about_youtube_color));
        ZackElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        /*intent.setData(Uri.parse(String.format("http://youtube.com/channel/%s", id)));

        if (AboutPageUtils.isAppInstalled(mContext, "com.google.android.youtube")) {
            intent.setPackage("com.google.android.youtube");
        }*/

        ZackElement.setIntent(intent);
        addItem(ZackElement);

        return this;
    }

    /*
        Add Jake Element
     */
    public AboutPage addJake(String id) {
        Element jakeElement = new Element();
        jakeElement.setTitle("Jake Fallin");
        jakeElement.setStaffIcon(R.drawable.jake);
        //jakeElement.setColor(ContextCompat.getColor(mContext, R.color.about_instagram_color));
        jakeElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        /*intent.setData(Uri.parse("http://instagram.com/_u/" + id));

        if (AboutPageUtils.isAppInstalled(mContext, "com.instagram.android")) {
            intent.setPackage("com.instagram.android");
        }*/

        jakeElement.setIntent(intent);
        addItem(jakeElement);

        return this;
    }


    /*
        Add Play store Element
     */
    public AboutPage addPlayStore(String id) {
        Element playStoreElement = new Element();
        playStoreElement.setTitle(mContext.getString(R.string.about_play_store));
        playStoreElement.setIcon(R.drawable.about_icon_google_play);
        playStoreElement.setValue(id);

        Uri uri = Uri.parse("market://details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        playStoreElement.setIntent(goToMarket);

        addItem(playStoreElement);
        return this;
    }

    /*
        Add Youtube Element
     */
    public AboutPage addYoutube(String id) {
        Element youtubeElement = new Element();
        youtubeElement.setTitle(mContext.getString(R.string.about_youtube));

        youtubeElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(String.format("http://youtube.com/channel/%s", id)));

        if (AboutPageUtils.isAppInstalled(mContext, "com.google.android.youtube")) {
            intent.setPackage("com.google.android.youtube");
        }

        youtubeElement.setIntent(intent);
        addItem(youtubeElement);

        return this;
    }

    public AboutPage addMolly(String id) {
        Element mollyElement = new Element();
        mollyElement.setTitle("Molly Sokota ");
        mollyElement.setJob("Android Developer");
        //mollyElement.setTag("Android Developer");
         mollyElement.setStaffIcon(R.drawable.molly);
        //mollyElement.setColor(ContextCompat.getColor(mContext, R.color.about_twitter_color));
        mollyElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        /*if (AboutPageUtils.isAppInstalled(mContext, "com.twitter.android")) {
            intent.setPackage("com.twitter.android");
            intent.setData(Uri.parse(String.format("twitter://user?screen_name=%s", id)));
        } else {
            intent.setData(Uri.parse(String.format("http://twitter.com/intent/user?screen_name=%s", id)));
        }*/

        mollyElement.setIntent(intent);
        addItem(mollyElement);
        return this;
    }

    public AboutPage addJerry(String id) {
        Element jerryElement = new Element();
        jerryElement.setTitle("Jerry Vogel");
        jerryElement.setStaffIcon(R.drawable.jerry);
        //jerryElement.setColor(ContextCompat.getColor(mContext, R.color.about_play_store_color));
        jerryElement.setValue(id);

        /*Uri uri = Uri.parse("market://details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        jerryElement.setIntent(goToMarket);*/

        addItem(jerryElement);
        return this;
    }

    /*
        Add Rutvik Element
    */
    public AboutPage addRutvik(String id) {
        Element rutvikElement = new Element();
        rutvikElement.setTitle("Rutvik Manohar");
        rutvikElement.setStaffIcon(R.drawable.rutvik);

        addItem(rutvikElement);

        return this;
        }

    public AboutPage addNico(String id) {
        Element nicoElement = new Element();
        nicoElement.setTitle("Nico Cobb");
        nicoElement.setStaffIcon(R.drawable.nico);
        nicoElement.setValue(id);

       /* Uri uri = Uri.parse("market://details?id=" + id);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        nicoElement.setIntent(goToMarket);*/

        addItem(nicoElement);
        return this;
    }
    public AboutPage addMatt(String id) {
        Element mattElement = new Element();
        mattElement.setTitle("Matt Muccio");
        mattElement.setStaffIcon(R.drawable.matt);
        //mattElement.setColor(ContextCompat.getColor(mContext, R.color.about_github_color));
        mattElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        //intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        mattElement.setIntent(intent);
        addItem(mattElement);

        return this;
    }
    public AboutPage addMrsRichardson(String id) {
        Element richardsonElement = new Element();
        richardsonElement.setTitle("Mrs.Richardson Project Manager");
        richardsonElement.setStaffIcon(R.drawable.about_icon_github);
        //richardsonElement.setColor(ContextCompat.getColor(mContext, R.color.about_github_color));
        richardsonElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        richardsonElement.setIntent(intent);

        addItem(richardsonElement);
        return this;
    }

    public AboutPage addBotto(String id) {
        Element bottoElement = new Element();
        bottoElement.setTitle("Will Botto");
        bottoElement.setStaffIcon(R.drawable.about_icon_github);
        //bottoElement.setColor(ContextCompat.getColor(mContext, R.color.about_github_color));
        bottoElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        //intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        bottoElement.setIntent(intent);
        addItem(bottoElement);

        return this;
    }
    /*
    Add Griffen Element
    */
    public AboutPage addGriffen(String id) {
        Element girfElement = new Element();
        girfElement.setTitle("Griffen Spincken");
        girfElement.setStaffIcon(R.drawable.griffen);
        //girfElement.setColor(ContextCompat.getColor(mContext, R.color.about_github_color));
        girfElement.setValue(id);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        //intent.setData(Uri.parse(String.format("https://github.com/%s", id)));

        girfElement.setIntent(intent);
        addItem(girfElement);

        return this;
    }

    /*
        Add Website Element
    */
    public AboutPage addWebsite(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }
        Element websiteElement = new Element();
        websiteElement.setTitle(mContext.getString(R.string.about_website));
        websiteElement.setIcon(R.drawable.about_icon_link);
        //websiteElement.setColor(ContextCompat.getColor(mContext, R.color.about_item_icon_color));
        websiteElement.setValue(url);

        Uri uri = Uri.parse(url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, uri);

        websiteElement.setIntent(browserIntent);
        addItem(websiteElement);

        return this;
    }

    public AboutPage addItem(Element element) {
        LinearLayout wrapper = (LinearLayout) mView.findViewById(R.id.about_providers);
        wrapper.addView(createItem(element));
        wrapper.addView(getSeparator(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.about_separator_height)));
        return this;
    }

    public AboutPage setImage(int resource) {
        this.mImage = resource;
        return this;

    }

    public AboutPage addGroup(String name) {

        TextView textView = new TextView(mContext);
        textView.setText(name);
        TextViewCompat.setTextAppearance(textView, R.style.about_groupTextAppearance);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);



        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_group_text_padding);
        textView.setPadding(padding, padding, padding, padding);


        if (mIsRTL) {
            textView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        } else {
            textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            textParams.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
        }
        textView.setLayoutParams(textParams);

        ((LinearLayout) mView.findViewById(R.id.about_providers)).addView(textView);
        LinearLayout wrapper = (LinearLayout) mView.findViewById(R.id.about_providers);
        wrapper.addView(getSeparator(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mContext.getResources().getDimensionPixelSize(R.dimen.about_separator_height)));
        return this;
    }

    public AboutPage isRTL(boolean value) {
        this.mIsRTL = value;
        return this;
    }

    public AboutPage setDescription(String description) {
        this.mDescription = description;
        return this;
    }

    public View create() {
        TextView description = (TextView) mView.findViewById(R.id.description);
        ImageView image = (ImageView) mView.findViewById(R.id.image);
        if (mImage > 0) {
            image.setImageResource(mImage);
        }

        if (!TextUtils.isEmpty(mDescription)) {
            description.setText(mDescription);
        }

        description.setGravity(Gravity.CENTER);

        if (mCustomFont != null) {
            description.setTypeface(mCustomFont);
        }

        return mView;
    }

    private View createItem(final Element element) {
        LinearLayout wrapper = new LinearLayout(mContext);
        wrapper.setOrientation(LinearLayout.HORIZONTAL);
        wrapper.setClickable(true);

        if (element.getOnClickListener() != null) {
            wrapper.setOnClickListener(element.getOnClickListener());
        } else if (element.getIntent() != null) {
            wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {

                        mContext.startActivity(element.getIntent());
                    } catch (Exception e) {
                    }
                }
            });
        } else {
            wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        showDialog();
                    } catch (Exception e) {
                    }
                }
            });
        }


        TypedValue outValue = new TypedValue();
        mContext.getTheme().resolveAttribute(R.attr.selectableItemBackground, outValue, true);
        wrapper.setBackgroundResource(outValue.resourceId);

        int padding = mContext.getResources().getDimensionPixelSize(R.dimen.about_text_padding);
        wrapper.setPadding(padding, padding, padding, padding);
        LinearLayout.LayoutParams wrapperParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wrapper.setLayoutParams(wrapperParams);

        /*TextView jobView = new TextView(mContext);
        jobView.setText("job");
        TextViewCompat.setTextAppearance(jobView, R.style.about_groupTextAppearance);
        LinearLayout.LayoutParams sTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);*/

        TextView textView = new TextView(mContext);
        TextViewCompat.setTextAppearance(textView, R.style.about_elementTextAppearance);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textParams);
        if (mCustomFont != null) {
            textView.setTypeface(mCustomFont);
        }

        ImageView iconView = null;

        if (element.getIcon() != null) {
            iconView = new ImageView(mContext);
            int size = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_size);
            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(size, size);
            iconView.setLayoutParams(iconParams);
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            iconView.setPadding(iconPadding, 0, iconPadding, 0);

            if (Build.VERSION.SDK_INT < 21) {
                //Drawable drawable = VectorDrawableCompat.create(iconView.getResources(), element.getIcon(), iconView.getContext().getTheme());
            } else {
                iconView.setImageResource(element.getIcon());
            }

            Drawable wrappedDrawable = DrawableCompat.wrap(iconView.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();
            if (element.getAutoIconColor()){
                if (element.getColor() != null) {
                    DrawableCompat.setTint(wrappedDrawable, element.getColor());
                } else {
                    DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, R.color.about_item_icon_color));
                }
            }

        } else {
            int iconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_icon_padding);
            textView.setPadding(iconPadding, iconPadding, iconPadding, iconPadding);

        }
        if (element.getStaffIcon() != null) {
            iconView = new ImageView(mContext);
            int staffSize = mContext.getResources().getDimensionPixelSize(R.dimen.about_staff_size);
            LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(staffSize, staffSize);
            iconView.setLayoutParams(iconParams);
            int staffIconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_staff_padding);
            iconView.setPadding(staffIconPadding, 0, staffIconPadding, 0);

            if (Build.VERSION.SDK_INT < 21) {
                Drawable drawable = VectorDrawableCompat.create(iconView.getResources(), element.getStaffIcon(), iconView.getContext().getTheme());
                iconView.setImageDrawable(drawable);
            } else {
                iconView.setImageResource(element.getStaffIcon());
            }

            Drawable wrappedDrawable = DrawableCompat.wrap(iconView.getDrawable());
            wrappedDrawable = wrappedDrawable.mutate();
            if (element.getAutoIconColor()){
                if (element.getColor() != null) {
                    DrawableCompat.setTint(wrappedDrawable, element.getColor());
                } else {
                    DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(mContext, R.color.about_item_icon_color));
                }
            }

        } else {
            int staffIconPadding = mContext.getResources().getDimensionPixelSize(R.dimen.about_staff_padding);
            textView.setPadding(staffIconPadding, staffIconPadding, staffIconPadding, staffIconPadding);

        }


        textView.setText(element.getTitle());



        if (mIsRTL) {

            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.END;

            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            wrapper.addView(textView);
            if (element.getIcon() != null) {
                wrapper.addView(iconView);
            }
            if (element.getStaffIcon() != null) {
                wrapper.addView(iconView);
            }
        } else {
            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.START;
            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            textParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            if (element.getIcon() != null) {
                wrapper.addView(iconView);
            }
            if (element.getStaffIcon() != null) {
                wrapper.addView(iconView);
            }

            wrapper.addView(textView);
        }




        /*jobView.setText(element.getJob());



        if (mIsRTL) {

            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.END;

            wrapper.setGravity(gravity | Gravity.END);
            //noinspection ResourceType
            sTextParams.gravity = gravity | Gravity.END;
            wrapper.addView(textView);
            if (element.getIcon() != null) {
                wrapper.addView(iconView);
            }
            if (element.getStaffIcon() != null) {
                wrapper.addView(iconView);
            }
        } else {
            final int gravity = element.getGravity() != null ? element.getGravity() : Gravity.START;
            wrapper.setGravity(gravity | Gravity.CENTER_VERTICAL);
            //noinspection ResourceType
            sTextParams.gravity = gravity | Gravity.CENTER_VERTICAL;
            if (element.getIcon() != null) {
                wrapper.addView(iconView);
            }
            if (element.getStaffIcon() != null) {
                wrapper.addView(iconView);
            }

            wrapper.addView(textView);
        }*/

        return wrapper;
    }

    private void showDialog()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.d, null);
        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final AlertDialog dialog = alertDialogBuilder.create();
        Button cancel = (Button) view.findViewById(R.id.button_cancel);



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private View getSeparator() {
        return mInflater.inflate(R.layout.about_page_separator, null);
    }
}