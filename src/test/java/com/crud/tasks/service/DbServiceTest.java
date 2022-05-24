package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    void shouldGetAllTasks() {
        // Given
        Task task =new Task(1L, "title", "content");
        List<Task> taskList = List.of(task);
        when(repository.findAll()).thenReturn(taskList);

        // When
        List<Task> allTasks = dbService.getAllTasks();

        // Then
        assertThat(allTasks.size()).isEqualTo(1);
        assertThat(allTasks.contains(task));
    }

    @Test
    void shouldFindTaskById() {
        // Given
        Task task = new Task(1L, "title", "content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        // When
        Task taskById = dbService.findTaskById(1L);

        // Then
        assertEquals(taskById, task);
    }

    @Test
    void shouldSaveTask() {
        // Given
        Task task =new Task(1L, "title", "content");
        when(repository.save(task)).thenReturn(task);

        // When
        Task task1 = dbService.saveTask(task);

        // Then
        assertEquals(task, task1);
    }

    @Test
    void shouldDeleteTask() {
        // Given
        Task task =new Task(1L, "title", "content");
        doNothing().when(repository).deleteById(1L);
        // When
        dbService.deleteTask(1L);

        // Then
        verify(repository, times(1)).deleteById(1L);
    }
}
