package chatty;

import chatty.command.Command;
import chatty.controller.Parser;
import chatty.controller.Storage;
import chatty.controller.Ui;
import chatty.exception.ChattyException;
import chatty.task.TaskList;

public class Chatty {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Chatty(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = storage.loadTasks();
        } catch (Exception e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.sendIntroMsg();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(ui, fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (ChattyException e) {
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Chatty("./data/tasks.csv").run();
    }
}
