package com.greenfoxacademy;

public class TodoApp {
    private final TaskService taskService;

    public TodoApp(TaskService taskService) {
//        TaskRepository taskRepository = new FileTaskRepository("resources/todo.txt");
//        this.taskService = new TaskService(taskRepository);

        this.taskService = taskService;
    }

    public void run(String[] args) {
        if (args.length < 1) {
            printUsage();
            return;
        }

        if (args[0].equals("-l")) {
            taskService.listAllTasks();
            return;
        }

        if (args[0].equals("-a")) {
            if (args.length < 2) {
                System.out.println("Unable to add: no task provided");
                return;
            }

            taskService.addTask(args[1]);
            return;
        }

        if (args[0].equals("-c")) {
            if (args.length < 2) {
                System.out.println("Unable to check: no index provided");
                return;
            }

            try {
                taskService.completeTask(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                System.out.println("Unable to check: index is not a number");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Unable to check: index is out of bound");
            }
            return;
        }

        if (args[0].equals("-r")) {
            if (args.length < 2) {
                System.out.println("Unable to remove: no index provided");
                return;
            }

            try {
                taskService.removeTask(Integer.parseInt(args[1]));
            } catch (NumberFormatException e) {
                System.out.println("Unable to remove: index is not a number");
            }
            return;
        }

        if (args[0].equals("-u")) {
            if (args.length < 3) {
                System.out.println("Unable to update: no index and task provided");
                return;
            }

            long id = 0;

            try {
                id = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Unable to update: index is not a number");
                return;
            }

            try {
                taskService.updateName(id, args[2]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Unable to update: index is out of bound");
            }

            return;
        }

        System.out.println("Unsupported argument");
    }

    private void printUsage() {
        System.out.println("Command Line Todo application\n" +
                "=============================\n" +
                "\n" +
                "Command line arguments:\n" +
                " -l   Lists all the tasks\n" +
                " -a   Adds a new task\n" +
                " -u   Updates a task\n" +
                " -r   Removes an task\n" +
                " -c   Completes an task");
    }
}
