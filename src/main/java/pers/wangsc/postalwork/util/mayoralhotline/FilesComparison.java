package pers.wangsc.postalwork.util.mayoralhotline;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilesComparison {
    private String respondedFileDirectory;
    private String receivedFileDirectory;
    private List<String> respondedFileCodeList;
    private List<String> receivedFileCodeList;

    private static String regexPattern = "\\d{14}(([(]\\d[)])|-\\d)?";
    private static Pattern pattern = Pattern.compile(regexPattern);

    public FilesComparison(String receivedFileDirectory, String respondedFileDirectory) {
        this.receivedFileDirectory = receivedFileDirectory;
        this.respondedFileDirectory = respondedFileDirectory;
        receivedFileCodeList = new ArrayList<>();
        respondedFileCodeList = new ArrayList<>();
        File[] receivedFiles = new File(receivedFileDirectory).listFiles();
        File[] respondedFiles = new File(respondedFileDirectory).listFiles();
        for (File file : receivedFiles) {
            String filename = file.getName();
            Matcher matcher = pattern.matcher(filename);
            if (matcher.find()) {
                receivedFileCodeList.add(matcher.group(0));
            }
        }
        for (File doneFile : respondedFiles) {
            Matcher matcher = pattern.matcher(doneFile.getName());
            if (matcher.find()) {
                respondedFileCodeList.add(matcher.group(0));
            }
        }
    }

    public List<String> receivedNotHas() {
        List<String> receivedNotHasList = new ArrayList<>();
        for (String doneCode : respondedFileCodeList) {
            if (!receivedFileCodeList.contains(doneCode)) {
                receivedNotHasList.add(doneCode);
            }
        }
        return receivedNotHasList;
    }

    public List<String> respondedNotHas() {
        List<String> respondedNotHasList = new ArrayList<>();
        for (String countedCode : receivedFileCodeList) {
            if (!respondedFileCodeList.contains(countedCode)) {
                respondedNotHasList.add(countedCode);
            }
        }
        return respondedNotHasList;
    }
}
