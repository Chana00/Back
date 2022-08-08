package jpabook.jpashop.dto.post;

import jpabook.jpashop.domain.wish.Photo;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class GetPostDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Post post;
    }

    @Getter
    @Setter
    public static class Post{
        private User post_user_id;
        private String title;
        private String content;
        private String category;
        private int isPrivate;
        private int isCompleted;
        private int isParticipate;
        private int viewCount;
        private int likeCount;
       //private List<Likes> likesList;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        private List<Long> photoIdList;
        private List<Comment> commentList;
    }

    @Getter
    @Setter
    public static class Comment {
        private Long id;
        private String content;
        private String userName;
        private Boolean secret;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        private List<Comment> commentList;
    }

}
