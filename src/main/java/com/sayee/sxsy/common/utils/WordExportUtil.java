package com.sayee.sxsy.common.utils;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;
import org.apache.poi.xwpf.usermodel.*;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.Sides;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*******************************************
 *
 * @Package com.cccuu.project.myUtils
 * @Author duan
 * @Date 2018/3/29 17:55
 * @Version V1.0
 *******************************************/
public class WordExportUtil  {

    /**
     * 根据模板生成word
     * @param path     模板的路径
     * @param savePath    文件保存的路径
     * @param params   需要替换的参数
     * @param tableList   需要插入的参数
     * @param fileName 生成word文件的文件名
     * @param response
     */
    public void getWord(String path,String tempPath ,String savePath, Map<String, Object> params, List<String[]> tableList, String fileName, HttpServletResponse response) throws Exception {
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        CustomXWPFDocument doc = new CustomXWPFDocument(is);
        this.replaceInPara(doc, params);    //替换文本里面的变量
        this.replaceInTable(doc, params, tableList); //替换表格里面的变量

        FileInputStream tempIs = new FileInputStream(tempPath);
        CustomXWPFDocument tempXdf = new CustomXWPFDocument(tempIs);
        this.setStyle(tempXdf,doc);
        this.setTableStyle(tempXdf,doc);


        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        doc.write(os);
        FileOutputStream output=new FileOutputStream(savePath);
        doc.write(output);
        this.close(os);
        this.close(is);

    }
    /**
     * 替换段落里面的变量
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInPara(CustomXWPFDocument doc, Map<String, Object> params) {
        Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
        XWPFParagraph para;
        while (iterator.hasNext()) {
            para = iterator.next();
            this.replaceInPara(para, params);
        }
    }
    /**
     * 替换段落里面的变量
     * @param para   要替换的段落
     * @param params 参数
     */
    private  void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                matcher = matcher(runText);
                if (matcher.find()) {
                    while ((matcher = matcher(runText)).find()) {
                        runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                    }
                    para.removeRun(i);
                    //重新插入run里内容格式可能与原来模板的格式不一致
                    para.insertNewRun(i).setText(runText);
                }
            }
        }
    }

    /**
     * 处理文档替换内容的格式问题（word）
     * @param tempDoc
     * @param doc
     */
    private  void setStyle(XWPFDocument tempDoc,XWPFDocument doc) {
        Iterator<XWPFParagraph> iterator = tempDoc.getParagraphsIterator();
        Iterator<XWPFParagraph> iterator2 = doc.getParagraphsIterator();
        XWPFParagraph para ;
        XWPFParagraph para2;
        while (iterator.hasNext()&&iterator2.hasNext()) {
            para = iterator.next();
            para2 = iterator2.next();
            this.setStyleInPara(para,para2);
        }
    }

    /**
     * 处理段落替换内容的格式问题（word）
     * @param para
     * @param para2
     */
    private  void setStyleInPara(XWPFParagraph para, XWPFParagraph para2) {
        List<XWPFRun> runs;
        List<XWPFRun> runs2;
        Matcher matcher;
        if (matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            runs2 = para2.getRuns();
            for (int i=0; i<runs.size(); i++) {
                XWPFRun run = runs.get(i);
                XWPFRun run2 = runs2.get(i);
                String runText = run.toString();
                matcher = matcher(runText);
                if (matcher.find()) {
                    //按模板文件格式设置格式
                    run2.getCTR().setRPr(run.getCTR().getRPr());
                }
            }
        }
    }
    /**
     * 处理文档替换内容的格式问题（word）
     * @param tempDoc
     * @param doc
     */
    private  void setTableStyle(XWPFDocument tempDoc,XWPFDocument doc) {
        Iterator<XWPFTable> iterator = tempDoc.getTablesIterator();
        Iterator<XWPFTable> iterator2 = doc.getTablesIterator();
        XWPFTable table ;
        XWPFTable table2;
        while (iterator.hasNext()&&iterator2.hasNext()) {
            table = iterator.next();
            table2 = iterator2.next();
            this.setStyleInPara(table,table2);
        }
    }

    /**
     * 处理表格替换内容的格式问题（word）
     * @param table
     * @param table2
     */
    private  void setStyleInPara(XWPFTable table, XWPFTable table2) {
        List<XWPFTableRow> row ;
        List<XWPFTableCell> cell = null;
        List<XWPFParagraph> para = null;
        List<XWPFTableRow> rows ;
        List<XWPFTableCell> cells = null;
        List<XWPFParagraph> paras = null;
        if (table.getRows().size() > 1) {
            if (matcher(table.getText()).find()) {
                row=table.getRows();
                rows=table2.getRows();
                this.aa(para,paras,cell,cells,row,rows);
            }
        }
    }
    private void aa(List<XWPFParagraph> para,List<XWPFParagraph> paras,List<XWPFTableCell> cell,List<XWPFTableCell> cells,List<XWPFTableRow> row,List<XWPFTableRow> rows){
        for (int r1=0; r1 < row.size();r1++){
            for (int rr1=0; rr1 < rows.size();rr1++){
                cells = rows.get(rr1).getTableCells();
                cell = row.get(r1).getTableCells();
                if (r1==rr1){
                    for (int c1=0; c1 < cell.size();c1++) {
                        for (int cc1 = 0; cc1 < cells.size(); cc1++) {
                            paras = cells.get(cc1).getParagraphs();
                            para = cell.get(c1).getParagraphs();
                            if(c1==cc1){
                                for (int p1=0; p1 < para.size();p1++) {
                                    for (int pp1 = 0; pp1 < paras.size(); pp1++) {
                                        if(p1==pp1){ this.setStyleInPara(para.get(p1),paras.get(pp1));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        for (XWPFTableRow r : row) {
//            for (XWPFTableRow rr : rows) {
//                cells = rr.getTableCells();
//                cell = r.getTableCells();
//                for (XWPFTableCell c : cell) {
//                    for (XWPFTableCell cc : cells) {
//                        paras = cc.getParagraphs();
//                        para = c.getParagraphs();
//                        for (XWPFParagraph pp : paras) {
//                            for (XWPFParagraph p : para) {
//                                this.setStyleInPara(p,pp);
//                                break;
//                            }
//                            break;
//                        }
//                        break;
//                    }
//                    break;
//                }
//                break;
//            }
//            continue;
//        }
    }
    /**
     * 替换段落里面的变量
     * @param para   要替换的段落
     * @param params 参数
     * @param doc d对有图片的word进行操作
     */
    private void replaceInPara(XWPFParagraph para, Map<String, Object> params, CustomXWPFDocument doc) {
        List<XWPFRun> runs;
        Matcher matcher;
        if (this.matcher(para.getParagraphText()).find()) {
            runs = para.getRuns();
            int start = -1;
            int end = -1;
            String str = "";
            for (int i = 0; i < runs.size(); i++) {
                XWPFRun run = runs.get(i);
                String runText = run.toString();
                if ('$' == runText.charAt(0) && '{' == runText.charAt(1)) {
                    start = i;
                }
                if ((start != -1)) {
                    str += runText;
                }
                if ('}' == runText.charAt(runText.length() - 1)) {
                    if (start != -1) {
                        end = i;
                        break;
                    }
                }
            }

//            for (int i = start; i <= end; i++) {
//                para.removeRun(i);
//                i--;
//                end--;
//            }

            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                if (str.indexOf(key) != -1) {
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        str = str.replace(key, value.toString());
                        runs.get(end).setText(str,end);
                        //para.createRun().setText(str, end);
                        break;
                    } else if (value instanceof Map) {
                        str = str.replace(key, "");
                        Map pic = (Map) value;
                        int width = Integer.parseInt(pic.get("width").toString());
                        int height = Integer.parseInt(pic.get("height").toString());
                        int picType = getPictureType(pic.get("type").toString());
                        byte[] byteArray = (byte[]) pic.get("content");
                        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteArray);
                        try {
                            //int ind = doc.addPicture(byteInputStream,picType);
                            //doc.createPicture(ind, width , height,para);
                            doc.addPictureData(byteInputStream, picType);
                            doc.createPicture(doc.getAllPictures().size() - 1, width, height, para);
                            para.createRun().setText(str, 0);
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


    /**
     * 为表格插入数据，行数不够添加新行
     *
     * @param table     需要插入数据的表格
     * @param tableList 插入数据集合
     */
    private static void insertTable(XWPFTable table, List<String[]> tableList) {
        //创建行,根据需要插入的数据添加新行，不处理表头
        for (int i = 0; i < tableList.size(); i++) {
            XWPFTableRow row = table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        int length = table.getRows().size();
        for (int i = 1; i < length - 1; i++) {
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for (int j = 0; j < cells.size(); j++) {
                XWPFTableCell cell = cells.get(j);
                String s = tableList.get(i - 1)[j];
                cell.setText(s);
            }
        }
    }

    /**
     * 替换表格里面的变量
     * @param doc    要替换的文档
     * @param params 参数
     */
    private void replaceInTable(CustomXWPFDocument doc, Map<String, Object> params, List<String[]> tableList) {
        Iterator<XWPFTable> iterator = doc.getTablesIterator();
        XWPFTable table;
        List<XWPFTableRow> rows;
        List<XWPFTableCell> cells;
        List<XWPFParagraph> paras;
        while (iterator.hasNext()) {
            table = iterator.next();
            if (table.getRows().size() > 1) {
                //判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
                if (this.matcher(table.getText()).find()) {
                    rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            paras = cell.getParagraphs();
                            for (XWPFParagraph para : paras) {
                                //this.replaceInPara(para, params, doc);
                                this.replaceInPara(para, params);
                            }
                        }
                    }
                } else {
                    insertTable(table, tableList);  //插入数据
                }
            }
        }
    }


    /**
     * 正则匹配字符串
     *
     * @param str
     * @return
     */
    private Matcher matcher(String str) {
        Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        return matcher;
    }


    /**
     * 根据图片类型，取得对应的图片类型代码
     *
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType) {
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if (picType != null) {
            if (picType.equalsIgnoreCase("png")) {
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            } else if (picType.equalsIgnoreCase("dib")) {
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            } else if (picType.equalsIgnoreCase("emf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            } else if (picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")) {
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            } else if (picType.equalsIgnoreCase("wmf")) {
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }

    /**
     * 将输入流中的数据写入字节数组
     *
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in, boolean isClose) {
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (isClose) {
                try {
                    in.close();
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            }
        }
        return byteArray;
    }


    /**
     * 关闭输入流
     *
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     *
     * @param os
     */
    private void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> getsignlist(String text){
        List<String> tt = new ArrayList<String>();
        String[] textlist=text. split("\\$\\{");
        for (int i = 0;i <textlist.length; i++){
            if (textlist[i].indexOf("}")!=-1){
                String signname=StringUtils.trim(textlist[i].substring(0,textlist[i].indexOf("}")));
                tt.add("${"+signname+"}");
                if (textlist[i].indexOf("}")<textlist[i].length()-1){
                    String other=StringUtils.trim(textlist[i].substring((textlist[i].indexOf("}")+1),textlist[i].length()-1));
                    tt.add(other);
                }
            }else {
                tt.add(textlist[i]);
            }
        }
        return tt;
    }

    /**
     * word 转 pdf
     * @param wordFile
     * @param pdfFile
     */
    public void wToPdfChange(String wordFile,String pdfFile){//wordFile word 的路径  //pdfFile pdf 的路径

        ActiveXComponent app = null;
        System.out.println("开始转换...");
        // 开始时间
        // long start = System.currentTimeMillis();
        try {
            // 打开word
            app = new ActiveXComponent("Word.Application");
            // 获得word中所有打开的文档
            Dispatch documents = app.getProperty("Documents").toDispatch();
            System.out.println("打开文件: " + wordFile);
            // 打开文档
            Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
            // 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
            File target = new File(pdfFile);
            if (target.exists()) {
                target.delete();
            }
            System.out.println("另存为: " + pdfFile);
            Dispatch.call(document, "SaveAs", pdfFile, 17);
            // 关闭文档
            Dispatch.call(document, "Close", false);
        }catch(Exception e) {
            System.out.println("转换失败"+e.getMessage());
        }finally {
            // 关闭office
            app.invoke("Quit", 0);
        }
    }

    //这里传入的文件为word转化生成的pdf文件
    public void PDFprint(File file ,String printerName) throws Exception {
        PDDocument document = null;
        try {
            document = PDDocument.load(file);
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setJobName(file.getName());
            if (printerName != null) {
                // 查找并设置打印机
                //获得本台电脑连接的所有打印机
                PrintService[] printServices = PrinterJob.lookupPrintServices();                			 if(printServices == null || printServices.length == 0) {
                    System.out.print("打印失败，未找到可用打印机，请检查。");
                    return ;
                }
                PrintService printService = null;
                //匹配指定打印机
                for (int i = 0;i < printServices.length; i++) {
                    System.out.println(printServices[i].getName());
                    if (printServices[i].getName().contains(printerName)) {
                        printService = printServices[i];
                        break;
                    }
                }
                if(printService!=null){
                    printJob.setPrintService(printService);
                }else{
                    System.out.print("打印失败，未找到名称为" + printerName + "的打印机，请检查。");
                    return ;
                }
            }
            //设置纸张及缩放
            PDFPrintable pdfPrintable = new PDFPrintable(document, Scaling.ACTUAL_SIZE);
            //设置多页打印
            Book book = new Book();
            PageFormat pageFormat = new PageFormat();
            //设置打印方向
            pageFormat.setOrientation(PageFormat.PORTRAIT);//纵向
            pageFormat.setPaper(getPaper());//设置纸张
            book.append(pdfPrintable, pageFormat, document.getNumberOfPages());
            printJob.setPageable(book);
            printJob.setCopies(1);//设置打印份数
            //添加打印属性
            HashPrintRequestAttributeSet pars = new HashPrintRequestAttributeSet();
            pars.add(Sides.DUPLEX); //设置单双页
            printJob.print(pars);
        }finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Paper getPaper() {
        Paper paper = new Paper();
        // 默认为A4纸张，对应像素宽和高分别为 595, 842
        int width = 595;
        int height = 842;
        // 设置边距，单位是像素，10mm边距，对应 28px
        int marginLeft = 10;
        int marginRight = 0;
        int marginTop = 10;
        int marginBottom = 0;
        paper.setSize(width, height);
        // 下面一行代码，解决了打印内容为空的问题
        paper.setImageableArea(marginLeft, marginRight, width - (marginLeft + marginRight), height - (marginTop + marginBottom));
        return paper;
    }


}