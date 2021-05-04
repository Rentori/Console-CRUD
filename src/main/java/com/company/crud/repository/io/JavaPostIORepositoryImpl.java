package main.java.com.company.crud.repository.io;

import main.java.com.company.crud.model.Label;
import main.java.com.company.crud.model.Post;
import main.java.com.company.crud.model.PostStatus;
import main.java.com.company.crud.repository.LabelRepository;
import main.java.com.company.crud.repository.PostRepository;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JavaPostIORepositoryImpl implements PostRepository {
    LabelRepository labelRepository = new JavaLabelIORepositoryImpl();
    private final String path = "/home/viktor/java-projects/consoleCRUD/src/main/resources/files/posts.txt";

    @Override
    public Post save(Post post) {
        post.setId(autoSetId());
        post.setPostStatus(String.valueOf(PostStatus.ACTIVE));
        post.setCreated(getCurrentDate());
        post.setUpdated(getCurrentDate());

        try (BufferedWriter labelsWriter = new BufferedWriter(new FileWriter(path, true))) {
            labelsWriter.write(postToString(post) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
              sortById(fillArrayList());
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        List<Post> postList = fillArrayList().stream()
                .peek(n -> {
                    if (n.getId().equals(post.getId())) {
                        n.setContent(post.getContent());
                        n.setUpdated(getCurrentDate());
                        n.setPostStatus(String.valueOf(PostStatus.UNDER_REVIEW));
                    }
                }).collect(Collectors.toList());

        writeFromArrayList(postList);
        return post;
    }

    @Override
    public void deleteById(Long id) {
        List<Post> postArrayList =fillArrayList().stream()
                .peek(n -> {
                    if (n.getId().equals(id)) {
                        n.setPostStatus(String.valueOf(PostStatus.DELETED));
                    }
                }).collect(Collectors.toList());
        writeFromArrayList(postArrayList);
    }

    @Override
    public Post getById(Long id) {
        ArrayList<Post> postArrayList = new ArrayList<>(fillArrayList());
        return postArrayList.stream().filter(n -> n.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return fillArrayList();
    }

    @Override
    public List<Post> getPostsById(int ...id) {
        List<Post> postList = fillArrayList();
        ArrayList<Post> posts = new ArrayList<>();
        try {
            for (int i: id) {
                posts.add(postList.get(i - 1));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incorrect index, try again");
        }

        return posts;
    }

    private void sortById(List<Post> postList) {
        List<Post> sortedPostList = postList.stream()
                .sorted(Comparator.comparingLong(Post::getId))
                .collect(Collectors.toList());
        writeFromArrayList(sortedPostList);
    }

    private String postToString(Post post) {
        StringBuilder concatString = new StringBuilder(post.getId()
                + ";" + post.getContent()
                + ";" + post.getCreated()
                + ";" + post.getUpdated()
                + ";" + post.getPostStatus() + ";");
        for(Label p: post.getLabels()) {
            concatString.append(p.getId()).append(",");
        }
        return concatString.toString();
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    private Post stringToPost(String line) {
        String[] splitLine = line.split(";");
        return new Post(
                Long.parseLong(splitLine[0]),
                splitLine[1],
                splitLine[2],
                splitLine[3],
                splitLine[4],
                splitLabel(splitLine[5]));
    }

    private List<Post> fillArrayList() {
        ArrayList<Post> postsList = new ArrayList<>();

        try (BufferedReader postReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = postReader.readLine()) != null) {
                postsList.add(stringToPost(line));
            }
        } catch (IOException e) {
            System.out.println("File not found: " + e);
        }
        return postsList;
    }


    private void writeFromArrayList(List<Post> postList) {
        try (BufferedWriter postWriter = new BufferedWriter(new FileWriter(path))) {
            for (Post post : postList) {
                postWriter.write(postToString(post) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Label> splitLabel(String label) {
        String[] labelsId = label.split(",");
        int[] labels = new int[labelsId.length];
        for (int i = 0; i < labelsId.length; i++) {
            labels[i] = Integer.parseInt(labelsId[i]);
        }
        return labelRepository.getLabelsById(labels);
    }

    private long autoSetId() {
        long countsOfId = 1L;
        ArrayList<Post> idList = new ArrayList<>(fillArrayList());
        for (Post post : idList) {
            if (countsOfId == post.getId()) countsOfId++;
            else break;
        }
        return countsOfId;
    }
}
