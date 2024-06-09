package com.example.jokebot.repository;

import com.example.jokebot.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {
}
