package mehdi.sakout.aboutpage.sample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Element adsElement = new Element();
        //adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.dummy_image)
                .addGroup("Show us Some Love!")
                .addItem(new Element().setTitle("Version 2.4"))
                //.addItem(adsElement)

                .addWebsite("app.ridgewood.k12.nj.us")

                .addPlayStore("com.ideashower.readitlater.pro")

                .addGroup("Who we are")
                .addMrsRichardson("medyo")
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
        copyRightsElement.setColor(ContextCompat.getColor(this, mehdi.sakout.aboutpage.R.color.about_item_icon_color));
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, copyrights, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRightsElement;
    }
}
