/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * @author AYAZ
 */
public class AsansorThread implements Runnable {
    private  boolean active;
    private  AtomicBoolean running = new AtomicBoolean(false);
    private  int floor,destination,capacity,count_inside;
    private  String direction,mode,isim;
    private  LinkedList<Kuyruk> asansorIciKuyruk;
    
    public   LinkedList<Kuyruk> silinecekler = new LinkedList();
    public   LinkedList<Kuyruk> oylesine = new LinkedList();
    public   LinkedList<Kuyruk> eklenicekler = new LinkedList(); 
    
    public AsansorThread(){
        this.active = false;
        this.mode = "idle"; 
        this.destination = 0;
        this.floor = 0;
        this.direction = "null"; 
        this.capacity = 10;
        this.count_inside = 0;
        this.asansorIciKuyruk = new LinkedList<>();
        this.isim = "Asansor1";
        Thread.currentThread().setName(isim);
        running.set(true);
    }
    
    public AsansorThread(String isim){
        this.active = false;
        this.mode = "idle"; 
        this.destination = 0;
        this.floor = 0;
        this.direction = "null"; 
        this.capacity = 10;
        this.count_inside = 0;
        this.asansorIciKuyruk = new LinkedList<>();
        this.isim = isim;
        Thread.currentThread().setName(isim);
        running.set(false);
    }
    
    @Override
    public void run (){
        if(running.get()){
            while(running.get()){
                if(isActive() == false){
                    initial();
                    if(!Sistem.getAsansorDisiKuyruk().isEmpty()){
                        inithareketEt();
                     }
                }else{
                    hareketEt();
                }
            }      
        }else{
            while(true){
                while(running.get()){
                    if(isActive() == false ){
                        initial();
                        //inithareketEt();
                    }else{
                        if(running.get())
                           hareketEt();
                     }
                }
            }    
        }
    }

    
    public void initial(){
        //System.out.println(isim+" Aktif oldu");
        setActive(true);setMode("Working");
    }
    
