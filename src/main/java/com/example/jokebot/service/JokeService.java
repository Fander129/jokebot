package com.example.jokebot.service;

import com.example.jokebot.model.Joke;
import com.example.jokebot.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeService {

    @Autowired
    private JokeRepository jokeRepository;

    public List<Joke> getAllJokes() {
        return jokeRepository.findAll();
    }

    public Joke getJokeById(Long id) {
        return jokeRepository.findById(id).orElse(null);
    }

    public Joke createJoke(Joke joke) {
        return jokeRepository.save(joke);
    }

    public Joke updateJoke(Long id, Joke joke) {
        Joke existingJoke = jokeRepository.findById(id).orElse(null);
        if (existingJoke != null) {
            existingJoke.setContent(joke.getContent());
            return jokeRepository.save(existingJoke);
        } else {
            return null;
        }
    }

    public void deleteJoke(Long id) {
        jokeRepository.deleteById(id);
    }
}
