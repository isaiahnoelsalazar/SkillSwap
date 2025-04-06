package com.salazarisaiahnoel.skillswap;

import static com.salazarisaiahnoel.skillswap.fragments.HomeFragment.bnv;
import static com.salazarisaiahnoel.skillswap.fragments.SkillPageFragment.fromAllSkills;
import static com.salazarisaiahnoel.skillswap.fragments.TabFavoritesFragment.refreshData;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.message_items;
import static com.salazarisaiahnoel.skillswap.others.GlobalThings.skill_items;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.saiaaaaaaa.cod.EasySQL;
import com.salazarisaiahnoel.skillswap.fragments.InstructorDetailFragment;
import com.salazarisaiahnoel.skillswap.fragments.SkillListFragment;
import com.salazarisaiahnoel.skillswap.fragments.SkillPageFragment;
import com.salazarisaiahnoel.skillswap.fragments.SplashScreenFragment;
import com.salazarisaiahnoel.skillswap.fragments.StaticSplashScreenFragment;
import com.salazarisaiahnoel.skillswap.fragments.TabHomeFragment;
import com.salazarisaiahnoel.skillswap.others.GlobalThings;
import com.salazarisaiahnoel.skillswap.others.MessageItem;
import com.salazarisaiahnoel.skillswap.others.SkillItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "skillswapdb";
    public static final String TABLE_NAME = "skillswaptable";
    public static final String LIKES_TABLE_NAME = "skillswaplikestable";
    public static final String MESSAGE_TABLE_NAME = "skillswapmessagestable";
    public static final String[] COLUMNS = {"title:TEXT", "level:TEXT", "description:TEXT", "instructor:TEXT", "category:TEXT"};
    public static final String[] LIKES_COLUMNS = {"title:TEXT"};
    public static final String[] MESSAGE_COLUMNS = {"instructor:TEXT", "message_content:TEXT"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EasySQL easySQL = new EasySQL(this);
        GlobalThings.easySQL = new EasySQL(this);
        if (!easySQL.doesTableExist(DATABASE_NAME, TABLE_NAME)){
            easySQL.createTable(DATABASE_NAME, TABLE_NAME, COLUMNS);
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Intro to Python", "level:Beginner", "description:Learn how to program with the Python programming language.", "instructor:John Doe", "category:Technology"});
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Intro to Java", "level:Beginner", "description:Learn how to program with the Java programming language.", "instructor:John Doe", "category:Technology"});
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Advanced Java Programming", "level:Advanced", "description:Continue learning how to program with Java.", "instructor:John Doe", "category:Technology"});
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Intro to HTML", "level:Beginner", "description:Learn how to create websites using HTML.", "instructor:John Doe", "category:Technology"});
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Basic Yoga Poses", "level:Beginner", "description:Relax with us and remove your stress.", "instructor:Miss Baker", "category:Fitness"});
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Beginner Strength Training Exercises", "level:Beginner", "description:Start your journey to becoming stronger!", "instructor:Mister Strong", "category:Fitness"});
            easySQL.insertToTable(DATABASE_NAME, TABLE_NAME, new String[]{"title:Intermediate Strength Training Exercises", "level:Intermediate", "description:Power up your strength training journey with Mister Strong!", "instructor:Mister Strong", "category:Fitness"});
        }
        if (!easySQL.doesTableExist(DATABASE_NAME, LIKES_TABLE_NAME)){
            easySQL.createTable(DATABASE_NAME, LIKES_TABLE_NAME, LIKES_COLUMNS);
        }
        if (!easySQL.doesTableExist(DATABASE_NAME, MESSAGE_TABLE_NAME)){
            easySQL.createTable(DATABASE_NAME, MESSAGE_TABLE_NAME, MESSAGE_COLUMNS);
        }
        List<String> messagetemps = new ArrayList<>();
        String messagetemp = "";
        int messagecounter = 0;
        for (int a = 0; a < easySQL.getTableValues(DATABASE_NAME, MESSAGE_TABLE_NAME).size(); a++){
            String t = easySQL.getTableValues(DATABASE_NAME, MESSAGE_TABLE_NAME).get(a).split(":")[1].trim();
            if (messagecounter != MESSAGE_COLUMNS.length - 1){
                messagetemp += t.substring(1, t.length() - 1) + "<topicseparator>";
                messagecounter++;
            } else {
                messagetemp += t.substring(1, t.length() - 1);
                messagetemps.add(messagetemp);
                messagetemp = "";
                messagecounter = 0;
            }
        }
        List<String> temps = new ArrayList<>();
        String temp = "";
        int counter = 0;
        for (int a = 0; a < easySQL.getTableValues(DATABASE_NAME, TABLE_NAME).size(); a++){
            String t = easySQL.getTableValues(DATABASE_NAME, TABLE_NAME).get(a).split(":")[1].trim();
            if (counter != COLUMNS.length - 1){
                temp += t.substring(1, t.length() - 1) + "<topicseparator>";
                counter++;
            } else {
                temp += t.substring(1, t.length() - 1);
                temps.add(temp);
                temp = "";
                counter = 0;
            }
        }
        List<Integer> simple_images = new ArrayList<>();
        List<Integer> images = new ArrayList<>();
        List<Integer> category_images = new ArrayList<>();
        simple_images.add(R.drawable.twotone_monitor_24);
        simple_images.add(R.drawable.twotone_monitor_24);
        simple_images.add(R.drawable.twotone_monitor_24);
        simple_images.add(R.drawable.twotone_monitor_24);
        simple_images.add(R.drawable.round_fitness_center_24);
        simple_images.add(R.drawable.round_fitness_center_24);
        simple_images.add(R.drawable.round_fitness_center_24);
        images.add(R.drawable.image_python);
        images.add(R.drawable.image_java);
        images.add(R.drawable.image_advancedjava);
        images.add(R.drawable.image_html);
        images.add(R.drawable.image_yoga);
        images.add(R.drawable.image_beginnerstrength);
        images.add(R.drawable.image_intermediatestrength);
        category_images.add(R.drawable.image_technology);
        category_images.add(R.drawable.image_technology);
        category_images.add(R.drawable.image_technology);
        category_images.add(R.drawable.image_technology);
        category_images.add(R.drawable.image_fitness);
        category_images.add(R.drawable.image_fitness);
        category_images.add(R.drawable.image_fitness);
        skill_items.clear();
        for (int a = 0; a < temps.size(); a++){
            skill_items.add(new SkillItem(temps.get(a).split("<topicseparator>")[0], temps.get(a).split("<topicseparator>")[1], temps.get(a).split("<topicseparator>")[2], temps.get(a).split("<topicseparator>")[3], temps.get(a).split("<topicseparator>")[4], simple_images.get(a), images.get(a), category_images.get(a)));
        }
        message_items.clear();
        for (int a = 0; a < messagetemps.size(); a++){
            message_items.add(new MessageItem(messagetemps.get(a).split("<topicseparator>")[0], messagetemps.get(a).split("<topicseparator>")[1]));
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, new StaticSplashScreenFragment()).commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, new SplashScreenFragment()).commit();
            }
        }, 750);
    }

    @Override
    public void onBackPressed() {
        if ((getSupportFragmentManager().findFragmentById(R.id.mainFrame) instanceof SkillPageFragment)){
            try {
                refreshData();
            } catch (Exception ignored){
            }
            if (!fromAllSkills){
                getWindow().setStatusBarColor(getColor(R.color.white));
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        if (getSupportFragmentManager().findFragmentById(R.id.mainFrame) instanceof SkillListFragment ||
                getSupportFragmentManager().findFragmentById(R.id.mainFrame) instanceof InstructorDetailFragment){
            getWindow().setStatusBarColor(getColor(R.color.blue));
            int flags = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            getWindow().getDecorView().setSystemUiVisibility(flags);
        }
        if (!(getSupportFragmentManager().findFragmentById(R.id.mainFrame) instanceof SplashScreenFragment)){
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.mainFrame)).commit();
        } else {
            if (!(getSupportFragmentManager().findFragmentById(R.id.homeFrame) instanceof TabHomeFragment)){
                bnv.setSelectedItemId(R.id.tab_home);
            } else {
                super.onBackPressed();
            }
        }
    }
}