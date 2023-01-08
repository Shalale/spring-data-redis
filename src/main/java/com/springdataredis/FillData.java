package com.springdataredis;

import com.springdataredis.dao.entity.User;
import com.springdataredis.dao.repository.UserRepository;
import com.springdataredis.model.constants.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class FillData {
    private final UserRepository repository;

    @PostConstruct
    private void createUserData() {
        repository.save(new User(1L, "Shalale", LocalDateTime.now(), LocalDateTime.now(), Status.ACTIVE));
        repository.save(new User(2L, "Hikmet", LocalDateTime.now(), LocalDateTime.now(), Status.ACTIVE));
        repository.save(new User(3L, "Arya", LocalDateTime.now(), LocalDateTime.now(), Status.ACTIVE));
        repository.save(new User(4L, "Nur", LocalDateTime.now(), LocalDateTime.now(), Status.ACTIVE));
    }
}
