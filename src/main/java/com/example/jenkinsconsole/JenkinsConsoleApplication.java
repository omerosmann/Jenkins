package com.example.jenkinsconsole;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@EnableScheduling // Scheduled görevlerin etkinleştirilmesi
@SpringBootApplication
public class JenkinsConsoleApplication {
    public static void main(String[] args) {
        SpringApplication.run(JenkinsConsoleApplication.class, args);
    }

    // RestTemplate Bean'i oluşturuyoruz
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}


//******************************************************************************************************************


//    private static final String JENKINS_URL = "http://localhost:8080";
//    private static final String PROJECT_NAME = "deneme";
//    private static String lastBuildNumber = "";
//    private static Set<String> errorMessagesSet = new HashSet<>();
//
//    public static void main(String[] args) {
//        SpringApplication.run(JenkinsConsoleApplication.class, args);
//
//        while (true) {
//            String currentBuildNumber = getLastBuildNumber();
//            if (!currentBuildNumber.equals(lastBuildNumber)) {
//                checkForErrors(currentBuildNumber);
//                lastBuildNumber = currentBuildNumber;
//            }
//
//            try {
//                Thread.sleep(5000); // 5 saniye aralıklarla kontrol et
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private static String getLastBuildNumber() {
//        String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/lastBuild/api/json";
//        RestTemplate restTemplate = new RestTemplate();
//        JenkinsJobInfo jobInfo = restTemplate.getForObject(apiUrl, JenkinsJobInfo.class);
//        return jobInfo != null ? String.valueOf(jobInfo.getNumber()) : "";
//    }
//
//    private static void checkForErrors(String buildNumber) {
//        String apiUrl = JENKINS_URL + "/job/" + PROJECT_NAME + "/" + buildNumber + "/consoleText";
//        RestTemplate restTemplate = new RestTemplate();
//        String consoleOutput = restTemplate.getForObject(apiUrl, String.class);
//        String[] errorMessages = parseErrorMessages(consoleOutput);
//
//        // Eğer yeni bir yapı işlemi numarasındaysak, önceki hataları temizleyelim ve yeni hataları ekrana yazdıralım
//        if (!buildNumber.equals(lastBuildNumber)) {
//            errorMessagesSet.clear();
//            System.out.println("Error Messages in Console Output for Project: " + PROJECT_NAME + " (Build Number: " + buildNumber + ")");
//        }
//
//        // Hataları ekrana yazdır ve hataları saklamak için Set'e ekleyelim
//        for (String errorMessage : errorMessages) {
//            if (errorMessagesSet.add(errorMessage)) {
//                System.out.println(errorMessage);
//            }
//        }
//    }
//
//    private static String[] parseErrorMessages(String text) {
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
//
//class JenkinsJobInfo {
//    private int number;
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//}


//******************************************************************************************************************


//    public static void main(String[] args) {
//        SpringApplication.run(JenkinsConsoleApplication.class, args);
//
//        // Jenkins URL ve Proje Adı
//        String jenkinsUrl = "http://localhost:8080/"; // Jenkins sunucu URL'si
//        String projectName = "deneme"; // Jenkins'deki proje adı
//
//        // Jenkins API URL'leri
//        String lastBuildUrl = jenkinsUrl + "/job/" + projectName + "/lastBuild/api/json";
//
//        // Daha önceki yapı işlem numarasını saklamak için bir değişken tanımlayalım
//        String previousBuildNumber = "";
//
//        // Hataları saklamak için bir küme (Set) oluşturalım
//        Set<String> errorMessagesSet = new HashSet<>();
//
//        // Sonsuz döngüde proje yapılandırıldığında işlemi tekrarlayalım
//        while (true) {
//            // Jenkins API'den son yapı işleminin numarasını alalım
//            String lastBuildNumber = getLastBuildNumber(lastBuildUrl);
//
//            // Eğer son yapı işlemi numarası değiştiyse, konsol çıktısındaki hata mesajlarını alalım
//            if (!lastBuildNumber.equals(previousBuildNumber)) {
//                String consoleOutputApiUrl = jenkinsUrl + "/job/" + projectName + "/" + lastBuildNumber + "/consoleText";
//                String consoleOutput = getConsoleOutput(consoleOutputApiUrl);
//                String[] errorMessages = parseErrorMessages(consoleOutput);
//
//                // Hataları saklayan kümeye ekleyelim
//                for (String errorMessage : errorMessages) {
//                    errorMessagesSet.add(errorMessage);
//                }
//
//                // Hataları yazdıralım
//                if (!errorMessagesSet.isEmpty()) {
//                    System.out.println("Error Messages in Console Output for Project: " + projectName);
//                    for (String errorMessage : errorMessagesSet) {
//                        System.out.println(errorMessage);
//                    }
//                } else {
//                    System.out.println("No Error Messages in Console Output for Project: " + projectName);
//                }
//
//                // previousBuildNumber'ı güncelleyelim
//                previousBuildNumber = lastBuildNumber;
//
//                // Hataları saklayan küme (Set) ve hataların çıktısını temizleyelim
//                errorMessagesSet.clear();
//            }
//
//            // 1 saniye bekleme süresi ekleyelim
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Jenkins API'den son yapı işleminin numarasını almak için kullanılan metod
//    private static String getLastBuildNumber(String apiUrl) {
//        RestTemplate restTemplate = new RestTemplate();
//        JenkinsJobInfo jobInfo = restTemplate.getForObject(apiUrl, JenkinsJobInfo.class);
//        return jobInfo != null ? String.valueOf(jobInfo.getNumber()) : "";
//    }
//
//    // Jenkins API'den son yapı işleminin konsol çıktısını almak için kullanılan metod
//    private static String getConsoleOutput(String apiUrl) {
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject(apiUrl, String.class);
//    }
//
//    // Metin içerisindeki hata mesajlarını çıkaran güncellenmiş parse metodu
//    private static String[] parseErrorMessages(String text) {
//        // Hata mesajlarını tespit etmek için düzenli ifade (regex) kullanalım
//        Pattern pattern = Pattern.compile("(?i)(Error|Exception|IOException|NullPointerException|RuntimeException):.*?(\\r?\\n|$)");
//        Matcher matcher = pattern.matcher(text);
//
//        // Hata mesajlarını saklamak için bir küme (Set) oluşturalım
//        Set<String> errorMessagesSet = new HashSet<>();
//
//        // Hata mesajlarını parse edelim ve kümede saklayalım
//        while (matcher.find()) {
//            errorMessagesSet.add(matcher.group());
//        }
//
//        // Kümeden hata mesajlarını dizi olarak döndürelim
//        return errorMessagesSet.toArray(new String[0]);
//    }
//}
//
//// Jenkins API'den dönen JSON yanıtını karşılayacak sınıf
//class JenkinsJobInfo {
//    private int number;
//
//    public int getNumber() {
//        return number;
//    }
//
//    public void setNumber(int number) {
//        this.number = number;
//    }
//}


//******************************************************************************************************************

//    public static void main(String[] args) {
//        SpringApplication.run(JenkinsConsoleApplication.class, args);
//
//
//        // Jenkins URL ve Proje Adı
//        String jenkinsUrl = "http://localhost:8080/"; // Jenkins sunucu URL'si
//        String projectName = "rent-a-car"; // Jenkins'deki proje adı
//
//
//        // Jenkins API URL'leri
//        String consoleOutputApiUrl = jenkinsUrl + "/job/" + projectName + "/lastBuild/consoleText";
//
//        // Jenkins API çağrısı için RestTemplate kullanacağız
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Jenkins API'dan console output'u alıyoruz
//        String consoleOutput = restTemplate.getForObject(consoleOutputApiUrl, String.class);
//
//        // Console output içerisindeki "Error" ve "Exception" başlıklı mesajları bulma işlemi için parse metodu çağırıyoruz
//        String[] errorMessages = parseErrorMessages(consoleOutput);
//
//        // "Error" ve "Exception" başlıklı mesajları ekrana yazdıralım
//        System.out.println("Error and Exception Messages in Console Output for Project: " + projectName);
//        for (String errorMessage : errorMessages) {
//            System.out.println(errorMessage);
//        }
//    }
//
//    // Metin içerisindeki "Error" ve "Exception" başlıklı mesajları çıkaran parse metodu
//    private static String[] parseErrorMessages(String text) {
//        // Hata ve istisna mesajlarını tespit etmek için düzenli ifade (regex) kullanalım
//        Pattern pattern = Pattern.compile("(?i)(Error|Exception):.*?(\\r?\\n|$)");
//        Matcher matcher = pattern.matcher(text);
//
//        // "Error" ve "Exception" başlıklı mesajları saklamak için bir StringBuilder kullanalım
//        StringBuilder errorMessagesBuilder = new StringBuilder();
//
//        // "Error" ve "Exception" başlıklı mesajları parse edelim
//        while (matcher.find()) {
//            // "Error" veya "Exception" başlıklı mesajları StringBuilder'a ekleyelim
//            errorMessagesBuilder.append(matcher.group()).append("\n");
//        }
//
//        // StringBuilder'dan "Error" ve "Exception" başlıklı mesajları dizi olarak döndürelim
//        return errorMessagesBuilder.toString().split("\n");
//    }


//******************************************************************************************************************

//        String apiToken = "11ef50bb4dbade9431832773ae5bb8250b"; // Jenkins API tokeni
//
//        // Jenkins API URL'leri
//        String consoleOutputApiUrl = jenkinsUrl + "/job/" + projectName + "/lastBuild/consoleText";
//
//        // Jenkins API çağrısı için RestTemplate kullanacağız
//        RestTemplate restTemplate = new RestTemplate();
//
//        // Authorization başlığı için tokenı kodlayalım ve isteğe ekleyelim
//        String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString(("user:" + apiToken).getBytes());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", authHeaderValue);
//
//        // API isteğine Authorization başlığını ekleyelim
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        // Jenkins API'dan console output'u alıyoruz
//        ResponseEntity<String> response = restTemplate.exchange(consoleOutputApiUrl, HttpMethod.GET, entity, String.class);
//        String consoleOutput = response.getBody();
//
//        // Console output içerisindeki "Error" ve "Exception" başlıklı mesajları bulma işlemi için parse metodu çağırıyoruz
//        String[] errorMessages = parseErrorMessages(consoleOutput);
//
//        // "Error" ve "Exception" başlıklı mesajları ekrana yazdıralım
//        System.out.println("Error and Exception Messages in Console Output for Project: " + projectName);
//        for (String errorMessage : errorMessages) {
//            System.out.println(errorMessage);
//        }
//    }
//
//    // Metin içerisindeki "Error" ve "Exception" başlıklı mesajları çıkaran parse metodu
//    private static String[] parseErrorMessages(String text) {
//        // Hata ve istisna mesajlarını tespit etmek için düzenli ifade (regex) kullanalım
//        Pattern pattern = Pattern.compile("(?i)(Error|Exception):.*?(\\r?\\n|$)");
//        Matcher matcher = pattern.matcher(text);
//
//        // "Error" ve "Exception" başlıklı mesajları saklamak için bir StringBuilder kullanalım
//        StringBuilder errorMessagesBuilder = new StringBuilder();
//
//        // "Error" ve "Exception" başlıklı mesajları parse edelim
//        while (matcher.find()) {
//            // "Error" veya "Exception" başlıklı mesajları StringBuilder'a ekleyelim
//            errorMessagesBuilder.append(matcher.group()).append("\n");
//        }
//
//        // StringBuilder'dan "Error" ve "Exception" başlıklı mesajları dizi olarak döndürelim
//        return errorMessagesBuilder.toString().split("\n");
//    }










