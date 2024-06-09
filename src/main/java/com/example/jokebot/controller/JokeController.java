package com.example.jokebot.controller;

import com.example.jokebot.model.Joke;
import com.example.jokebot.service.JokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jokes")
public class JokeController {
    @Autowired
    private JokeService jokeService;

    @GetMapping
    public List<Joke> getAllJokes() {
        return jokeService.getAllJokes();
    }

    @GetMapping("/{id}")
    public Joke getJokeById(@PathVariable Long id) {
        return jokeService.getJokeById(id);
    }

    @PostMapping
    public Joke createJoke(@RequestBody Joke joke) {
        return jokeService.createJoke(joke);
    }

    @PutMapping("/{id}")
    public Joke updateJoke(@PathVariable Long id, @RequestBody Joke joke) {
        return jokeService.updateJoke(id, joke);
    }

    @DeleteMapping("/{id}")
    public void deleteJoke(@PathVariable Long id) {
        jokeService.deleteJoke(id);
    }
}
