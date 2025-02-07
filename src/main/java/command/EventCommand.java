package command;

import controller.Storage;
import controller.Ui;
import task.Event;
import task.TaskList;

public class EventCommand extends Command {
    String eventDescription;
    String startTime;
    String endTime;

    public EventCommand(String eventDescription, String startTime, String endTime) {
        this.eventDescription = eventDescription;
        this.startTime = startTime;
        this.endTime = endTime;
    }

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
