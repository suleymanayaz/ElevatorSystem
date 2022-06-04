/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

import java.util.LinkedList;

/**
 *
 * @author ayaz
 */
public class Mall_Main {
    
    /**
    * @instanceVariable _ground_Floor_Elevator_Queue queue of people waiting for the elevator on the ground floor
    * @instanceVariable _first_Floor_Elevator_Queue queue of people waiting for the elevator on the first floor
    * @instanceVariable _second_Floor_Elavator_Queue queue of people waiting for the elevator on the second floor
    * @instanceVariable _third_Floor_Elevator queue of people waiting for the elevator on the third floor
    * @instanceVariable _fourth_Floor_Elavator_Queue queue of people waiting for the elevator on the fourth floor
    * @instanceVariable _out_Of_Floor_Elavator_Queue queue of people waiting for the elevator to exit
    */
    private static LinkedList<QueueElevator> _ground_Floor_Elevator_Queue = new LinkedList<>();
    private static LinkedList<QueueElevator> _first_Floor_Elevator_Queue = new LinkedList<>();
    private static LinkedList<QueueElevator> _second_Floor_Elevator_Queue = new LinkedList<>();
    private static LinkedList<QueueElevator> _third_Floor_Elevator_Queue = new LinkedList<>();
    private static LinkedList<QueueElevator> _fourth_Floor_Elevator_Queue = new LinkedList<>();
    private static LinkedList<QueueElevator> _out_Of_Floor_Elevator_Queue = new LinkedList<>();
    
    /**
    * @instanceVariable _ground_Floor_Number_Of_Person
    * @instanceVariable _first_Floor_Number_Of_Person
    * @instanceVariable _second_Floor_Number_Of_Person
    * @instanceVariable _third_Floor_Number_Of_Person
    * @instanceVariable _fourth_Floor_Number_Of_Person
    * @instanceVariable _exit_Number_Of_Person
    */
    private static int _ground_Floor_Number_Of_Person = 0;
    private static int _first_Floor_Number_Of_Person = 0;
    private static int _second_Floor_Number_Of_Person = 0;
    private static int _third_Floor_Number_Of_Person = 0;
    private static int _fourth_Floor_Number_Of_Person = 0;
    private static int _exit_Number_Of_Person = 0;
    
    
    /**
     * @instanceVariable  _elevator_1 The thread that will run the 1st elevator
     * @instanceVariable  _elevator_2 The thread that will run the 2st elevator
     * @instanceVariable  _elevator_3 The thread that will run the 3st elevator
     * @instanceVariable  _elevator_4 The thread that will run the 4st elevator
     * @instanceVariable  _elevator_5 The thread that will run the 5st elevator
     */
    private static ElevatorThread _elevator_1;
    private static ElevatorThread _elevator_2;
    private static ElevatorThread _elevator_3;
    private static ElevatorThread _elevator_4;
    private static ElevatorThread _elevator_5;
    
    public static void main(String[] args){
        Thread MallLogin = new Thread(new MallLogin());
        Thread MallExit = new Thread(new MallExit());
        _elevator_1 = new ElevatorThread();
        _elevator_2 = new ElevatorThread("Elevator2");
        _elevator_3 = new ElevatorThread("Elevator3");
        _elevator_4 = new ElevatorThread("Elevator4");
        _elevator_5 = new ElevatorThread("Elevator5");
        Thread _Elevator_Check = new Thread( new ControlThread(_elevator_2,_elevator_3,_elevator_4,_elevator_5));
        
        Thread _elevator_Thread_1 = new Thread(_elevator_1);
        Thread _elevator_Thread_2 = new Thread(_elevator_2);
        Thread _elevator_Thread_3 = new Thread(_elevator_3);
        Thread _elevator_Thread_4 = new Thread(_elevator_4);
        Thread _elevator_Thread_5 = new Thread(_elevator_5);
        
        
        MallLogin.start(); 
        MallExit.start();
        _elevator_Thread_1.start();
        _elevator_Thread_2.start();
        _elevator_Thread_3.start();
        _elevator_Thread_4.start();
        _elevator_Thread_5.start();
        _Elevator_Check.start();
       
        
        
    }

 

 
    /**
     * @return the _ground_Floor_Number_Of_Person
     */
    public static int getGround_Floor_Number_Of_Person() {
        return _ground_Floor_Number_Of_Person;
    }

