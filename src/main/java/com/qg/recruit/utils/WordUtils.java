package com.qg.recruit.utils;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author 郑俊铭
 * Date: 2017/12/3
 * Time: 15:31
 * No struggle, talent how to match the willfulness.
 * Description:
 */
public class WordUtils {
    private static Configuration configuration;

    static
    {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(WordUtils.class, "/");
    }

    private WordUtils() {
        throw new AssertionError();
    }

    public static void exportWord(Map dataMap, String exportFilePath, String exportFileName, String ftlFile) throws IOException {
        // 获得模板
        Template freemarkerTemplate = configuration.getTemplate(ftlFile, "UTF-8");

        // 输出文件
        File outFile = new File(exportFilePath + File.separator + exportFileName);

        if (!outFile.getParentFile().exists()) {
            outFile.getParentFile().mkdirs();
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));

        try {
            freemarkerTemplate.process(dataMap, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        writer.flush();
        writer.close();
    }
}
