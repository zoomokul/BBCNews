package utilities;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Methods {
    public static void writeexcel(List<String> veri,int colNumber,int sheetNumber){
        String excelPath="C:\\Users\\gazi\\IdeaProjects\\BBCNews\\src\\test\\java\\datas\\test.xlsx";

        try {
            File src=new File(excelPath);
            FileInputStream fis=new FileInputStream(src);
            XSSFWorkbook wb=new XSSFWorkbook(fis);
            XSSFSheet sh1= wb.getSheetAt(sheetNumber);

            for (int i = 0; i < veri.size(); i++) {
                XSSFRow rownumber=sh1.createRow(i);
                rownumber.createCell(colNumber).setCellValue(veri.get(i));
            }

            FileOutputStream fout=new FileOutputStream(new File(excelPath));

            wb.write(fout);

            fout.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }




    }
}
