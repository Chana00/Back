package jpabook.jpashop.service;

import jpabook.jpashop.common.exception.SowonException;
import jpabook.jpashop.common.exception.Status;
import jpabook.jpashop.domain.wish.Post;
import jpabook.jpashop.domain.wish.User;
import jpabook.jpashop.dto.post.CreatePostDto;
import jpabook.jpashop.dto.post.GetPostDto;
import jpabook.jpashop.dto.post.UpdatePostDto;
import jpabook.jpashop.repository.PostRepository;
import jpabook.jpashop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepo;
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    public void insertPost(CreatePostDto.Request reqDto) {
        User user = userRepo.findById(reqDto.getUserId()).orElseThrow(() -> new SowonException(Status.ACCESS_DENIED));
        Post post = modelMapper.map(reqDto, Post.class);
        post.setPost_user_id(user);
        postRepo.save(post);
    }

  /*  public Post updatePost(Post post, Long id) {
        Post findPost = postRepo.findById(id).get();

        findPost.setContent(post.getContent());
        findPost.setTitle(post.getTitle());

        return postRepo.save(findPost);
    }*/

    public void updatePost(UpdatePostDto.Request reqDto, Long id) {
        Post findPost = postRepo.findById(id).get();

        findPost.setContent(reqDto.getContent());
        findPost.setTitle(reqDto.getTitle());
        findPost.setCategory(reqDto.getCategory());
        findPost.setIsParticipate(reqDto.getIsParticipate());
        findPost.setIsPrivate(reqDto.getIsPrivate());
        //Post post = modelMapper.map(reqDto, Post.class);
        postRepo.save(findPost);
    }

    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }

    public GetPostDto.Response getPost(Long id) {
        Post post =  postRepo.findById(id).orElseThrow(()-> new SowonException(Status.NOT_FOUND));
        GetPostDto.Post resPost = modelMapper.map(post, GetPostDto.Post.class);

        return GetPostDto.Response.builder().post(resPost).build();
    }


    public Page<Post> findAllPage(Pageable pageable) {

        return postRepo.findAll(pageable);

    }

  /*  public void findAllPage(Pageable pageable) {

         postRepo.findAll(pageable).map(GetPostDto.Response::from);

    }*/

}
