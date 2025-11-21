package core.model.storage;

import core.model.Publisher;
import java.util.ArrayList;


public class EditorialStorage{
    
    private static EditorialStorage instance;
    private ArrayList<Publisher> editoriales;

    public ArrayList<Publisher> getEditoriales() {
        return editoriales;
    }

    public void setEditoriales(ArrayList<Publisher> editoriales) {
        this.editoriales = editoriales;
    }
    
    private EditorialStorage(){
        this.editoriales = new ArrayList<>();
    }
    
    public static EditorialStorage getInstance(){
        if (instance == null) {
            instance = new EditorialStorage();
        }
        return instance;
    }
    public void addEditorial(Publisher editorial) {
        this.editoriales.add(editorial);
    }
    
}
