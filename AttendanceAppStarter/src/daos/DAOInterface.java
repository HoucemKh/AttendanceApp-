package daos;

import repositories.Repository;


/**
 *
 * @author Houcem
 */
public interface DAOInterface {

    public Repository load(String filename);

    public void store(String filename, Repository repository);
    
    }
