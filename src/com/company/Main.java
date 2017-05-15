package com.company;

public class Main  {


    public static void main(String[] args) {
        Character []arr = new Character[5];
        arr[0] = 'E' ;
        arr[1] = 'D';
        arr[2] = 'A';
        arr[3] = 'B';
        arr[4] = 'A';

	   SortHeap sortHeap = new SortHeap(arr);
        System.out.println("*****");
        for (int i =0 ;i < arr.length ; i++){
            System.out.println(arr[i]);
        }

    }


}
