/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

/**
 *
 * @author ayaz
 */
public class QueueElevator {
    /**
     * @instanceVariable _person_Number number of people in the elevator
     * @instanceVariable _destination_Floor  target floor of the elevator
     * @instanceVariable _current_Floor  floor with elevator
     */
    private int _person_Number = 0;
    private int _destination_Floor = 0;
    private int _current_Floor = 0;
    
    /**
     * @param _person_Number
     * @param _destination_Floor
     * @param _current_Floor 
     */
    
    public QueueElevator(int _person_Number,int _destination_Floor,int _current_Floor){
        this._person_Number = _person_Number;
        this._destination_Floor = _destination_Floor;
        this._current_Floor = _current_Floor;        
    }

    /**
     * @return the _person_Number
     */
    public int getPerson_Number() {
        return _person_Number;
    }

    /**
     * @return the _destination_Floor
     */
    public int getDestination_Floor() {
        return _destination_Floor;
    }

    /**
     * @return the _current_Floor
     */
    public int getCurrent_Floor() {
        return _current_Floor;
    }
    
}
