package pers.wangsc.postalwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.wangsc.edocument.util.StringUtil;
import pers.wangsc.edocument.word.Word;
import pers.wangsc.postalwork.dao.MayoralHotlineDao;
import pers.wangsc.postalwork.entity.*;

import java.io.File;
import java.util.*;

@Service
public class MayoralHotlineService {
    @Autowired
    private MayoralHotlineDao mayoralHotlineDao;
    @Autowired
    private MayoralHotlineLabeledService mayoralHotlineLabeledService;
    @Autowired
    private ExpressBrandService expressBrandService;

    public List<MayoralHotline> findAll() {
        return mayoralHotlineDao.findAll();
    }

    public List<String> getFileNameDidNotAddInDB(String filesDirectory) {
        List<String> fileNamesInDB = mayoralHotlineDao.findAllFileName();
        List<String> result = new ArrayList();
        for (File file : new File(filesDirectory).listFiles()) {
            String fileName = file.getName();
            Boolean isValidWordFile = !fileName.startsWith("~$") && (fileName.endsWith(".doc") || fileName.endsWith(".docx"));
            if (isValidWordFile) {
                if (fileNamesInDB.contains(fileName)) {
                    fileNamesInDB.remove(fileName);
                } else {
                    result.add(file.getAbsolutePath());
                }
            }
        }
        return result;
    }

    public int saveFromFiles(String filesDirectory, String tableLocation) {
        var fileNameDidNotAddInDB = getFileNameDidNotAddInDB(filesDirectory);
        int[] tableLocations = StringUtil.delimiterStringToNoOffsetIntArray(tableLocation);
        int addedFilesCounter = 0;
        for (String filePath : fileNameDidNotAddInDB) {
            Word word = new Word(filePath);
            if (!word.getValid()) {
                continue;
            }
            var data = word.getTableContent(0, tableLocations);
            data.add(new File(filePath).getName());
            MayoralHotline hotline = new MayoralHotline(data);
            hotline.setCreateDateTime(new Date());
            mayoralHotlineDao.save(hotline);
//            var labeled=mayoralHotlineLabeledService.setByMayoralHotline(hotline);
//            mayoralHotlineLabeledService.save(labeled);
            addedFilesCounter++;
        }
        return addedFilesCounter;
    }

    public Page<MayoralHotline> findAllOrderByDeadlineDateTimeDesc(int pageNum, int pageSize) {
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 100;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return mayoralHotlineDao.findAllByOrderByDeadlineDateTimeDesc(pageable);
    }

    public Page<MayoralHotline> findIdByOrderByDeadlineDateTimeDesc(int pageNum, int pageSize) {
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 100;
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return mayoralHotlineDao.findByOrderByDeadlineDateTimeDesc(pageable);
    }
}
