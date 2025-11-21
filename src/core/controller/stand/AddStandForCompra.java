/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.stand;

import core.controller.utils.Response;
import core.controller.utils.Status;


public class AddStandForCompra {
    
    public static Response addStandForCompra(String standData, String standsSelectedData ){
        if (standsSelectedData.contains(standData)) {
            return new Response("Stand is in the list", Status.BAD_REQUEST);
        }else{
            return new Response("Stand added to book successfully", Status.OK);
        }
    }
}
