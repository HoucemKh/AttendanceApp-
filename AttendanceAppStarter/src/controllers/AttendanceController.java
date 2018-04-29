package controllers;

import helpers.InputHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import model.Swipe;
import static model.Swipe.SwipeDateTimeComparator;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author Houcem
 */
public class AttendanceController extends Repository//extends Repository //
{
    private final Repository repository;
 
    public AttendanceController() {
        
        InputHelper inputHelper = new InputHelper();
        char c = inputHelper.readCharacter("Load an already existing Swipe File (Y/N)?");
        if (c == 'Y' || c == 'y'){
            String fileName = inputHelper.readString("Enter filename");
            this.repository = new Repository(fileName);
            
        }
        else{
            this.repository = new Repository();
        }
    }
    
   
   
    /**
     *
     */
    public void run() {
        boolean finished = false;
        String cardId = null;
        
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesByCardIdOrderedByDateTime(); // 
                    break;                    
                case 'D': 
                    listSwipeStatistics(); //
                    break;
                case 'Q': 
                    InputHelper inputHelper = new InputHelper();
                    String filename = inputHelper.readString("Enter the filename to store");
                    repository.store(filename);
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");       
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }    
    
    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");  
        InputHelper inputHelper = new InputHelper();
        boolean validSwipeId = false;
        Swipe requiredSwipe=null;
        do {
            int swipeId = inputHelper.readInt("Enter Swipe Id");
            requiredSwipe = repository.getItem(swipeId);           
            if (requiredSwipe == null) {
                validSwipeId = true;
            }
        } while (!validSwipeId);
        System.out.println("Swipe\n========\n" + requiredSwipe);
        int newSwipeId = inputHelper.readInt("Enter New Swipe Id");
        String newCardId = inputHelper.readString("Enter New Card Id");
        String newRoom = "General";
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        Calendar newStartDate = Calendar.getInstance();
        
        
        char c = inputHelper.readCharacter("Normal or Visitor Swipe (N/V)?", "NV");
       
            
        if (c == 'N') {
             Swipe newSwipe = new Swipe(newSwipeId, newCardId, newRoom, newStartDate);
             repository.add(newSwipe); 
             System.out.println("Swipe added");
        }
        
        else if(c == 'V'){
           String visitorName = inputHelper.readString("Enter Visitor Name:");
           String visitorCompany = inputHelper.readString("Enter Visitor Company:");
           VisitorSwipe visitorSwipe = new VisitorSwipe(newSwipeId, newCardId, newRoom, newStartDate, visitorName, visitorCompany); 
            repository.add(visitorSwipe);
            System.out.println("Swipe added");
        }       
    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");    
        Iterator it = repository.getItems().listIterator();
        while(it.hasNext()){
        Swipe s = (Swipe) it.next();
        System.out.println(s);
        } 
    }      
      

    private void listSwipesByCardIdOrderedByDateTime() { 
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
        InputHelper inputHelper = new InputHelper();
        
        String cardToList = inputHelper.readString("Enter CardId number:");
        ArrayList<Swipe> list = new ArrayList <Swipe>(); 
        Iterator it = repository.getItems().listIterator();
            while (it.hasNext()) {
                Swipe s = (Swipe) it.next();           
                if (s.getCardId().equals(cardToList)){
                    list.add(s);

                }
            }
            Collections.sort(list, Swipe.SwipeDateTimeComparator);
            Iterator i = list.listIterator();
            while (i.hasNext()){
                Swipe s = (Swipe) i.next();
                System.out.println(s);
            }
        
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
        InputHelper inputHelper = new InputHelper();
        String room = inputHelper.readString("Enter Room Number");
         ArrayList<Swipe> Rooms = new ArrayList <Swipe>();
        List<Calendar> list = new ArrayList<Calendar>();
        Iterator it = repository.getItems().listIterator();
         int numberofSwipes = 0;
         while (it.hasNext()) {
            Swipe s = (Swipe) it.next();  
           
            if (s.getRoom().equals(room)){
               Rooms.add(s);
               list.add(s.getSwipeDateTime());
               Collections.sort(list);
               numberofSwipes = Rooms.size();
            }    
        }
          Collections.sort(Rooms, Swipe.SwipeDateTimeComparator);
            System.out.println("Number of Swipes:"  + numberofSwipes);
            
            Calendar firstElement =  Rooms.get(0).getSwipeDateTime();
            
            System.out.println("Last Swipe Date Time:"  + Swipe.formatSwipeDateTime(firstElement));
        }
        
    }

