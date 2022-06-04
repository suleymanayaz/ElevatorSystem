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
public class MallExit implements Runnable {
    /**
     * @instanceVariable _descend_Person_Number
     * 
     */
    private static int _descend_Person_Number;
    private AtomicBoolean _running = new AtomicBoolean(false);
    private LinkedList<QueueElevator> _temp_Floor_Queue  = new LinkedList<>();
    private QueueElevator _temp_Descend;
    
    public MallExit(){
        this._descend_Person_Number = 0;
    }
    
    
    
    
    @Override
    public void run() {
        _running.set(true);
        while(_running.get()){
            try{
                //System.out.println("Mall Exit Thread waiting to get notified at time :"+ System.currentTimeMillis());
                Thread.sleep(1000);
                _mall_Exit_Thread();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                _running.set(false);
            }
        }
    }
    
    private void _mall_Exit_Thread() throws InterruptedException{
        int _random_Number = (int) (1 + Math.random() * 5);
        _descend_Person_Number = _random_Number;
        _random_Number = (int) (1 + Math.random() * 4 );
        if(_check_Floor_Elevator(_random_Number) ==  -1){
            Thread.sleep(1);
            _mall_Exit_Thread();
        }
    }
    
    private int _check_Floor_Elevator(int _random_Number){
        int _check_Number = 0;
        int _temp_Count = 0;
        _temp_Descend = new QueueElevator(_descend_Person_Number,0,_random_Number);
        
        switch(_random_Number){
            case 1:
                _temp_Floor_Queue.clear();
                _temp_Count = 0;
                _temp_Floor_Queue = (LinkedList)Mall_Main.getFirst_Floor_Elevator_Queue().clone();
                for(QueueElevator _temp : _temp_Floor_Queue)
                    _temp_Count = _temp_Count + _temp.getPerson_Number();
                
                if(Mall_Main.getFirst_Floor_Number_Of_Person() - _temp_Count >= _descend_Person_Number){
                    Mall_Main.getOut_Of_Floor_Elevator_Queue().offer(_temp_Descend);
                    Mall_Main.getFirst_Floor_Elevator_Queue().offer(_temp_Descend);
                    _check_Number = _check_Number + 1;
                }
                break;
            case 2:
                _temp_Floor_Queue.clear();
                _temp_Count = 0;
                _temp_Floor_Queue = (LinkedList)Mall_Main.getSecond_Floor_Elevator_Queue().clone();
                for(QueueElevator _temp : _temp_Floor_Queue)
                    _temp_Count = _temp_Count + _temp.getPerson_Number();
                if(Mall_Main.getSecond_Floor_Number_Of_Person() - _temp_Count >= _descend_Person_Number){
                    Mall_Main.getOut_Of_Floor_Elevator_Queue().offer(_temp_Descend);
                    Mall_Main.getSecond_Floor_Elevator_Queue().offer(_temp_Descend);
                    _check_Number = _check_Number + 1;
                }
                break;
            case 3: 
                _temp_Floor_Queue.clear();
                _temp_Count = 0;
                _temp_Floor_Queue = (LinkedList)Mall_Main.getThird_Floor_Elevator_Queue().clone();
                for(QueueElevator _temp : _temp_Floor_Queue)
                    _temp_Count = _temp_Count + _temp.getPerson_Number();
                if(Mall_Main.getThird_Floor_Number_Of_Person() - _temp_Count >= _descend_Person_Number){
                    Mall_Main.getOut_Of_Floor_Elevator_Queue().offer(_temp_Descend);
                    Mall_Main.getThird_Floor_Elevator_Queue().offer(_temp_Descend);
                    _check_Number = _check_Number + 1;
                }
                break;
            case 4:
                _temp_Floor_Queue.clear();
                _temp_Count = 0;
                _temp_Floor_Queue = (LinkedList)Mall_Main.getFourth_Floor_Elevator_Queue().clone();
                for(QueueElevator _temp : _temp_Floor_Queue)
                    _temp_Count = _temp_Count + _temp.getPerson_Number();
                if(Mall_Main.getFourth_Floor_Number_Of_Person() - _temp_Count >= _descend_Person_Number){
                    Mall_Main.getOut_Of_Floor_Elevator_Queue().offer(_temp_Descend);
                    Mall_Main.getFourth_Floor_Elevator_Queue().offer(_temp_Descend);
                    _check_Number = _check_Number + 1;
                }
                break;
        }
        if(_check_Number == 0)
            return  -1;
        return 0;
    }

    
}
