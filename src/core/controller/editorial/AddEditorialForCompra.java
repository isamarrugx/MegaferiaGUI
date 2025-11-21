/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.editorial;

import core.controller.utils.Response;
import core.controller.utils.Status;

public class AddEditorialForCompra {
    
    public static Response addEditorialForCompra(String editorialData, String editorialesSelectedData) {
        if (editorialesSelectedData.contains(editorialData)) {
            return new Response("Editorial is in the list", Status.BAD_REQUEST);
        } else {
            return new Response("Editorial added to book successfully", Status.OK);
        }
    }
}
