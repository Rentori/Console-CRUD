package main.java.com.company.crud.repository.io;

import main.java.com.company.crud.model.Label;
import main.java.com.company.crud.repository.LabelRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaLabelIORepositoryImpl implements LabelRepository {
    private final String path = "/home/viktor/java-projects/consoleCRUD/src/main/resources/files/labels.txt";

    @Override
    public Label save(Label label) {
        label.setId(autoSetId());
        try (BufferedWriter labelsWriter = new BufferedWriter(new FileWriter(path, true))) {
            labelsWriter.write(labelToString(label) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sortById(fillArrayList());
        }
        return label;
    }

    @Override
    public Label update(Label label) {
        List<Label> labelsList = fillArrayList().stream()
                .peek(n -> {
                    if (n.getId() == label.getId()) {
                        n.setName(label.getName());
                    }
                }).collect(Collectors.toList());

        writeFromArrayList(labelsList);
        return label;
    }

    @Override
    public Label getById(Long id) {
        ArrayList<Label> labelArrayList = new ArrayList<>(fillArrayList());
        return labelArrayList.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        ArrayList<Label> labelArrayList = new ArrayList<>(fillArrayList());
        labelArrayList.removeIf(n -> n.getId() == id);
        writeFromArrayList(labelArrayList);
    }

    @Override
    public List<Label> getAll() {
        return fillArrayList();
    }

    @Override
    public List<Label> getLabelsById(int ...id) {
        List<Label> labelsList = fillArrayList();
        ArrayList<Label> labels = new ArrayList<>();
        for (int i: id) {
            labels.add(labelsList.get(i - 1));
        }
        return labels;
    }

    private String labelToString(Label label) {
        return label.getId() + ";" + label.getName();
    }

    private Label stringToLabel(String line) {
        String[] splitLine = line.split(";");
        return new Label(splitLine[1], Long.parseLong(splitLine[0]));
    }

    private List<Label> fillArrayList() {
        ArrayList<Label> labelsList = new ArrayList<>();

        try (BufferedReader labelsReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = labelsReader.readLine()) != null) {
                labelsList.add(stringToLabel(line));
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
        return labelsList;
    }

    private void writeFromArrayList(List<Label> labelsList) {
        try (BufferedWriter labelsWriter = new BufferedWriter(new FileWriter(path))) {
            for (Label label : labelsList) {
                labelsWriter.write(labelToString(label) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortById(List<Label> labelList) {
        List<Label> sortedLabelList = labelList.stream()
                .sorted(Comparator.comparingLong(Label::getId))
                .collect(Collectors.toList());
        writeFromArrayList(sortedLabelList);
    }


    private long autoSetId() {
        long countsOfId = 1L;
        ArrayList<Label> idList = new ArrayList<>(fillArrayList());
        for (Label label : idList) {
            if (countsOfId == label.getId()) countsOfId++;
            else break;
        }
        return countsOfId;
    }
}
