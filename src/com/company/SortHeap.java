package com.company;

/**
 * Created by khale on 15/05/2017.
 */
public class SortHeap extends  Heap {
    public SortHeap(Object[] array) {
        super(array);
    }
 //make  here  compare  object
    @Override
    public boolean compare(Object frist, Object second) {
        if ((Character)frist > (Character) second ){
            return  true;
        }
        return false;
    }
}
