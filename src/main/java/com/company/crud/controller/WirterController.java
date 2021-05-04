package main.java.com.company.crud.controller;

import main.java.com.company.crud.model.Writer;
import main.java.com.company.crud.repository.io.JavaWriterIORepositoryImpl;
import main.java.com.company.crud.repository.WriterRepository;

import java.util.List;

public class WirterController {
    WriterRepository writerIORepository = new JavaWriterIORepositoryImpl();

    public Writer save(Writer writer) {
        return writerIORepository.save(writer);
    }

    public Writer update(Writer writer) {
        return writerIORepository.update(writer);
    }

    public Writer getById(Long id) {
        return writerIORepository.getById(id);
    }

    public void deleteById(Long id) {
        writerIORepository.deleteById(id);
    }

    public List<Writer> getAll() {
        return writerIORepository.getAll();
    }
}
