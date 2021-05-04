package main.java.com.company.crud.repository;

import main.java.com.company.crud.model.Label;
import main.java.com.company.crud.repository.GenericRepository;

import java.util.List;

public interface LabelRepository extends GenericRepository<Label, Long> {
    List<Label> getLabelsById(int ...id);
}
