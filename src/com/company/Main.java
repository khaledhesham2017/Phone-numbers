package com.company;

import java.sql.Struct;
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
	// write your code her
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Service[] services = new Service[size];
        for (int i = 0; i < size; i++) {
            services[i] = new Service();
            services[i].id = i;
//            services[i].freq =  scanner.nextInt();
            services[i].freq = i/10;
        }
        scanner.close();
        int lastFreq = -1;
        PClass pClass = initPClass();
        int start = 0;
        for (int i = 0; i < size; i++) {
            //TODO
            //fix a bug up here
            if (services[i].freq != lastFreq)
            {
               pClass = assign(services,start,i, pClass);
               start = i + 1;
            }
            lastFreq = services[i].freq;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(services[i].assignedNo);
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
        if (availableClass.remainingSize < end - start)
        {
            availableClass.id++;
            availableClass.remainingSize = (int) Math.pow(10, availableClass.id - 1);
            availableClass.capacity = availableClass.id * availableClass.remainingSize;
        }
        //TODO
        //or here
        for (int i = start; i <= end; i++) {
            services[i].assignedNo = availableClass.capacity - availableClass.remainingSize;
            availableClass.remainingSize--;

        }
        return availableClass ;
    }
}
