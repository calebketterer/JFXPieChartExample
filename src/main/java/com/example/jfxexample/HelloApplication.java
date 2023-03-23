package com.example.jfxexample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PieChartExample extends Application {

    @Override
    public void start(Stage stage) {
        // Get the root directories
        File[] roots = File.listRoots();

        // Create a list of data for the chart
        List<PieChart.Data> data = new ArrayList<>();

        // Iterate over the root directories and add their sizes to the chart data
        for (File root : roots) {
            long size = calculateDirectorySize(root);
            data.add(new PieChart.Data(root.getName(), size));
        }

        // Create the chart
        PieChart chart = new PieChart();
        chart.getData().addAll(data);

        // Set the chart title
        chart.setTitle("File Analysis");

        // Create the scene and add the chart to it
        Scene scene = new Scene(chart, 800, 600);

        // Show the scene
        stage.setScene(scene);
        stage.show();
    }

    private long calculateDirectorySize(File directory) {
        long size = 0;
        if (directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += calculateDirectorySize(file);
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
