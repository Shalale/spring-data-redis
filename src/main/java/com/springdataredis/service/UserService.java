package com.springdataredis.service;

import com.springdataredis.dao.entity.User;
import com.springdataredis.dao.repository.UserRepository;
import com.springdataredis.model.constants.Status;
import com.springdataredis.model.dto.UserResponse;
import com.springdataredis.model.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CacheConfig(cacheNames = "user")
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final ModelMapper mapper;

    public UserResponse createUser(UserRequest request){
        var user = mapper.map(request, User.class);
        repository.save(user);

        return mapper.map(user, UserResponse.class);
    }

    @Cacheable(key = "#id")
    public UserResponse getUser(Long id){
        log.info("Getting user information for id {}", id);
        var user = fetchIfExist(id);
        return mapper.map(user, UserResponse.class);
    }

    @CachePut(cacheNames = "user", key = "#id")
    public void updateUser(Long id, String username){
        User user = fetchIfExist(id);
        user.setUsername(username);
        repository.save(user);
    }

    @CacheEvict(cacheNames = "user", key = "#id")
    public void delete(Long id){
        var user = fetchIfExist(id);
        repository.delete(user);
    }
    private User fetchIfExist(Long id){
        return repository.findById(id).orElseThrow();
    }

    @CacheEvict(cacheNames = "user", allEntries = true)
    public void deleteAllCache() {
    }
}
