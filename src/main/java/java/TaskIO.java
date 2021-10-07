package java;

import java.io.*;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(out);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm", Locale.ROOT);

        outputStream.writeInt(tasks.size());

        int number = 0;

        for (Task t : tasks) {
            outputStream.writeInt(number);
            outputStream.writeInt(t.getTitle().length());
            outputStream.writeUTF(t.getTitle());
            outputStream.writeInt(t.isActive() ? 1 : 0);
            //String interval = (t.getRepeatInterval().toString());
            outputStream.writeLong(t.isRepeated() ? t.getRepeatInterval().getDays() * 86400L : 0);
            if (t.isRepeated()) {
                String startTime = (t.getStartTime().format(formatter));
                outputStream.writeUTF(startTime);
                //outputStream.writeInt(t.getStartTime().getSecond());
                String endTime = (t.getEndTime().format(formatter));
                outputStream.writeUTF(endTime);
                //outputStream.writeInt(t.getEndTime().getSecond());
            } else {
                String time = (t.getTime().format(formatter));
                outputStream.writeUTF(time);
            }
            number++;
            outputStream.flush();
            outputStream.close();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm:ss", Locale.ROOT);

        int taskCounter = inputStream.readInt();
        Period interval = null;

        for (int i = 0; i < taskCounter; i++) {
            int theNumberOfTask = inputStream.readInt();
            int titleLength = inputStream.readInt();
            String title = inputStream.readUTF();

            int activity = inputStream.readInt();
            tasks.getTask(i).setActive(activity > 0);
            long repetitionInterval = inputStream.readLong();
            if (repetitionInterval > 0) {
                String tmpStart = inputStream.readUTF();
                LocalDateTime startTime = LocalDateTime.parse(tmpStart, formatter);
                String tmpEnd = inputStream.readUTF();
                LocalDateTime endTime = LocalDateTime.parse(tmpEnd, formatter);
                interval = Period.ofDays((int) repetitionInterval / 86400);
                Task tmpTask = new Task(title, startTime, endTime, interval);
                tasks.add(tmpTask);
            } else {
                String tmpTime = inputStream.readUTF();
                LocalDateTime Time = LocalDateTime.parse(tmpTime, formatter);
                Task tmpTask = new Task(title, Time);
                tasks.add(tmpTask);
            }
        }
        inputStream.close();
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        write(tasks, fos);
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        read(tasks, fis);

    }

    /*
     *IO method writes a task from list to a stream at JSON format
     */
    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(out);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm", Locale.ROOT);

        bufferedWriter.write(tasks.size() + "\n");
        int taskNumber = 0;
        for (Task t : tasks) {
            bufferedWriter.write("\n" + taskNumber + "\n");
            bufferedWriter.write(+t.getTitle().length() + "\n");
            bufferedWriter.write(t.getTitle() + "\n");
            bufferedWriter.write(t.isActive() ? "1\n" : "0\n");
            bufferedWriter.write(t.isRepeated() ? t.getRepeatInterval().getDays() * 86400 + "\n" : null + "\n");
            if (t.getRepeatInterval() != null) {
                String start = t.getStartTime().format(formatter);
                bufferedWriter.write(start + "\n");
                String end = t.getEndTime().format(formatter);
                bufferedWriter.write(end + "\n");
            } else {
                String time = t.getTime().format(formatter);
                bufferedWriter.write(time);
            }
            taskNumber++;
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(in);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm", Locale.ROOT);

        int taskCounter = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < taskCounter; i++) {
            String taskNumber = bufferedReader.readLine();
            String titleLength = bufferedReader.readLine();
            String title = bufferedReader.readLine();
            String isActive = bufferedReader.readLine();
            String intervalReader = bufferedReader.readLine();
            //set activity in boolean
            boolean activity = isActive.equals("1");
            String strInterval = bufferedReader.readLine();
            //set interval in Period
            long longInterval=strInterval.equals("null")?0:Long.parseLong(strInterval);
            Period interval=longInterval>0?Period.ofDays((int) (longInterval / 86400)):null;
            if(interval != null){
                String tmpStart = bufferedReader.readLine();
                LocalDateTime start = LocalDateTime.parse(tmpStart,formatter);
                String tmpEnd = bufferedReader.readLine();
                LocalDateTime end = LocalDateTime.parse(tmpEnd, formatter);
                Task tmpTask = new Task(title, start,end,interval);
                tasks.add(tmpTask);
            }
            else{
                String tmpTime= bufferedReader.readLine();
                LocalDateTime time = LocalDateTime.parse(tmpTime,formatter);
                Task tmpTask = new Task(title, time);
                tasks.add(tmpTask);
            }
        }
        bufferedReader.close();
    }

    //Method writes tasks to a file
    public static void writeText(AbstractTaskList tasks, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        write(tasks, fileWriter);
    }

    //Method reads tasks from a file
    public static void readText(AbstractTaskList tasks, File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        read(tasks, fileReader);
    }

}


