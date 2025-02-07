package chatty.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToString_shouldReturnCorrectFormat() {
        String todoDescription = "watch lecture video";
        Todo newTodo = new Todo(todoDescription);
        String actual = newTodo.toString();
        String expected = "[T][ ] watch lecture video";

        assertEquals(expected, actual);
    }

    @Test
    public void todoToString_shouldReturnCorrectFormat_whenMarkedDoneAndUnDone() {
        String todoDescription = "watch lecture video";
        Todo newTodo = new Todo(todoDescription);
        newTodo.markDone();
        String markDoneActual = newTodo.toString();
        String markDoneExpected = "[T][X] watch lecture video";

        assertEquals(markDoneExpected, markDoneActual);

        newTodo.unmarkDone();
        String markUnDoneActual = newTodo.toString();
        String markUnDoneExpected = "[T][ ] watch lecture video";

        assertEquals(markUnDoneExpected, markUnDoneActual);
    }
}
