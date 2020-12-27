/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;

/**
 *
 * @author AYAZ
 */
public class Sistem {
    private static LinkedList<Kuyruk> zeminKatAsansorKuyruk = new LinkedList<>();
    private static LinkedList<Kuyruk> birinciKatAsansorKuyruk = new LinkedList<>();
    private static LinkedList<Kuyruk> ikinciKatAsansorKuyruk = new LinkedList<>();
    private static LinkedList<Kuyruk> ucuncuKatAsansorKuyruk = new LinkedList<>();
    private static LinkedList<Kuyruk> dorduncuKatAsansorKuyruk = new LinkedList<>();
    private static LinkedList<Kuyruk> AsansorDisiKuyruk = new LinkedList<>();
    private static int zeminKatKisiSayisi = 0;
    private static int birinciKatKisiSayisi = 0;
    private static int ikinciKatKisiSayisi = 0;
    private static int ucuncuKatKisiSayisi = 0;
    private static int dorduncuKatKisiSayisi = 0;
    private static int exitcount = 0;
    private static AsansorThread asansor1;
    private static AsansorThread asansor2;
    private static AsansorThread asansor3;
    private static AsansorThread asansor4;
    private static AsansorThread asansor5;
    
    public static void main(String[] args) {
      Thread avmlogin = new Thread(new AVMLogin());
      Thread avmexit = new Thread(new AVMExit());
      asansor1 = new AsansorThread();
      asansor2 = new AsansorThread("Asansor2");
      asansor3 = new AsansorThread("Asansor3");
      asansor4 = new AsansorThread("Asansor4");
      asansor5 = new AsansorThread("Asansor5");
      Thread asansor1t = new Thread(asansor1);
      Thread asansor2t = new Thread(asansor2);
      Thread asansor3t = new Thread(asansor3);
      Thread asansor4t = new Thread(asansor4);
      Thread asansor5t = new Thread(asansor5);
      Thread kontrolt = new Thread(new KontrolThread(asansor2,asansor3,asansor4,asansor5));
      
      avmlogin.start();
      avmexit.start();
      asansor1t.start();
      asansor2t.start();
      asansor3t.start();
      asansor4t.start();
      asansor5t.start();
      kontrolt.start();
    }

    /**
     * @return the zeminKatAsansorKuyruk
     */
    public static LinkedList<Kuyruk> getZeminKatAsansorKuyruk() {
        return zeminKatAsansorKuyruk;
    }

    /**
     * @param aZeminKatAsansorKuyruk the zeminKatAsansorKuyruk to set
     */
    public static void setZeminKatAsansorKuyruk(LinkedList<Kuyruk> aZeminKatAsansorKuyruk) {
        zeminKatAsansorKuyruk = aZeminKatAsansorKuyruk;
    }

    /**
     * @return the birinciKatAsansorKuyruk
     */
    public static LinkedList<Kuyruk> getBirinciKatAsansorKuyruk() {
        return birinciKatAsansorKuyruk;
    }

    /**
     * @param aBirinciKatAsansorKuyruk the birinciKatAsansorKuyruk to set
     */
    public static void setBirinciKatAsansorKuyruk(LinkedList<Kuyruk> aBirinciKatAsansorKuyruk) {
        birinciKatAsansorKuyruk = aBirinciKatAsansorKuyruk;
    }

    /**
     * @return the ikinciKatAsansorKuyruk
     */
    public static LinkedList<Kuyruk> getIkinciKatAsansorKuyruk() {
        return ikinciKatAsansorKuyruk;
    }

    /**
     * @param aIkinciKatAsansorKuyruk the ikinciKatAsansorKuyruk to set
     */
    public static void setIkinciKatAsansorKuyruk(LinkedList<Kuyruk> aIkinciKatAsansorKuyruk) {
        ikinciKatAsansorKuyruk = aIkinciKatAsansorKuyruk;
    }

    /**
     * @return the ucuncuKatAsansorKuyruk
     */
    public static LinkedList<Kuyruk> getUcuncuKatAsansorKuyruk() {
        return ucuncuKatAsansorKuyruk;
    }

    /**
     * @param aUcuncuKatAsansorKuyruk the ucuncuKatAsansorKuyruk to set
     */
    public static void setUcuncuKatAsansorKuyruk(LinkedList<Kuyruk> aUcuncuKatAsansorKuyruk) {
        ucuncuKatAsansorKuyruk = aUcuncuKatAsansorKuyruk;
    }

    /**
     * @return the dorduncuKatAsansorKuyruk
     */
    public static LinkedList<Kuyruk> getDorduncuKatAsansorKuyruk() {
        return dorduncuKatAsansorKuyruk;
    }

