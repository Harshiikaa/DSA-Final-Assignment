package JobScheduling;
import java.util.Comparator;

public class Compare implements Comparator<JobModel> {
    @Override
    public int compare(JobModel o1, JobModel o2) {
        // comparing the profit between the  first and second objects and swapping
        if(o1.getProfit()<o2.getProfit()){
            return 1;
        }
        else if(o1.getProfit()>o2.getProfit()){
            return -1;
        }
        return 0;
    }
}
