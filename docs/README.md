# Chatty User Guide
___
## Introduction

Chatty is a simple yet powerful task management chatbot designed to help you organize your tasks efficiently. 
It supports adding different types of tasks, marking them as completed, searching for tasks, and more—all 
through a command-line interface. This guide will walk you through how to install and use Chatty effectively.
___
## Quick Start

1. **Ensure you have Java 17 or above installed** on your computer.
    - **Mac users**: Ensure you have the exact JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
    - Windows & Linux users: Download Java from
   [Oracle's official website](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
   or install it using your package manager.

2. **Download the latest `.jar` file** from [here](https://github.com/Siyan-G/ip/releases).

3. **Copy the file** to the folder you want to use as the home folder for your Chatty bot.

4. **Open a command terminal**:
    - `cd` into the folder where you placed the `chatty.jar` file.

5. **Run the application** using the following command:
   <pre>
   <code>
   java -jar chatty.jar
   </code>
   </pre>

6. The GUI should appear in a few seconds similar to the picture below.
   ![Screenshot of the GUI](https://Siyan-G.github.io/ip/Ui.png)

7. Type your commands into the text field and press Send to execute them. 
For example, typing `help` and pressing Send will display a list of available commands.
___
## Available Commands

**Warning:** All commands are case-sensitive and must be typed in lowercase.

### 1. `todo [task]`
Adds a new task without a deadline.  
**Example**: `todo buy groceries`

### 2. `deadline [task] /by [dd/mm/yyyy hhmm]`
Adds a task with a specific deadline.  
**Example**: `deadline submit assignment /by 20/02/2025 2359`

### 3. `event [task] /from [start date/time] /to [end date/time]`
Adds an event with a duration.  
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
Finds all tasks containing the specified keyword (case-sensitive) in the description.  
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

## Additional Features
### Data Storage
Chatty automatically saves your tasks in a local file. The next time you run Chatty, 
your previous tasks will still be available.

### Error Handling
If you enter an incorrect command, Chatty will notify you and provide guidance on the correct format.

## Troubleshooting
### Chatty does not start when I run the .jar file.
- Ensure you have Java 17 or later installed.
- Check that you are in the correct directory where chatty.jar is located.
- Try running `java -version` to verify your Java installation.

### Chatty does not save my tasks after restarting.

- Make sure you have write permissions for the folder where chatty.jar is stored.
- The storage file might be corrupted. Try deleting it and running Chatty again.

### How do I reset Chatty?

- Delete the task storage file (chatty.txt) in the home folder where chatty.jar is stored.
- Restart Chatty.

#### Found a bug or have an idea for improvement? Report them in [GitHub Issues](https://github.com/Siyan-G/ip/issues).