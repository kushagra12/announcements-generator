package com.example.kushagravaish.example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SlotFragment.OnListFragmentInteractionListener{

    private int pos;
    private SlotFragment slotFragment;

    private int[] getDay(int pos){
        switch(pos){
            case 0:
                return FreeSlotsStore.MONDAY;
            case 1:
                return FreeSlotsStore.TUESDAY;
            case 2:
                return FreeSlotsStore.WEDNESDAY;
            case 3:
                return FreeSlotsStore.THURSDAY;
            case 4:
                return FreeSlotsStore.FRIDAY;
            default:
                return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pos = getIntent().getIntExtra("CSI.TIMINGS.TOO.LAZY",0);
        createDuties();

        slotFragment = SlotFragment.newInstance(1);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,slotFragment).commit();
        DataStore.getInstance().createOrShuffle();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDuties();
                getSupportFragmentManager().beginTransaction().remove(slotFragment).commit();

                slotFragment = SlotFragment.newInstance(1);
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,slotFragment).commit();
                DataStore.getInstance().createOrShuffle();
            }
        });
    }

    private void createDuties(){
        DataStore.getInstance().createOrShuffle();
        AnnouncementsGenerator generator = new AnnouncementsGenerator(DataStore.getInstance().getMembers());

        List<String> firstGuys = generator.makeSchedule(getDay(pos));
        List<String> secondGuys = generator.makeSchedule(getDay(pos));

        while(secondGuys == null) {
            DataStore.getInstance().createOrShuffle();
            firstGuys = generator.makeSchedule(getDay(pos));
            secondGuys = generator.makeSchedule(getDay(pos));
        }
        List<AnnouncementSlot> duties = new ArrayList<AnnouncementSlot>();
        int k = 0;
        for (int i = 0; i < firstGuys.size(); i++) {
            if(pos == 2 && i == 3)
                k = 1;
            duties.add(new AnnouncementSlot(secondGuys.get(i),FreeSlotsStore.Timings[i + k],firstGuys.get(i)));
        }
        DataStore.getInstance().setDuties(duties);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(AnnouncementSlot item) {

    }
}
