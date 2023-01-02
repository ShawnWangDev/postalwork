import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaTest {
    @Test
    public void filePathGetFileName() {
        List<String> filePathList = new ArrayList<>();
        filePathList.add("C:\\a\\b\\c.txt");
        filePathList.add("D:\\aa\\bb\\cc.txt");
        filePathList.add("D:\\abc.txt");
        filePathList.add("C:\\Windows\\assembly\\PublisherPolicy.tme");
        for (String s : filePathList) {
            System.out.println(s.substring(s.lastIndexOf(File.separatorChar) + 1));
        }
    }
}
