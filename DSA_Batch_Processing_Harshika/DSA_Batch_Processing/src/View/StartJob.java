package View;
import JobScheduling.SequencingJob;
import controller.TaskController;
import model.JobModel;
import model.JobTaskModel;
import model.TaskModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartJob  extends JFrame {
    int count = 0;
    TaskController taskController = new TaskController();
    JButton backButton;
    JLabel jobsLabel;
    JLabel selectJobLabel;
    JButton startJobButton;
    JScrollPane jtf;
    String text="";
    JButton jobCompletedButton;
    ScrollableLabel t;
    public StartJob(){
        setTitle("Start Job");
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
        JLabel label = new JLabel("Start Job From Here");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 45));
        label.setBounds(400, 100, 800, 40);
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

        selectJobLabel = new JLabel("Select Job");
        selectJobLabel.setFont(new Font("San Serif", Font.PLAIN, 20));
        selectJobLabel.setBounds(350, 200, 200, 20);
        selectJobLabel.setForeground(Color.black);
        add(selectJobLabel);

        jobsLabel = new JLabel("Job Here");
        jobsLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        jobsLabel.setBounds(520,200,200,30);
        add(jobsLabel);
      t=new ScrollableLabel(text);
      t.setFont(new Font("San Serif", Font.PLAIN, 15));
       jtf=new JScrollPane(t);
        jtf.setBounds(400,300,400,55);
        jtf.setVisible(true);
        add(jtf);

        startJobButton = new JButton("Start Job");
        startJobButton.setBounds(450,400,300,45);
        startJobButton.setForeground(Color.BLACK);
        startJobButton.setBackground(Color.orange);
        startJobButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        add(startJobButton);

        jobCompletedButton = new JButton("Job Completed");
        jobCompletedButton.setBounds(450,500,300,45);
        jobCompletedButton.setForeground(Color.BLACK);
        jobCompletedButton.setBackground(Color.orange);
        jobCompletedButton.setFont(new Font("Arial", Font.BOLD, 20));//font
        add(jobCompletedButton);

       startJobButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JOptionPane.showMessageDialog(null, "job started");
               try {
                   jobSchedule();
               } catch (InterruptedException ex) {
                   ex.printStackTrace();
               }
           }
       });
    }

    public void topoSort(int jobId){
        JobModel jobModel = taskController.fetchJobBYId(jobId);
        int vertices = jobModel.getNumOfTask();
        System.out.println("i am vertix: "+vertices);
        Graph graph = new Graph(vertices);

        ArrayList<JobTaskModel> jtm = taskController.fetchTaskModels(jobId);
        System.out.println("I am arrat list of task");
        for(int i=0; i<jtm.size(); i++){
            graph.addEdge(jtm.get(i).getSource(),jtm.get(i).getDestination());
        }
        int[] tasks=graph.topologicalSort();

        for (int i=0; i<tasks.length; i++){
            TaskModel tm =new TaskModel();
            tm=taskController.getTaskById(tasks[i]);
            System.out.println(tm.getTask());
             text+= tm.getTask()+"->";
             t.setText(text);
        }
    }

    public void jobSchedule() throws InterruptedException {

        ArrayList<JobModel> jobModels = taskController.fetchJob();
        SequencingJob sequencingJob = new SequencingJob();

        ArrayList<JobScheduling.JobModel> jminsc = new ArrayList<>();

        //creating job model from the job model retrieved from database
        for(int i=0; i<jobModels.size(); i++){
            JobScheduling.JobModel jobm = new JobScheduling.JobModel(jobModels.get(i).getJobId(),jobModels.get(i).getProfit(),jobModels.get(i).getTime());
            jminsc.add(jobm);
        }

        List<Integer> scheduledJobs = new ArrayList<Integer>();
        scheduledJobs=sequencingJob.scheduling(jminsc);
        int[] allTask =  new int[scheduledJobs.size()];
        int[] allTime = new int[scheduledJobs.size()];

        for(int i=0; i<scheduledJobs.size()-1; i++){
            JobModel JM = taskController.fetchJobBYId(scheduledJobs.get(i));
             allTime[i]=JM.getTime();
             allTask[i]=JM.getJobId();

        }
        //created list of executables tasks
        System.out.println(Arrays.toString(allTask));
        Runnable[] TASKS = new Runnable[allTask.length];
        for(int tim = 0; tim<allTime.length;tim++) {

            int finalTim = tim;
            TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    text="";
                    t.setText(text);

                    System.out.println("run task");
                    JobModel job = taskController.fetchJobBYId(allTask[finalTim]);

                    String jobName = job.getJobName();
                    System.out.println(jobName);
                    jobsLabel.setText(jobName);
                    topoSort(allTask[finalTim]);
                }
            };
            TASKS[tim]=task;
        }
        for(int i=0; i<allTime.length; i++){
        }
        //list of date object of when task will execute
        ArrayList<Calendar> TIMES = new ArrayList<>();
       for(int timeTo=0; timeTo<allTime.length;timeTo++){
           Calendar date = Calendar.getInstance();
           date.set(Calendar.HOUR_OF_DAY,10);
           date.set(Calendar.MINUTE,allTime[timeTo]);
           date.set(Calendar.SECOND,0);
           TIMES.add(date);
           System.out.println(date.getTime());
       }
       //executing tasks in the given time
        Timer timer = new Timer();
        for(int i=0; i<TASKS.length; i++){
            final int index=i;
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    TASKS[index].run();;
                }
            };
            timer.schedule(task,TIMES.get(i).getTime());
        }
    }
    public static void main(String[] args) {
        new StartJob().setVisible(true);
    }
}
