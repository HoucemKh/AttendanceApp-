/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceapp;

import controllers.AttendanceController;

/**
 *
 * @author mga
 */
public class AttendanceApp {

    public static void run() {        
        System.out.println("Attendance App\n" + "==============\n\n");
        
        AttendanceController attendanceController = new AttendanceController();  
        
        attendanceController.run();
        
        System.out.println("Thank you for using Attendance App. Good bye.\n");        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AttendanceApp attendanceApp = new AttendanceApp();
        attendanceApp.run();
    }
    
}
