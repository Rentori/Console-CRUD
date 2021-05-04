package main.java.com.company.crud.repository;

import main.java.com.company.crud.model.Post;
import main.java.com.company.crud.repository.GenericRepository;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Long> {
    List<Post> getPostsById(int ...id);
}
