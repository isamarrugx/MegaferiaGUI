/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.table.consultasAdd;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.persona.Person;
import core.model.persona.author.Author;
import core.model.persona.author.AuthorBookService;
import core.model.persona.utils.FullName;
import core.model.storage.PersonaStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class AutoresConAddTable {
    
    public static Response updateAutoresConAdd(DefaultTableModel model) {
        try {
            PersonaStorage personaStorage = PersonaStorage.getInstance();
            ArrayList<Person> personas = personaStorage.getPersonas();
            if (personas == null || personas.isEmpty()) {
                return new Response("No authors available to display", Status.NO_CONTENT);
            }
            ArrayList<Author> authorsMax = new ArrayList<>();
            ArrayList<Author> autores = new ArrayList<>();
            for(Person p: personas){
                if (p instanceof Author) {
                    autores.add((Author) p);
                }
            }
            int maxPublishers = -1;
            for (Author author : autores) {
                if (AuthorBookService.countDistinctPublishers(author)> maxPublishers) {
                    maxPublishers = AuthorBookService.countDistinctPublishers(author);
                    authorsMax.clear();
                    authorsMax.add(author);
                } else if (AuthorBookService.countDistinctPublishers(author) == maxPublishers) {
                    authorsMax.add(author);
                }
            }

            model.setRowCount(0);

            for (Author author : authorsMax) {
                model.addRow(new Object[] { author.getId(), FullName.unitVariables(author.getFirstname(), author.getLastname()), maxPublishers });
            }
            return new Response("Authors table updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
