package chatty.command;

import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.task.Event;
import chatty.task.TaskList;

/**
 * Represents a command to add a new event to the task list.
 * <p>
 * This class is used to create an Event object with a description, start time, and end time,
 * and then add it to the TaskList. It also saves the updated task list to storage and
 * provides feedback to the user through the Ui component.
 * </p>
 */
public class EventCommand extends Command {

    private final String eventDescription;
    private final String startTime;
    private final String endTime;

    /**
     * Constructs an EventCommand with a specified event description, start time, and end time.
     *
     * @param eventDescription A brief description of the event.
     * @param startTime The starting time of the event, represented as a String.
     * @param endTime The ending time of the event, represented as a String.
     */
    public EventCommand(String eventDescription, String startTime, String endTime) {
        this.eventDescription = eventDescription;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Executes the command to add the new event to the task list and save it to storage.
     * Provides feedback to the user on the result of the operation.
     *
     * @param tasks The TaskList to which the new event will be added.
     * @param ui The UI to communicate feedback to the user.
     * @param storage The storage responsible for saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(eventDescription, startTime, endTime);
        tasks.add(newEvent);
        storage.saveTasks(tasks);
        ui.sendMessage(String.format("New event %s added to the list.\nNow you have %d tasks.",
                newEvent,
                tasks.getNumOfTasks()));
    }
}