    /**
     * @param aGround_Floor_Number_Of_Person the _ground_Floor_Number_Of_Person to set
     */
    public static void setGround_Floor_Number_Of_Person(int aGround_Floor_Number_Of_Person) {
        _ground_Floor_Number_Of_Person = aGround_Floor_Number_Of_Person;
    }

    /**
     * @return the _first_Floor_Number_Of_Person
     */
    public static int getFirst_Floor_Number_Of_Person() {
        return _first_Floor_Number_Of_Person;
    }

    /**
     * @param aFirst_Floor_Number_Of_Person the _first_Floor_Number_Of_Person to set
     */
    public static void setFirst_Floor_Number_Of_Person(int aFirst_Floor_Number_Of_Person) {
        _first_Floor_Number_Of_Person = aFirst_Floor_Number_Of_Person;
    }

    /**
     * @return the _second_Floor_Number_Of_Person
     */
    public static int getSecond_Floor_Number_Of_Person() {
        return _second_Floor_Number_Of_Person;
    }

    /**
     * @param aSecond_Floor_Number_Of_Person the _second_Floor_Number_Of_Person to set
     */
    public static void setSecond_Floor_Number_Of_Person(int aSecond_Floor_Number_Of_Person) {
        _second_Floor_Number_Of_Person = aSecond_Floor_Number_Of_Person;
    }

    /**
     * @return the _third_Floor_Number_Of_Person
     */
    public static int getThird_Floor_Number_Of_Person() {
        return _third_Floor_Number_Of_Person;
    }

    /**
     * @param aThird_Floor_Number_Of_Person the _third_Floor_Number_Of_Person to set
     */
    public static void setThird_Floor_Number_Of_Person(int aThird_Floor_Number_Of_Person) {
        _third_Floor_Number_Of_Person = aThird_Floor_Number_Of_Person;
    }

    /**
     * @return the _fourth_Floor_Number_Of_Person
     */
    public static int getFourth_Floor_Number_Of_Person() {
        return _fourth_Floor_Number_Of_Person;
    }

    /**
     * @param aFourth_Floor_Number_Of_Person the _fourth_Floor_Number_Of_Person to set
     */
    public static void setFourth_Floor_Number_Of_Person(int aFourth_Floor_Number_Of_Person) {
        _fourth_Floor_Number_Of_Person = aFourth_Floor_Number_Of_Person;
    }

    /**
     * @return the _exit_Number_Of_Person
     */
    public static int getExit_Number_Of_Person() {
        return _exit_Number_Of_Person;
    }

    /**
     * @param aExit_Number_Of_Person the _exit_Number_Of_Person to set
     */
    public static void setExit_Number_Of_Person(int aExit_Number_Of_Person) {
        _exit_Number_Of_Person = aExit_Number_Of_Person;
    }

    /**
     * @return the _elevator_1
     */
    public static ElevatorThread getElevator_1() {
        return _elevator_1;
    }

    /**
     * @param aElevator_1 the _elevator_1 to set
     */
    public static void setElevator_1(ElevatorThread aElevator_1) {
        _elevator_1 = aElevator_1;
    }

    /**
     * @return the _elevator_2
     */
    public static ElevatorThread getElevator_2() {
        return _elevator_2;
    }

    /**
     * @param aElevator_2 the _elevator_2 to set
     */
    public static void setElevator_2(ElevatorThread aElevator_2) {
        _elevator_2 = aElevator_2;
    }

    /**
     * @return the _elevator_3
     */
    public static ElevatorThread getElevator_3() {
        return _elevator_3;
    }

    /**
     * @param aElevator_3 the _elevator_3 to set
     */
    public static void setElevator_3(ElevatorThread aElevator_3) {
        _elevator_3 = aElevator_3;
    }

