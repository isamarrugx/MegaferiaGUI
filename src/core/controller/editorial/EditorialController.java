/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.editorial;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.Publisher;
import core.model.persona.Manager;
import core.model.persona.Person;
import core.model.storage.EditorialStorage;
import core.model.storage.PersonaStorage;
import java.util.ArrayList;

public class EditorialController {

    public static Response createEditorial(String nit, String name, String address, String managerInfo) {
        PersonaStorage personasData = PersonaStorage.getInstance();
        EditorialStorage editorialesData = EditorialStorage.getInstance();
        ArrayList<Publisher> editoriales = editorialesData.getEditoriales();

        try {
            if (nit.isBlank() || nit.isEmpty()) {
                return new Response("NIT must be not Empty", Status.BAD_REQUEST);
            }
            if (name.isBlank() || name.isEmpty()) {
                return new Response("Name must be not Empty", Status.BAD_REQUEST);
            }
            if (address.isBlank() || address.isEmpty()) {
                return new Response("Address must be not Empty", Status.BAD_REQUEST);
            }
            if (managerInfo.equalsIgnoreCase("Seleccione uno...")) {
                return new Response("Select One Manager", Status.BAD_REQUEST);
            }
            if (!nit.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d$")) {
                return new Response("NIT must be in this format XXX.XXX.XXX-X", Status.BAD_REQUEST);
            }
            String[] managerData = managerInfo.split(" - ");

            long managerId = Long.parseLong(managerData[0]);

            Manager manager = null;
            for (Person manag : personasData.getPersonas()) {
                if (manag instanceof Manager) {
                    if (manag.getId() == managerId) {
                        manager = (Manager) manag;
                    }
                }
            }
            Publisher editorialTemp = new Publisher(nit, name, address, manager);
            editoriales.add(editorialTemp);
            return new Response("Publisher created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