    public void inithareketEt(){
        getAsansorIciKuyruk().offer(Sistem.getAsansorDisiKuyruk().peek()); // burda asansordısıkuyruktan direk atıyorum zaten ilk cagrıyı sonra ıcıne eklıyorum 
        setCount_inside(getCount_inside()+getAsansorIciKuyruk().peek().getKisiSayisi());
        Sistem.getZeminKatAsansorKuyruk().remove(getAsansorIciKuyruk().peek());
        setDestination(getAsansorIciKuyruk().peek().getHedefKat());
        setDirection("up");
        while(getFloor() != getDestination()){
            print();
            sleep();
            setFloor(getFloor()+1);
        }
        print();
        Kuyruk atilan;
        switch(getDestination()){
            case 1:
                    atilan = getAsansorIciKuyruk().poll();
                    Sistem.setBirinciKatKisiSayisi(Sistem.getBirinciKatKisiSayisi()+atilan.getKisiSayisi());
                    setCount_inside(getCount_inside()-atilan.getKisiSayisi());
                    //System.out.println("eklenen  1. kat[ "+atilan.getKisiSayisi()+","+atilan.getHedefKat()+"]");                
                    break;
            case 2:
                    atilan = getAsansorIciKuyruk().poll();
                    Sistem.setIkinciKatKisiSayisi(Sistem.getIkinciKatKisiSayisi()+atilan.getKisiSayisi());
                    setCount_inside(getCount_inside()-atilan.getKisiSayisi());
                    //System.out.println("eklenen  2. kat[ "+atilan.getKisiSayisi()+","+atilan.getHedefKat()+"]");
                    break;
            case 3:
                    atilan = getAsansorIciKuyruk().poll();
                    Sistem.setUcuncuKatKisiSayisi(Sistem.getUcuncuKatKisiSayisi()+atilan.getKisiSayisi());
                    setCount_inside(getCount_inside()-atilan.getKisiSayisi());
                    //System.out.println("eklenen  3. kat[ "+atilan.getKisiSayisi()+","+atilan.getHedefKat()+"]");
                    break;
            case 4:
                    atilan = getAsansorIciKuyruk().poll();
                    Sistem.setDorduncuKatKisiSayisi(Sistem.getDorduncuKatKisiSayisi()+atilan.getKisiSayisi());
                    setCount_inside(getCount_inside()-atilan.getKisiSayisi());
                    //System.out.println("eklenen  4. kat[ "+atilan.getKisiSayisi()+","+atilan.getHedefKat()+"]");
                    break;
        }
    }
    
    
    public void hareketEt(){
        int size = 0;
        Kuyruk current = null;
        if(Sistem.getAsansorDisiKuyruk().peek()!= null && isActive()){
             //System.out.println(Sistem.getAsansorDisiKuyruk().size()+"------------------");
             current = Sistem.getAsansorDisiKuyruk().peek();          
             setDestination(current.getSuanKat());
             if(getFloor() > getDestination()){
                    setDirection("down");
                    while(getFloor() != getDestination()){
                        if(!isActive()){
                            break;
                        }
                        print();
                        sleep();
                        if(!isActive()){
                            break;
                        }
                        if(getFloor() -1 < 0){
                            break;
                        }
                        setFloor(getFloor()-1);
                    }
             }else if (getFloor() < getDestination()){
                    setDirection("up");
                    while(getFloor() != getDestination()){
                        if(!isActive()){
                            break;
                        }
                        print();
                        sleep();
                        if(!isActive()){
                            break;
                        }
                        setFloor(getFloor()+1);
                    }
             }
            if(getAsansorIciKuyruk().isEmpty() && isActive()){
                if(getFloor() == 0 && Sistem.getZeminKatAsansorKuyruk().peek() != null && isActive()){
                    silinecekler.clear();
                    eklenicekler.clear();
                    eklenicekler = (LinkedList)Sistem.getZeminKatAsansorKuyruk().clone();
                    for(Kuyruk temp : eklenicekler){
                        if(getCount_inside()+temp.getKisiSayisi() <= getCapacity() && isActive()){
                            getAsansorIciKuyruk().offer(temp);
                            silinecekler.offer(temp);
                            setCount_inside(getCount_inside()+temp.getKisiSayisi());
                        }
                    }
                    for(Kuyruk n: silinecekler){
                        Sistem.getZeminKatAsansorKuyruk().remove(n);
                        if(Sistem.getAsansorDisiKuyruk().contains(n))
                            Sistem.getAsansorDisiKuyruk().remove(n);
                    }
                }else if (getFloor() == 1 && Sistem.getBirinciKatAsansorKuyruk().peek() != null && isActive()){
                    silinecekler.clear();
                    eklenicekler.clear();
                    eklenicekler = (LinkedList)Sistem.getBirinciKatAsansorKuyruk().clone();
                    for(Kuyruk temp : eklenicekler){
                        if(getCount_inside()+temp.getKisiSayisi() <= getCapacity() && isActive()){
                            getAsansorIciKuyruk().offer(temp);
                            silinecekler.offer(temp);
                            setCount_inside(getCount_inside()+temp.getKisiSayisi());
                        }
                    }
                    Sistem.setBirinciKatKisiSayisi(Sistem.getBirinciKatKisiSayisi() - getCount_inside());
                    for(Kuyruk n: silinecekler){
                        Sistem.getBirinciKatAsansorKuyruk().remove(n);
                        if(Sistem.getAsansorDisiKuyruk().contains(n))
                            Sistem.getAsansorDisiKuyruk().remove(n);   
                    }
                }else if (getFloor() == 2 && Sistem.getIkinciKatAsansorKuyruk().peek() != null && isActive()){
                    silinecekler.clear();
                    eklenicekler.clear();
                    eklenicekler = (LinkedList)Sistem.getIkinciKatAsansorKuyruk().clone();
                    for(Kuyruk temp : eklenicekler){
                        if(getCount_inside()+temp.getKisiSayisi() <= getCapacity() && isActive()){
                            getAsansorIciKuyruk().offer(temp);
                            silinecekler.offer(temp);
                            setCount_inside(getCount_inside()+temp.getKisiSayisi());
                        }
                    }
                    Sistem.setIkinciKatKisiSayisi(Sistem.getIkinciKatKisiSayisi() - getCount_inside());
                    for(Kuyruk n: silinecekler){
                        Sistem.getIkinciKatAsansorKuyruk().remove(n);
                        if(Sistem.getAsansorDisiKuyruk().contains(n))
                        Sistem.getAsansorDisiKuyruk().remove(n);
                    }
                }else if(getFloor() == 3 && Sistem.getUcuncuKatAsansorKuyruk().peek() != null && isActive()){
                    silinecekler.clear();
                    eklenicekler.clear();
                    eklenicekler = (LinkedList)Sistem.getUcuncuKatAsansorKuyruk().clone();
                    for(Kuyruk temp : eklenicekler){
                        if(getCount_inside()+temp.getKisiSayisi() <= getCapacity() && isActive()){
                            getAsansorIciKuyruk().offer(temp);
                            silinecekler.offer(temp);
                            setCount_inside(getCount_inside()+temp.getKisiSayisi());
                        }
                    }
                    Sistem.setUcuncuKatKisiSayisi(Sistem.getUcuncuKatKisiSayisi() - getCount_inside());
                    for(Kuyruk n: silinecekler){
                        Sistem.getUcuncuKatAsansorKuyruk().remove(n);
                        if(Sistem.getAsansorDisiKuyruk().contains(n))
                            Sistem.getAsansorDisiKuyruk().remove(n);
                    }
                }else if (getFloor() == 4 && Sistem.getDorduncuKatAsansorKuyruk().peek() != null && isActive()){
                    silinecekler.clear();
                    eklenicekler.clear();
                    eklenicekler = (LinkedList)Sistem.getDorduncuKatAsansorKuyruk().clone();
                    for(Kuyruk temp : eklenicekler){
                        if(getCount_inside()+temp.getKisiSayisi() <= getCapacity() && isActive()){
                            getAsansorIciKuyruk().offer(temp);
                            silinecekler.offer(temp);
                            setCount_inside(getCount_inside()+temp.getKisiSayisi());
                        }
                    }
                    Sistem.setDorduncuKatKisiSayisi(Sistem.getDorduncuKatKisiSayisi() - getCount_inside());
                    for(Kuyruk n: silinecekler){
                        Sistem.getDorduncuKatAsansorKuyruk().remove(n);
                        if(Sistem.getAsansorDisiKuyruk().contains(n))
                            Sistem.getAsansorDisiKuyruk().remove(n);  
                    }
                }
            }
              
            if(!getAsansorIciKuyruk().isEmpty()){
                int buyuk = 0;
                if(getAsansorIciKuyruk().size() > 1){
                    for(Kuyruk n : getAsansorIciKuyruk()){
                        if(n.getHedefKat() > buyuk){
                            buyuk = n.getHedefKat();
                        }
                    }
                setDestination(buyuk);
                }else if (getAsansorIciKuyruk().peek() !=null){
                    setDestination(getAsansorIciKuyruk().peek().getHedefKat());
                }            
                if(getFloor() > getDestination() && isActive()){
                    setDirection("down");
                    while(getFloor() != getDestination()){
                        if(!isActive()){
                             break;
                        }
                        print();
                        kontrol(getFloor());
                        sleep();
                        if(!isActive()){
                            break;
                         }
                        if(getFloor() -1 < 0){
                            break;
                        }
                        setFloor(getFloor()-1);
                    }    
                }else if (getFloor() < getDestination() && isActive()){
                     setDirection("up");
                    while(getFloor() != getDestination()){
                        if(!isActive()){
                             break;
                        }
                        print();
                        kontrol(getFloor());
                        sleep();
                        if(!isActive()){
                            break;
                         }
                         if(getFloor() +1 >4){
                            break;
                        }
                        setFloor(getFloor()+1);
                    }
                }else{
                    kontrol(getFloor());
                    print();
                }
                kontrol(getFloor());
                print();
            }     
        }
    }
    
