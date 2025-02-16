# Chatty User Guide

## Quick Start

1. **Ensure you have Java 17 or above installed** on your computer.
   - **Mac users**: Ensure you have the exact JDK version prescribed [here](#).

2. **Download the latest `.jar` file** from [here](#).

3. **Copy the file** to the folder you want to use as the home folder for your Chatty bot.

4. **Open a command terminal**:
   - `cd` into the folder where you placed the `chatty.jar` file.

5. **Run the application** using the following command:
   ```bash
   java -jar chatty.jar
   
6. The GUI should appear in a few seconds similar to the picture below.
   ![Screenshot of the GUI](/docs/Ui.png)

7. Type the command in the text field and click Send to execute it. e.g. typing 'help' and clicking 'Send' will show you a list of commands available with their formats

## Available Commands

**Warning:** All commands are case-sensitive and must be typed in lowercase.

### 1. `todo [task]`
Adds a new task without a deadline.  
**Example**: `todo buy groceries`

### 2. `deadline [task] /by [dd/mm/yyyy hhmm]`
Adds a task with a specific deadline.  
**Example**: `deadline submit assignment /by 20/02/2025 2359`

### 3. `event [task] /from [start date/time] /to [end date/time]`
Adds an event with a specific date.  
**Example**: `event dental appointment /from 05-03/25 10am /to 11am`

### 4. `list`
Displays all tasks.  
**Example**: `list`

### 5. `mark [task number]`
Marks a specific task as completed.  
**Example**: `mark 2`

### 6. `unmark [task number]`
Marks a specific task as not completed.  
**Example**: `unmark 2`

### 7. `find [keyword]`
Finds all tasks containing the specified keyword in the description.  
**Example**: `find groceries`

### 8. `delete [task number]`
Deletes a specific task.  
**Example**: `delete 3`

### 9. `help`
Displays a list of available commands.  
**Example**: `help`

### 10. `bye`
Exits the application.  
**Example**: `bye`


---

## Command Summary

| Command                                                    | Description                                           |
|------------------------------------------------------------|-------------------------------------------------------|
| `todo [task]`                                              | Adds a new task without a deadline.                   |
| `deadline [task] /by [dd/mm/yyyy hhmm]`                    | Adds a task with a specific deadline.                 |
| `event [task] /from [start date/time] /to [end date/time]` | Adds an event with a specific date.                   |
| `list`                                                     | Displays all tasks.                                  |
| `mark [task number]`                                       | Marks a specific task as completed.                  |
| `unmark [task number]`                                     | Marks a specific task as not completed.              |
| `delete [task number]`                                     | Deletes a specific task.                             |
| `help`                                                     | Displays a list of available commands.               |
| `find [keyword]`                                           | Finds all tasks containing the specified keyword.    |
| `bye`                                                      | Exits the application.                               |
