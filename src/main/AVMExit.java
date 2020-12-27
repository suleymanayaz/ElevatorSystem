/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author AYAZ
 */

public class AVMExit implements Runnable {
    
    private static  int cıkanInsanSayisi;
    private AtomicBoolean running = new AtomicBoolean(false);
    private LinkedList<Kuyruk> current = new LinkedList();
    Kuyruk cikan;
    
    public AVMExit(){
        cıkanInsanSayisi = 0;
    }
    
    @Override
    public void run(){
        running.set(true);
        while(running.get()){
            try { 
                String name = "Avm Exit Thread";
                //System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
                Thread.sleep(1000);
                cikis();
            }catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                running.set(false);
            }
        }
        
    }

    public void cikis() throws InterruptedException{
        int random = (int) ( 1 + Math.random() * 5);
        cıkanInsanSayisi = random;
        random = (int) (1 + Math.random() * 4 );
        if(kontrol(random) == -1){
            Thread.sleep(1);
            cikis();
        }
    }
    
    
    public int kontrol(int random){
        int kontrol = 0,sayac;
        cikan = new Kuyruk(getCıkanInsanSayisi(),0,random);
        switch(random){
            case 1:
                current.clear();
                sayac  = 0;
                current = (LinkedList)Sistem.getBirinciKatAsansorKuyruk().clone();
                for(Kuyruk temp : current){
                    sayac = sayac + temp.getKisiSayisi();
                } 
                if(Sistem.getBirinciKatKisiSayisi() - sayac >= getCıkanInsanSayisi()){
                    Sistem.getAsansorDisiKuyruk().offer(cikan);
                    Sistem.getBirinciKatAsansorKuyruk().offer(cikan);
                    //System.out.println("Avm Çıkmak İsteyen ["+getCıkanInsanSayisi() +","+random+"]");
                    kontrol++;
                }
                break;
            case 2:
                current.clear();
                sayac  = 0;
                current = (LinkedList)Sistem.getIkinciKatAsansorKuyruk().clone();
                for(Kuyruk temp : current){
                    sayac = sayac + temp.getKisiSayisi();
                }
                if(Sistem.getIkinciKatKisiSayisi() - sayac >= getCıkanInsanSayisi()){
                    Sistem.getAsansorDisiKuyruk().offer(cikan);
                    Sistem.getIkinciKatAsansorKuyruk().offer(cikan);
                    //System.out.println("Avm Çıkmak İsteyen ["+getCıkanInsanSayisi() +","+random+"]");
                    kontrol++;
                 }
                 break;
            case 3:
                current.clear();
                sayac  = 0;
                current = (LinkedList)Sistem.getUcuncuKatAsansorKuyruk().clone();
                for(Kuyruk temp : current){
                    sayac = sayac + temp.getKisiSayisi();
                } 
                if(Sistem.getUcuncuKatKisiSayisi() - sayac >= getCıkanInsanSayisi()){
                    Sistem.getAsansorDisiKuyruk().offer(cikan);
                    Sistem.getUcuncuKatAsansorKuyruk().offer(cikan);
                    //System.out.println("Avm Çıkmak İsteyen ["+getCıkanInsanSayisi() +","+random+"]");
                    kontrol++;
                }
                break;
            case 4:
                current.clear();
                sayac  = 0;
                current = (LinkedList)Sistem.getDorduncuKatAsansorKuyruk().clone();
                for(Kuyruk temp : current){
                    sayac = sayac + temp.getKisiSayisi();
                }
               
                if(Sistem.getDorduncuKatKisiSayisi() - sayac >= getCıkanInsanSayisi()){
                    Sistem.getAsansorDisiKuyruk().offer(cikan);
                    Sistem.getDorduncuKatAsansorKuyruk().offer(cikan);
                    //System.out.println("Avm Çıkmak İsteyen ["+getCıkanInsanSayisi() +","+random+"]");
                    kontrol++;
                }
                break;
        }
        if(kontrol==0){
            return -1;
        }
        return 0;
    }

    
    public static int getCıkanInsanSayisi() {
        return cıkanInsanSayisi;
    }

    /**
     * @param aCıkanInsanSayisi the cıkanInsanSayisi to set
     */
    public static void setCıkanInsanSayisi(int aCıkanInsanSayisi) {
        cıkanInsanSayisi = aCıkanInsanSayisi;
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
