import java.util.ArrayList;
import java.util.Arrays;

public class HeapSort{
    public static Integer[] array; 
    public ArrayList<ArrayList<Integer>> snap_shots = new ArrayList<ArrayList<Integer>>();

    public HeapSort(Integer[] input){
        //set integer array as input
        array = input;
    }

    public void set_array(Integer[] input_array){
        //initialize the integer array
        array = input_array;
    }

    public Integer[] get_array(){
        //returns the integer array
        return array;
    }

    public ArrayList<ArrayList<Integer>> show_snaps(){
        //returns all snap shots of the array
        return snap_shots;
    }

    public void clear_history(){
        //removes all snap shots of the array
        snap_shots = new ArrayList<ArrayList<Integer>>();
    }

    public void swap(int a, int b){
        snap_shots.add(new ArrayList<Integer>(Arrays.asList(array)));
        //swaps two integer's position
        Integer temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void heapify(int i){
        //creates parent node, and left and right children nodes
        int parent = array[i];
        int left_index = i*2 + 1;
        int right_index = i*2 + 2; 


        if (left_index >= array.length && right_index >= array.length){ //return if left and right children nodes do not exist
            return;
        }else if (left_index >= array.length){ //swap if left index is greater than half of array and right child is greater than parent
            if (array[right_index] > parent){
                swap(i, right_index);
                reheap_down(right_index, array.length);
            }
            return;
        }else if (right_index >= array.length){ //swap if right index is greater than half of array and left child is greater than parent
            if (array[left_index] > parent){
                swap(i, left_index);
                reheap_down(left_index, array.length);
            }
            return;
        }


        if (array[left_index] > array[right_index]){  //if left child is larger than right and larger than parent, swap with parent
            if (array[left_index] > parent){
                swap(i, left_index);
                reheap_down(left_index, array.length);
            }
        }else{
            if (array[right_index] > parent){ //swap right child if larger than parent
                swap(i, right_index);
                reheap_down(right_index, array.length);
            }
        }
    }

    public void build_heap(){
        //builds the heap
        for(int i = array.length/2; i >= 0; i--){
            heapify(i);
        }
    }

    public void reheap_down(int i ,int len){
        int parent;
        int left_index;
        int right_index;

        while (true){
            parent = array[i];
            left_index = i*2 + 1;
            right_index = i*2 + 2; 

            if (left_index >= len && right_index >= len){ //parent has no children
                return;
            }else if (left_index >= len){ //parent only has right children
                if (array[right_index] <= parent){
                    return;
                }else{
                    swap(i, right_index);
                    i = right_index;
                }
            }else if (right_index >= len){ //parent only has left children
                if (array[left_index] <= parent){
                    return;
                }else{
                    swap(i, left_index);
                    i = left_index;
                }
            }else{ //parent has both children
                if (array[left_index] <= parent && array[right_index] <= parent){
                    return;
                }

                if (array[left_index] > array[right_index]){ 
                    swap(i, left_index);
                    i = left_index;
                }else{  
                    swap(i, right_index);
                    i = right_index;
                }

            }
        }
    }

    public void sort(){
        //executes the sort function
        build_heap();

        for(int i = array.length - 1; i >= 1; i--){
            swap(i, 0);
            reheap_down(0,i);
        }
    }
}