/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;



import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author AYAZ
 */
public class AVMLogin implements Runnable {
    private static int girenInsanSayisi;
    private AtomicBoolean running = new AtomicBoolean(false);
    Kuyruk giren;

    public AVMLogin(){
        girenInsanSayisi = 0;
    }
    
    
    public void run(){
        running.set(true);
        while(running.get()){
            try { 
               giris();
               String name = "Avm Login Thread";
               //System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
               Thread.sleep(500);
            }catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                running.set(false);  
            }
        }
    }

    
    public void giris(){
        int random = (int) (1 + Math.random() * 10);
        girenInsanSayisi = random;
        random = (int) ( 1 + Math.random() * 4);
        giren = new Kuyruk(girenInsanSayisi,random,0);
        Sistem.getZeminKatAsansorKuyruk().offer(giren);
        Sistem.getAsansorDisiKuyruk().offer(giren);
        //System.out.println("Avm Giren ["+getGirenInsanSayisi()+","+random+"]");
    }
    
    
    
    
    /**
     * @return the girenInsanSayisi
     */
    public static int getGirenInsanSayisi() {
        return girenInsanSayisi;
    }

    /**
     * @param aGirenInsanSayisi the girenInsanSayisi to set
     */
    public static void setGirenInsanSayisi(int aGirenInsanSayisi) {
        girenInsanSayisi = aGirenInsanSayisi;
    }

    /**
     * @return the running
     */
    public AtomicBoolean getRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }
    
}
