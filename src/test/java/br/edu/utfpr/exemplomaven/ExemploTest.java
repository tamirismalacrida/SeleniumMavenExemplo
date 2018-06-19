package br.edu.utfpr.exemplomaven;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author andreendo
 */
public class ExemploTest {

    /**
     * Vc precisa identificar onde estah o chromedriver. Baixar de:
     * https://sites.google.com/a/chromium.org/chromedriver/downloads
     *
     * Versão utilizada do chromedriver: 2.35.528139
     */
    //private static String CHROMEDRIVER_LOCATION = "/home/utfpr/install/selenium/chromedriver";
     private static String CHROMEDRIVER_LOCATION = "C:\\Selenium\\chromedriver.exe";
    
    private static int scId = 0;

    WebDriver driver;
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
    }
    
    @Before
    public void before() {
        ChromeOptions chromeOptions = new ChromeOptions();
        //Opcao headless para MacOS e Linux
        //chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
    @After
    public void after() {
        driver.close();
    }
    
    @Test
    public void testMainPage()
    {
        driver.get("https://ration.io/signup");
        
        WebElement terms = driver.findElement(By.xpath("//*[@id=\"signup\"]/div/div/p[2]/a")); 
        terms.click();
       
        WebElement retorno = driver.findElement(By.xpath("//*[@id=\"login\"]/div/h1") ); 
        assertTrue(retorno.getText().contains("Sign in to your account"));               
    }
    
    @Test 
    public void testLogin()
    {
        driver.get("https://ration.io/login");
        
        WebElement email = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[1]/input"));
        email.sendKeys("tamirismalacrida@gmail.com");
        
        WebElement password = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[2]/input"));
        password.sendKeys("120491");
        
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/form/div[4]/button") );
        submit.click();
                
        WebElement logado = driver.findElement(By.xpath("//*[@id=\"friends\"]/div/div[1]/h2") );
        assertTrue(logado.getText().contains("View and add people to share stuff with."));               
    }
    
     @Test
    public void testRecoveryPassword(){
        
        driver.get("https://ration.io/login");

        WebElement esqSenha = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div/p/a"));
        esqSenha.click();
        
        WebElement email = driver.findElement(By.name("email-address"));
        email.sendKeys("tamirismalacrida@gmail.com");
        
        WebElement btn = driver.findElement(By.xpath("//*[@id=\"forgot-password\"]/div/div/form/div[2]/button"));
        btn.click();
                
        WebElement retorno = driver.findElement(By.xpath("//*[@id=\"forgot-password\"]/div/h1") );
        assertTrue(retorno.getText().contains("Recover password"));
        
    }   
}
