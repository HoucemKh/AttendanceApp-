package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author Houcem
 */
public class Swipe implements Comparable<Swipe> {

    /**
     *
     */
    protected final int id;

    /**
     *
     */
    protected String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;
    
    public static int lastSwipeIdUsed = 0;
    private ArrayList<Swipe> newSwipes;
    static final char EOLN='\n';       
    static final String QUOTE="\"";    
    
    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    }    
    
    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     
    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }
    //me
    public String getCardId(){
        return this.cardId;
    }
    
    public void setCardId (String cardId){
      this.cardId= cardId;  
    }
    
    public String getRoom(){
        return this.room;
    }
    
    public void setRoom(String room){
        this.room= room;
    }
    
    public  Calendar getSwipeDateTime(){
        return this.swipeDateTime;
    }
    
    public void addSwipe(Swipe newSwipe) {
        this.newSwipes.add(newSwipe);
    }
    
     @Override
    public int hashCode() {
          return getId() * 31 + getCardId().hashCode() * 31 + getRoom().hashCode() * 31 +  getSwipeDateTime().hashCode() * 31;
    }

   @Override
    public boolean equals(Object o) {
         if (o instanceof Swipe) {
            Swipe c = (Swipe)o;
            return  c.getId() == getId() &&
                    c.getCardId() == getCardId() &&
                    c.getRoom().equals(getRoom()) && 
                    c.getSwipeDateTime().equals(getSwipeDateTime());
        } else {
            return false;
        }
    }
    
    public static Comparator<Swipe> SwipeDateTimeComparator  = (Swipe p, Swipe q) -> {
        if (p.getSwipeDateTime().before(q.getSwipeDateTime())) {
            return 1;
        } else if (p.getSwipeDateTime().after(q.getSwipeDateTime())) {
            return -1;
        } else {
            return 0;
        }
    };

    
 
    @Override
    public int compareTo(Swipe compareSwipe) {
        
            int swpId = ((Swipe) compareSwipe).getId(); 
		
		//ascending order
		return this.id - swpId;
		
                
		//descending order
		//return customerId - this.custId;   
                
              
    }
 
    // Methods required: getters, setters, hashCode, equals, compareTo, comparator
    
    /**
     *
     * @param calendar
     * @return
     */
        
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    }    
    
     
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    
    public String toString(char delimiter){        
        String str = Integer.toString(this.id) + delimiter + QUOTE + this.cardId + QUOTE + delimiter +
                QUOTE + this.room + QUOTE + delimiter + QUOTE + Swipe.formatSwipeDateTime(getSwipeDateTime()) + QUOTE + EOLN ;
        return str;  
    }
    
}
