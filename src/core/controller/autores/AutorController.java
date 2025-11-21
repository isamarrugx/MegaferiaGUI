/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.autores;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.persona.author.Author;
import core.model.persona.Person;
import core.model.storage.PersonaStorage;
import java.util.ArrayList;

public class AutorController {
     public static Response addAutor(String idtxt, String firstName, String lastName) {
        PersonaStorage personaRegister = PersonaStorage.getInstance();
        ArrayList<Person> autores = personaRegister.getPersonas();
        try {
            long id;
            try {
                id = Long.parseLong(idtxt);
                if (id < 0) {
                    return new Response("ID must be greater than or equal to 0", Status.BAD_REQUEST);
                }
                if (String.valueOf(id).length() > 15) {
                    return new Response("ID must have a maximum of 15 characters.", Status.BAD_REQUEST);
                }
                if (autores != null) {
                    for (Person s : autores) {
                        if (s.getId() == id) {
                            return new Response("Id in use. Enter another id", Status.BAD_REQUEST);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                return new Response("ID must be a number", Status.BAD_REQUEST);
            }
            if (firstName.isBlank() || firstName.isEmpty()) {
                return new Response("First name must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastName.isBlank() || lastName.isEmpty()) {
                return new Response("Last name must be not empty", Status.BAD_REQUEST);
            }
            
            
            Author autorTemp = new Author(id, firstName, lastName);
            autores.add(autorTemp);

            return new Response("Author created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
