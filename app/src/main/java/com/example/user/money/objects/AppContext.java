package com.example.user.money.objects;

import android.app.Application;

import com.example.user.money.database.DbAdapter;

import java.io.File;
import java.io.IOException;

/**
 * Created by User on 02.01.2017.
 */

public class AppContext extends Application{
    private static final String ICONS_FOLDER = "icons";
    private File iconFolder;

    public static final int IMAGE_WIDTH_THMB = 96;
    public static final int IMAGE_HEIGHT_THMB = 96;

    private static DbAdapter dbAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
    dbAdapter = new DbAdapter(this);
    }

    public static DbAdapter getDbAdapter(){
        return dbAdapter;
    }

    public String getIconsFolder(){
        if(iconFolder == null){
            iconFolder = new File(getApplicationInfo().dataDir+"/"+ICONS_FOLDER);
        }
        if(!iconFolder.exists()){
            try{
                if(!iconFolder.createNewFile()){
                    throw new Exception("cant create folder for icons!");
                }
            }catch (IOException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return iconFolder.getAbsolutePath();
    }
}
