/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package code;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author ayaz
 */
public class MallLogin implements Runnable {

    
    /**
     * 
     */
    private static int _mount_Person_Number;
    private AtomicBoolean _running = new AtomicBoolean(false);
    private QueueElevator _temp_Mount;
    
    public MallLogin(){
        this._mount_Person_Number = 0;
    }
    
    
    @Override
    public void run() {
       _running.set(true);
       while(_running.get()){
           try{
               _mall_Login_Thread();
               //System.out.println("Mall Login Thread waiting to get notified at time :"+ System.currentTimeMillis());
               Thread.sleep(500);
           }catch(InterruptedException e){
               Thread.currentThread().interrupt();
               _running.set(false);
           }
        }
    }
    
    private void _mall_Login_Thread(){
        int _random_Number = (int)(1 + Math.random() * 10 );
        _mount_Person_Number = _random_Number;
        _random_Number = (int) (1 + Math.random() * 4 );
        _temp_Mount = new QueueElevator(_mount_Person_Number,_random_Number,0);
        Mall_Main.getGround_Floor_Elevator_Queue().offer(_temp_Mount);
        Mall_Main.getOut_Of_Floor_Elevator_Queue().offer(_temp_Mount);
    }
    
}
