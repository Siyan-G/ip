package chatty.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for the {@link Todo} class.
 * <p>
 * This test class verifies the behavior of the {@link Todo} class, specifically the formatting of its string
 * representation in different states (done or not done). It ensures that the {@link Todo#toString()} method
 * returns the correct format both when the task is unmarked and marked as done.
 * </p>
 */
public class TodoTest {

    /**
     * Tests the {@link Todo#toString()} method to ensure that it returns the correct string format for a
     * {@link Todo} task that is not marked as done.
     * <p>
     * The expected string format for a not-done task is "[T][ ] <task description>".
     * </p>
     */
    @Test
    public void todoToString_shouldReturnCorrectFormat() {
        String todoDescription = "watch lecture video";
        Todo newTodo = new Todo(todoDescription);
        String actual = newTodo.toString();
        String expected = "[T][ ] watch lecture video";

        assertEquals(expected, actual);
    }

    /**
     * Tests the {@link Todo#toString()} method to ensure that it returns the correct string format for a
     * {@link Todo} task when marked as done and then unmarked as done.
     * <p>
     * The expected string format for a done task is "[T][X] <task description>" and for an undone task,
     * it is "[T][ ] <task description>".
     * </p>
     */
    @Test
    public void todoToString_shouldReturnCorrectFormat_whenMarkedDoneAndUnDone() {
        String todoDescription = "watch lecture video";
        Todo newTodo = new Todo(todoDescription);

        // Mark the task as done and check the format
        newTodo.markDone();
        String markDoneActual = newTodo.toString();
        String markDoneExpected = "[T][X] watch lecture video";
        assertEquals(markDoneExpected, markDoneActual);

        // Unmark the task and check the format again
        newTodo.unmarkDone();
        String markUnDoneActual = newTodo.toString();
        String markUnDoneExpected = "[T][ ] watch lecture video";
        assertEquals(markUnDoneExpected, markUnDoneActual);
    }
}
