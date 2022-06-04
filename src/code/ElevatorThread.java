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
public class ElevatorThread implements Runnable{
    
    /**
     * @instanceVariable _active determines if the elevator is active
     * @instanceVariable _current_Floor floor with elevator
     * @instanceVariable _destination_Floor  target floor of the elevator
     * @instanceVariable _capacity_Elevator  capacity of the elevator
     * @instanceVariable _count_Inside number of people in the elevator
     */
    private boolean _active;
    private int _current_Floor;
    private int _destination_Floor;
    private int _capacity_Elevator;
    private int _count_Inside;
    
    /**
     * @instanceVariable _inside_Elevator_Queue  
     * @instanceVariable _direction
     * @instanceVariable _mode_Elevator
     * @instanceVariable _name_Elevator
     * @instanceVariable _running 
     */
    private LinkedList<QueueElevator> _inside_Elevator_Queue;
    private String _direction;
    private String _mode_Elevator;
    private String _name_Elevator;
    private AtomicBoolean _running = new AtomicBoolean(false);
    
    
    public LinkedList<QueueElevator> _temp_Queue = new LinkedList<>();
    public LinkedList<QueueElevator> _descend_Queue = new LinkedList<>();
    public LinkedList<QueueElevator> _mount_Queue = new LinkedList<>();
    
        
    
    public ElevatorThread (){
        this._active = false;
        this._mode_Elevator = "idle";
        this._destination_Floor = 0;
        this._current_Floor = 0;
        this._direction = "null";
        this._capacity_Elevator = 10;
        this._count_Inside = 0;
        this._inside_Elevator_Queue = new LinkedList<>();
        this._name_Elevator = "Elevator_!";
        Thread.currentThread().setName(_name_Elevator);
        _running.set(true);
    }
    public ElevatorThread(String _name_Elevator){
        this._active = false;
        this._mode_Elevator = "idle";
        this._destination_Floor = 0;
        this._current_Floor = 0;
        this._direction = "null";
        this._capacity_Elevator = 10;
        this._count_Inside = 0;
        this._inside_Elevator_Queue = new LinkedList<>();
        this._name_Elevator = _name_Elevator;
        Thread.currentThread().setName(_name_Elevator);
        _running.set(false);
    }

    @Override
    public void run() {
        
        if(getRunning().get()){
            while(getRunning().get()){
                if( isActive() == false ){
                    _initial_Elevator();
                    if(!Mall_Main.getOut_Of_Floor_Elevator_Queue().isEmpty())
                        _initial_Work_Elevator_Move();
                }
                else
                    _move_Elevator();
            }
        }else{
            while(true){
                while(getRunning().get()){
                    if(isActive() == false)
                        _initial_Elevator();
                    else
                        if(getRunning().get()) _move_Elevator();
                }
            }
        }

    }
    
    private void _print_System_Floor_State(){
        _temp_Queue.clear();
        int _temp_counter_person = 0;
        System.out.print("0.floor : all : "+Mall_Main.getGround_Floor_Number_Of_Person()+ " queue: ");
        _temp_Queue = (LinkedList)Mall_Main.getGround_Floor_Elevator_Queue().clone();
        for (QueueElevator _pe: _temp_Queue)
            _temp_counter_person = _temp_counter_person + _pe.getPerson_Number();
        System.out.println(_temp_counter_person);
        
        _temp_counter_person = 0;
        _temp_Queue.clear();
        System.out.print("1.floor : all : "+Mall_Main.getFirst_Floor_Number_Of_Person() +" queue : ");
        _temp_Queue = (LinkedList)Mall_Main.getFirst_Floor_Elevator_Queue().clone();
        for (QueueElevator _pe: _temp_Queue)
            _temp_counter_person = _temp_counter_person + _pe.getPerson_Number();
        System.out.println(_temp_counter_person);
        
        _temp_counter_person = 0;
        _temp_Queue.clear();
        System.out.print("2.floor : all : "+Mall_Main.getSecond_Floor_Number_Of_Person() +" queue : ");
        _temp_Queue = (LinkedList)Mall_Main.getSecond_Floor_Elevator_Queue().clone();
        for (QueueElevator _pe: _temp_Queue)
            _temp_counter_person = _temp_counter_person + _pe.getPerson_Number();
        System.out.println(_temp_counter_person);
        
        _temp_counter_person = 0;
        _temp_Queue.clear();
        System.out.print("3.floor : all : "+Mall_Main.getThird_Floor_Number_Of_Person() +" queue : ");
        _temp_Queue = (LinkedList)Mall_Main.getThird_Floor_Elevator_Queue().clone();
        for (QueueElevator _pe: _temp_Queue)
            _temp_counter_person = _temp_counter_person + _pe.getPerson_Number();
        System.out.println(_temp_counter_person);
        
        _temp_counter_person = 0;
        _temp_Queue.clear();
        System.out.print("4.floor : all : "+Mall_Main.getFourth_Floor_Number_Of_Person() +" queue : ");
        _temp_Queue = (LinkedList)Mall_Main.getFourth_Floor_Elevator_Queue().clone();
        for (QueueElevator _pe: _temp_Queue)
            _temp_counter_person = _temp_counter_person + _pe.getPerson_Number();
        System.out.println(_temp_counter_person);
        System.out.println("exit Person count : "  + Mall_Main.getExit_Number_Of_Person() );
        
    }
    
