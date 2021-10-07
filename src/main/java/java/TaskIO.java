package java;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

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

        for (Task t : tasks) {
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
            outputStream.flush();
            outputStream.close();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException {
        DataInputStream inputStream = new DataInputStream(in);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm:ss", Locale.ROOT);

        int taskCounter = inputStream.readInt();
        Period interval;

        for (int i = 0; i < taskCounter; i++) {
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
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm", Locale.ROOT);
        JsonWriter writer = new JsonWriter(out);

        writer.beginObject();
        writer.beginArray();
        writer.name("The number of tasks: ").value(tasks.size());
        for (Task t : tasks) {
            writer.beginObject();
            writer.name("The length of name: ").value(t.getTitle().length());
            writer.name("Name: ").value(t.getTitle());
            writer.name("Activity: ").value(t.isActive() ? 1 : 0);
            writer.name("Repetition interval: ").value(t.isRepeated() ? t.getRepeatInterval().getDays() * 86400L : 0);
            if (t.isRepeated()) {
                String startTime = (t.getStartTime().format(formatter));
                writer.name("Start time: ").value(startTime);
                String endTime = (t.getEndTime().format(formatter));
                writer.name("End time: ").value(endTime);
            } else {
                String time = (t.getTime().format(formatter));
                writer.name("Execution time: ").value(time);
            }
            writer.endObject();
        }
        writer.endArray();
        writer.endObject();



    }

    public static void read(AbstractTaskList tasks, Reader in) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy.MM.dd HH:mm:ss", Locale.ROOT);
        JsonReader reader = new JsonReader(in);
        int numberOfTasks = 0;
        int titleLength = 0;
        String title = null;
        Long intervalLong;
        Period interval = null;
        int activity = 0;
        String startStr;
        LocalDateTime start = null;
        String endStr;
        LocalDateTime end = null;
        String timeStr;
        LocalDateTime time = null;
        numberOfTasks = reader.nextInt();
        reader.beginObject();
        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            String name = reader.nextName();
            if (name.equals("The number of tasks: ")) numberOfTasks = reader.nextInt();
            if (name.equals("The length of name: ")) titleLength = reader.nextInt();
            if (name.equals("Name: ")) title = reader.nextString();
            if (name.equals("Activity: ")) activity = reader.nextInt();
            if (name.equals("Repetition interval: ")) {
                intervalLong = reader.nextLong();
                interval=Period.ofDays((int) (intervalLong / 86400));
            }
            if(name.equals("Start time: ")) {
                startStr=reader.nextString();
                start=LocalDateTime.parse(startStr, formatter);
            }
            if(name.equals("End time: ")){
                endStr=reader.nextString();
                end=LocalDateTime.parse(endStr, formatter);
            }
            if(name.equals("Execution time: ")){
                timeStr=reader.nextString();
                time=LocalDateTime.parse(timeStr, formatter);
            }
            if(interval!=null){
                Task tmpTask = new Task(title,start,end,interval);
                tmpTask.setActive(activity==1);
                tasks.add(tmpTask);
            }
            else{
                Task tmpTask = new Task(title, time);
                tmpTask.setActive(activity==1);
                tasks.add(tmpTask);
            }
            reader.endObject();
        }
        reader.endArray();
        reader.endObject();
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


