package com.example.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import  org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;
import  java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task>getAllTasks()
    {
        return taskRepository.findAll();
    }

    @PostMapping
    public Task addTask(@RequestBody Task task)
    {
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDescription(updatedTask.getDescription());
        task.setCompleted(updatedTask.isCompleted());
        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
}
