package main.java.com.company.crud.view;

import main.java.com.company.crud.controller.LabelController;
import main.java.com.company.crud.controller.PostController;
import main.java.com.company.crud.model.Label;
import main.java.com.company.crud.model.Post;

import java.util.*;

public class PostView {
    String operationsInfo = ("""
            ----------------------------------
            1: Save post.
            2: Update post.
            3: Get post by ID.
            4: Delete post.
            5: Get all posts.
            6: Exit.
            ----------------------------------
            """);
    Scanner in = new Scanner(System.in);
    Boolean appStatus = true;
    String operation;
    LabelController labelController = new LabelController();
    PostController postController = new PostController();

    public void runPostView() {
        try {
            appStatus = true;
            while (appStatus) {
                System.out.println(operationsInfo);
                System.out.print("Enter a command: ");
                operation = in.next();

                switch (operation) {
                    case "1" -> savePostView();
                    case "2" -> updatePostView();
                    case "3" -> getByIdView();
                    case "4" -> deleteById();
                    case "5" -> getAllView();
                    case "6" -> appStatus = false;
                    default -> System.out.println("Unknown command, try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input error.");
        }
    }

    public void savePostView() {
        System.out.print("Enter content name: ");
        String content = in.next();
        System.out.print("Enter labels id: ");
        String[] stringId = in.next().split(",");
        int[] id = new int[stringId.length];
        for (int i = 0; i < stringId.length; i++) {
            id[i] = Integer.parseInt(stringId[i]);
        }
        List<Label> labelList = labelController.getLabelsById(id);
        postController.save(new Post(content, labelList));

    }

    public void updatePostView() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        System.out.print("Enter a new content: ");
        String content = in.next();
        postController.update(new Post(id, content));
    }

    public void deleteById() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        postController.deleteById(id);
    }

    public void getByIdView() {
        System.out.print("Enter id: ");
        Long id = in.nextLong();
        Post post = postController.getById(id);
        if (post == null) System.out.println("ID not found.");
        else post.getInfo();
    }

    public void getAllView() {
        List<Post> postList = postController.getAll();
        postList.forEach(Post::getInfo);
    }
}
