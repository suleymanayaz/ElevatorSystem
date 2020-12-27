/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author AYAZ
 */
public class Kuyruk {
    private int kisiSayisi = 0;
    private int hedefKat = 0;
    private int suanKat= 0;
    
    public Kuyruk(int kisiSayisi,int hedefKat,int suanKat){
        this.kisiSayisi= kisiSayisi;
        this.hedefKat = hedefKat;
        this.suanKat = suanKat;
    }
    
    public int getKisiSayisi(){
        return this.kisiSayisi;
    }
    
    public int getSuanKat(){
        return this.suanKat;
    }
    public int getHedefKat(){
        return this.hedefKat;
    }
}
