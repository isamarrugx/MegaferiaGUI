/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.table;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.Publisher;
import core.model.Stand;
import core.model.persona.author.Author;
import core.model.persona.Manager;
import core.model.persona.narrator.Narrator;
import core.model.persona.Person;
import core.model.persona.utils.FullName;
import core.model.storage.EditorialStorage;
import core.model.storage.PersonaStorage;
import core.model.storage.StandStorage;
import core.model.storage.LibroStorage;
import core.model.libro.audioBook.AudioBook;
import core.model.libro.Book;
import core.model.libro.DigitalBook;
import core.model.libro.PrintedBook;
import core.model.persona.author.AuthorBookService;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.Comparator;

public class EditorialTable {

    public static Response updateEditorialTable(DefaultTableModel model) {
        try {
            EditorialStorage editorialStorage = EditorialStorage.getInstance();
            ArrayList<Publisher> editoriales = editorialStorage.getEditoriales();

            if (editoriales == null || editoriales.isEmpty()) {
                return new Response("No editorials available to display", Status.NO_CONTENT, editoriales.clone());
            }

            editoriales.sort(Comparator.comparing(Publisher::getNit));
            model.setRowCount(0);
            for (Publisher editorial : editoriales) {
                Object[] rowData = {
                        editorial.getNit(),
                        editorial.getName(),
                        editorial.getAddress(),
                        FullName.unitVariables(editorial.getManager().getFirstname(),
                                editorial.getManager().getLastname()),
                        editorial.getStandQuantity()
                };
                model.addRow(rowData);
            }
            return new Response("Editorial table updated successfully", Status.OK, editoriales.clone());
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

    

    

    

    

    

    
}
