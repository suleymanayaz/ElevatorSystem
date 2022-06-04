/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author ayaz
 */
public class ControlThread implements Runnable{
    /**
     * 
     */
    private final AtomicBoolean _running = new AtomicBoolean(false);
    public ElevatorThread _elevator_2,_elevator_3,_elevator_4,_elevator_5;
    private static int _temp_count = 0;
    private LinkedList<QueueElevator> _temp_Queue_List = new LinkedList<>();
    
    public ControlThread(ElevatorThread _elevator_2,ElevatorThread _elevator_3,ElevatorThread _elevator_4,ElevatorThread _elevator_5){
        this._elevator_2 = _elevator_2;
        this._elevator_3 = _elevator_3;
        this._elevator_4 = _elevator_4;
        this._elevator_5 = _elevator_5;
    }
    
    
    @Override
    public void run() {
        _running.set(true);
        while(_running.get()){
            synchronized(Mall_Main.getOut_Of_Floor_Elevator_Queue()){
                _check_Out_Floor_Queue();
            }
        }
    }
    
    private void _check_Out_Floor_Queue(){
        _temp_count = 0;
        _temp_Queue_List.clear();
        _temp_Queue_List = (LinkedList)Mall_Main.getOut_Of_Floor_Elevator_Queue().clone();
        if(_temp_Queue_List.peek() != null){
            for(QueueElevator _temp: _temp_Queue_List){
                if(_temp != null)
                    _temp_count= _temp_count + _temp.getPerson_Number();
            }
        }
        _check_Elevator(_temp_count);
    }
    
    private void _check_Elevator(int _temp_count){
        if(_temp_count < _elevator_2.getCapacity_Elevator()*2 && _elevator_2.isActive() && _elevator_2.getInside_Elevator_Queue().isEmpty() && _elevator_2.getCount_Inside() == 0){
            _elevator_2.getRunning().set(false);
            _elevator_2.setActive(false);
            _elevator_2.setMode_Elevator("idle");
            _elevator_2.setCurrent_Floor(0);
        }else if(_temp_count >= _elevator_2.getCapacity_Elevator()*2  && _temp_count < _elevator_2.getCapacity_Elevator()*3 && !_elevator_2.isActive() ){
           _elevator_2.getRunning().set(true);
            _elevator_2.setActive(true);
            _elevator_2.setMode_Elevator("working");
        }else{
            //_thread_Sleep();
        }
        
        if(_temp_count < _elevator_3.getCapacity_Elevator()*4 && _elevator_3.isActive() && _elevator_3.getInside_Elevator_Queue().isEmpty() && _elevator_3.getCount_Inside() == 0){
            _elevator_3.getRunning().set(false);
            _elevator_3.setActive(false);
            _elevator_3.setMode_Elevator("idle");
            _elevator_3.setCurrent_Floor(0);
        }else if(_temp_count >= _elevator_3.getCapacity_Elevator()*4  && _temp_count < _elevator_3.getCapacity_Elevator()*6 && !_elevator_3.isActive() ){
           _elevator_3.getRunning().set(true);
            _elevator_3.setActive(true);
            _elevator_3.setMode_Elevator("working");
        }else{
            //_thread_Sleep();
        }
        
        if(_temp_count < _elevator_4.getCapacity_Elevator()*6 && _elevator_4.isActive() && _elevator_4.getInside_Elevator_Queue().isEmpty() && _elevator_4.getCount_Inside() == 0){
            _elevator_4.getRunning().set(false);
            _elevator_4.setActive(false);
            _elevator_4.setMode_Elevator("idle");
            _elevator_4.setCurrent_Floor(0);
        }else if(_temp_count >= _elevator_4.getCapacity_Elevator()*6  && _temp_count < _elevator_4.getCapacity_Elevator()*8 && !_elevator_4.isActive() ){
           _elevator_4.getRunning().set(true);
            _elevator_4.setActive(true);
            _elevator_4.setMode_Elevator("working");
        }else{
            //_thread_Sleep();
        }
        
        if(_temp_count < _elevator_5.getCapacity_Elevator()*8 && _elevator_5.isActive() && _elevator_5.getInside_Elevator_Queue().isEmpty() && _elevator_5.getCount_Inside() == 0){
            _elevator_5.getRunning().set(false);
            _elevator_5.setActive(false);
            _elevator_5.setMode_Elevator("idle");
            _elevator_5.setCurrent_Floor(0);
        }else if(_temp_count >= _elevator_5.getCapacity_Elevator()*8 && _elevator_5.isActive() ){
           _elevator_5.getRunning().set(true);
            _elevator_5.setActive(true);
            _elevator_5.setMode_Elevator("working");
        }else{
            //_thread_Sleep();
        }
    }
    
}
