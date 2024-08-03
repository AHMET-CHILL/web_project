package com.chill.questapp.repos;

import com.chill.questapp.entities.Post;
import com.chill.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


}
