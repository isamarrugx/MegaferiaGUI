/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.autores;

import core.controller.utils.Response;
import core.controller.utils.Status;


public class AutorToLibroController {
    
    public static Response addAutorToLibro(String autorData, String autoresSelectedData){
        if (autoresSelectedData.contains(autorData)) {
            return new Response("Autor is in the list", Status.BAD_REQUEST);
        }else{
            return new Response("Autor added to book successfully", Status.OK);
        }
    }
    
}
