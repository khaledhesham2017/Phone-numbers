package com.company;

/**
 * Created by billy on 5/15/17.
 */
public class Heap {
    private int heapSize;
    public int[] array;

    public Heap(int size){

        heapSize = 0;
        array = new int[size];

    }

    private int parent(int i){
        return ((i-1)/2);
    }
    private int left(int i){
        return (2*i + 1);
    }
    private int right(int i){
        return (2*i + 2);
    }

    private void buildMaxHeap(){

        heapSize = array.length-1;
        for(int i = (heapSize)/2; i >=0; i-- ){
            maxHeapify(i);
        }

    }

    private void maxHeapify(int i){

        int l = left(i);
        int r = right(i);
        int largest = i;

        if(l <= heapSize && array[l] > array[largest]){

            largest = l;

        }else{

            largest = i;

        }

        if(r <= heapSize && array[r] > array[largest]){

            largest = r;

        }

        if(largest != i){
            //swap
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            maxHeapify(largest);

        }
    }

    public void Heapsort(){

        buildMaxHeap();

        for(int i = heapSize; i > 0; i--){

            //swap
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapSize--;

            maxHeapify(0);

        }
    }
}
