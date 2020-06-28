package io.projeto.api.project.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "project_regular_meeting")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectRegularMeeting {

    @Id
    @Column(name = "project_regular_meeting_id")
    private String id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "description")
    private String description;

    @Column(name = "meeting_url")
    private String meetingUrl;
}
