package se2203.nshilbay_assignment1;


class MergeSort implements SortingStrategy{
    //the attributes of the MergeSort class as given by the uml are an integer array and a SortingHubController
    private int[] arr;
    private SortingHubController controller;

    //constructor for the merge sort class that takes in an integer array and a controller as parameters
    public MergeSort(int[] arr, SortingHubController controller) {
        this.arr = arr;
        this.controller = controller;
    }

    //sort method
    public void sort(int[] arr) {
        int length = arr.length;

        int currentSz;

        //the start of the left subarray
        int startOfLeft;

        // Merge subarrays bottom up. Start off with merging subarrays of size 1 to create sorted subarrays of size 2, etc.
        for (currentSz = 1; currentSz <= length - 1; currentSz = 2 * currentSz) {

            for (startOfLeft = 0; startOfLeft < length - 1; startOfLeft += 2 * currentSz) {

                // Find the ending of left subarray; assign middle + 1 as the start of right subarray
                int middle = Math.min(startOfLeft + currentSz - 1, length - 1);
                int endOfRight = Math.min(startOfLeft + 2 * currentSz - 1, length - 1);
                merge(arr, startOfLeft, middle, endOfRight);
            }
        }
    }

    //Merge method
    public void merge(int[] arr, int start, int middle, int end) {

        //declare and initialize array parts
        int part1StartIndex = start;
        int part1EndIndex = middle;
        int part2startIndex = middle + 1;
        int part2endIndex = end;

        //temporary array that stores the sorted array
        int[] temporaryArr = new int[end - start + 1];
        int i = 0; //index for the temporary array
        while ((part1StartIndex <= part1EndIndex) && (part2startIndex <= part2endIndex)) {
            if (arr[part1StartIndex] < arr[part2startIndex]) {
                temporaryArr[i] = arr[part1StartIndex];
                part1StartIndex++;
            }
            else{
                temporaryArr[i] = arr[part2startIndex];
                part2startIndex++;
            }
            i++;
        }
        //copying the leftovers from both parts to the temporary array
        while (part1StartIndex <= part1EndIndex) {
            temporaryArr[i] = arr[part1StartIndex];
            part1StartIndex++;
            i++;
        }
        while (part2startIndex <= part2endIndex) {
            temporaryArr[i] = arr[part2startIndex];
            part2startIndex++;
            i++;
        }

        //updating array with content from the temporary array
        for (int k = start, j = 0; k <= end; k++, j++)
            arr[k] = temporaryArr[j];
        try {
            //the Thread.sleep() method helps show the animation of updating the bars
            Thread.sleep(100);
            controller.updateGraph(arr);
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }

    //run the sort method inside the run method which is part of the Runnable interface
    public void run() {
        sort(arr);
    }

}