package core.controller.stand;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.Stand;
import core.model.storage.StandStorage;
import java.util.ArrayList;

public class StandController {

    public static Response addStand(String idtxt, String pricetxt) {
        StandStorage standRegister = StandStorage.getInstance();
        ArrayList<Stand> stands= standRegister.getStands();
        try {
            long id;
            double price;
            try {
                id = Long.parseLong(idtxt);
                if (id < 0) {
                    return new Response("ID must be greater than or equal to 0", Status.BAD_REQUEST);
                }
                if (String.valueOf(id).length() > 15) {
                    return new Response("ID must have a maximum of 15 characters.", Status.BAD_REQUEST);
                }
                if (stands != null) {
                    for (Stand s: stands) {
                        if(s.getId()== id){
                            return new Response("Id in use. Enter another id", Status.BAD_REQUEST);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                return new Response("ID must be a number", Status.BAD_REQUEST);
            }

            try {
                price = Double.parseDouble(pricetxt);
                if (price <= 0) {
                    return new Response("Price must be greater than to 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Price must be a number", Status.BAD_REQUEST);
            }
            
            Stand standTemp = new Stand(id, price);
            stands.add(standTemp);
            
            return new Response("Stand created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

}
