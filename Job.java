import java.time.LocalDateTime;

public class Job {

    private static int idCounter = 1;

    private int jobId;
    private String jobName;
    private int numberOfCopies;
    private String fileType;
    private LocalDateTime timestamp;
    private boolean isCompleted;

    public Job(String jobName, int numberOfCopies, String fileType) {
        this.jobId = idCounter++;
        this.jobName = jobName;
        this.numberOfCopies = numberOfCopies;
        this.fileType = fileType;
        this.timestamp = LocalDateTime.now();
        this.isCompleted = false;
    }

    public int getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public String getFileType() {
        return fileType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Job ID: " + jobId +
               "\nName: " + jobName +
               "\nCopies: " + numberOfCopies +
               "\nFile Type: " + fileType +
               "\nTime: " + timestamp +
               "\nStatus: " + (isCompleted ? "Completed" : "Printing...");
    }
}