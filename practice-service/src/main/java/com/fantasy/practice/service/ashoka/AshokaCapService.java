package com.fantasy.practice.service.ashoka;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by jiaji on 2017/7/24.
 */
@Service
public class AshokaCapService {
    private static final Logger logger = LoggerFactory.getLogger(AshokaCapService.class);

    final String dir = "/Users/jiaji/Desktop/result/";
    String lineSeperator = "\r\n";

    public void doCap() throws Exception {
        InputStream myxls = new FileInputStream("/Users/jiaji/Desktop/source.xlsx");
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        XSSFWorkbook wb = new XSSFWorkbook(myxls);
        XSSFSheet sheet = wb.getSheetAt(1);
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(xssfRow.getCell(1).getRawValue())
                        .append("_")
                        .append(xssfRow.getCell(2))
                        .append("_")
                        .append(xssfRow.getCell(3))
                        .append("_")
                        .append(xssfRow.getCell(0));
                String fileName = sb.toString();
                File file = new File(dir + fileName + ".txt");
                if (file.exists() && file.length() > 0) {
                    continue;
                }
                FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
                String url = xssfRow.getCell(4).getStringCellValue();
                String urlPre = url.substring(0, url.lastIndexOf("/"));
                String path = URLEncoder.encode(URLDecoder.decode(url.substring(url.lastIndexOf("/") + 1, url.length()), "utf-8"), "utf-8");
                url = urlPre + "/" + path;
                logger.error("准备抓取rowNum:{}  url:{}", rowNum, url);
                Document doc;
                try {
                    doc = Jsoup.connect(url).userAgent("Mozilla").get();
                    if (doc == null) {
                        logger.error("rowNum:{} 有问题的url:{} doc为null", rowNum, url);
                        continue;
                    }
                    Elements lines = doc.select(".divcontent");
                    if (lines.size() == 0) {
                        logger.error("rowNum:{} 有问题的url:{} lines为空", rowNum, url);
                        continue;
                    }
                    for (int i = 0; i < lines.size() - 1; i++) {
                        fileWriter.write(lines.get(i).text());
                        fileWriter.write(lineSeperator);
                    }
                } catch (Exception e) {
                    logger.error("rowNum:{} 有问题的url:{} 发生了异常:{}", rowNum, url, e.getMessage());
                } finally {
                    fileWriter.flush();
                    fileWriter.close();
                }
            }
        }
        wb.close();
    }
}