    /**
     * @param aDorduncuKatAsansorKuyruk the dorduncuKatAsansorKuyruk to set
     */
    public static void setDorduncuKatAsansorKuyruk(LinkedList<Kuyruk> aDorduncuKatAsansorKuyruk) {
        dorduncuKatAsansorKuyruk = aDorduncuKatAsansorKuyruk;
    }

    /**
     * @return the AsansorDisiKuyruk
     */
    public static LinkedList<Kuyruk> getAsansorDisiKuyruk() {
        return AsansorDisiKuyruk;
    }

    /**
     * @param aAsansorDisiKuyruk the AsansorDisiKuyruk to set
     */
    public static void setAsansorDisiKuyruk(LinkedList<Kuyruk> aAsansorDisiKuyruk) {
        AsansorDisiKuyruk = aAsansorDisiKuyruk;
    }

    /**
     * @return the zeminKatKisiSayisi
     */
    public static int getZeminKatKisiSayisi() {
        return zeminKatKisiSayisi;
    }

    /**
     * @param aZeminKatKisiSayisi the zeminKatKisiSayisi to set
     */
    public static void setZeminKatKisiSayisi(int aZeminKatKisiSayisi) {
        zeminKatKisiSayisi = aZeminKatKisiSayisi;
    }

    /**
     * @return the birinciKatKisiSayisi
     */
    public static int getBirinciKatKisiSayisi() {
        return birinciKatKisiSayisi;
    }

    /**
     * @param aBirinciKatKisiSayisi the birinciKatKisiSayisi to set
     */
    public static void setBirinciKatKisiSayisi(int aBirinciKatKisiSayisi) {
        birinciKatKisiSayisi = aBirinciKatKisiSayisi;
    }

    /**
     * @return the ikinciKatKisiSayisi
     */
    public static int getIkinciKatKisiSayisi() {
        return ikinciKatKisiSayisi;
    }

    /**
     * @param aIkinciKatKisiSayisi the ikinciKatKisiSayisi to set
     */
    public static void setIkinciKatKisiSayisi(int aIkinciKatKisiSayisi) {
        ikinciKatKisiSayisi = aIkinciKatKisiSayisi;
    }

    /**
     * @return the ucuncuKatKisiSayisi
     */
    public static int getUcuncuKatKisiSayisi() {
        return ucuncuKatKisiSayisi;
    }

    /**
     * @param aUcuncuKatKisiSayisi the ucuncuKatKisiSayisi to set
     */
    public static void setUcuncuKatKisiSayisi(int aUcuncuKatKisiSayisi) {
        ucuncuKatKisiSayisi = aUcuncuKatKisiSayisi;
    }

    /**
     * @return the dorduncuKatKisiSayisi
     */
    public static int getDorduncuKatKisiSayisi() {
        return dorduncuKatKisiSayisi;
    }

    /**
     * @param aDorduncuKatKisiSayisi the dorduncuKatKisiSayisi to set
     */
    public static void setDorduncuKatKisiSayisi(int aDorduncuKatKisiSayisi) {
        dorduncuKatKisiSayisi = aDorduncuKatKisiSayisi;
    }

    /**
     * @return the asansor1
     */
    public static AsansorThread getAsansor1() {
        return asansor1;
    }

    /**
     * @param aAsansor1 the asansor1 to set
     */
    public static void setAsansor1(AsansorThread aAsansor1) {
        asansor1 = aAsansor1;
    }

    /**
     * @return the asansor2
     */
    public static AsansorThread getAsansor2() {
        return asansor2;
    }

    /**
     * @param aAsansor2 the asansor2 to set
     */
    public static void setAsansor2(AsansorThread aAsansor2) {
        asansor2 = aAsansor2;
    }

    /**
     * @return the asansor3
     */
    public static AsansorThread getAsansor3() {
        return asansor3;
    }

    /**
     * @param aAsansor3 the asansor3 to set
     */
    public static void setAsansor3(AsansorThread aAsansor3) {
        asansor3 = aAsansor3;
    }

    /**
     * @return the asansor4
     */
    public static AsansorThread getAsansor4() {
        return asansor4;
    }

    /**
     * @param aAsansor4 the asansor4 to set
     */
    public static void setAsansor4(AsansorThread aAsansor4) {
        asansor4 = aAsansor4;
    }

    /**
     * @return the asansor5
     */
    public static AsansorThread getAsansor5() {
        return asansor5;
    }

    /**
     * @param aAsansor5 the asansor5 to set
     */
    public static void setAsansor5(AsansorThread aAsansor5) {
        asansor5 = aAsansor5;
    }

    /**
     * @return the exitcount
     */
    public static int getExitcount() {
        return exitcount;
    }

    /**
     * @param aExitcount the exitcount to set
     */
    public static void setExitcount(int aExitcount) {
        exitcount = aExitcount;
    }
    
}
