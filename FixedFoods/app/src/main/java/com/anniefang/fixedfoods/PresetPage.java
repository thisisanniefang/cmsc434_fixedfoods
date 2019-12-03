package com.anniefang.fixedfoods;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.util.SparseBooleanArray;
import java.util.ArrayList;

public class PresetPage  extends AppCompatActivity {

    private ListView presets_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preset_page);


        String s[] = new String[]{"Alkaline", "Dairy Free", "Detox", "Gluten Free", "Glycemic", "High Protein", "Keto", "Low Cholesterol", "Vegan", "Vegetarian"};
        presets_lv = findViewById(R.id.presets);
        presets_lv.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_multiple_choice,s));
        presets_lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        presets_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                ListView lv = (ListView) arg0;
                TextView tv = (TextView) lv.getChildAt(arg2);
                String s = tv.getText().toString();
                //TODO: Do something after selection?
            }
        });

    }

    public void Save(View view) {
        // TODO: Save the selections?
        SparseBooleanArray sp = presets_lv.getCheckedItemPositions();
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<sp.size();i++){
            if(sp.valueAt(i)==true){
                String s = ((TextView) presets_lv.getChildAt(sp.keyAt(i))).getText().toString();
                list.add(s);
            }
        }
        // Go to home
        Intent intent = new Intent(getApplicationContext(), PreferencesPage.class);
        intent.putExtra("data", list);
        setResult(1, intent);
        finish();
    }

}
