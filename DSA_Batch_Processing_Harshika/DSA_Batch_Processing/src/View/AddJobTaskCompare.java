package View;

import controller.TaskController;
import model.JobTaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class AddJobTaskCompare extends JFrame{
  JButton backButton;
  JLabel jobIdLabel;
  JTextField jobIdText;
  JComboBox taskCombo;
  JComboBox dependentCombo;
  JButton addTaskButton;
  JLabel currentTaskLabel;
  JLabel dependentTaskLabel;

    public AddJobTaskCompare(){
        setTitle("Select Task For Job");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720 );
        setResizable(false);
        setLayout(null);
        Color c = new Color(78, 151, 225);
        getContentPane().setBackground(c);
        initilize();
    }

    void initilize(){
        JLabel label = new JLabel("Select Dependent Task From Here");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 45));
        label.setBounds(300, 100, 800, 40);
        add(label);

        backButton = new JButton("Back");
        backButton.setBounds(0, 10, 70, 20);
        backButton.setFont(new Font("Roboto", Font.BOLD, 10));
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

        TaskController taskController = new TaskController();
        ArrayList<Integer> idtaskList = taskController.fetchTask();
        String[] idOfTaskList=new String[idtaskList.size()+1];
        System.out.println(idtaskList);
        for(int i=0; i<idtaskList.size(); i++){
               System.out.println(idtaskList.get(i));
               idOfTaskList[i]=idtaskList.get(i).toString();
        }
        System.out.println(Arrays.toString(idOfTaskList));

        jobIdLabel = new JLabel("Job Id");
        jobIdLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        jobIdLabel.setBounds(350, 160, 400, 40);
        jobIdLabel.setForeground(Color.black);
        add(jobIdLabel);

        jobIdText = new JTextField();
        jobIdText.setBounds(600, 160, 400, 40);
        add(jobIdText);

        currentTaskLabel = new JLabel("Current Task");
        currentTaskLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        currentTaskLabel.setBounds(350, 220, 400, 40);
        currentTaskLabel.setForeground(Color.black);
        add(currentTaskLabel);

        taskCombo = new JComboBox(idOfTaskList);
        taskCombo.setFont(new Font("Roboto", Font.PLAIN, 25));
        taskCombo.setBounds(600, 220, 400, 40);
        add(taskCombo);

        dependentTaskLabel = new JLabel("Dependent Task");
        dependentTaskLabel.setFont(new Font("San Serif", Font.PLAIN, 25));
        dependentTaskLabel.setBounds(350, 280, 400, 40);
        dependentTaskLabel.setForeground(Color.black);
        add(dependentTaskLabel);

        dependentCombo = new JComboBox(idOfTaskList);
        dependentCombo.setFont(new Font("Roboto", Font.PLAIN, 25));
        dependentCombo.setBounds(600, 280, 400, 40);
        add(dependentCombo);

        addTaskButton = new JButton("Add Task");
        addTaskButton.setBounds(450,400,300,45);
        addTaskButton.setForeground(Color.BLACK);
        addTaskButton.setBackground(Color.orange);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        add(addTaskButton);

        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jobId;
                int source;
                int destination;

                if(jobIdText.getText().isEmpty()||String.valueOf(taskCombo.getSelectedItem()).isEmpty()){
                    JOptionPane.showMessageDialog(null, "blank fields", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    jobId=Integer.parseInt(jobIdText.getText());
                    source=Integer.parseInt(String.valueOf(dependentCombo.getSelectedItem()));
                    destination=Integer.parseInt(String.valueOf(taskCombo.getSelectedItem()));
                    JobTaskModel jobTaskModel = new JobTaskModel(jobId,source,destination);
                    TaskController taskController1 = new TaskController();
                    int insert = taskController.addJobTask(jobTaskModel);

                    if(insert>0){
                        JOptionPane.showMessageDialog(null, "Task added Successfully");
                    }else{
                        JOptionPane.showMessageDialog(null, "Failed to add Task");
                    }
                }
            }
        });
    }
    public static void main(String[] args) {
        new AddJobTaskCompare().setVisible(true);
    }

}
