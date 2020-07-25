package io.projeto.api.project.domain;

import io.projeto.api.common.util.UUIDGenerator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "project_hashtag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ProjectHashtag {

    @Id
    @Column(name = "project_hashtag_id")
    private String id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "hashtag")
    private String hashtag;

    ProjectHashtag(String projectId, String hashtag) {
        this.id = UUIDGenerator.nextUUID();
        this.projectId = projectId;
        this.hashtag = hashtag;
    }
}
