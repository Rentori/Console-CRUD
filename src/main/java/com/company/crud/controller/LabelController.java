package main.java.com.company.crud.controller;

import main.java.com.company.crud.model.Label;
import main.java.com.company.crud.repository.io.JavaLabelIORepositoryImpl;
import main.java.com.company.crud.repository.LabelRepository;

import java.util.List;

public class LabelController {
    LabelRepository labelRepository = new JavaLabelIORepositoryImpl();

    public Label saveLabel(Label label) {
        return labelRepository.save(label);
    }

    public Label updateLabel(Label label) {
        return labelRepository.update(label);
    }

    public Label getById(Long id) {
        return labelRepository.getById(id);
    }

    public void deleteById(Long id) {
        labelRepository.deleteById(id);
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public List<Label> getLabelsById(int ...id) {
        return labelRepository.getLabelsById(id);
    }

}
