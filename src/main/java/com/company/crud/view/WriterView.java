package main.java.com.company.crud.view;

import main.java.com.company.crud.controller.PostController;
import main.java.com.company.crud.controller.WirterController;
import main.java.com.company.crud.model.Post;
import main.java.com.company.crud.model.Writer;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WriterView {
    String operationsInfo = ("""
            ----------------------------------
            1: Save writer.
            2: Update writer.
            3: Get writer by ID.
            4: Delete writer.
            5: Get all writers.
            6: Exit.
            ----------------------------------
            """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    PostController postController = new PostController();
    WirterController wirterController = new WirterController();

    public void runWriterView() {
        try {
            appStatus = true;
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> saveWriterView();
                    case "2" -> updateWriterView();
                    case "3" -> getByIdView();
                    case "4" -> deleteById();
                    case "5" -> getAll();
                    case "6" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    public void saveWriterView() {
        System.out.print("Enter first name: ");
        String firstName = in.next();
        in.reset();

        System.out.print("Enter last name: ");
        String lastName = in.next();

        System.out.println("Enter posts id: ");
        String[] stringId = in.next().split(",");
        int[] id = new int[stringId.length];
        for (int i = 0; i < stringId.length; i++) {
            id[i] = Integer.parseInt(stringId[i]);
        }

        List<Post> postList = postController.getPostsById(id);
        wirterController.save(new Writer(firstName, lastName, postList));
    }

    public void updateWriterView() {
        System.out.print("Enter ID: ");
        Long id = in.nextLong();
        System.out.print("Enter first name: ");
        String firstName = in.next();
        System.out.print("Enter last name: ");
        String lastName = in.next();
        wirterController.update(new Writer(id, firstName, lastName));
    }

    public void getByIdView() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        Writer writer = wirterController.getById(id);
        if (writer == null) System.out.println("ID not found.");
        else writer.getInfo();
    }

    public void deleteById() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        wirterController.deleteById(id);
    }

    public void getAll() {
        List<Writer> writerList = wirterController.getAll();
        writerList.forEach(Writer::getInfo);
    }
}
