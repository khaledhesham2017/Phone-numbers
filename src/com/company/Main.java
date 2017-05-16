package com.company;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static class Service
    {
        int id;

        int freq;
        int assignedNo;
    }
    static class PClass{
        int id;
        int remainingSize;
        int capacity;
    }
    public static void main(String[] args) {
        //IMPORTANT!!!!
        //              the lines contain '//<<-----#' ignore them in the pseudo code
        //              the lines contain '//<<-----*' should be implemented in pseudo code regardless if its commented in code
        Scanner scanner = new Scanner(System.in); //<<-----#
        Random random = new Random();
        System.out.print("Please enter the number of services: "); // <<----#
        String serviceNum = scanner.next();
        while (!isValid(serviceNum)){
            System.out.println("Invalid Number!");
            System.out.print("Please enter the number of services again: ");
            serviceNum = scanner.next();
        }
        int size = Integer.parseInt(serviceNum);
        Service[] services = new Service[size];
        for (int i = 0; i < size; i++) {
            services[i] = new Service();//<<-----#
            System.out.print("Enter the service frequency "+ (i+1) + ": " );
            services[i].id = i;
            services[i].freq = random.nextInt(100) + 1;
//            String freq =  scanner.next(); ///<<-----*
//
//            if(isValid(freq))
//                services[i].freq = (Integer.parseInt(freq)); //<<-----#
//            else {
//                System.out.println("Invalid Number!");//<<-----#
//
//                i--;//<<-----#
//            }
        }
        scanner.close();
        new Heap(services) { //<<-----#
            @Override//<<-----#
            public boolean compare(Object first, Object second) {//<<-----#
                return ((Service) first).freq < ((Service) second).freq;//<<-----#
            }//<<-----#
        };

        // heapSort(services); //<<-----*

        int lastFreq = services[0].freq;
        PClass pClass = initPClass();
        int start = 0;
        for (int i = 0; i < size; i++) {  // loop 1
            // loop till find a change in freq value
            // worst case all freq are different => n^2
            // best case all have the same freq => n

           if (services[i].freq != lastFreq || i == size - 1)
            {
                //this condition annoying me
                //if anybody got a better idea change it
                if (i != size - 1)
               pClass = assign(services,start,i, pClass);
                else{
                if(services[i].freq != lastFreq){
                    pClass = assign(services,start,i, pClass);
                    pClass = assign(services,i,i+1, pClass);
                }
                else {
                    assign(services, start, i+1, pClass);
                }
            }
               start = i ;
            }
            lastFreq = services[i].freq;
        }
        System.out.println("\n");
        for (int i = 0; i < size; i++) {
//            System.out.println("SN: " +  (services[i].id+1) + "\tFreq: " +  (services[i].freq) + "\tAN: " + services[i].assignedNo);
            System.out.println("Freq: " +  (services[i].freq) + "\tAN: " + services[i].assignedNo);
        }
    }
    private static PClass initPClass()
    {
        PClass pClass = new PClass();
        pClass.id = 1;
        pClass.remainingSize = (int) Math.pow(10, pClass.id - 1);
        pClass.capacity = pClass.id * pClass.remainingSize;
        return pClass;
    }
    private static PClass assign(Service[] services, int start, int end, PClass availableClass){
        // u can ignore this as it will have max loop of 9 loops in worst case  of input size 10^9
        // one loop best case
        while (availableClass.remainingSize < end - start) {
            availableClass.id++;
            availableClass.remainingSize = (int) Math.pow(10, availableClass.id - 1);
            availableClass.capacity = (availableClass.id) * availableClass.remainingSize;
        }
        //loop 2
        for (int i = start; i < end; i++) { // loop 2
            services[i].assignedNo = availableClass.capacity - availableClass.remainingSize;
            availableClass.remainingSize--;
        }
        return availableClass ;

    }
    private static Boolean isValid(String text){
        try {
            int num = Integer.parseInt(text);
            if(num <= 0)
                return false;

        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }


}
