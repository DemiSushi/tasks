package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt"); //relative path
        //c:\\users\\yj\\Desktop\\ip\\data\\data.txt <<<< absolute path
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);
        printAllDataUsingStreams(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        //printDeadlinesUsingStream(tasksData);
        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStream(tasksData));


    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    private static int countDeadlinesUsingStream(ArrayList<Task> tasks){
        System.out.println("Me");
        int count = (int) tasks.stream().filter((Task t) -> t instanceof Deadline)
                .count();
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printAllDataUsingStreams(ArrayList<Task>tasks){
        System.out.println("Using Streams:...");
        tasks.stream().forEach(System.out::println);
    }
    //instanceof returns true and false
    //for each task known as t, if t is the same object as Deadline, print it out
    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static void printDeadlinesUsingStreams(ArrayList<Task> tasks){
        System.out.println("Using Streams ....");
        tasks.stream().filter((Task t)-> t instanceof Deadline)
                .sorted((Task t1,Task t2) ->t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }


    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String filterString){
        System.out.println("Filtering Task");
        return (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());
    }

}
