package View;

import controller.TaskController;
import model.JobModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateJob extends JFrame {
    JLabel jobLabel;
    JTextField jobText;
    JLabel jobIdLabel;
    JTextField jobIdText;
    JLabel endTimeLabel;
    JTextField endTimeText;
    JLabel profitLabel;
    JTextField profitText;
    JLabel taskNumberLabel;
    JTextField taskNumberText;
    JButton createJobButton;
    JButton addTaskButton;
    JButton backButton;
    public CreateJob() {
        initialize();
        uIInitialize();
    }
    public void initialize () {
        setTitle("Create Job from Here");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720 );
        setResizable(false);
        setLayout(null);
        Color c = new Color(78, 151, 225);
        getContentPane().setBackground(c);
    }
    public void uIInitialize(){
        JLabel label = new JLabel("Create your Job Here");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 45));
        label.setBounds(450, 100, 500, 40);
        add(label);

        backButton = new JButton("Back");
        backButton.setBounds(0, 10, 100, 20);
        backButton.setFont(new Font("Roboto", Font.BOLD, 15));
        backButton.setFocusPainted(false);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddTaskPage addTaskPage = new AddTaskPage();
                addTaskPage.show();
                dispose();
            }
        });
        jobIdLabel = new JLabel("Job Id");
        jobIdLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        jobIdLabel.setBounds(450, 160, 400, 40);
        jobIdLabel.setForeground(Color.black);
        add(jobIdLabel);

        jobIdText = new JTextField();
        jobIdText.setBounds(600, 160, 400, 40);
        add(jobIdText);

        endTimeLabel = new JLabel("End Time");
        endTimeLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        endTimeLabel.setBounds(450, 220, 400, 40);
        endTimeLabel.setForeground(Color.black);
        add(endTimeLabel);

        endTimeText = new JTextField();
        endTimeText.setBounds(600, 220, 400, 40);
        add(endTimeText);

        profitLabel=new JLabel("Profit");
        profitLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        profitLabel.setBounds(450, 280, 400, 40);
        profitLabel.setForeground(Color.black);
        add(profitLabel);

        profitText = new JTextField();
        profitText.setBounds(600, 280, 400, 40);
        add(profitText);

        taskNumberLabel = new JLabel("Num of task");
        taskNumberLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        taskNumberLabel.setBounds(450, 340, 400, 40);
        taskNumberLabel.setForeground(Color.black);
        add(taskNumberLabel);

        taskNumberText = new JTextField();
        taskNumberText.setBounds(600, 340, 400, 40);
        add(taskNumberText);

        jobLabel = new JLabel("Job name");
        jobLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        jobLabel.setBounds(450, 400, 400, 40);
        jobLabel.setForeground(Color.black);
        add(jobLabel);

        jobText = new JTextField();
        jobText.setBounds(600, 400, 400, 40);
        add(jobText);

        createJobButton = new JButton("CREATE JOB");
        createJobButton.setBounds(450, 500, 400, 40);
        createJobButton.setForeground(Color.BLACK);
        createJobButton.setBackground(Color.orange);
        createJobButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        add(createJobButton);

        addTaskButton = new JButton("ADD TASK");
        addTaskButton.setBounds(450, 550, 400, 40);
        addTaskButton.setForeground(Color.BLACK);
        addTaskButton.setBackground(Color.orange);
        addTaskButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        add(addTaskButton);

        createJobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addJob();
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddJobTaskCompare addJobTask = new AddJobTaskCompare();
                addJobTask.show();
                dispose();
            }
        });
    }
    private void addJob(){
        int id=Integer.parseInt(jobIdText.getText());
        String jobName = jobText.getText();
        int numOfTask = Integer.parseInt(taskNumberText.getText());
        int dead=Integer.parseInt(endTimeText.getText());
        int profit = Integer.parseInt(profitText.getText());
        if(id==0||jobName.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all field","Try again",JOptionPane.ERROR_MESSAGE);
        }else{
            JobModel jobModel = new JobModel(id, jobName,numOfTask,dead,profit);
            TaskController taskController = new TaskController();
            int insert = taskController.addJob(jobModel);
            if(insert>0){
                JOptionPane.showMessageDialog(null, "Job Created Successfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Failed to create job");
            }
        }

    }
    public static void main(String[] args) {
        new CreateJob().setVisible(true);
    }
}