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
import core.model.persona.utils.FullName;
import core.model.storage.LibroStorage;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class LibroFormatoTable {
    
    public static Response updateLibroFormatoConAdd(DefaultTableModel model, String format) {
        try {
            LibroStorage libroStorage = LibroStorage.getInstance();
            ArrayList<Book> books = libroStorage.getLibros();
            model.setRowCount(0);

            for (Book book : books) {
                if (book.getFormat().equals(format)) {
                    String authors = FullName.unitVariables(book.getAuthors().get(0).getFirstname(), book.getAuthors().get(0).getLastname());
                    for (int i = 1; i < book.getAuthors().size(); i++) {
                        authors += (", " + FullName.unitVariables(book.getAuthors().get(i).getFirstname(), book.getAuthors().get(i).getLastname()));
                    }
                    if (book instanceof PrintedBook printedBook) {
                        model.addRow(new Object[] { printedBook.getTitle(), authors, printedBook.getIsbn(),
                                printedBook.getGenre(), printedBook.getFormat(), printedBook.getValue(),
                                printedBook.getPublisher().getName(), printedBook.getCopies(), printedBook.getPages(),
                                "-", "-", "-" });
                    }
                    if (book instanceof DigitalBook digitalBook) {
                        model.addRow(new Object[] { digitalBook.getTitle(), authors, digitalBook.getIsbn(),
                                digitalBook.getGenre(), digitalBook.getFormat(), digitalBook.getValue(),
                                digitalBook.getPublisher().getName(), "-", "-",
                                digitalBook.hasHyperlink() ? digitalBook.getHyperlink() : "No", "-", "-" });
                    }
                    if (book instanceof AudioBook audiobook) {
                        model.addRow(new Object[] { audiobook.getTitle(), authors, audiobook.getIsbn(),
                                audiobook.getGenre(), audiobook.getFormat(), audiobook.getValue(),
                                audiobook.getPublisher().getName(), "-", "-", "-",
                                FullName.unitVariables(audiobook.getNarrator().getFirstname(), audiobook.getNarrator().getLastname()), audiobook.getDuration() });
                    }
                }
            }
            return new Response("Books by format table updated successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
