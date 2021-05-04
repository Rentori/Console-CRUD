package main.java.com.company.crud.repository.io;

import main.java.com.company.crud.model.Post;
import main.java.com.company.crud.model.Writer;
import main.java.com.company.crud.repository.PostRepository;
import main.java.com.company.crud.repository.WriterRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JavaWriterIORepositoryImpl implements WriterRepository {
    PostRepository postRepository = new JavaPostIORepositoryImpl();
    private final String path = "/home/viktor/java-projects/consoleCRUD/src/main/resources/files/writers.txt";

    @Override
    public Writer save(Writer writer) {
        writer.setId(autoSetId());
        try (BufferedWriter writersWriter = new BufferedWriter(new FileWriter(path, true))) {
            writersWriter.write(writerToString(writer) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sortById(fillArrayList());
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> writerList = fillArrayList().stream()
                .peek(n -> {
                    if (n.getId().equals(writer.getId())) {
                        n.setFirstName(writer.getFirstName());
                        n.setLastName(writer.getLastName());
                    }
                }).collect(Collectors.toList());

        writeFromArrayList(writerList);
        return writer;
    }

    @Override
    public Writer getById(Long id) {
        ArrayList<Writer> writerArrayList = new ArrayList<>(fillArrayList());
        return writerArrayList.stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        ArrayList<Writer> writerArrayList = new ArrayList<>(fillArrayList());
        writerArrayList.removeIf(n -> n.getId().equals(id));
        writeFromArrayList(writerArrayList);
    }

    @Override
    public List<Writer> getAll() {
        return fillArrayList();
    }

    private void writeFromArrayList(List<Writer> writerList) {
        try (BufferedWriter writerWriter = new BufferedWriter(new FileWriter(path))) {
            for (Writer writer : writerList) {
                writerWriter.write(writerToString(writer) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Writer> fillArrayList() {
        ArrayList<Writer> writersList = new ArrayList<>();

        try (BufferedReader writerReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = writerReader.readLine()) != null) {
                writersList.add(stringToWriter(line));
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
        return writersList;
    }

    private String writerToString(Writer writer) {
        StringBuilder concatString = new StringBuilder(writer.getId()
                + ";" + writer.getFirstName()
                + ";" + writer.getLastName() + ";");
        for(Post p: writer.getPosts()) {
            concatString.append(p.getId()).append(",");
        }
        return concatString.toString();
    }

    private void sortById(List<Writer> writerList) {
        List<Writer> sortedWriterList = writerList.stream()
                .sorted(Comparator.comparingLong(Writer::getId))
                .collect(Collectors.toList());
        writeFromArrayList(sortedWriterList);
    }

    private Writer stringToWriter(String line) {
        String[] splitLine = line.split(";");
        return new Writer(
                Long.parseLong(splitLine[0]),
                splitLine[1],
                splitLine[2],
                splitPosts(splitLine[3]));
    }

    private List<Post> splitPosts(String postLine) {
        String[] postsId = postLine.split(",");
        int[] posts = new int[postsId.length];
        for (int i = 0; i < postsId.length; i++) {
            posts[i] = Integer.parseInt(postsId[i]);
        }
        return postRepository.getPostsById(posts);
    }

    private long autoSetId() {
        long countsOfId = 1L;
        ArrayList<Writer> idList = new ArrayList<>(fillArrayList());
        for (Writer writer : idList) {
            if (countsOfId == writer.getId()) countsOfId++;
            else break;
        }
        return countsOfId;
    }
}
