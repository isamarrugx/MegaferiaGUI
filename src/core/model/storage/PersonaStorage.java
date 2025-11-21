package core.model.storage;

import core.model.persona.Person;
import java.util.ArrayList;

public class PersonaStorage {

    private static PersonaStorage instance;
    private ArrayList<Person> personas;

    public ArrayList<Person> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Person> personas) {
        this.personas = personas;
    }
    
    private PersonaStorage(){
        this.personas =new ArrayList<>();
    }
    
    public static PersonaStorage getInstance(){
        if(instance == null){
            instance = new PersonaStorage();
        }
        return instance;
    }
    public void addPersona(Person persona) {
        this.personas.add(persona);
    }
    
}
