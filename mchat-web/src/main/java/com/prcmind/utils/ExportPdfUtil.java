package com.prcmind.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * 
 * 导出pdf工具类 <br>
 * 导出pdf工具类
 * 
 * @param outpath
 *            输出路径
 * @param fontPath
 *            字体路径
 * @param templateName
 *            pdf模板文件路径名
 * @param content
 *            需要填充内容
 * @return
 */
public class ExportPdfUtil {

	public static String exportpdf(String outpath, String templateName, Map<String, String> content,HttpServletResponse response) throws IOException {
	
		// 得到当前时间
		Date now = new Date();
		SimpleDateFormat dataformat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String t = dataformat.format(now);
		// 得到一个随机数
		String ran = Math.random() + "";
		// 以当前时间加上一个随机数获取下载的文件以保证不重名
		String filename = t + "-" + ran;

		String savepath = outpath + File.separator + filename + ".pdf";
		PdfReader reader = null;
		ByteArrayOutputStream bos = null;
		PdfStamper ps = null;
		FileOutputStream fos = null;
		try {
			// 创建字体
//			BaseFont chineseSong = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			// 读取pdf
//			String path=System.getProperty("user.dir")+"\\src\\main\\resources\\template\\GB.pdf";
//			System.out.println(path);
//			reader = new PdfReader(path	);
			reader = new PdfReader(templateName);
			bos = new ByteArrayOutputStream();
			ps = new PdfStamper(reader, bos);
			AcroFields s = ps.getAcroFields();
			// 添加所创建的字体
//			s.addSubstitutionFont(chineseSong);
			// 找到pdf中输入域并替换为内容
			BaseFont bf =BaseFont.createFont("STSong-Light",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);   
			s.addSubstitutionFont(bf);
			Iterator<String> it = s.getFields().keySet().iterator();
			while (it.hasNext()) {
				String name = (String) it.next();
				s.setField("" + name.trim(), content.get(name.trim()));
			}
			// 这两步必须有,否则pdf生成失败
			ps.setFormFlattening(true);
			ps.close();
			// 输出pdf
			fos = new FileOutputStream(savepath);
			fos.write(bos.toByteArray());
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		} finally {
			if (null != reader) {
				reader.close();
			}
			try {
				if (null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				System.out.println("failed to close ByteArrayOutputStream ");
			}
			try {
				if (null != ps) {
					ps.close();
				}
			} catch (DocumentException e) {
				System.out.println("failed to close PdfStamper ");
			} catch (IOException e) {
				System.out.println("failed to close PdfStamper ");
			}
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				System.out.println("failed to close FileOutputStream ");
			}
		}
		return filename;
	}
	
	public static void main(String[] args) throws IOException {
		Map<String,String> content=new HashMap<String,String>();
		  content.put("name", "你好");//根据模板定义的输入域的名字（如：name），填充值
		  content.put("sex", "男");
		  content.put("birthDate", "2016-05-12");
		  content.put("createTime", "2017-05-12");
//		  content.put("score", "98");
		  content.put("r_score", "98");
		  content.put("r_f_score", "98");
		  content.put("enterpriseName", "测试机构");
		  content.put("medicName", "鲍赣修");
		  content.put("gestationalWeeks", "40周1天");
		  content.put("age", "1周岁");
		  content.put("births", "足月；剖腹产；双胞胎");
		 ExportPdfUtil.exportpdf("D:\\test", "D:\\test\\B.pdf",  content,null);
	}
}
