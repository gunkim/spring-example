package dev.gunlog.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarService {
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class.getName());
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);

    private final String applicationName;
    private final String tokensDirectoryPath;
    private final String credentialsFilePath;

    public CalendarService(
            @Value("${calendar.application-name}") String applicationName,
            @Value("${calendar.token-directory-path}") String tokensDirectoryPath,
            @Value("${calendar.credentials-file-path}") String credentialsFilePath) {
        this.applicationName = applicationName;
        this.tokensDirectoryPath = tokensDirectoryPath;
        this.credentialsFilePath = credentialsFilePath;
    }

    public List<Event> getNowSchedules() throws GeneralSecurityException, IOException, IllegalArgumentException {
        Events events;
        try {
            DateTime now = new DateTime(System.currentTimeMillis());
            events = getCalendar().events().list("primary")
                    .setMaxResults(10)
                    .setTimeMin(now)
                    .setOrderBy("startTime")
                    .setSingleEvents(true)
                    .execute();
        } catch(GoogleJsonResponseException e) {
            logger.info("Google Response Error", e);
            return List.of();
        }
        return events.getItems();
    }
    public Calendar getCalendar() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar.Builder(httpTransport, this.JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(this.applicationName)
                .build();
    }
    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = Optional.ofNullable(CalendarService.class.getResourceAsStream(this.credentialsFilePath))
                .orElseThrow(() -> new FileNotFoundException("Resource not found: " + this.credentialsFilePath));

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(this.JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, this.JSON_FACTORY, clientSecrets, this.SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(this.tokensDirectoryPath)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
}