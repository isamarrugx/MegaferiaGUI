/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.model.storage;

import core.model.Stand;
import java.util.ArrayList;


public class StandStorage {
    private static StandStorage instance;
    private ArrayList <Stand> stands;
    
    private StandStorage(){
        this.stands = new ArrayList<>();
    }
    
    public static StandStorage getInstance(){
        if(instance == null){
            instance = new StandStorage();
        }
        return instance;
    }
    
    public void addStand(Stand stand) {
        this.stands.add(stand);
    }

    public ArrayList<Stand> getStands() {
        return stands;
    }

    public void setStands(ArrayList<Stand> stands) {
        this.stands = stands;
    }
    
}
