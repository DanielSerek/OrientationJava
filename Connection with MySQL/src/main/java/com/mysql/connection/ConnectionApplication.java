package com.mysql.connection;

import com.mysql.connection.services.AssigneeService;
import com.mysql.connection.services.TODOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConnectionApplication implements CommandLineRunner {

    private TODOService todoService;
    private AssigneeService assigneeService;

    @Autowired
    ConnectionApplication (TODOService todoService, AssigneeService assigneeService){
        this.todoService = todoService;
        this.assigneeService = assigneeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConnectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        todoService.createTask("Shop", "Go to the shop and buy milk", LocalDateTime.of(2020, Month.JULY, 19, 19, 30, 40), true, false);
//        todoService.createTask("Sleep", "Get some sleep", new GregorianCalendar(2020, Calendar.JULY, 14).getTime(), true, false);
//        Date date = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        String strDate = dateFormat.format(date);
//        todoService.createTask("Coding", "Practice coding", strDate, true, false);
//        todoService.createTask("Neighbour", "Kill the neighbour", strDate, true, false);
//        assigneeService.createAssignee("Daniel Šerek", "daniel.serek.uk@gmail.com");
        assigneeService.createAssignee("Noname", "noname@gmail.com");
    }
}
