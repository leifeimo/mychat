package com.prcmind.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.lowagie.text.DocumentException;

public class S {
    /** The resulting text file with info about a PDF. */

  public static final String RESULT  = "H:/开源项目/ceshi.txt";//存放由pdf转换成txt文件的路径。

  /**

   * Main method.

   * @param args no arguments needed

   * @throws DocumentException

   * @throws IOException

   */

  public static void main(String[] args)

      throws DocumentException, IOException {

      PrintWriter writer = new PrintWriter(new FileOutputStream(RESULT));//txt文件写入流

      String string = "C:/Users/leichang/Desktop/dubbo/R/A.pdf";//pdf文件路径

      inspect(writer,string); //调用读取方法

      writer.close();

  }

  /**

   * Inspect a PDF file and write the info to a txt file

   * @param writer Writer to a text file

   * @param filename Path to the PDF file

   * @throws IOException

   */

  public static void inspect(PrintWriter writer, String filename)

      throws IOException {

      PdfReader reader = new PdfReader(filename); //读取pdf所使用的输出流

      int num = reader.getNumberOfPages();//获得页数

      String content = "";  //存放读取出的文档内容

      for (int i = 1; i <= num; i++) {

         content += PdfTextExtractor.getTextFromPage(reader, i); //读取第i页的文档内容

                }

     writer.write(content);//写入文件内容

      writer.flush();

  }

  public static void html2PDF() throws Exception {  
      String outputFile = "D:/test.pdf";  
      OutputStream os = new FileOutputStream(outputFile);  
      ITextRenderer renderer = new ITextRenderer();  
      ITextFontResolver fontResolver = renderer.getFontResolver();  
      fontResolver.addFont("C:/Windows/fonts/simsun.ttc",  
              BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);  
      StringBuffer html = new StringBuffer();  
      // DOCTYPE 必需写否则类似于 这样的字符解析会出现错误  
      html.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  
      html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");  
      html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">")  
              .append("<head>")  
              .append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")  
              .append("<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;}</style>")  
              .append("</head>")  
              .append("<body><strong><span style=\"font-size: 20pt; \">欢迎使用</span></strong>");  
      html.append("<div>支持中文！</div>");  
      html.append("</body></html>");  
      System.out.println(html.toString());  
      renderer.setDocumentFromString(html.toString());  
      // 解决图片的相对路径问题  
      // renderer.getSharedContext().setBaseURL("file:/F:/teste/html/");  
      renderer.layout();  
      renderer.createPDF(os);  
      os.close();  
  }  
}

