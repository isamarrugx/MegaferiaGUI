/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.table.consultasAdd;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.libro.Book;
import core.model.libro.DigitalBook;
import core.model.libro.PrintedBook;
import core.model.libro.audioBook.AudioBook;
import core.model.persona.Person;
import core.model.persona.author.Author;
import core.model.persona.utils.FullName;
import core.model.storage.LibroStorage;
import core.model.storage.PersonaStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class LibroAutorTable {
    
    public static Response updateLibroAutorConAdd(DefaultTableModel model, String authorData) {
        try {
            LibroStorage libroStorage = LibroStorage.getInstance();
            ArrayList<Book> libros = libroStorage.getLibros();
            if (libros == null || libros.isEmpty()) {
                return new Response("No books available to display for the given author", Status.NO_CONTENT);
            }
            double authorId;
            if (authorData.equalsIgnoreCase("Seleccione uno...")) {
                return new Response("You must select an author", Status.BAD_REQUEST);
            }
            try {
                authorId = Double.parseDouble(authorData.split(" - ")[0]);
            } catch (NumberFormatException e) {
                return new Response("Invalid author ID format", Status.BAD_REQUEST);
            }
            Author author = null;
            PersonaStorage personaStorage = PersonaStorage.getInstance();
            for (Person person : personaStorage.getPersonas()) {
                if (person instanceof Author a && a.getId() == authorId) {
                    author = a;
                    break;
                }
            }
            if (author == null) {
                return new Response("Author not found", Status.NOT_FOUND);
            }
            for (Book book : author.getBooks()) {
                String authors = FullName.unitVariables(book.getAuthors().get(0).getFirstname(), book.getAuthors().get(0).getLastname());
                for (int i = 1; i < book.getAuthors().size(); i++) {
                    authors += (", " + FullName.unitVariables(book.getAuthors().get(i).getFirstname(), book.getAuthors().get(i).getLastname()));
                }
                if (book instanceof PrintedBook printedBook) {
                    model.addRow(new Object[] { printedBook.getTitle(), authors, printedBook.getIsbn(),
                            printedBook.getGenre(), printedBook.getFormat(), printedBook.getValue(),
                            printedBook.getPublisher().getName(), printedBook.getCopies(), printedBook.getPages(), "-",
                            "-", "-" });
                }
                if (book instanceof DigitalBook digitalBook) {
                    model.addRow(new Object[] { digitalBook.getTitle(), authors, digitalBook.getIsbn(),
                            digitalBook.getGenre(), digitalBook.getFormat(), digitalBook.getValue(),
                            digitalBook.getPublisher().getName(), "-", "-",
                            digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No", "-", "-" });
                }
                if (book instanceof AudioBook audiobook) {
                    model.addRow(
                            new Object[] { audiobook.getTitle(), authors, audiobook.getIsbn(), audiobook.getGenre(),
                                    audiobook.getFormat(), audiobook.getValue(), audiobook.getPublisher().getName(),
                                    "-", "-", "-", FullName.unitVariables(audiobook.getNarrator().getFirstname(), audiobook.getNarrator().getLastname()), audiobook.getDuration() });
                }
            }
            return new Response("Books by author table updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
