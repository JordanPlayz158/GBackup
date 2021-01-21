package me.JordanPlayz158.GBackup.commands;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import me.JordanPlayz158.GBackup.Main;
import me.JordanPlayz158.GBackup.Utilities;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleUpload implements Runnable {
    @Override
    public void run() {
        try {
            main(GUpload.name);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private static final String APPLICATION_NAME = "GBackup (General Backup)";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final File TOKENS_DIRECTORY_PATH = new File(Main.plugin.getDataFolder(), "tokens");

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_FILE);
    private static final File CREDENTIALS_FILE_PATH = new File(Main.plugin.getDataFolder(), "credentials.json");

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(TOKENS_DIRECTORY_PATH))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public static void main(String name) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        InputStreamContent inputStreamContent = new InputStreamContent("", new BufferedInputStream(new FileInputStream(Utilities.config("backupFolder") + "/" + name)));

        com.google.api.services.drive.model.File body = new com.google.api.services.drive.model.File();
        body.setName(name);
        try {
             service.files().create(body, inputStreamContent).execute();
            System.out.println(Utilities.pluginPrefix() + " Backup Upload Complete");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
