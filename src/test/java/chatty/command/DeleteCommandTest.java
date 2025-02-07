package chatty.command;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeleteCommandTest {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = mock(Ui.class);
        storage = mock(Storage.class);

        tasks.add(new Todo("Sample Task"));
    }

    @Test
    void execute_shouldDeleteTask_whenValidIndexGiven() throws ChattyTaskNotFoundException {
        int taskIndex = 1;
        Task taskToDelete = tasks.getTask(taskIndex);
        DeleteCommand deleteCommand = new DeleteCommand(taskIndex);

        deleteCommand.execute(tasks, ui, storage);

        assertThrows(ChattyTaskNotFoundException.class, () -> tasks.getTask(taskIndex));

        verify(ui).sendMessage(String.format("Task %d %s has been deleted", taskIndex, taskToDelete));

        verify(storage).saveTasks(tasks);
    }
}
