// Programming Assignment #3 for CSCI3320 - Advanced Programming
// Written by Justin Shapiro

import java.util.Scanner;
import java.util.Stack;

@SuppressWarnings("unchecked")

public class PA3TowersOfHanoi {
    static Stack<Integer> towerA = new Stack<Integer>();
    static Stack<Integer> towerB = new Stack<Integer>();
    static Stack<Integer> towerC = new Stack<Integer>();
    static int move_number = 0;
            
    public static char getTowerLetter(Stack<Integer> _tower) {
        if (_tower == towerA)
            return 'A';
        else if (_tower == towerB)
            return 'B';
        else if (_tower == towerC)
            return 'C';
            
        return 'C'; 
    }
    
    public static void printTowerDisks(Stack<Integer> _tower) {
        Stack<Integer> temp_tower = new Stack<Integer>();
        
        for (Stack<Integer> i = (Stack<Integer>)_tower.clone(); !i.empty(); i.pop()) 
            temp_tower.push(new Integer(i.peek()));
            
        System.out.print("[");
        
        while (!temp_tower.empty()) {
            System.out.print("" + temp_tower.pop());
            
            if (!temp_tower.empty()) 
                System.out.print(", ");
        }
        
        System.out.println("]");
    }                              
   
    public static void printCurrentState(Stack<Integer> t1, Stack<Integer> t2, Stack<Integer> t3) {
        System.out.println("** Move Number: " + move_number);
      
        for (char i = 'A'; i < 'D'; i++) {
            System.out.print("" + i + ": ");
            if (getTowerLetter(t1) == i)
                printTowerDisks(t1);
            else if (getTowerLetter(t2) == i)
                printTowerDisks(t2);
            else
                printTowerDisks(t3);
        }
        
        System.out.println("");                
    }
    
    public static void printCurrentState(Stack<Integer> t1, Stack<Integer> t2, Stack<Integer> t3, char to, char from) {
        System.out.println("" + from + " -> " + to);
        printCurrentState(t1, t2, t3);
    }    
    
    public static void moveDisks(int stack_size, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> storage) {
        if (stack_size == 1) {
            destination.push(new Integer(source.pop()));
            
            move_number++;
            char to = getTowerLetter(destination);
            char from = getTowerLetter(source);
            printCurrentState(source, destination, storage, to, from);
        }
        else {
            moveDisks(stack_size - 1, source, storage, destination);
            
            destination.push(source.pop());
            
            move_number++;
            char to = getTowerLetter(destination);
            char from = getTowerLetter(source);
            printCurrentState(source, destination, storage, to, from);
            
            moveDisks(stack_size - 1, storage, destination, source);
        }
    }        
            
    public static void main(String[] args) {
        System.out.print("Enter number of disks: ");
        Scanner num_disks_str = new Scanner(System.in);											
        int num_disks = num_disks_str.nextInt();	
      
        for (int i = num_disks; i>= 1; i--)
            towerA.push(new Integer(i));
         
        printCurrentState(towerA, towerB, towerC);  
        moveDisks(towerA.size(), towerA, towerC, towerB);
        
        System.out.println("Number of Moves: " + move_number);
   }
} 