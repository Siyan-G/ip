package chatty.task;

import chatty.exception.ChattyTaskNotFoundException;

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


    public void delete(int index) throws ChattyTaskNotFoundException {
        if (index <= 0 || index > numOfTasks) {
            throw new ChattyTaskNotFoundException(index);
        } else {
            tasks.remove(index - 1);
            numOfTasks--;
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

    /**
     * Searches for tasks that contain the specified keyword in their names.
     * <p>
     * This method iterates through the task list and checks if each task's name contains the provided keyword (case-insensitive).
     * If a task's name contains the keyword, it is added to a new {@link TaskList} which is then returned.
     * </p>
     *
     * @param keyword The keyword to search for in the task names.
     * @return A {@link TaskList} containing all tasks whose names contain the keyword. If no tasks match, an empty task list is returned.
     * @throws ChattyTaskNotFoundException If there are no tasks in the list or if none of the tasks contain the keyword.
     */
    public TaskList tasksContain(String keyword) throws ChattyTaskNotFoundException {
        TaskList taskList = new TaskList();
        if (numOfTasks == 0) {
            return taskList;
        } else {
            for (int i = 0; i < numOfTasks; i++) {
                if (tasks.get(i).contains(keyword)) {
                    taskList.add(tasks.get(i));
                }
            }
            return taskList;
        }
    }

    @Override
    public String toString() {
        if (numOfTasks == 0) {
            return "No task currently";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numOfTasks; i++) {
                sb.append("\n").append(String.format("%d. %s", i + 1, tasks.get(i).toString()));
            }
            return sb.toString();
        }
    }

}
