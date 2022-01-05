package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utilities.Methods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class C6 {
    public  static List<String> glinks = new ArrayList<String>();
    public  static List<String> gtitles = new ArrayList<String>();
    public  static List<String> gcontent = new ArrayList<String>();
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //driver.manage().window().maximize();

        driver.get("https://www.bbc.com/");

        //Storing the links in a list and traversing through the links
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // This line will print the number of links and the count of links.
        System.out.println("No of links are "+ links.size());

        //checking the links fetched.
        for(int i=0;i<links.size();i++)
        {
            WebElement E1= links.get(i);
            String url= E1.getAttribute("href");
            verifyLinks(url);
        }
        Methods.writeexcel(glinks,0,0);
        Methods.writeexcel(gtitles,0,1);
        Methods.writeexcel(gcontent,0,2);

        driver.quit();
    }


    public static void verifyLinks(String linkUrl)

    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);


        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=400)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage()+"is a broken link");

            }

            //Fetching and Printing the response code obtained
            else{
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
                glinks.add(linkUrl);





                driver.get(linkUrl);
                gtitles.add(driver.getTitle());
                System.out.println("*******************************");
                System.out.println(driver.getTitle());
                System.out.println("*******************************");
                String content=driver.findElement(By.xpath("//meta[@name='description']")).getAttribute("content");
                gcontent.add(content);
                System.out.println(content);

                driver.quit();




            }
        }catch (Exception e) {
        }
    }
}
