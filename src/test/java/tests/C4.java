package tests;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class C4 {
    public static void main(String []args){
        String excelPath="C:\\Users\\gazi\\IdeaProjects\\BBCNews\\src\\test\\java\\datas\\test.xlsx";



        try {



            File src=new File(excelPath);



            FileInputStream fis=new FileInputStream(src);



            XSSFWorkbook wb=new XSSFWorkbook(fis);



            XSSFSheet sh1= wb.getSheetAt(0);


            ArrayList<String> cars = new ArrayList<String>();
            cars.add("Volvo");
            cars.add("BMW");
            cars.add("Ford");
            cars.add("Mazda");
            for (int i = 0; i < cars.size(); i++) {
                XSSFRow rownumber=sh1.createRow(i);
                rownumber.createCell(0).setCellValue(cars.get(i));
                System.out.println(cars.get(i));
            }



// here we need to specify where you want to save file

            FileOutputStream fout=new FileOutputStream(new File(excelPath));


// finally write content

            wb.write(fout);

// close the file

            fout.close();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
}