    private void _move_Elevator(){
        QueueElevator _temp_current_Thread_Elevator_out_Queue = null;
        
        if(Mall_Main.getOut_Of_Floor_Elevator_Queue().peek() != null && _active){
            _temp_current_Thread_Elevator_out_Queue = Mall_Main.getOut_Of_Floor_Elevator_Queue().peek();
            _destination_Floor = _temp_current_Thread_Elevator_out_Queue.getCurrent_Floor();
            if(getCurrent_Floor() > getDestination_Floor() ){
                _direction = "down";
                while(getCurrent_Floor() != getDestination_Floor() ){
                    if(!_active)
                        break;
                    _print_System_State();
                    _thread_Elevator_Sleep();
                    if(!_active)
                        break;
                    if(getCurrent_Floor() -1 < 0)
                        break;
                    setCurrent_Floor(getCurrent_Floor() - 1) ;
                    
                }
            } else if (getCurrent_Floor() < getDestination_Floor() ){
                _direction = "up";
                while(getCurrent_Floor() != getDestination_Floor()){
                    if(!_active)
                        break;
                    _print_System_State();
                    _thread_Elevator_Sleep();
                    if(!_active)
                        break;
                    setCurrent_Floor(getCurrent_Floor() + 1);
                }
            }
            if(getInside_Elevator_Queue().isEmpty() && _active){
                if(getCurrent_Floor() == 0 && Mall_Main.getGround_Floor_Elevator_Queue().peek() != null && _active){
                 _descend_Queue.clear();
                 _mount_Queue.clear();
                 _mount_Queue = (LinkedList)Mall_Main.getGround_Floor_Elevator_Queue().clone();
                 for(QueueElevator _temp_que : _mount_Queue){
                     if(getCount_Inside() + _temp_que.getPerson_Number() <= getCapacity_Elevator() && _active){
                            getInside_Elevator_Queue().offer(_temp_que);
                         _descend_Queue.offer(_temp_que);
                         _count_Inside  = getCount_Inside() + _temp_que.getPerson_Number();
                         
                     }
                 }
                 for(QueueElevator _temp_pe : _descend_Queue){
                     Mall_Main.getGround_Floor_Elevator_Queue().remove(_temp_pe);
                     if(Mall_Main.getOut_Of_Floor_Elevator_Queue().contains(_temp_pe))
                         Mall_Main.getOut_Of_Floor_Elevator_Queue().remove(_temp_pe);
                 }
                }
            }else if (getCurrent_Floor() == 1 && Mall_Main.getFirst_Floor_Elevator_Queue().peek() != null && _active){
                 _descend_Queue.clear();
                 _mount_Queue.clear();
                 _mount_Queue = (LinkedList)Mall_Main.getFirst_Floor_Elevator_Queue().clone();
                 for(QueueElevator _temp_que : _mount_Queue){
                     if(getCount_Inside() + _temp_que.getPerson_Number() <= getCapacity_Elevator() && _active){
                         getInside_Elevator_Queue().offer(_temp_que);
                         _descend_Queue.offer(_temp_que);
                         _count_Inside  = getCount_Inside() + _temp_que.getPerson_Number();
                         
                     }
                 }
                 Mall_Main.setFirst_Floor_Number_Of_Person(Mall_Main.getFirst_Floor_Number_Of_Person() - getCount_Inside());
                 for(QueueElevator _temp_pe : _descend_Queue){
                     Mall_Main.getFirst_Floor_Elevator_Queue().remove(_temp_pe);
                     if(Mall_Main.getOut_Of_Floor_Elevator_Queue().contains(_temp_pe))
                         Mall_Main.getOut_Of_Floor_Elevator_Queue().remove(_temp_pe);
                 }
            }else if (getCurrent_Floor() == 2 && Mall_Main.getSecond_Floor_Elevator_Queue().peek() != null && _active){
                _descend_Queue.clear();
                 _mount_Queue.clear();
                 _mount_Queue = (LinkedList)Mall_Main.getSecond_Floor_Elevator_Queue().clone();
                 for(QueueElevator _temp_que : _mount_Queue){
                     if(getCount_Inside() + _temp_que.getPerson_Number() <= getCapacity_Elevator() && _active){
                         getInside_Elevator_Queue().offer(_temp_que);
                         _descend_Queue.offer(_temp_que);
                         _count_Inside  = getCount_Inside() + _temp_que.getPerson_Number();
                         
                     }
                 }
                 Mall_Main.setSecond_Floor_Number_Of_Person(Mall_Main.getSecond_Floor_Number_Of_Person() - getCount_Inside());
                 for(QueueElevator _temp_pe : _descend_Queue){
                     Mall_Main.getSecond_Floor_Elevator_Queue().remove(_temp_pe);
                     if(Mall_Main.getOut_Of_Floor_Elevator_Queue().contains(_temp_pe))
                         Mall_Main.getOut_Of_Floor_Elevator_Queue().remove(_temp_pe);
                 }
            }else if (getCurrent_Floor() == 3 && Mall_Main.getThird_Floor_Elevator_Queue().peek() != null && _active){
                 _descend_Queue.clear();
                 _mount_Queue.clear();
                 _mount_Queue = (LinkedList)Mall_Main.getThird_Floor_Elevator_Queue().clone();
                 for(QueueElevator _temp_que : _mount_Queue){
                     if(getCount_Inside() + _temp_que.getPerson_Number() <= getCapacity_Elevator() && _active){
                         getInside_Elevator_Queue().offer(_temp_que);
                         _descend_Queue.offer(_temp_que);
                         _count_Inside  = getCount_Inside() + _temp_que.getPerson_Number();
                         
                     }
                 }
                 Mall_Main.setThird_Floor_Number_Of_Person(Mall_Main.getThird_Floor_Number_Of_Person() - getCount_Inside());
                 for(QueueElevator _temp_pe : _descend_Queue){
                     Mall_Main.getThird_Floor_Elevator_Queue().remove(_temp_pe);
                     if(Mall_Main.getOut_Of_Floor_Elevator_Queue().contains(_temp_pe))
                         Mall_Main.getOut_Of_Floor_Elevator_Queue().remove(_temp_pe);
                 }
            } else if (getCurrent_Floor() == 4 && Mall_Main.getFourth_Floor_Elevator_Queue().peek() != null && _active){
                 _descend_Queue.clear();
                 _mount_Queue.clear();
                 _mount_Queue = (LinkedList)Mall_Main.getFourth_Floor_Elevator_Queue().clone();
                 for(QueueElevator _temp_que : _mount_Queue){
                     if(getCount_Inside() + _temp_que.getPerson_Number() <= getCapacity_Elevator() && _active){
                         getInside_Elevator_Queue().offer(_temp_que);
                         _descend_Queue.offer(_temp_que);
                         _count_Inside  = getCount_Inside() + _temp_que.getPerson_Number();
                         
                     }
                 }
                 Mall_Main.setFourth_Floor_Number_Of_Person(Mall_Main.getFourth_Floor_Number_Of_Person() - getCount_Inside());
                 for(QueueElevator _temp_pe : _descend_Queue){
                     Mall_Main.getFourth_Floor_Elevator_Queue().remove(_temp_pe);
                     if(Mall_Main.getOut_Of_Floor_Elevator_Queue().contains(_temp_pe))
                         Mall_Main.getOut_Of_Floor_Elevator_Queue().remove(_temp_pe);
                 }
            }
            
        }
        
        if (!_inside_Elevator_Queue.isEmpty()){
            int _max_Floor  = 0;
            if (getInside_Elevator_Queue().size() > 1){
                for(QueueElevator _temp : getInside_Elevator_Queue()){
                    if(_temp.getDestination_Floor() > _max_Floor){
                        _max_Floor = _temp.getDestination_Floor();
                    }
                }
                _destination_Floor = _max_Floor ;
                
            }else if ( getInside_Elevator_Queue().peek()!=null){
                _destination_Floor  = getInside_Elevator_Queue().peek().getDestination_Floor();
            }
            if(getCurrent_Floor() > getDestination_Floor() && _active){
                _direction = "down";
                while(getCurrent_Floor() != getDestination_Floor()){
                    if(!_active)
                        break;
                    _print_System_State();
                    _control_Floor_State(getCurrent_Floor());
                    _thread_Elevator_Sleep();
                    if(!_active)
                        break;
                    if(getCurrent_Floor() -1 < 0)
                        break;
                    setCurrent_Floor(getCurrent_Floor() - 1) ;
                    
                }
            }else if (getCurrent_Floor() < getDestination_Floor() && _active){
                _direction = "up";
                while(getCurrent_Floor() != getDestination_Floor()){
                    if(!_active)
                        break;
                    _print_System_State();
                    _control_Floor_State(getCurrent_Floor());
                    _thread_Elevator_Sleep();
                    if(!_active)
                        break;
                    if(getCurrent_Floor() +1 > 4)
                        break;
                    setCurrent_Floor(getCurrent_Floor() + 1);
                    
                }
            }else{
                _control_Floor_State(getCurrent_Floor());
                _print_System_State();
            }
            _control_Floor_State(getCurrent_Floor());
            _print_System_State();
        }
    }
    
