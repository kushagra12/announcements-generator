package com.example.kushagravaish.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kushagra Vaish on 25-07-2016.
 */

public class DataStore {
    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private List<CsiMember> members;

    private List<AnnouncementSlot> duties;

    private DataStore() {
        createOrShuffle();
    }

    public void createOrShuffle(){
        members = new ArrayList<CsiMember>();
        members.add(new CsiMember("Jane Doe 1",FreeSlotsStore.JD_1));
        members.add(new CsiMember("Josh Doe 2", FreeSlotsStore.JD_2));
        members.add(new CsiMember("Jane Doe 3", FreeSlotsStore.JD_3));
        members.add(new CsiMember("Joe Doe 4", FreeSlotsStore.JD_4));
        members.add(new CsiMember("Jane Doe 5", FreeSlotsStore.JD_5));
        members.add(new CsiMember("Jon Doe 6", FreeSlotsStore.JD_6));
        members.add(new CsiMember("Jon Doe 7", FreeSlotsStore.JD_7));
        members.add(new CsiMember("Jane Doe 8", FreeSlotsStore.JD_8));
        members.add(new CsiMember("Jay Doe 9", FreeSlotsStore.JD_9));
        members.add(new CsiMember("Josh Doe 10", FreeSlotsStore.JD_10));
        members.add(new CsiMember("Jane Doe 11", FreeSlotsStore.JD_11));
        members.add(new CsiMember("Jane Doe 12", FreeSlotsStore.JD_12));
        members.add(new CsiMember("Joe Doe 13", FreeSlotsStore.JD_13));
        members.add(new CsiMember("Jane Doe 14", FreeSlotsStore.JD_14));
        Collections.shuffle(members);
    }

    public DataStore(List<CsiMember> members) {
        this.members = members;
    }

    public List<CsiMember> getMembers() {
        return members;
    }

    public List<AnnouncementSlot> getDuties() {
        return duties;
    }

    public void setDuties(List<AnnouncementSlot> duties) {
        this.duties = duties;
    }

    public class CsiMember{
        String name;
        List<Integer> slots;
        int timesChosen;

        public CsiMember(String name, int[] slots) {
            this.name = name;
            this.slots = new ArrayList<Integer>();
            for (int i = 0; i < slots.length; i++) {
                this.slots.add(slots[i]);
            }
            this.timesChosen = 0;
        }
    }
}
