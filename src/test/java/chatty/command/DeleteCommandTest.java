package chatty.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chatty.controller.Storage;
import chatty.exception.ChattyTaskNotFoundException;
import chatty.task.Task;
import chatty.task.TaskList;
import chatty.task.Todo;
import chatty.ui.Ui;

/**
 * Unit test class for the {@link DeleteCommand} class.
 * <p>
 * This test class verifies the behavior of the {@link DeleteCommand} when attempting to delete a task
 * from the {@link TaskList}. It uses mocks for the {@link Ui} and {@link Storage} classes to isolate
 * the logic being tested and ensure that the task is correctly removed and saved.
 * </p>
 */
class DeleteCommandTest {

    private TaskList tasks; // List of tasks to be tested.
    private Ui ui; // Mocked user interface.
    private Storage storage; // Mocked storage.

    /**
     * Sets up the test environment before each test case.
     * Initializes a {@link TaskList}, adds a sample task, and mocks the {@link Ui} and {@link Storage}.
     */
    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = mock(Ui.class);
        storage = mock(Storage.class);

        tasks.add(new Todo("Sample Task"));
    }

    /**
     * Tests the {@link DeleteCommand#execute(TaskList, Ui, Storage)} method to ensure that the task is deleted
     * when a valid task index is provided. Verifies that the task is no longer available in the {@link TaskList},
     * and that the correct message is sent to the user interface and tasks are saved to storage.
     * <p>
     * This test expects a {@link ChattyTaskNotFoundException} to be thrown when attempting to retrieve the deleted
     * task by its index.
     * </p>
     *
     * @throws ChattyTaskNotFoundException if the task is not found in the task list (expected in this test).
     */
    @Test
    void execute_shouldDeleteTask_whenValidIndexGiven() throws ChattyTaskNotFoundException {
        int taskIndex = 1;
        Task taskToDelete = tasks.getTask(taskIndex);
        DeleteCommand deleteCommand = new DeleteCommand(taskIndex);

        deleteCommand.execute(tasks, ui, storage);

        // Verifies that the task was deleted
        assertThrows(ChattyTaskNotFoundException.class, () -> tasks.getTask(taskIndex));

        // Verifies that the correct message was sent to the UI
        verify(ui).getMessage(String.format("Yikes! Task %d: %s, has been deleted.", taskIndex, taskToDelete));

        // Verifies that the tasks were saved after the deletion
        verify(storage).saveTasks(tasks);
    }
}
