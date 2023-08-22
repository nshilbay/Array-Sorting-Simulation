package se2203.nshilbay_assignment1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SortingHubController implements Initializable {
    @FXML
    private Pane graph;

    @FXML
    private Button reset;

    @FXML
    private Button sort;

    @FXML
    private Slider controlArraySize;

    @FXML
    private Label sizeValue;

    @FXML
    private Label arraySize;

    @FXML
    private Label algorithm;

    @FXML
    private ComboBox <String> sortingMethod;

    @FXML
    private int [] arr;

    @FXML
    private SortingStrategy choice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initialize the comboBox options
        sortingMethod.getItems().addAll("Merge Sort", "Insertion Sort");
        sortingMethod.getSelectionModel().selectFirst();

        //initialize the array to have length 64 and call the populateArray method to generate random yet unique values between 1 and 64
        populateArray(64);
        updateGraph(arr);
        //choice = new MergeSort(arr, this);//make choice a MergeSort object as default

    }

    @FXML
    //event listener for the reset button
    void resetGraph(){
        //initialize the array to have length 64 and call the populateArray method to generate random yet unique values between 1 and 64
        sortingMethod.setPromptText("Merge Sort");
        sortingMethod.getSelectionModel().selectFirst();
        populateArray(64);
        updateGraph(arr);//display the rectangles on the graph
        //choice = new MergeSort(arr, this);//make choice a MergeSort object as default

    }


    @FXML
    //event listener for the slider called ControlArraySize
    void displayArraySize()
    {
        //show the size of the array on the side of the slider
        sizeValue.setText(String.valueOf((int) controlArraySize.getValue()));
        //generate a random yet unique array using the chosen array size
        populateArray((int) controlArraySize.getValue());
        updateGraph(arr);//display the rectangles on the graph
    }

    @FXML
    //event listener for the ComboBox called sortingMethod
    public void setSortStrategy(){
        //create a MergeSort object if the user chooses MergeSort
        if (sortingMethod.getValue().equals("Merge Sort")) {
            choice = new MergeSort(arr, this);

        }
        //create an InsertionSort object if the user chooses InsertionSort
        else{
            choice = new InsertionSort(arr, this);

        }
    }
//String.valueOf(sortingMethod.getSelectionModel().getSelectedItem()).equals("Merge Sort")
    public void updateGraph( int[] arr){
        //lambda expression for the Platform.runLater() method which executes updates on the JavaFX application thread
        Platform.runLater(()->{
            //clear the rectangles from plane
            graph.getChildren().clear();

            //for loop to iterate through array
            for(int i = 0; i<arr.length;i++)
            {
                //equations to calculate the rectangle height, width, x-position, and y-position
                int rectangleHeight = (int)(arr[i] * (graph.getPrefHeight()/arr.length));
                int rectangleWidth = (int)(graph.getPrefWidth()/arr.length);
                int xPosition = (int)(i * graph.getPrefWidth()/arr.length);
                int yPosition = (int)(graph.getPrefHeight() - rectangleHeight);

                //create rectangle objects using a constructor which takes in the height, width, x-position, and y-position
                Rectangle rect = new Rectangle(xPosition, yPosition, rectangleWidth-1, rectangleHeight);

                //set the rectangle bars to a red color
                rect.setFill(Color.RED);

                //add the bars to the graph
                graph.getChildren().add(rect);
            }
        });


    }

    @FXML
    //event listener for the sort button
    public void sortGraph()
    {
        //call the setSortStrategy() method
        setSortStrategy();
        //create a new thread using the lambda expression
        new Thread(choice).start();
    }


    //method to populate the array randomly and uniquely using the arrSize as a parameter
    public void populateArray(int arrSize)
    {
        //initialize the array with the given array size
        arr = new int[arrSize];

        //declare and initialize an ArrayList of type Integer
        ArrayList <Integer> secondArr = new ArrayList<>();


        //assign the array values from 1 to the array size
        for(int i= 0; i < arr.length; i++){
            arr[i]=i+1;
            secondArr.add(arr[i]);
        }

        //set the slider and the label to the array length
        controlArraySize.setValue(arr.length);
        sizeValue.setText((String.valueOf(arr.length)));

        //shuffle the ArrayList
        Collections.shuffle(secondArr);

        // convert the ArrayList back into an array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = secondArr.get(i);
        }

    }

}