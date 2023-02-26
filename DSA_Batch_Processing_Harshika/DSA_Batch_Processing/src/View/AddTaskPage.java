package View;
import controller.TaskController;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskPage extends JFrame {
    JLabel taskId;
    JTextField taskIdText;
    JLabel taskLabel;
    JTextField taskText;
    JButton addTaskButton;
    JButton scheduleJobButton;
    public AddTaskPage() {
        initialize();
        UIInitialize();
        ActionHandling();
    }
    public void initialize() {
        setTitle("Add Your Tasks Here");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720 );
        setResizable(false);
        setLayout(null);
        Color c = new Color(78, 151, 225);
        getContentPane().setBackground(c);
    }

    public void UIInitialize() {
        JLabel label = new JLabel("Add your Tasks");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 45));
        label.setBounds(450, 100, 400, 40);
        add(label);

        taskId = new JLabel("Task ID");
        taskId.setForeground(Color.WHITE);
        taskId.setFont(new Font("Arial", Font.BOLD, 25));
        taskId.setBounds(450, 200, 400, 40);
        add(taskId);

        taskIdText = new JTextField();
        taskIdText.setForeground(Color.WHITE);
        taskIdText.setBounds(600, 200, 400, 40);
        taskIdText.setFont(new Font("Ariel", Font.BOLD, 20));//font
        add(taskIdText);

        taskLabel = new JLabel("Add Task");
        taskLabel.setForeground(Color.WHITE);
        taskLabel.setFont(new Font("Arial", Font.BOLD, 25));
        taskLabel.setBounds(450, 300, 400, 40);
        add(taskLabel);

        taskText = new JTextField();
        taskText.setForeground(Color.black);
        taskText.setBounds(600, 300, 400, 40);
        taskText.setFont(new Font("Arial", Font.BOLD, 25));//font
        add(taskText);

        addTaskButton = new JButton("ADD");
        addTaskButton.setForeground(Color.BLACK);
        addTaskButton.setBackground(Color.orange);
        addTaskButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        addTaskButton.setBounds(450,400,300,45);
        add(addTaskButton);

        scheduleJobButton = new JButton("CREATE JOB");
        scheduleJobButton.setForeground(Color.BLACK);
        scheduleJobButton.setBackground(Color.orange);
        scheduleJobButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        scheduleJobButton.setBounds(450,500,300,45);
        add(scheduleJobButton);
    }
    public void ActionHandling() {
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });
        scheduleJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateJob createJob = new CreateJob();
                createJob.show();
                dispose();
            }
        });
    }

    private void addTask(){
        String task=taskText.getText();
        int taskId = Integer.parseInt(taskIdText.getText());

        if(task.isEmpty()||taskIdText.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all the fields");
        }

        else{
            TaskModel taskModel = new TaskModel(task,taskId);

            TaskController taskController = new TaskController();
            int insert =taskController.addTask(taskModel);

            if (insert > 0) {
                JOptionPane.showMessageDialog(null, "Task Added Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to Add Task");
            }
        }
    }

    public static void main(String[] args) {
        new AddTaskPage().setVisible(true);
    }
}