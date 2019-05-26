import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class baseScrape {


    public static void main(String[] args) {
        File path = new File("/snap/firefox/216/firefox-bin");
        System.setProperty("webdriver.firefox.bin", path.getAbsolutePath());
        FirefoxDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        //driver.navigate().to("https://create.kahoot.it/details/a97b0a90-26d1-4513-aeb8-75f39d22a41f");
        driver.navigate().to("https://create.kahoot.it/details/a97b0a90-26d1-4513-aeb8-75f39d22a41f");
        String questionData = "//*[@id=\"layout\"]/div[2]/main/div[2]/section[1]/dl";

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(questionData)));


        WebElement langName = driver.findElement(By.xpath(questionData));

        String curCount = langName.getText();
        int questions = countLines(curCount) / 2;

        System.out.println(curCount);
        for (int i = 1; i < questions + 1; i++) {
            driver.findElement(By.xpath("//*[@id=\"question-" + i + "\"]")).click();
        }

        System.out.println("\n\n\n\n");
        System.out.println("New Shit:");
        WebElement langNameNew = driver.findElement(By.xpath(questionData));
        String completeData = langNameNew.getText();
        System.out.println(completeData);


        driver.close();









        /*try {
            Connection.Response res =  Jsoup.connect("https://create.kahoot.it/details/a97b0a90-26d1-4513-aeb8-75f39d22a41f/?page=2").method(
                    Connection.Method.GET).execute();

             Document doc = res.parse();
            System.out.println(doc.outerHtml());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }*/


    }

    private static int countLines(String str) {
        String[] lines = str.split("\r\n|\r|\n");
        return lines.length;
    }


}
