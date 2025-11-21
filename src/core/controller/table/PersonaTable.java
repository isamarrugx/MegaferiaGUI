/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.table;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.persona.Manager;
import core.model.persona.Person;
import core.model.persona.author.Author;
import core.model.persona.author.AuthorBookService;
import core.model.persona.narrator.Narrator;
import core.model.persona.utils.FullName;
import core.model.storage.PersonaStorage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.DefaultTableModel;

public class PersonaTable {
    
    public static Response updatePersonaTable(DefaultTableModel model) {
        try {
            PersonaStorage personaStorage = PersonaStorage.getInstance();
            ArrayList<Person> personas = personaStorage.getPersonas();
            if (personas == null || personas.isEmpty()) {
                return new Response("No personas available to display", Status.NO_CONTENT);
                
            }
            personas.sort(Comparator.comparing(Person::getId));
            model.setRowCount(0);
            for (Person persona : personas) {
                Object[] rowData = {
                        persona.getId(),
                        FullName.unitVariables(persona.getFirstname(), persona.getLastname()),
                        persona instanceof Manager ? "Gerente" : persona instanceof Author ? "Autor" : "Narrador",
                        persona instanceof Manager ? ((((Manager) persona).getPublisher())== null ? "NO": ((Manager) persona).getPublisher().getName()) : "-",
                        persona instanceof Manager ? "0"
                                : persona instanceof Author ? AuthorBookService.getAuthorBookCount((Author) persona)
                                        : ((Narrator) persona).getAudioBookCount()
                };
                model.addRow(rowData);
            }
            return new Response("Persona table updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error" + e, Status.INTERNAL_SERVER_ERROR);
        }
    }
}
