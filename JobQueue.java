import java.util.LinkedList;
import java.util.Queue;

public class JobQueue {

    private static Queue<Job> queue = new LinkedList<>();

    public static void addJob(Job job) {
        queue.add(job);
    }

    public static Job removeJob() {
        return queue.poll();
    }

    public static Job peekJob() {
        return queue.peek();
    }

    public static boolean isEmpty() {
        return queue.isEmpty();
    }

    public static Queue<Job> getAllJobs() {
        return new LinkedList<>(queue);
    }

    public static Job findJobById(int id) {
        for (Job job : queue) {
            if (job.getJobId() == id) {
                return job;
            }
        }
        return null;
    }

    public static boolean removeById(int id) {
        Job target = null;

        for (Job job : queue) {
            if (job.getJobId() == id) {
                target = job;
                break;
            }
        }

        if (target != null) {
            queue.remove(target);
            return true;
        }

        return false;
    }
}