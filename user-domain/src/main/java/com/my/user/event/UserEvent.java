package com.my.user.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.app.aws.adapter.sns.EventType;
import com.my.app.aws.adapter.sns.PublishRequest;
import com.my.app.aws.adapter.sns.SnsPublisher;
import com.my.user.User;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserEvent extends PublishRequest {

    private final SnsPublisher publisher;
    private UserDto oldUser;
    private UserDto newUser;
    private EventType eventType;

    private UserEvent(SnsPublisher publisher) {
        super("arn:aws:sns:us-east-1:333482735878:TestTopic");
        this.publisher = publisher;
        this.eventType = EventType.CREATED;
    }

    public static UserEvent created(SnsPublisher publisher) {
        return new UserEvent(publisher)
                .setEventType(EventType.CREATED);
    }

    public static UserEvent updated(SnsPublisher publisher) {
        return new UserEvent(publisher)
                .setEventType(EventType.UPDATED);
    }

    public static UserEvent deleted(SnsPublisher publisher) {
        return new UserEvent(publisher)
                .setEventType(EventType.DELETED);
    }

    public void publish() {
        publisher.publish(this);
    }


    public UserEvent setNewUser(User newUser) {
        this.newUser = new UserEvent.UserDto()
                .withEmail(newUser.getEmail().getValue())
                .withFullname(newUser.getFullname())
                .withUsername(newUser.getUsername().getValue());
        return this;
    }

    public UserEvent setOldUser(User oldUser) {
        this.oldUser = new UserEvent.UserDto()
                .withEmail(oldUser.getEmail().getValue())
                .withFullname(oldUser.getFullname())
                .withUsername(oldUser.getUsername().getValue());
        return this;
    }

    private UserEvent setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public UserDto getNewUser() {
        return newUser;
    }

    public UserDto getOldUser() {
        return oldUser;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserDto implements Serializable {
        private String username;
        private String fullname;
        private String email;

        public UserDto withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserDto withFullname(String fullname) {
            this.fullname = fullname;
            return this;
        }

        public UserDto withEmail(String email) {
            this.email = email;
            return this;
        }

        public String getUsername() {
            return username;
        }

        public String getFullname() {
            return fullname;
        }

        public String getEmail() {
            return email;
        }
    }


    
}