    /**
     * @return the _elevator_4
     */
    public static ElevatorThread getElevator_4() {
        return _elevator_4;
    }

    /**
     * @param aElevator_4 the _elevator_4 to set
     */
    public static void setElevator_4(ElevatorThread aElevator_4) {
        _elevator_4 = aElevator_4;
    }

    /**
     * @return the _elevator_5
     */
    public static ElevatorThread getElevator_5() {
        return _elevator_5;
    }

    /**
     * @param aElevator_5 the _elevator_5 to set
     */
    public static void setElevator_5(ElevatorThread aElevator_5) {
        _elevator_5 = aElevator_5;
    }

    /**
     * @return the _ground_Floor_Elevator_Queue
     */
    public static LinkedList<QueueElevator> getGround_Floor_Elevator_Queue() {
        return _ground_Floor_Elevator_Queue;
    }

    /**
     * @param aGround_Floor_Elevator_Queue the _ground_Floor_Elevator_Queue to set
     */
    public static void setGround_Floor_Elevator_Queue(LinkedList<QueueElevator> aGround_Floor_Elevator_Queue) {
        _ground_Floor_Elevator_Queue = aGround_Floor_Elevator_Queue;
    }

    /**
     * @return the _first_Floor_Elevator_Queue
     */
    public static LinkedList<QueueElevator> getFirst_Floor_Elevator_Queue() {
        return _first_Floor_Elevator_Queue;
    }

    /**
     * @param aFirst_Floor_Elevator_Queue the _first_Floor_Elevator_Queue to set
     */
    public static void setFirst_Floor_Elevator_Queue(LinkedList<QueueElevator> aFirst_Floor_Elevator_Queue) {
        _first_Floor_Elevator_Queue = aFirst_Floor_Elevator_Queue;
    }

    /**
     * @return the _second_Floor_Elevator_Queue
     */
    public static LinkedList<QueueElevator> getSecond_Floor_Elevator_Queue() {
        return _second_Floor_Elevator_Queue;
    }

    /**
     * @param aSecond_Floor_Elevator_Queue the _second_Floor_Elevator_Queue to set
     */
    public static void setSecond_Floor_Elevator_Queue(LinkedList<QueueElevator> aSecond_Floor_Elevator_Queue) {
        _second_Floor_Elevator_Queue = aSecond_Floor_Elevator_Queue;
    }

    /**
     * @return the _third_Floor_Elevator_Queue
     */
    public static LinkedList<QueueElevator> getThird_Floor_Elevator_Queue() {
        return _third_Floor_Elevator_Queue;
    }

    /**
     * @param aThird_Floor_Elevator_Queue the _third_Floor_Elevator_Queue to set
     */
    public static void setThird_Floor_Elevator_Queue(LinkedList<QueueElevator> aThird_Floor_Elevator_Queue) {
        _third_Floor_Elevator_Queue = aThird_Floor_Elevator_Queue;
    }

    /**
     * @return the _fourth_Floor_Elevator_Queue
     */
    public static LinkedList<QueueElevator> getFourth_Floor_Elevator_Queue() {
        return _fourth_Floor_Elevator_Queue;
    }

    /**
     * @param aFourth_Floor_Elevator_Queue the _fourth_Floor_Elevator_Queue to set
     */
    public static void setFourth_Floor_Elevator_Queue(LinkedList<QueueElevator> aFourth_Floor_Elevator_Queue) {
        _fourth_Floor_Elevator_Queue = aFourth_Floor_Elevator_Queue;
    }

    /**
     * @return the _out_Of_Floor_Elevator_Queue
     */
    public static LinkedList<QueueElevator> getOut_Of_Floor_Elevator_Queue() {
        return _out_Of_Floor_Elevator_Queue;
    }

    /**
     * @param aOut_Of_Floor_Elevator_Queue the _out_Of_Floor_Elevator_Queue to set
     */
    public static void setOut_Of_Floor_Elevator_Queue(LinkedList<QueueElevator> aOut_Of_Floor_Elevator_Queue) {
        _out_Of_Floor_Elevator_Queue = aOut_Of_Floor_Elevator_Queue;
    }
    
    
    
}
