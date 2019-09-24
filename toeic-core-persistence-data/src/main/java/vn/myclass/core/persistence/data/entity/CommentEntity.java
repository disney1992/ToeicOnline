package vn.myclass.core.persistence.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @Column(name="commentid")
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name="userid")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name="listenguidelineid")
    private ListenguidelineEntity listenguidelineEntity;
    @Column(name = "content")
    private String content;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public ListenguidelineEntity getListenguidelineEntity() {
        return listenguidelineEntity;
    }

    public void setListenguidelineEntity(ListenguidelineEntity listenguidelineEntity) {
        this.listenguidelineEntity = listenguidelineEntity;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