    private void _control_Floor_State(int _current_Floor){
    
        QueueElevator _temp_current_Thread_Elevator_Inside_Queue = null;
        QueueElevator _temp_2_current_Thread_Elevator_Inside_Queue = null;
        _descend_Queue.clear();
        if(_active){
            int _inside_queue_size = getInside_Elevator_Queue().size();
            for(int index = 0 ; index < _inside_queue_size ; index++){
                _temp_current_Thread_Elevator_Inside_Queue = getInside_Elevator_Queue().get(index);
                if(_temp_current_Thread_Elevator_Inside_Queue.getDestination_Floor() == _current_Floor){
                    _descend_Queue.add(_temp_current_Thread_Elevator_Inside_Queue);
                    switch(_current_Floor){
                        case 0:
                            _count_Inside = getCount_Inside() - _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number();
                            Mall_Main.setExit_Number_Of_Person(Mall_Main.getExit_Number_Of_Person() + _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number());
                            _temp_2_current_Thread_Elevator_Inside_Queue = _temp_current_Thread_Elevator_Inside_Queue;
                            break;
                        case 1:
                            Mall_Main.setFirst_Floor_Number_Of_Person(Mall_Main.getFirst_Floor_Number_Of_Person()+ _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number());
                            _count_Inside = getCount_Inside() - _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number();
                            break;
                        case 2:
                            Mall_Main.setSecond_Floor_Number_Of_Person(Mall_Main.getSecond_Floor_Number_Of_Person()+ _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number());
                            _count_Inside = getCount_Inside() - _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number();
                            break;
                        case 3:
                            Mall_Main.setThird_Floor_Number_Of_Person(Mall_Main.getThird_Floor_Number_Of_Person()+ _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number());
                            _count_Inside = getCount_Inside() - _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number();
                            break;
                        case 4:
                            Mall_Main.setFourth_Floor_Number_Of_Person(Mall_Main.getFourth_Floor_Number_Of_Person()+ _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number());
                            _count_Inside = getCount_Inside() - _temp_current_Thread_Elevator_Inside_Queue.getPerson_Number();
                            break;
                        default:
                            break;
                            
                    }
                }
            }
            if(_temp_2_current_Thread_Elevator_Inside_Queue != null){
                Mall_Main.getOut_Of_Floor_Elevator_Queue().remove(_temp_2_current_Thread_Elevator_Inside_Queue);
            }
            for(QueueElevator _temp : _descend_Queue){
                getInside_Elevator_Queue().remove(_temp);
            }
            
        }
    }
    
    
    
