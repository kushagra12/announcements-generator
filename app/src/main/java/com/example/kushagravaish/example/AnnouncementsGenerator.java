package com.example.kushagravaish.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kushagra Vaish on 25-07-2016.
 */

public class AnnouncementsGenerator {

    private List<DataStore.CsiMember> members;

    private boolean isAvailable(DataStore.CsiMember member, int slot){
        if(member.timesChosen < 2)
            for (int i: member.slots){
                if(i == slot)
                    return true;
            }
        return false;
    }

    public List<String> makeSchedule(int[] day){
        List<String> membersList = new ArrayList<String>();
        if(makeSchedule(day, 0, membersList))
            return membersList;
        else
            return null;
    }

    public boolean makeSchedule(int[] day, int i, List<String> list){
        if(i >= day.length)
            return true;
        int slot = day[i];
        for(int m = 0; m < members.size(); m++){
            if(isAvailable(members.get(m),slot)){
                list.add(i, members.get(m).name);
                for(int k = 0; k < members.get(i).slots.size(); k++){
                    if(members.get(m).slots.get(k) == slot){
                        members.get(m).slots.remove(k);
                        break;
                    }
                }
                members.get(m).timesChosen++;
                if(makeSchedule(day,i+1,list))
                    return true;
                list.remove(i);
                members.get(m).slots.add(slot);
                members.get(m).timesChosen--;
            }
        }
        return false;
    }

    AnnouncementsGenerator(List<DataStore.CsiMember> members){
        this.members = members;
    }
}
