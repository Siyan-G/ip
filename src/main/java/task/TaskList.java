package task;

import exception.ChattyTaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> tasks;
    private int numOfTasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numOfTasks = 0;
    }

    public TaskList(ArrayList<Task> tasks, int numOfTasks) {
        this.tasks = tasks;
        this.numOfTasks = numOfTasks;
    }

    public Task getTask(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > tasks.size()) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            return tasks.get(index - 1);
        }
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }


    public boolean delete(int index) {
        if (index < 0 || index >= numOfTasks) {
            return false;
        } else {
            tasks.remove(index - 1);
            numOfTasks--;
            return true;
        }
    }

    public boolean add(Task task) {
        this.tasks.add(task);
        numOfTasks++;
        return true;
    }

    public void mark(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > numOfTasks) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            tasks.get(index - 1).markDone();
        }
    }

    public void unmark(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > numOfTasks) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            tasks.get(index - 1).unmarkDone();
        }
    }

    public String[] toCSV() {
        String[] taskList = new String[numOfTasks];
        for (int i = 0; i < numOfTasks; i++) {
            taskList[i] = tasks.get(i).toCsv();
        }
        return taskList;
    }

    @Override
    public String toString() {
        if (numOfTasks == 0) {
            return "No task currently";
        } else {
            StringBuilder sb = new StringBuilder(String.format("You current have %d tasks in the list", this.getNumOfTasks()));
            for (int i = 0; i < numOfTasks; i++) {
                sb.append("\n").append(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            return sb.toString();
        }
    }

}
