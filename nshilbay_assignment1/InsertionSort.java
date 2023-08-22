package se2203.nshilbay_assignment1;

public class InsertionSort implements SortingStrategy{
    //the attributes of the InsertionSort class as given by the uml are a private integer array and an object of the SortingHubController class
    private int[] arr;
    private SortingHubController controller;

    //constructor for the InsertionSort class which take in an integer array and a controller as parameters
    public InsertionSort(int[] arr, SortingHubController controller) {
        this.arr = arr;
        this.controller = controller;
    }

    //sort method
    public void sort(int[] arr) {
        //for loop that iterates through loop
        for (int i = 1; i < arr.length; i++) {

            int key = arr[i];
            int j = 0;

            //compare the key with its predecessor elements and then move the elements that are greater than the key one index up
            for (j = i - 1; (j >= 0) && arr[j] > key; j--){
                arr[j + 1] = arr[j];

                //try-catch method for error exception
                try {
                    Thread.sleep(10);//the Thread.sleep() method helps show the animation of updating the bars
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            //update the graph using the controller
            controller.updateGraph(arr);
        }
        arr[j + 1] = key;
    }
}

    //run the sort method inside the run method which is part of the Runnable interface
    public void run(){
        sort (arr);
    }
}






