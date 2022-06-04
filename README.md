# ✨ Evelator System ✨


[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)]()

✨ Elevator system of a multithreading Mall. ✨
## Project Achievements

- Multithreading 
- Synchronous, Asynchronus Methods
- Dominance of FIFO , LIFO , Linked List data struct structure
- OOP 

> Subject of the project: Mall entrances,
Mall Exits,
Elevators in the Mall,
Controlling the elevators in the mall, ensuring that all these systems work simultaneously with Multithreading.
When you want to access variables at the same time, there is a lot of lock situation, but how will this be solved?

The important structure in this project is the queue structure. When accessing a list with this queue structure and we need to modify this list, the first thing we should do is get a clone of that object!!.


```sh
0.floor : all : 0 queue: 23
1.floor : all : 2 queue : 1
2.floor : all : 0 queue : 0
3.floor : all : 15 queue : 2
4.floor : all : 0 queue : 0
exit Person count : 0
active :true
		 mode :Working
		 floor : 2
		 destination : 1
		 direction : down
		 capacity : 10
		 count_inside : 0
		 inside :[]
active : true
		 mode :working
		 floor : 1
		 destination : 1
		 direction : up
		 capacity : 10
		 count_inside : 0
		 inside :[]

active : false
		 mode :idle
		 floor : 0
		 destination : 0
		 direction : null
		 capacity : 10
		 count_inside : 0
		 inside :[]

active : false
		 mode :idle
		 floor : 0
		 destination : 0
		 direction : null
		 capacity : 10
		 count_inside : 0
		 inside :[]

active : false
		 mode :idle
		 floor : 0
		 destination : 0
		 direction : null
		 capacity : 10
		 count_inside : 0
		 inside :[]
0.floor : [[5,4], [9,1], [9,1], ]
1.floor : [[1,0], ]
2.floor : []
3.floor : [[2,0], ]
4.floor : []


```
In the above output: 
- How many people are on the floors
- Number of queues waiting for elevators on floors
```sh
0.floor : all : 0 queue: 23
1.floor : all : 2 queue : 1
2.floor : all : 0 queue : 0
3.floor : all : 15 queue : 2
4.floor : all : 0 queue : 0
```

- Status of 5 elevators
```sh
active :true
		 mode :Working
		 floor : 2
		 destination : 1
		 direction : down
		 capacity : 10
		 count_inside : 0
		 inside :[]
active : true
		 mode :working
		 floor : 1
		 destination : 1
		 direction : up
		 capacity : 10
		 count_inside : 0
		 inside :[]

active : false
		 mode :idle
		 floor : 0
		 destination : 0
		 direction : null
		 capacity : 10
		 count_inside : 0
		 inside :[]

active : false
		 mode :idle
		 floor : 0
		 destination : 0
		 direction : null
		 capacity : 10
		 count_inside : 0
		 inside :[]

active : false
		 mode :idle
		 floor : 0
		 destination : 0
		 direction : null
		 capacity : 10
		 count_inside : 0
		 inside :[]
```

- Where people on floors want to move

```sh
0.floor : [[5,4], [9,1], [9,1], ]
1.floor : [[1,0], ]
2.floor : []
3.floor : [[2,0], ]
4.floor : []
``` 



## Tech

- Java programing Language


## Installation
This min requires [Java-8 SDK](https://www.oracle.com/tr/java/technologies/javase/javase8-archive-downloads.html)

```sh
  private void _mall_Login_Thread(){
        int _random_Number = (int)(1 + Math.random() * 10 );
        _mount_Person_Number = _random_Number;
        ...
        
  private void _mall_Exit_Thread() throws InterruptedException{
        int _random_Number = (int) (1 + Math.random() * 5);
        _descend_Person_Number = _random_Number;
        ...
```
If you want to change the number of people entering and leaving the store, you need to change these lines in the Mall Login, MallExit Files.


## License

MIT

**Free Software, Hell Yeah!**