    private void _initial_Work_Elevator_Move(){
        getInside_Elevator_Queue().offer(Mall_Main.getOut_Of_Floor_Elevator_Queue().peek());
       _count_Inside = getCount_Inside() + getInside_Elevator_Queue().peek().getPerson_Number();
       Mall_Main.getGround_Floor_Elevator_Queue().remove(getInside_Elevator_Queue().peek());
       _destination_Floor = getInside_Elevator_Queue().peek().getDestination_Floor();
       _direction = "up";
       while(getCurrent_Floor() != getDestination_Floor()){
           _print_System_State();
           _thread_Elevator_Sleep();
            setCurrent_Floor(getCurrent_Floor() + 1) ;
       }
       _print_System_State();
       
       QueueElevator _getting_Off_Elevator;
       
       switch(getDestination_Floor()){
           case 1: 
               _getting_Off_Elevator = getInside_Elevator_Queue().poll();
               Mall_Main.setFirst_Floor_Number_Of_Person( Mall_Main.getFirst_Floor_Number_Of_Person() + _getting_Off_Elevator.getPerson_Number());
               _count_Inside = getCount_Inside() - _getting_Off_Elevator.getPerson_Number();
               break;
           case 2:
               _getting_Off_Elevator = getInside_Elevator_Queue().poll();
               Mall_Main.setSecond_Floor_Number_Of_Person( Mall_Main.getSecond_Floor_Number_Of_Person() + _getting_Off_Elevator.getPerson_Number());
               _count_Inside = getCount_Inside() - _getting_Off_Elevator.getPerson_Number();
               break;
           case 3:
               _getting_Off_Elevator = getInside_Elevator_Queue().poll();
               Mall_Main.setThird_Floor_Number_Of_Person( Mall_Main.getThird_Floor_Number_Of_Person() + _getting_Off_Elevator.getPerson_Number());
               _count_Inside = getCount_Inside() - _getting_Off_Elevator.getPerson_Number();
               break;
           case 4:
               _getting_Off_Elevator = getInside_Elevator_Queue().poll();
               Mall_Main.setFourth_Floor_Number_Of_Person( Mall_Main.getFourth_Floor_Number_Of_Person() + _getting_Off_Elevator.getPerson_Number());
               _count_Inside = getCount_Inside() - _getting_Off_Elevator.getPerson_Number();
               break;
       } 
    }
    
    
    private void _thread_Elevator_Sleep(){
        try{
           //System.out.println(_name_Elevator + "Thread" + "waiting to get notified at time : " + System.currentTimeMillis());
           Thread.sleep(200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    private void _print_System_State(){
        LinkedList<QueueElevator> _temp_current_Thread_Elevator_Inside_Queue = new LinkedList<>();
        if(Thread.currentThread().getName().equals("Thread-3")){
            _print_System_Floor_State();
            System.out.println("active :"+ Mall_Main.getElevator_1()._active);
            System.out.println("\t\t mode :"+ Mall_Main.getElevator_1()._mode_Elevator);
            System.out.println("\t\t floor : "+ Mall_Main.getElevator_1().getCurrent_Floor());
            System.out.println("\t\t destination : "+Mall_Main.getElevator_1().getDestination_Floor());
            System.out.println("\t\t direction : "+Mall_Main.getElevator_1().getDirection());
            System.out.println("\t\t capacity : "+ Mall_Main.getElevator_1().getCapacity_Elevator());
            System.out.println("\t\t count_inside : "+ Mall_Main.getElevator_1().getCount_Inside());
            System.out.print("\t\t inside :[");
            if(getInside_Elevator_Queue().peek() != null){
                _temp_current_Thread_Elevator_Inside_Queue = (LinkedList)Mall_Main.getElevator_1().getInside_Elevator_Queue().clone();
                for(QueueElevator _pe : _temp_current_Thread_Elevator_Inside_Queue)
                    System.out.print("["+_pe.getPerson_Number()+","+_pe.getDestination_Floor()+"] ,");
            }
            
            System.out.println("]\nactive : "+Mall_Main.getElevator_2()._active);
            System.out.println("\t\t mode :"+ Mall_Main.getElevator_2()._mode_Elevator);
             System.out.println("\t\t floor : "+ Mall_Main.getElevator_2().getCurrent_Floor());
            System.out.println("\t\t destination : "+Mall_Main.getElevator_2().getDestination_Floor());
            System.out.println("\t\t direction : "+Mall_Main.getElevator_2().getDirection());
            System.out.println("\t\t capacity : "+ Mall_Main.getElevator_2().getCapacity_Elevator());
            System.out.println("\t\t count_inside : "+ Mall_Main.getElevator_2().getCount_Inside());
            System.out.print("\t\t inside :[");
            if(getInside_Elevator_Queue().peek() != null){
                _temp_current_Thread_Elevator_Inside_Queue = (LinkedList)Mall_Main.getElevator_2().getInside_Elevator_Queue().clone();
                for(QueueElevator _pe : _temp_current_Thread_Elevator_Inside_Queue)
                    System.out.print("["+_pe.getPerson_Number()+","+_pe.getDestination_Floor()+"] ,");
            }
            System.out.println("]");
            System.out.println("\nactive : "+Mall_Main.getElevator_3()._active);
            System.out.println("\t\t mode :"+ Mall_Main.getElevator_3()._mode_Elevator);
             System.out.println("\t\t floor : "+ Mall_Main.getElevator_3().getCurrent_Floor());
            System.out.println("\t\t destination : "+Mall_Main.getElevator_3().getDestination_Floor());
            System.out.println("\t\t direction : "+Mall_Main.getElevator_3().getDirection());
            System.out.println("\t\t capacity : "+ Mall_Main.getElevator_3().getCapacity_Elevator());
            System.out.println("\t\t count_inside : "+ Mall_Main.getElevator_3().getCount_Inside());
            System.out.print("\t\t inside :[");
            if(getInside_Elevator_Queue().peek() != null){
                _temp_current_Thread_Elevator_Inside_Queue = (LinkedList)Mall_Main.getElevator_3().getInside_Elevator_Queue().clone();
                for(QueueElevator _pe : _temp_current_Thread_Elevator_Inside_Queue)
                    System.out.print("["+_pe.getPerson_Number()+","+_pe.getDestination_Floor()+"] ,");
            }
            System.out.println("]");

            System.out.println("\nactive : "+Mall_Main.getElevator_4()._active);
            System.out.println("\t\t mode :"+ Mall_Main.getElevator_4()._mode_Elevator);
             System.out.println("\t\t floor : "+ Mall_Main.getElevator_4().getCurrent_Floor());
            System.out.println("\t\t destination : "+Mall_Main.getElevator_4().getDestination_Floor());
            System.out.println("\t\t direction : "+Mall_Main.getElevator_4().getDirection());
            System.out.println("\t\t capacity : "+ Mall_Main.getElevator_4().getCapacity_Elevator());
            System.out.println("\t\t count_inside : "+ Mall_Main.getElevator_4().getCount_Inside());
            System.out.print("\t\t inside :[");
            if(getInside_Elevator_Queue().peek() != null){
                _temp_current_Thread_Elevator_Inside_Queue = (LinkedList)Mall_Main.getElevator_4().getInside_Elevator_Queue().clone();
                for(QueueElevator _pe : _temp_current_Thread_Elevator_Inside_Queue)
                    System.out.print("["+_pe.getPerson_Number()+","+_pe.getDestination_Floor()+"] ,");
            }
            System.out.println("]");

            System.out.println("\nactive : "+Mall_Main.getElevator_5()._active);
            System.out.println("\t\t mode :"+ Mall_Main.getElevator_5()._mode_Elevator);
             System.out.println("\t\t floor : "+ Mall_Main.getElevator_5().getCurrent_Floor());
            System.out.println("\t\t destination : "+Mall_Main.getElevator_5().getDestination_Floor());
            System.out.println("\t\t direction : "+Mall_Main.getElevator_5().getDirection());
            System.out.println("\t\t capacity : "+ Mall_Main.getElevator_5().getCapacity_Elevator());
            System.out.println("\t\t count_inside : "+ Mall_Main.getElevator_5().getCount_Inside());
            System.out.print("\t\t inside :[");
            if(getInside_Elevator_Queue().peek() != null){
                _temp_current_Thread_Elevator_Inside_Queue = (LinkedList)Mall_Main.getElevator_5().getInside_Elevator_Queue().clone();
                for(QueueElevator _pe : _temp_current_Thread_Elevator_Inside_Queue)
                    System.out.print("["+_pe.getPerson_Number()+","+_pe.getDestination_Floor()+"] ,");
            }
            
            System.out.println("]");
            _temp_Queue = (LinkedList)Mall_Main.getGround_Floor_Elevator_Queue().clone();
            System.out.print("0.floor : [");
            for(QueueElevator _pe : _temp_Queue)
                System.out.print("["+_pe.getPerson_Number() + ","+_pe.getDestination_Floor()+"], ");
            System.out.println("]");
            
            _temp_Queue.clear();
            _temp_Queue = (LinkedList)Mall_Main.getFirst_Floor_Elevator_Queue().clone();
            System.out.print("1.floor : [");
            for(QueueElevator _pe : _temp_Queue)
                System.out.print("["+_pe.getPerson_Number() + ","+_pe.getDestination_Floor()+"], ");
            System.out.println("]");
            
            _temp_Queue.clear();
            _temp_Queue = (LinkedList)Mall_Main.getSecond_Floor_Elevator_Queue().clone();
            System.out.print("2.floor : [");
            for(QueueElevator _pe : _temp_Queue)
                System.out.print("["+_pe.getPerson_Number() + ","+_pe.getDestination_Floor()+"], ");
            System.out.println("]");
            
            _temp_Queue.clear();
            _temp_Queue = (LinkedList)Mall_Main.getThird_Floor_Elevator_Queue().clone();
            System.out.print("3.floor : [");
            for(QueueElevator _pe : _temp_Queue)
                System.out.print("["+_pe.getPerson_Number() + ","+_pe.getDestination_Floor()+"], ");
            System.out.println("]");
            
            _temp_Queue.clear();
            _temp_Queue = (LinkedList)Mall_Main.getFourth_Floor_Elevator_Queue().clone();
            System.out.print("4.floor : [");
            for(QueueElevator _pe : _temp_Queue)
                System.out.print("["+_pe.getPerson_Number() + ","+_pe.getDestination_Floor()+"], ");
            System.out.println("]\n");
            
        }   
        
        
    }
    
  
    
    
    private void _initial_Elevator(){
        setActive(true);
        setMode_Elevator("Working");
    }

    /**
     * @return the _active
     */
    public boolean isActive() {
        return _active;
    }

    /**
     * @param _active the _active to set
     */
    public void setActive(boolean _active) {
        this._active = _active;
    }

    /**
     * @return the _mode_Elevator
     */
    public String getMode_Elevator() {
        return _mode_Elevator;
    }

    /**
     * @param _mode_Elevator the _mode_Elevator to set
     */
    public void setMode_Elevator(String _mode_Elevator) {
        this._mode_Elevator = _mode_Elevator;
    }

    /**
     * @return the _capacity_Elevator
     */
    public int getCapacity_Elevator() {
        return _capacity_Elevator;
    }

    /**
     * @return the _inside_Elevator_Queue
     */
    public LinkedList<QueueElevator> getInside_Elevator_Queue() {
        return _inside_Elevator_Queue;
    }

    /**
     * @return the _count_Inside
     */
    public int getCount_Inside() {
        return _count_Inside;
    }

    /**
     * @return the _running
     */
    public AtomicBoolean getRunning() {
        return _running;
    }

    /**
     * @return the _current_Floor
     */
    public int getCurrent_Floor() {
        return _current_Floor;
    }

    /**
     * @param _current_Floor the _current_Floor to set
     */
    public void setCurrent_Floor(int _current_Floor) {
        this._current_Floor = _current_Floor;
    }

    /**
     * @return the _destination_Floor
     */
    public int getDestination_Floor() {
        return _destination_Floor;
    }

    /**
     * @return the _direction
     */
    public String getDirection() {
        return _direction;
    }

    /**
     * @return the _name_Elevator
     */
    public String getName_Elevator() {
        return _name_Elevator;
    }
    

}
