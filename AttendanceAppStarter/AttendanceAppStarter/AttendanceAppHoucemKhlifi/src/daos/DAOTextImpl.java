/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositories.Repository;
import model.Swipe;

/**
 *
 * @author Houcem
 */
public class DAOTextImpl implements DAOInterface {

    static final char DELIMITER=',';
    @Override
    public Repository load(String filename) {
        Repository repository = new Repository();
       try (BufferedReader br = new BufferedReader(new FileReader(filename))) { 
            String[] temp;
            String line = br.readLine();
            while(line!=null){
                temp=line.split(Character.toString(DELIMITER));        
                int id = Integer.parseInt(temp[0]);
                String cardId = stripQuotes(temp[1]);
                String room = stripQuotes(temp[2]);
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String DateTime = stripQuotes(temp[3]);
                Date DateTimeDT;
                Calendar swipeDateTime = null;
                try{
                    DateTimeDT = dateFormat.parse(DateTime);
                    swipeDateTime = Calendar.getInstance();
                    swipeDateTime.setTime(DateTimeDT);         
                } catch(ParseException ex){
                    Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex); 
                }
                
                Swipe swipe = new Swipe(id, cardId,room, swipeDateTime);
                repository.add(swipe);                
                line = br.readLine();                
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repository;
    }

    @Override
    public void store(String filename, Repository repository) {
       try(PrintWriter output = new PrintWriter(filename)){
           output.print(repository.toString(DELIMITER));
           output.close();
       } catch(FileNotFoundException ex) {
           Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    private String stripQuotes(String str) {
         return str.substring(1, str.length()-1); 
    }
    //2nd Method 
    /*private String stripQuotes (String str) {
           String result = null;
    if (str != null && !str.isEmpty()) {
        // if the string contains a single quote or space, leave the double quotes
        if (str.contains("'") || str.contains(" "))
            result = str;
        else {
            if (str.startsWith("\"")) {
                result = str.substring(1, str.length() - 1);
            } else {
                result = str;
            }
        }
    }
    return result;

    }*/
}
