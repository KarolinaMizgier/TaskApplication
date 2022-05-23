package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {

    TaskMapper taskMapper = new TaskMapper();

    @Test
    public void shouldMapToTask() {
        // given
        TaskDto taskDto = new TaskDto(1L, "title", "content");

        // when
        Task task = taskMapper.mapToTask(taskDto);

        // then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void shouldMapToTaskDto() {
        // given
        Task task = new Task(1L, "title", "content");

        // when
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        // then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void shouldMapToTaskDtoList() {
        // given
        Task task = new Task(1L, "title", "content");
        List<Task> taskList = List.of(task);

        // when
        List<TaskDto> taskDtos = taskMapper.mapToTaskDtoList(taskList);

        // then
        Task task1 = taskList.get(0);
        TaskDto taskDto = taskDtos.get(0);
        assertEquals(taskDto.getId(), task1.getId());
        assertEquals(taskDto.getTitle(), task1.getTitle());
        assertEquals(taskDto.getContent(), task1.getContent());
    }

}
