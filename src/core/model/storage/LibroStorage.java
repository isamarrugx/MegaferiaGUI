/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.model.storage;

import core.model.libro.Book;
import java.util.ArrayList;

public class LibroStorage {
    private static LibroStorage instance;
    private ArrayList<Book> libros;

    public ArrayList<Book> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Book> libros) {
        this.libros = libros;
    }
    
    private LibroStorage(){
        this.libros = new ArrayList<>();
    }

    public static LibroStorage getInstance() {
        if (instance == null) {
            instance = new LibroStorage();
        }
        return instance;
    }
    
    
    public void addLibro(Book libro) {
        this.libros = new ArrayList<>();
    }
    
}
