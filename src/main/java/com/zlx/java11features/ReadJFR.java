package com.zlx.java11features;

import jdk.jfr.consumer.RecordedEvent;
import jdk.jfr.consumer.RecordingFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 读取.jfr文件信息
 */
public class ReadJFR {
    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("C:\\workspace\\java\\myproject\\myjavase\\src\\main\\java\\com\\zlx\\java11features\\aa.jfr");
//        final Path path = Paths.get("C:\\Users\\420\\Downloads\\Documents/ges.jfr");
        final List<RecordedEvent> recordedEvents = RecordingFile.readAllEvents(path);
        for (RecordedEvent event : recordedEvents) {
//            System.out.println(event.getStartTime() + ","  );
            System.out.println(event.toString());
        }
    }
}
