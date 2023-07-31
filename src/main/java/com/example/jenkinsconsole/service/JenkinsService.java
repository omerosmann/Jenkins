package com.example.jenkinsconsole.service;

import com.example.jenkinsconsole.exception.JenkinsErrorMessage;
import com.example.jenkinsconsole.dto.JenkinsJobInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class JenkinsService {

    private static final String JENKINS_URL = "http://localhost:8080";
    private static final String PROJECT_NAME = "deneme";
    private String lastBuildNumber = "";
    private Set<JenkinsErrorMessage> errorMessagesSet = new HashSet<>();

    private final RestTemplate restTemplate;

    @Autowired
    public JenkinsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLastBuildNumber() {
        String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/lastBuild/api/json";
        JenkinsJobInfo jobInfo = restTemplate.getForObject(apiUrl, JenkinsJobInfo.class);
        return jobInfo != null ? String.valueOf(jobInfo.getNumber()) : "";
    }

    @Scheduled(fixedDelay = 5000)
    public void checkForErrors() {
        String currentBuildNumber = getLastBuildNumber();

        if (!currentBuildNumber.isEmpty() && !currentBuildNumber.equals(lastBuildNumber)) {
            lastBuildNumber = currentBuildNumber;

            String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/" + lastBuildNumber + "/consoleText";
            String consoleOutput = restTemplate.getForObject(apiUrl, String.class);
            String[] errorMessages = parseErrorMessages(consoleOutput);

            if (errorMessages.length > 0) {
                System.out.println("Error Messages in Console Output for Project: " + PROJECT_NAME + " (Build Number: " + lastBuildNumber + ")");

                for (String errorMessage : errorMessages) {
                    JenkinsErrorMessage jenkinsErrorMessage = new JenkinsErrorMessage(errorMessage, isFromJenkins(errorMessage));
                    if (errorMessagesSet.add(jenkinsErrorMessage)) {
                        System.out.println(jenkinsErrorMessage.getMessage());
                    }
                }
            }
        }
    }

    private String[] parseErrorMessages(String text) {
        Pattern pattern = Pattern.compile("(?i)(Error|Exception|IOException):.*?(\\r?\\n|$)");
        Matcher matcher = pattern.matcher(text);

        Set<String> errorMessagesSet = new HashSet<>();
        while (matcher.find()) {
            errorMessagesSet.add(matcher.group());
        }

        return errorMessagesSet.toArray(new String[0]);
    }

    private boolean isFromJenkins(String errorMessage) {
        // Jenkins'den gelen error mesajları "CreateProcess error" veya "Sistem belirtilen dosyayı bulamıyor" içeriyorsa true döner.
        return errorMessage.contains("CreateProcess error") || errorMessage.contains("Sistem belirtilen dosyayı bulamıyor");
    }

    public Set<JenkinsErrorMessage> getErrorMessages() {
        return errorMessagesSet;
    }
}


//@Service
//public class JenkinsService {
//
//    private static final String JENKINS_URL = "http://localhost:8080";
//    private static final String PROJECT_NAME = "deneme";
//    private String lastBuildNumber = "";
//    private Set<JenkinsErrorMessage> errorMessagesSet = new HashSet<>();
//
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public JenkinsService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public String getLastBuildNumber() {
//        String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/lastBuild/api/json";
//        JenkinsJobInfo jobInfo = restTemplate.getForObject(apiUrl, JenkinsJobInfo.class);
//        return jobInfo != null ? String.valueOf(jobInfo.getNumber()) : "";
//    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void checkForErrors() {
//        String currentBuildNumber = getLastBuildNumber();
//
//        if (!currentBuildNumber.isEmpty() && !currentBuildNumber.equals(lastBuildNumber)) {
//            lastBuildNumber = currentBuildNumber;
//
//            String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/" + lastBuildNumber + "/consoleText";
//            String consoleOutput = restTemplate.getForObject(apiUrl, String.class);
//            String[] errorMessages = parseErrorMessages(consoleOutput);
//
//            if (errorMessages.length > 0) {
//                System.out.println("Error Messages in Console Output for Project: " + PROJECT_NAME + " (Build Number: " + lastBuildNumber + ")");
//
//                for (String errorMessage : errorMessages) {
//                    JenkinsErrorMessage jenkinsErrorMessage = new JenkinsErrorMessage(errorMessage, isFromJenkins(errorMessage));
//                    if (errorMessagesSet.add(jenkinsErrorMessage)) {
//                        System.out.println(jenkinsErrorMessage.getMessage());
//                    }
//                }
//            }
//        }
//    }
//
//    private String[] parseErrorMessages(String text) {
//        Pattern pattern = Pattern.compile("(?i)(Error|Exception|IOException):.*?(\\r?\\n|$)");
//        Matcher matcher = pattern.matcher(text);
//
//        Set<String> errorMessagesSet = new HashSet<>();
//        while (matcher.find()) {
//            errorMessagesSet.add(matcher.group());
//        }
//
//        return errorMessagesSet.toArray(new String[0]);
//    }
//
//    private boolean isFromJenkins(String errorMessage) {
//        // Jenkins'den gelen error mesajları "CreateProcess error" veya "Sistem belirtilen dosyayı bulamıyor" içeriyorsa true döner.
//        return errorMessage.contains("CreateProcess error") || errorMessage.contains("Sistem belirtilen dosyayı bulamıyor");
//    }
//}


//********************************************************************************************************************************************


//@Service
//public class JenkinsService {
//
//    private static final String JENKINS_URL = "http://localhost:8080";
//    private static final String PROJECT_NAME = "deneme";
//    private String lastBuildNumber = "";
//    private Set<String> errorMessagesSet = new HashSet<>();
//
//    private final RestTemplate restTemplate;
//
//    @Autowired
//    public JenkinsService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public String getLastBuildNumber() {
//        String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/lastBuild/api/json";
//        JenkinsJobInfo jobInfo = restTemplate.getForObject(apiUrl, JenkinsJobInfo.class);
//        return jobInfo != null ? String.valueOf(jobInfo.getNumber()) : "";
//    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void checkForErrors() {
//        String currentBuildNumber = getLastBuildNumber();
//
//        if (!currentBuildNumber.isEmpty() && !currentBuildNumber.equals(lastBuildNumber)) {
//            lastBuildNumber = currentBuildNumber;
//
//            String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/" + lastBuildNumber + "/consoleText";
//            String consoleOutput = restTemplate.getForObject(apiUrl, String.class);
//            String[] errorMessages = parseErrorMessages(consoleOutput);
//
//            // Hatalar mevcutsa ekrana yazdıralım
//            if (errorMessages.length > 0) {
//                System.out.println("Error Messages in Console Output for Project: " + PROJECT_NAME + " (Build Number: " + lastBuildNumber + ")");
//
//                for (String errorMessage : errorMessages) {
//                    System.out.println(errorMessage);
//                }
//            }
//        }
//    }
//
//    private String[] parseErrorMessages(String text) {
//        Pattern pattern = Pattern.compile("(?i)(Error|Exception|IOException|NullPointerException|RuntimeException):.*?(\\r?\\n|$)");
//        Matcher matcher = pattern.matcher(text);
//
//        Set<String> errorMessagesSet = new HashSet<>();
//        while (matcher.find()) {
//            errorMessagesSet.add(matcher.group());
//        }
//
//        return errorMessagesSet.toArray(new String[0]);
//    }
//}












