package repositories;
import daos.DAOTextImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import model.Swipe;


public class Repository  {
    private List<Swipe> items;    
    
    public Repository() {
        this.items = new ArrayList<>();       
    }
    
    public Repository(List<Swipe> items) {        
        this.items = items;
    }
    
    public Repository(String filename) {
        this();
        // Create dao and execute load
       DAOTextImpl dao = new DAOTextImpl();
       this.items = dao.load(filename).getItems();
        
    }

        public List<Swipe> getItems() {        
        return this.items;
    }
    

    public void setItems(List<Swipe> items) {        
        this.items = items;
    }
  
    public void add(Swipe item) {
        this.items.add(item);
    }
       
    public void remove(int id) {
        Predicate<Swipe> predicate = e->e.getId() == id;       
        this.items.removeIf(predicate);
    }
   
    public Swipe getItem(int id) {
        for (Swipe item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "\nItems: " + this.items;
    }    
   
    public String toString(char delimiter){
        String output ="";
        for (Swipe item: this.items){
            output +=item.toString(delimiter);
        }
        return output;
    }
   
    public void store(String filename) {       
        // create dao and execute store 
         DAOTextImpl dao = new DAOTextImpl();
         dao.store(filename, this);
    }        

}

