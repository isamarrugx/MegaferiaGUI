/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.libro;

import core.controller.utils.Response;
import core.controller.utils.Status;
import core.model.Publisher;
import core.model.libro.Book;
import core.model.libro.DigitalBook;
import core.model.persona.Person;
import core.model.persona.author.Author;
import core.model.storage.EditorialStorage;
import core.model.storage.LibroStorage;
import core.model.storage.PersonaStorage;
import java.util.ArrayList;

public class LibroVirtualController {

    public static Response addLibroVirtual(String title, String autorData, String isbn, String genre, String format, String valuetxt, String editorialData, String url) {

        try {
            PersonaStorage personas = PersonaStorage.getInstance();
            EditorialStorage editorales = EditorialStorage.getInstance();
            LibroStorage creteLibro = LibroStorage.getInstance();
            ArrayList<Book> libros = creteLibro.getLibros();

            if (title.isBlank()) {
                return new Response("Title must not be empty", Status.BAD_REQUEST);
            }

            if (autorData.isBlank()) {
                return new Response("Authors must not be empty", Status.BAD_REQUEST);
            }

            String[] autorsData = autorData.split("\n");
            ArrayList<Author> autoresFinales = new ArrayList<>();

            for (String authorData : autorsData) {
                long authorId;
                try {
                    authorId = Long.parseLong(authorData.split(" - ")[0]);
                } catch (NumberFormatException e) {
                    return new Response("Author ID must be numeric", Status.BAD_REQUEST);
                }

                for (Person p : personas.getPersonas()) {
                    if (p instanceof Author a && a.getId() == authorId) {
                        autoresFinales.add(a);
                        break;
                    }
                }
            }

            if (autoresFinales.isEmpty()) {
                return new Response("No valid authors selected", Status.BAD_REQUEST);
            }

            if (isbn.isBlank()) {
                return new Response("ISBN must not be empty", Status.BAD_REQUEST);
            }

            if (!isbn.matches("^\\d{3}-\\d{2}-\\d{6}-\\d$")) {
                return new Response("ISBN must match XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
            }

            if (genre.equalsIgnoreCase("Seleccione uno...")) {
                return new Response("Genre must not be empty", Status.BAD_REQUEST);
            }

            if (format.equalsIgnoreCase("Seleccione uno...")) {
                return new Response("Format must not be empty", Status.BAD_REQUEST);
            }

            if (valuetxt.isBlank()) {
                return new Response("Value must not be empty", Status.BAD_REQUEST);
            }

            double value;
            try {
                value = Double.parseDouble(valuetxt);
            } catch (NumberFormatException e) {
                return new Response("Value must be numeric", Status.BAD_REQUEST);
            }

            if (editorialData.isBlank()) {
                return new Response("Editorial must not be empty", Status.BAD_REQUEST);
            }

            String publisherNit = editorialData.split(" ")[1]
                    .replace("(", "")
                    .replace(")", "");

            Publisher editorial = null;

            for (Publisher p : editorales.getEditoriales()) {
                if (p.getNit().equals(publisherNit)) {
                    editorial = p;
                    break;
                }
            }

            if (editorial == null) {
                return new Response("Invalid editorial selected", Status.BAD_REQUEST);
            }

            DigitalBook libroTemp = url.isBlank()
                    ? new DigitalBook(title, autoresFinales, isbn, genre, format, value, editorial)
                    : new DigitalBook(title, autoresFinales, isbn, genre, format, value, editorial, url);

            libros.add(libroTemp);

            return new Response("DigitalBook created successfully", Status.CREATED);

        } catch (Exception e) {
            return new Response("Unexpected error: " + e, Status.INTERNAL_SERVER_ERROR);
        }
    }

}
