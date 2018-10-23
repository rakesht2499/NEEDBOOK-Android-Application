package com.example.admin.needbookapplication;

import android.app.Application;
import android.database.SQLException;
import android.widget.Toast;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static ArrayList<Subjects> subjects,subBio,subChem,subPhy;
    @Override
    public void onCreate() {
        super.onCreate();

        subjects = new ArrayList<Subjects>();
        subjects.add(new Subjects("Biology"));
        subjects.add(new Subjects("Physics"));
        subjects.add(new Subjects("Chemistry"));
        subjects.add(new Subjects("Mathematics"));

        subBio = new ArrayList<Subjects>();
        subBio.add(new Subjects("The Living World"));
        subBio.add(new Subjects("Plant Kingdom"));
        subBio.add(new Subjects("Animal Kingdom"));
        subBio.add(new Subjects("Biological Classification"));

        subChem = new ArrayList<Subjects>();
        subChem.add(new Subjects("Matter"));
        subChem.add(new Subjects("Lewis"));

        subPhy = new ArrayList<Subjects>();
        subPhy.add(new Subjects("Kinematics"));
        subPhy.add(new Subjects("Laws Of Motion"));


        ConnectToDB db = new ConnectToDB(this);
        try{
            db.open();
            db.createEntry("The Living World","Levels of Biodiversity",0);
            db.createEntry("The Living World","Taxonomic Categories",0);
            db.createEntry("Plant Kingdom","Bryophytes",0);
            db.createEntry("Plant Kingdom","Pteridophytes",0);
            db.createEntry("Plant Kingdom","Angiosperms",0);
            db.createEntry("Plant Kingdom","Algae",0);
            db.createEntry("Plant Kingdom","Gymnosperms",0);
            db.createEntry("Animal Kingdom","Vertebra",0);
            db.createEntry("Animal Kingdom","Levels of organization",0);
            db.createEntry("Animal Kingdom","Symmetry",0);
            db.createEntry("Animal Kingdom","Classification of Animals",0);
            db.createEntry("Animal Kingdom","Phylum",0);
            db.createEntry("Biological Classification","Bio1",0);
            db.createEntry("Biological Classification","Bio2",0);
            db.createEntry("Matter","Nature Of Matter",2);
            db.createEntry("Matter","Properties of Matter",2);
            db.createEntry("Lewis","Lewis Structure",2);
            db.createEntry("Lewis","Lewis Symbol",2);
            db.createEntry("Kinematics","Displacement & time",1);
            db.createEntry("Kinematics","Uniform Circular Motion",1);
            db.createEntry("Laws Of Motion","Newtons Third Law",1);
            db.createEntry("Laws Of Motion","Laws of motion",1);
            db.close();
        }
        catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
