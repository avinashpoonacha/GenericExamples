package playground;

import java.util.Arrays;

class ArraList {

    Object[] arr;
    int capacity = 10;
    int intialSize =0;

    public ArraList(){
        arr = new Object[capacity];
    }

    public void add(Object obj) {

        if(arr.length-intialSize <= 5){
            increaseCapacity();
        }
        arr[intialSize++]= obj;
    }

    public void increaseCapacity(){
        arr = Arrays.copyOf(arr, arr.length*2);
        System.out.println("increased Length to-->"+ arr.length);
    }

    public  Object get(int indx){
        if (indx < intialSize){
            return arr[indx];
        } else  {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int getSize(){
        return intialSize;
    }

    public void remove(int indx){
        if (indx < intialSize){
            arr[indx]=null;
            int tmp= indx;
            while(tmp< intialSize){
                arr[tmp] = arr[tmp+1];
                arr[tmp+1]=null;
                tmp++;
            }
            intialSize--;
        }else  {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void main(String[] args) {

        ArraList arr1= new ArraList();

        System.out.println(arr1.getSize());

        arr1.add(23);
        arr1.add("43434");
        arr1.add(564);

        System.out.println(arr1.getSize());
        arr1.remove(2);
        System.out.println(arr1.getSize());
    }
}