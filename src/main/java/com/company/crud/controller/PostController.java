package main.java.com.company.crud.controller;

import main.java.com.company.crud.model.Post;
import main.java.com.company.crud.repository.io.JavaPostIORepositoryImpl;
import main.java.com.company.crud.repository.PostRepository;

import java.util.List;

public class PostController {
    PostRepository postIORepository = new JavaPostIORepositoryImpl();

    public Post save(Post post) {
        return postIORepository.save(post);
    }

    public Post update(Post post) {
        return postIORepository.update(post);
    }

    public void deleteById(Long id) {
        postIORepository.deleteById(id);
    }

    public Post getById(Long id) {
        return postIORepository.getById(id);
    }

    public List<Post> getAll() {
        return postIORepository.getAll();
    }

    public List<Post> getPostsById(int ...id) {
        return postIORepository.getPostsById(id);
    }
}
