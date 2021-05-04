package main.java.com.company.crud.view;

import main.java.com.company.crud.controller.LabelController;
import main.java.com.company.crud.model.Label;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LabelView {
    String operationsInfo = ("""
                ----------------------------------
                1: Save label.
                2: Update label.
                3: Get label by ID.
                4: Delete label.
                5: Get all labels.
                6: Exit.
                ----------------------------------
                """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    LabelController labelController = new LabelController();

    public void runLabelView() {
        try {
            appStatus = true;
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> saveLabelView();
                    case "2" -> updateLabelView();
                    case "3" -> getByIdView();
                    case "4" -> deleteByIdView();
                    case "5" -> getAllView();
                    case "6" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    public void saveLabelView() {
        System.out.print("Enter name of label: ");
        String name = in.next();
        Label savedLabel = labelController.saveLabel(new Label(name, null));
        System.out.println("Name: " + savedLabel.getName() + "    ID: " + savedLabel.getId());
    }

    public void updateLabelView() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        System.out.print("Enter a new name: ");
        String name = in.next();
        System.out.println(labelController.updateLabel(new Label(name, id)));
    }

    public void getByIdView() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        Label label = labelController.getById(id);
        if (label == null) System.out.println("ID not found.");
        else System.out.println("Name: " + label.getName() + "    ID: " + label.getId());
    }

    public void deleteByIdView() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        labelController.deleteById(id);
    }

    public void getAllView() {
        ArrayList<Label> labelList = new ArrayList<>(labelController.getAll());
        labelList.forEach(n -> System.out.println("Name: " + n.getName() + "   ID: " + n.getId()));
    }
}

