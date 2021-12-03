package dev.gunlog;

import com.google.api.services.calendar.model.Event;
import dev.gunlog.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalendarServiceTests {
    @Autowired
    private CalendarService calendarService;
    @Test
    void test() throws GeneralSecurityException, IOException {
        List<Event> list = calendarService.test();

        for (Event event : list) {
            System.out.println(event);
        }
    }
}