package repositories;

import java.util.ArrayList;
import model.Swipe;

public interface RepositoryInterface {

    /**
     *
     * @param item
     */
    void add(Swipe item);

    /**
     *
     * @param id
     * @return
     */
    Swipe getItem(int id);

    /**
     *
     * @return
     */
    ArrayList<Swipe> getItems();

    /**
     *
     * @param id
     */
    
    void remove(int id);

    /**
     *
     * @param items
     */
    void setItems(ArrayList<Swipe> items);

    /**
     *
     * @param filename
     */
    
    void store(String filename);

    /**
     *
     * @return
     */
    @Override
    String toString();
    
}