    public void kontrol(int floor){
        Kuyruk current = null;
        silinecekler.clear();
        Kuyruk current2 = null;
        if(isActive()){
            int size = getAsansorIciKuyruk().size();
            for(int index = 0; index < size ;index++){
                current =  getAsansorIciKuyruk().get(index);
                if(current.getHedefKat()== floor){
                    silinecekler.add(current);
                    switch (floor) {
                        case 0:
                            setCount_inside(getCount_inside() - current.getKisiSayisi());
                            //System.out.println("AVM CIKIS YAPAN  [ "+current.getKisiSayisi()+" , "+current.getHedefKat()+"]");
                            Sistem.setExitcount(Sistem.getExitcount()+ current.getKisiSayisi());
                            current2= current;
                            break;
                        case 1:
                            Sistem.setBirinciKatKisiSayisi(Sistem.getBirinciKatKisiSayisi()+current.getKisiSayisi());
                            setCount_inside(getCount_inside() - current.getKisiSayisi());
                            //System.out.println("1.kat eklendi  [ "+current.getKisiSayisi()+" , "+current.getHedefKat()+"]");
                            break;
                        case 2:
                            Sistem.setIkinciKatKisiSayisi(Sistem.getIkinciKatKisiSayisi()+current.getKisiSayisi());
                            setCount_inside(getCount_inside() - current.getKisiSayisi());
                            //System.out.println("2.kat eklendi  [ "+current.getKisiSayisi()+" , "+current.getHedefKat()+"]");
                            break;
                        case 3:
                            Sistem.setUcuncuKatKisiSayisi(Sistem.getUcuncuKatKisiSayisi()+current.getKisiSayisi());
                            setCount_inside(getCount_inside() - current.getKisiSayisi());
                            //System.out.println("3.kat eklendi  [ "+current.getKisiSayisi()+" , "+current.getHedefKat()+"]");
                            break;
                        case 4:
                            Sistem.setDorduncuKatKisiSayisi(Sistem.getDorduncuKatKisiSayisi()+current.getKisiSayisi());
                            setCount_inside(getCount_inside() - current.getKisiSayisi());
                            System.out.println("3.kat eklendi  [ "+current.getKisiSayisi()+" , "+current.getHedefKat()+"]");    
                            break;
                        default:
                            break;
                    }
                }
            }
            if(current2!=null){
               Sistem.getAsansorDisiKuyruk().remove(current2);
            }
            for(Kuyruk n:silinecekler){
                getAsansorIciKuyruk().remove(n);
            }
        }
    }

    
    public void print2(){
        oylesine.clear();
        int sayac=  0;
        System.out.print("0.floor : queue : ");
        oylesine = (LinkedList)Sistem.getZeminKatAsansorKuyruk().clone();
        for(Kuyruk n : oylesine){
            sayac = sayac+n.getKisiSayisi();
        }
        System.out.println(sayac);
        sayac = 0;
        oylesine.clear();
        System.out.print("1.floor : all : "+Sistem.getBirinciKatKisiSayisi()+" queue : ");
        oylesine = (LinkedList)Sistem.getBirinciKatAsansorKuyruk().clone();
        for(Kuyruk n : oylesine){
            sayac = sayac+n.getKisiSayisi();
        }
        System.out.println(sayac);
        System.out.print("2.floor : all : "+Sistem.getIkinciKatKisiSayisi()+" queue : ");
        sayac = 0;
        oylesine.clear();
        oylesine = (LinkedList)Sistem.getIkinciKatAsansorKuyruk().clone();
        for(Kuyruk n : oylesine){
            sayac = sayac+n.getKisiSayisi();
        }
        System.out.println(sayac);
        System.out.print("3.floor : all : "+Sistem.getUcuncuKatKisiSayisi()+" queue : ");
        sayac = 0;
        oylesine.clear();
        oylesine = (LinkedList)Sistem.getUcuncuKatAsansorKuyruk().clone();
        for(Kuyruk n : oylesine){
            sayac = sayac+n.getKisiSayisi();
        }
        System.out.println(sayac);
        System.out.print("4.floor : all : "+Sistem.getDorduncuKatKisiSayisi()+" queue : ");
        sayac = 0;
        oylesine.clear();
        oylesine = (LinkedList)Sistem.getDorduncuKatAsansorKuyruk().clone();
        for(Kuyruk n : oylesine){
            sayac = sayac+n.getKisiSayisi();
        }
        System.out.println(sayac);
        System.out.println("exit count : "+Sistem.getExitcount());
    }
    
    
    
    
    public void sleep(){
        try{
            String name = isim+" Thread";
            //System.out.println(name+" waiting to get notified at time:"+System.currentTimeMillis());
            Thread.sleep(200);
        }catch(InterruptedException e){
                e.printStackTrace();
        }
        
    }
    
    
    public void print(){
        LinkedList<Kuyruk> current = new LinkedList();
        if(Thread.currentThread().getName().equals("Thread-2") ){
            print2();
            //System.out.println(isim+"\n");
            System.out.println("active : "+Sistem.getAsansor1().isActive());
            System.out.println("\t\t mode :"+Sistem.getAsansor1().getMode()+"\n\t\t floor : "+Sistem.getAsansor1().getFloor()+"\n\t\t destination :"+Sistem.getAsansor1().getDestination()+" \n\t\t direction : "+Sistem.getAsansor1().getDirection()+"\n\t\t capacity : "+Sistem.getAsansor1().getCapacity()+"\n\t\t count_inside : "+Sistem.getAsansor1().getCount_inside());
            System.out.print("\t\t inside :[ ");
            if(getAsansorIciKuyruk().peek()!=null){
                current =(LinkedList)Sistem.getAsansor1().getAsansorIciKuyruk().clone();
                for(Kuyruk n : current){
                    System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"] ,");
                }
            }
            System.out.println("]");
            System.out.println("active : "+Sistem.getAsansor2().isActive());
            System.out.println("\t\t mode :"+Sistem.getAsansor2().getMode()+"\n\t\t floor : "+Sistem.getAsansor2().getFloor()+"\n\t\t destination :"+Sistem.getAsansor2().getDestination()+" \n\t\t direction : "+Sistem.getAsansor2().getDirection()+"\n\t\t capacity : "+Sistem.getAsansor2().getCapacity()+"\n\t\t count_inside : "+Sistem.getAsansor2().getCount_inside());
            System.out.print("\t\t inside :[ ");
            if(Sistem.getAsansor2().getAsansorIciKuyruk().peek()!=null){
                current.clear();
                current = (LinkedList)Sistem.getAsansor2().getAsansorIciKuyruk().clone();
                for(Kuyruk n : current){
                   System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"] ,");
                }
            }
            System.out.println("]");
            System.out.println("active : "+Sistem.getAsansor3().isActive());
            System.out.println("\t\t mode :"+Sistem.getAsansor3().getMode()+"\n\t\t floor : "+Sistem.getAsansor3().getFloor()+"\n\t\t destination :"+Sistem.getAsansor3().getDestination()+" \n\t\t direction : "+Sistem.getAsansor3().getDirection()+"\n\t\t capacity : "+Sistem.getAsansor3().getCapacity()+"\n\t\t count_inside : "+Sistem.getAsansor3().getCount_inside());
            System.out.print("\t\t inside :[ ");
            if(Sistem.getAsansor3().getAsansorIciKuyruk().peek()!=null){
                current.clear();
                current = (LinkedList)Sistem.getAsansor3().getAsansorIciKuyruk().clone();
                for(Kuyruk n : current){
                    System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"] ,");
                }
            }
            System.out.println("]");
            System.out.println("active : "+Sistem.getAsansor4().isActive());
            System.out.println("\t\t mode :"+Sistem.getAsansor4().getMode()+"\n\t\t floor : "+Sistem.getAsansor4().getFloor()+"\n\t\t destination :"+Sistem.getAsansor4().getDestination()+" \n\t\t direction : "+Sistem.getAsansor4().getDirection()+"\n\t\t capacity : "+Sistem.getAsansor4().getCapacity()+"\n\t\t count_inside : "+Sistem.getAsansor4().getCount_inside());
            System.out.print("\t\t inside :[ ");
            if(Sistem.getAsansor4().getAsansorIciKuyruk().peek()!=null){
                current.clear();
                current = (LinkedList)Sistem.getAsansor4().getAsansorIciKuyruk().clone();
                for(Kuyruk n : current){
                     System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"] ,");
                }
            }
            System.out.println("]");
            System.out.println("active : "+Sistem.getAsansor5().isActive());
            System.out.println("\t\t mode :"+Sistem.getAsansor5().getMode()+"\n\t\t floor : "+Sistem.getAsansor5().getFloor()+"\n\t\t destination :"+Sistem.getAsansor5().getDestination()+" \n\t\t direction : "+Sistem.getAsansor5().getDirection()+"\n\t\t capacity : "+Sistem.getAsansor5().getCapacity()+"\n\t\t count_inside : "+Sistem.getAsansor5().getCount_inside());
            System.out.print("\t\t inside :[ ");
            if(Sistem.getAsansor5().getAsansorIciKuyruk().peek()!=null){
                current.clear();
                current = (LinkedList)Sistem.getAsansor5().getAsansorIciKuyruk().clone();
                for(Kuyruk n : current){
                     System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"] ,");
                }
            }
            System.out.println("]");
            oylesine = (LinkedList)Sistem.getZeminKatAsansorKuyruk().clone();
            System.out.print("0.floor : [");
            for(Kuyruk n : oylesine){
                System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"], ");
            }
            System.out.println("]");
            oylesine.clear();
            oylesine = (LinkedList)Sistem.getBirinciKatAsansorKuyruk().clone();
            System.out.print("1.floor : [");
            for(Kuyruk n : oylesine){
                System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"], ");
            }
            System.out.println("]");
            oylesine.clear();
            oylesine = (LinkedList)Sistem.getIkinciKatAsansorKuyruk().clone();
            System.out.print("2.floor : [");
            for(Kuyruk n : oylesine){
                System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"], ");
            }
            System.out.println("]");
            oylesine.clear();
            oylesine = (LinkedList)Sistem.getUcuncuKatAsansorKuyruk().clone();
            System.out.print("3.floor : [");
            for(Kuyruk n : oylesine){
                System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"], ");
            }
            System.out.println("]");
            oylesine.clear();
            oylesine = (LinkedList)Sistem.getDorduncuKatAsansorKuyruk().clone();
            System.out.print("4.floor : [");
           for(Kuyruk n : oylesine){
                System.out.print("["+n.getKisiSayisi()+","+n.getHedefKat()+"], ");
           }
            System.out.println("]");
            System.out.println(" ");
        }
   
    }
    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
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

    /**
     * @return the floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @param floor the floor to set
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * @return the destination
     */
    public int getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the count_inside
     */
    public int getCount_inside() {
        return count_inside;
    }

    /**
     * @param count_inside the count_inside to set
     */
    public void setCount_inside(int count_inside) {
        this.count_inside = count_inside;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the isim
     */
    public String getIsim() {
        return isim;
    }

    /**
     * @param isim the isim to set
     */
    public void setIsim(String isim) {
        this.isim = isim;
    }

    /**
     * @return the asansorIciKuyruk
     */
    public LinkedList<Kuyruk> getAsansorIciKuyruk() {
        return asansorIciKuyruk;
    }

    /**
     * @param asansorIciKuyruk the asansorIciKuyruk to set
     */
    public void setAsansorIciKuyruk(LinkedList<Kuyruk> asansorIciKuyruk) {
        this.asansorIciKuyruk = asansorIciKuyruk;
    }


  
}
