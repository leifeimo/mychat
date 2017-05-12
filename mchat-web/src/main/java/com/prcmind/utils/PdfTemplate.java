package com.prcmind.utils;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;


public class PdfTemplate {	
	public static void main(String[] args) {
		try {
			int count = 8;// 总记录数
			int pageCount = 4;// 每页记录数
			int index = 1; // 表格序号
			int page = 0;// 总共页数
			/** 主要控制总共的页数*/
			if (count >= pageCount && count % pageCount == 0) {
				page = count / pageCount;
			} else {
				page = count / pageCount + 1;
			}
			String TemplatePDF = "H:/test/PdfTemplate.pdf";//设置模板路径
			FileOutputStream fos = new FileOutputStream("H:/test/Pdftest.pdf");//需要生成PDF
			
			ByteArrayOutputStream baos[] = new ByteArrayOutputStream[page];//用于存储每页生成PDF流
			/** 向PDF模板中插入数据 */
			for (int item = 0; item < page; item++) {
				baos[item] = new ByteArrayOutputStream();
				PdfReader reader = new PdfReader(TemplatePDF);
				PdfStamper stamp = new PdfStamper(reader, baos[item]);
				AcroFields form = stamp.getAcroFields();
				form.setField("DepartmnetNmae", "蓝飞");//插入的数据都为字符类型
				form.setField("QQ:", "252462807");
				form.setField("pageNumber", "第" + (item + 1) + "页,共" + page
						+ "页");
				if (count % pageCount != 0 && item == page - 1) {
					System.out.println("====pageCount+" + pageCount + "=====");
					pageCount = count % pageCount;
				}
				/**因为PDF中的表格其实是众多的文本域组成,就是一个组数,所以把它循环出来就可以了*/
				for (int j = 0; j < pageCount; j++) {
					form.setField("ProjectTask[" + j + "]", index + "");
					form.setField("星期一[" + j + "]", "星期一[" + index + "]");
					form.setField("星期二[" + j + "]", "星期二[" + index + "]");
					form.setField("星期三[" + j + "]", "星期三[" + index + "]");
					form.setField("星期四[" + j + "]", "星期四[" + index + "]");
					form.setField("星期五[" + j + "]", "星期五[" + index + "]");
					form.setField("星期六[" + j + "]", "星期六[" + index + "]");
					form.setField("星期日[" + j + "]", "星期日[" + index + "]");
					form.setField("意见[" + j + "]", "同意[" + j + "]");
					index++;
				}
				stamp.setFormFlattening(true); // 千万不漏了这句啊, */
				stamp.close();
			}
			Document doc = new Document();
			PdfCopy pdfCopy = new PdfCopy(doc, fos);
			doc.open();
			PdfImportedPage impPage = null;
			/**取出之前保存的每页内容*/
			for (int i = 0; i < page; i++) {
				impPage = pdfCopy.getImportedPage(new PdfReader(baos[i]
						.toByteArray()), 1);
				pdfCopy.addPage(impPage);
			}
			doc.close();//当文件拷贝  记得关闭doc
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}
}
