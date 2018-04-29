package model;

import java.util.Calendar;

/**
 *
 * @author Houcem
 */
public class VisitorSwipe extends Swipe {
    
    static final char EOLN='\n';       
    static final String QUOTE="\"";
    
    /**
     *
     */
    protected String visitorName;

    /**
     *
     */
    protected String visitorCompany;

    /**
     *
     */
    public VisitorSwipe() {
        super();
        this.visitorName = "Unknown";
        this.visitorCompany = "Unknown";        
    }

    /**
     *
     * @param cardId
     * @param room
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(String cardId, String room, String visitorName, String visitorCompany) {
        super(cardId, room);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }

    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     * @param visitorName
     * @param visitorCompany
     */
    public VisitorSwipe(int swipeId, String cardId, String room, Calendar swipeDateTime, String visitorName, String visitorCompany) {
        super(swipeId, cardId, room, swipeDateTime);
        this.visitorName = visitorName;
        this.visitorCompany = visitorCompany;
    }
    
   public String getVisitorName(){
       return this.visitorName;
   }
   
   public void setVisitorName(String VisitorName){
       this.visitorName = visitorName;
   }
   
   public String getVisitorCompany(){
       return this.visitorCompany;
   }
   
   public void setVisitorCompany(String VisitorCompany){
       this.visitorCompany = visitorCompany;
   }
   
    
    // Methods required: getters, setters  
    
    @Override
    public String toString() {
        return super.toString() + "\nName: " + this.visitorName +  " - Company: " + this.visitorCompany;
    }
   @Override 
    public String toString(char delimiter){        
        String str = Integer.toString(this.id) + delimiter + QUOTE + this.cardId + QUOTE + delimiter +
                QUOTE + this.room + QUOTE + delimiter + QUOTE + Swipe.formatSwipeDateTime(getSwipeDateTime()) + QUOTE + delimiter + QUOTE + this.visitorName + QUOTE + delimiter + QUOTE + this.visitorCompany + QUOTE + EOLN ;
        return str;  
    }
    
    
}
