package com.company;

import java.sql.Struct;
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
        Random random = new Random();
        Scanner scanner = new Scanner(System.in); //<<-----#
        int size = scanner.nextInt();
        Service[] services = new Service[size];
        for (int i = 0; i < size; i++) {
            services[i] = new Service();//<<-----#
            services[i].id = i;
//            services[i].freq =  scanner.nextInt(); // use this in pseudo code
            services[i].freq = random.nextInt(10); //<<-----#
//            services[i].freq = i /3;
        }
        scanner.close();
        new Heap(services) { //<<-----#
            @Override//<<-----#
            public boolean compare(Object first, Object second) {//<<-----#
                return ((Service) first).freq > ((Service) second).freq;//<<-----#
            }//<<-----#
        };
        int lastFreq = services[0].freq;
        PClass pClass = initPClass();
        int start = 0;
        for (int i = 0; i < size; i++) {
            if (services[i].freq != lastFreq || i == size - 1)
            {
                //this condition annoying me
                //if anybody got a better idea change it
                if (i != size - 1)
               pClass = assign(services,start,i, pClass);
                else assign(services,start,i + 1, pClass);
               start = i ;
            }
            lastFreq = services[i].freq;
        }
        for (int i = 0; i < size; i++) {
            System.out.println("freq: " +  services[i].freq + " No: " + services[i].assignedNo);
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
        while (availableClass.remainingSize < end - start) {
            availableClass.id++;
            availableClass.remainingSize = (int) Math.pow(10, availableClass.id - 1);
            availableClass.capacity = (availableClass.id) * availableClass.remainingSize;
        }
        for (int i = start; i < end; i++) {
            services[i].assignedNo = availableClass.capacity - availableClass.remainingSize;
            availableClass.remainingSize--;
        }
        return availableClass ;

    }


}
