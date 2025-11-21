/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.stand;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.Publisher;
import core.model.Stand;
import core.model.storage.EditorialStorage;
import core.model.storage.StandStorage;
import java.util.ArrayList;

public class BuyStand {
    
    public static Response comprarStand(String standsData, String editorialesData){
        StandStorage standStorage = StandStorage.getInstance();
        EditorialStorage editorialStorage = EditorialStorage.getInstance();
        ArrayList<Stand> stands = standStorage.getStands();
        ArrayList<Publisher> editoriales = editorialStorage.getEditoriales();
        ArrayList<Stand> standsComprados = new ArrayList<>();
        ArrayList<Publisher> editorialesCompradas = new ArrayList<>();
        if (standsData.isBlank() || standsData.isEmpty()) {
            return new Response("Select a Stand", Status.BAD_REQUEST);
        }
        if (editorialesData.isBlank() || editorialesData.isEmpty()) {
            return new Response("Select a Publisher", Status.BAD_REQUEST);
        }
        String[] standInfo = standsData.split("\n");
    
        for(String standId: standInfo){
            for(Stand stand: stands){
                if(stand.getId() == Long.parseLong(standId)){
                    standsComprados.add(stand);
                }
            }
        }

        String[] editorialInfo = editorialesData.split("\n");
        
        for(String editoriall: editorialInfo){
            String nitEditorial = editoriall.split(" ")[1].replace("(", "").replace(")", "");
            for(Publisher editorial: editoriales){
                if(editorial.getNit().equals(nitEditorial)){
                    editorialesCompradas.add(editorial);
                }
            }
        }

        for(Stand stand: standsComprados){
            for(Publisher editorial: editorialesCompradas){
                stand.addPublisher(editorial);
                editorial.addStand(stand);
            }
        }
        return new Response("Stand(s) purchased successfully", Status.OK);

    }
}
