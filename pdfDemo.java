import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
    //所需jar包  itext-5.5.10.jar ,,中文输出 itext-asian.jar  详见158行
public class pdfDemo {


    public static void main(String[] args) throws  Exception{
        //用于生成pdf的路径
        String newPath="D:\\Q1\\每日PDF\\pdfDemo.pdf";
        //表头   详见88行
        String[] str={"表头1","表头2","表头3"};

        //内容    详见96行
        List<String> rzList=new ArrayList<>();
        rzList.add(0,"aaaa");
        rzList.add(1,"bbbb");
        rzList.add(2,"cccc");

        //表尾的内容 1-6是 113行 6-9 124行
        List<String> qtList=new ArrayList<>();
        qtList.add(0,"111");
        qtList.add(1,"222");
        qtList.add(1,"333");
        qtList.add(1,"444");
        qtList.add(1,"555");
        qtList.add(1,"666");
        qtList.add(1,"777777");
        qtList.add(1,"888888");
        qtList.add(1,"999999");
   /*     try {
            createPDF(newPath,str,rzList,qtList);
        }catch (Exception e){
            System.out.println("***");
        }*/
        createPDF(newPath,str,rzList,qtList);
    }





    public static void createPDF(String newPath, String []str, List<String> rzlist, List<String> qtlist) throws Exception {
        Document document = new Document(PageSize.A4.rotate());//A4制  rotate为横向
        PdfWriter.getInstance(document, new FileOutputStream(newPath));
        document.open();
        PdfPTable table = null;
        PdfPTable table1 = null;
        PdfPCell cell;

        table = new PdfPTable(3);//建立表格 ，5代表有5列的表格
        table.setTotalWidth(new float[]{110f, 440f, 110f});//每列设置宽度

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setLockedWidth(true);

        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setLockedWidth(true);
        cell = new PdfPCell(new Phrase("柜台市场（OTC）自建TA参数设置表",ChineseFont(1)));
        cell.setBorderWidthRight(0);	//单元格右侧border设为0
        cell.setBorderWidthLeft(0);		//单元格右侧border设为0
        cell.setBorderWidthBottom(0);	//单元格底部border设为0
        cell.setBorderWidthTop(0);		//单元格顶部border设为0
        cell.setColspan(4);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setFixedHeight(50f);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("         单位：登记结算部 ",ChineseFont(2)));
        cell.setBorderWidthRight(0);	//单元格右侧border设为0
        cell.setBorderWidthLeft(0);		//单元格右侧border设为0
        cell.setBorderWidthBottom(0);	//单元格底部border设为0
        cell.setBorderWidthTop(0);		//单元格顶部border设为0
        cell.setColspan(3);
        cell.setFixedHeight(30f);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
        //
        String []bs = {"ywlx", "ywmx", "bz"};
        // 第一行表头
        for (int j = 0; j < 3; j++) {
            cell = new PdfPCell(new Phrase(str[j],ChineseFont(2)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(50f);
            table.addCell(cell);
        }
        //内容，业务类型，业务明细，备注
        for(int k = 0; k < 3; k++){
            cell = new PdfPCell(new Phrase(rzlist.get(k),ChineseFont(2)));
            //cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(220f);
            table.addCell(cell);
            if(k==2){
                cell.setBorderWidthTop(0);		//单元格顶部border设为0
            }
        }
        //签字
        table1 = new PdfPTable(6);//建立表格 ，5代表有5列的表格
        table1.setTotalWidth(new float[]{110f, 110f, 110f,110f,110f,110f});//每列设置宽度
        table1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.setLockedWidth(true);


        for(int l = 0; l <6 ; l++){
            cell = new PdfPCell(new Phrase(qtlist.get(l),ChineseFont(2)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(32f);
            table1.addCell(cell);
            if(l==5){
                cell.setBorderWidthTop(0);		//单元格顶部border设为0
            }
        }
        //审核：                      设置日期：2017年 12月 13日
        for(int p = 6;p <9 ; p++){
            cell = new PdfPCell(new Phrase(qtlist.get(p),ChineseFont(3)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setFixedHeight(50f);

            cell.setBorderWidthRight(0);	//单元格右侧border设为0
            cell.setBorderWidthLeft(0);		//单元格右侧border设为0
            cell.setBorderWidthBottom(0);	//单元格底部border设为0
            cell.setBorderWidthTop(0);		//单元格顶部border设为0
            cell.setColspan(2);
            table1.addCell(cell);
        }

        document.add(table);
        document.add(table1);
        document.newPage();//手动换新页
        document.close();

    }

    /**
     *
     */




    /**
     * 中文字体支持

     */
    public static Font ChineseFont(int fontType) {
        BaseFont baseFont=null;
        try {                                                       //中文输出，需要itext-asian.jar 支持
            baseFont=BaseFont.createFont("STSong-Light","UniGB-UCS2-H", true);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Font chineseFont=new Font(baseFont,10f,Font.NORMAL,BaseColor.BLACK);
        if(fontType==1){
            chineseFont.setSize(20f);
        }else if(fontType==2){
            chineseFont.setSize(11f);
            chineseFont.setStyle("font-weight:bold;");//给字体加样式---加粗

        } else if(fontType==121){
            chineseFont.setSize(10f);
        } else if(fontType==3) {
            chineseFont.setStyle("font-weight:bold;");//给字体加样式---加粗
        }
//        	chineseFont.setSize(100);
        return chineseFont;
    }
}
