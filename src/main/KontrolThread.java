/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author AYAZ
 */
public class KontrolThread implements Runnable {
    private final AtomicBoolean running = new AtomicBoolean(false);
    AsansorThread asansor2,asansor3,asansor4,asansor5;
    private static int sayac = 0;
    LinkedList<Kuyruk> liste = new  LinkedList();
    public KontrolThread(AsansorThread asansor2, AsansorThread asansor3, AsansorThread asansor4 , AsansorThread asansor5){
         this.asansor2 = asansor2;
        this.asansor3 = asansor3;
        this.asansor4 = asansor4;
        this.asansor5 = asansor5;
    }
    
    public void run(){
        running.set(true);
        while(running.get()){
              synchronized(Sistem.getAsansorDisiKuyruk()){
                  kontrol();
              }
        }
    }
    
    
    
    public void kontrol(){
        sayac = 0;
        liste.clear();
        liste = (LinkedList)Sistem.getAsansorDisiKuyruk().clone();
        if(liste.peek() !=null){
               for(Kuyruk n : liste){
                   if(n !=null)
                     sayac = sayac + n.getKisiSayisi();
            }
        }
        asansorkontrol(sayac);
    }
            
    public void asansorkontrol(int sayac){
        if(sayac < asansor2.getCapacity()*2 && asansor2.isActive()  && asansor2.getAsansorIciKuyruk().isEmpty()  && asansor2.getCount_inside() == 0){
            asansor2.getRunning().set(false);
            asansor2.setActive(false);asansor2.setMode("idle");asansor2.setFloor(0);
        }else if(sayac >= asansor2.getCapacity()*2 && sayac < asansor2.getCapacity()*3 &&  !asansor2.isActive()){
            asansor2.setActive(true);asansor2.setMode("Working");asansor2.getRunning().set(true);
        }else {
            //sleep();
        }
        

        if(sayac < asansor3.getCapacity()*4 && asansor3.isActive()  && asansor3.getAsansorIciKuyruk().isEmpty() && asansor3.getCount_inside() == 0 ){
            asansor3.getRunning().set(false);
            asansor3.setActive(false);asansor3.setMode("idle");asansor3.setFloor(0);
        }else if(sayac >= asansor3.getCapacity()*4 && sayac < asansor3.getCapacity()*6 &&  !asansor3.isActive()){
            asansor3.setActive(true);asansor3.setMode("Working");asansor3.getRunning().set(true);
        }else{
            //sleep();
        }
        
  
        
        if(sayac < asansor4.getCapacity()*6 && asansor4.isActive() && asansor4.getAsansorIciKuyruk().isEmpty() && asansor4.getCount_inside() == 0 ){
            asansor4.getRunning().set(false);
            asansor4.setActive(false);asansor4.setMode("idle");asansor4.setFloor(0);
        }else if(sayac >= asansor4.getCapacity()*6 && sayac < asansor4.getCapacity()*8 &&  !asansor4.isActive()){
            asansor4.setActive(true);asansor4.setMode("Working");asansor4.getRunning().set(true);
        }else{
            //sleep();
        }
        
        
        if(sayac < asansor5.getCapacity()*8 && asansor5.isActive() && asansor5.getAsansorIciKuyruk().isEmpty() && asansor5.getCount_inside() == 0){
            asansor5.getRunning().set(false);
            asansor5.setActive(false);asansor5.setMode("idle");asansor5.setFloor(0);
        }else if(sayac >= asansor5.getCapacity()*8  &&  !asansor5.isActive()){
            asansor5.setActive(true);asansor5.setMode("Working");asansor5.getRunning().set(true);
        }else{
            //sleep();
        }
        
    }    
      
}
