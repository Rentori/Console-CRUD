package main.java.com.company.crud.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    String operationsInfo = ("""
            ----------------------------------
            1: Label
            2: Post
            3: Writer
            4: Exit.
            ----------------------------------
            """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;

    LabelView labelView = new LabelView();
    PostView postView = new PostView();
    WriterView writerView = new WriterView();

    public void runMainView() {
        try {
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> labelView.runLabelView();
                    case "2" -> postView.runPostView();
                    case "3" -> writerView.runWriterView();
                    case "4" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }
}
