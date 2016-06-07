package com.jakefallin.rhsapp;

/**
 * Created by Jake/Will on 6/1/2016.
 */

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.jakefallin.rhsapp.Objects.Element;
import com.jakefallin.rhsapp.UI.AboutPage;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.r)
                .addGroup("Show us Some Love!")
                .addItem(new Element().setTitle("Version 2.4"))
                .addWebsite("app.ridgewood.k12.nj.us")
                .addPlayStore("com.jakefallin.rhsapp")
                .addGroup("Us (Click to see more)")
                .addZack("medyo")
                .addMatt("medyo")
                .addGriffen("medyo")
                .addRutvik("medyo")
                .addNico("medyo")
                .addJake("medyo")
                .addMolly("medyo")
                .addJerry("medyo")
                .addBotto("medyo")
                .addItem(getCopyRightsElement())
                .create();

        setContentView(aboutPage);
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = String.format("2016 RHS APP DEV");
        copyRightsElement.setTitle(copyrights);
        //copyRightsElement.setIcon(R.drawable.about_icon_copy_right);
        copyRightsElement.setColor(ContextCompat.getColor(this, R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AboutActivity.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
