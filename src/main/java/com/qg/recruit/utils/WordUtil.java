package com.qg.recruit.utils;

import com.plutext.merge.BlockRange;
import com.plutext.merge.BlockRange.HfBehaviour;
import com.plutext.merge.BlockRange.NumberingHandler;
import com.plutext.merge.BlockRange.SectionBreakBefore;
import com.plutext.merge.BlockRange.StyleHandler;
import com.plutext.merge.DocumentBuilder;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 郑俊铭
 * Date: 2017/12/4
 * Time: 19:51
 * No struggle, talent how to match the willfulness.
 * Description: Word 导出工具类
 */
public class WordUtil {


	private static String path = Objects.requireNonNull(ClassUtils.getDefaultClassLoader().getResource("")).getPath();

	/**
	 * 导出Word
	 *
	 * @param studentMap 学生信息
	 * @param exportFilePath 导出文件的位置
	 * @throws IOException IOException
	 */
	public static void exportWord(Map<String, String> studentMap, String exportFilePath) throws IOException {
		// 模板文件
		String templatePath = path + "/templates/2018QG训练营报名表.docx";

		XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(templatePath));

		Iterator iterator = document.getTablesIterator();
		// 插入数据
		while (iterator.hasNext()) {
			XWPFTable table = (XWPFTable) iterator.next();
			int count = table.getNumberOfRows();
			for (int i = 0; i < count; i++) {
				XWPFTableRow row = table.getRow(i);
				List<XWPFTableCell> cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					for (Map.Entry<String, String> entry : studentMap.entrySet()) {
						if (cell.getText().equals(entry.getKey())) {
							cell.removeParagraph(0);
							cell.setText(entry.getValue());
						}
					}
				}
			}
		}
		// 保存文件
		FileOutputStream outputStream = new FileOutputStream(exportFilePath);
		document.write(outputStream);
		outputStream.close();
	}

	/**
	 * 将多个Word文档合并为一个Word文档
	 *
	 * @param fileList 文件名List，包含要合并的文件名
	 * @param mergeFilePath 合并之后的文件名
	 * @throws Docx4JException Docx4JException
	 */
	public static void mergeDoc(List<String> fileList, String mergeFilePath) throws Docx4JException {
		List<BlockRange> blockRanges = new ArrayList<>();
		for (String file : fileList) {
			BlockRange block = new BlockRange(WordprocessingMLPackage.load(new File(file)));
			blockRanges.add(block);
			block.setStyleHandler(StyleHandler.RENAME_RETAIN);
			block.setNumberingHandler(NumberingHandler.ADD_NEW_LIST);
			block.setRestartPageNumbering(false);
			block.setHeaderBehaviour(HfBehaviour.DEFAULT);
			block.setFooterBehaviour(HfBehaviour.DEFAULT);
			block.setSectionBreakBefore(SectionBreakBefore.NEXT_PAGE);
		}

		// Perform the actual merge
		DocumentBuilder documentBuilder = new DocumentBuilder();
		WordprocessingMLPackage output = documentBuilder.buildOpenDocument(blockRanges);

		// Save the result
		Docx4J.save(output, new File(mergeFilePath), Docx4J.FLAG_SAVE_ZIP_FILE);
	}

}
