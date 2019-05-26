import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class baseScrape {
    private static final String Correct = "This is a correct answer";
    private static final String Wrong = "This is a wrong answer";

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

        BufferedReader br = new BufferedReader(new StringReader(completeData));

        String lineTemp = null;
        ArrayList<String> elemental = new ArrayList<String>();

        try{
            while((lineTemp=br.readLine())!=null){
                elemental.add(lineTemp);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        System.out.println("Size: "+elemental.size());

        for(String s : elemental){
            System.out.println(s);
        }

        List<Question> questionSet = new ArrayList<Question>();
        int cur =0;
        boolean isNext = true;
        int qLength=1;
        for(int i =0; i<questions;i++) {
                List<String> preAdd = new ArrayList<String>();
                List<Integer> correct = new ArrayList<Integer>();

                int digits =0;
                int curQ = i+1;
                while(curQ>0){
                    curQ/=10;
                    digits++;
                }
                String question = elemental.get(cur).substring(digits+2,elemental.get(cur).length());

                isNext = true;
                cur++;
                String timeLimit = elemental.get(cur);
                cur++;
                int nextQ = i +2;
                int digits2=0;
                while(nextQ>0){
                 nextQ/=10;
                    digits2++;
                    }
                while(isNext){
                    if(cur>=elemental.size()){
                        isNext = false;
                    }else if(elemental.get(cur).length()>(digits+2)){
                        String elementCur = elemental.get(cur).substring(0,digits2+2);
                        String predicted = "Q"+(i+2)+":";
                       if(elementCur.equals(predicted)) {
                           isNext = false;
                       }
                    }
                    if(isNext){
                        preAdd.add(elemental.get(cur));
                        cur++;
                        String isCorrect = elemental.get(cur);

                        if(isCorrect.equals(Correct)){
                            correct.add(preAdd.size());
                            //The Marked answers are not input by their array positions
                            // ie: starting at 0. Instead, the correct answer input
                            //Assumes that the first question is not number 0 but number 1
                        }


                        cur++;
                    }

                }
            Question currentQuestion = new Question(question,preAdd,timeLimit,correct);

                questionSet.add(currentQuestion);

        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String printer = gson.toJson(questionSet);

        System.out.println(printer);

        try{
            FileWriter file = new FileWriter("questionData.txt");
            file.write(printer);
            file.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }














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
