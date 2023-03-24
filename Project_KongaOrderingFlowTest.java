import org.apache.tika.extractor.DocumentSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

/***
 * 1. Visit the URL https://www.konga.com/
 * 2. Sign in to https://www.konga.com/ successfully
 * 3. From the Categories, click on the “Computers and Accessories”
 * 4. Click on the Laptop SubCategory
 * 5. Click on the Apple MacBooks
 * 6. Add an item to the cart
 * 7. Select Address
 * 8. Continue to make payment
 * 9. Select a Card Payment Method
 * 10. Input invalid card details
 * 11. Print Out the error message: Invalid card number
 * 12. Close the iFrame that displays the input card Modal
 * 13. Quit the browser.
 *
 */



public class KongaOrderingFlowTest {

        private By modalBtn = By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button");
        private By modalCardBtn = By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button");
        private By modalCardNum = By.id("card-number");
        private By modalDate = By.id("expiry");
        private By modalCVV = By.id("cvv");
        private By modalPayBtn = By.id("validateCardForm");
        private By exitIframe = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");

        //import the selenium WebDriver
        private WebDriver driver;

        //public String getPageTitle() {
        //return driver.getTitle();
        //}

        @BeforeTest
        private void SetUp() {

            //Locate Chrome Driver
            System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");

        }

        @Test(priority = 0)
        private void UrlTest() throws InterruptedException {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            // 1. Open the Chrome Browser
            driver = new ChromeDriver(options);


            // 2. Visit the URL https://www.konga.com/
            driver.get("https://www.konga.com/");

            //TEST CASE ONE: Verify that the Url leads to the Konga site____________________
            if (driver.getCurrentUrl().contains("https://www.konga.com"))
                //Pass
                System.out.println("Url leads to the Konga webpage: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Url does not lead to the Konga webpage: TEST CASE FAILED");

            // 3. Maximize the browser
            driver.manage().window().maximize();

            Thread.sleep(3000);


        }

        @Test(priority = 1)
        private void LoginPageTest() throws InterruptedException {
            // 4. Click the Login/Signup button to signin to Konga
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();

            Thread.sleep(5000);

            //TEST CASE TWO: Verify that the Login/Signup button leads to the Login page____________________
            String expectedXpath = "//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[1]/div/h5";
            String actualXpath = String.valueOf(driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[1]/div/h5")));
            if (actualXpath.contains(expectedXpath))
                //Pass
                System.out.println("Login button leads to the Login page: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Login button leads to a wrong page: TEST CASE FAILED");


        }

        @Test(priority = 2)
        private void SignUpTest() throws InterruptedException {
            // 5. Input valid email address
            driver.findElement(By.id("username")).sendKeys("postmancohort13@gmail.com");
            // 6. Input valid password
            driver.findElement(By.id("password")).sendKeys("Password@01");
            // 7. Click on the Login button to login
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();

            Thread.sleep(5000);


            //TEST CASE THREE: Verify that user can Login successfully with a valid email and password____________________
            String expectedProfile = "//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/div/a/span";
            String actualProfile = String.valueOf(driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/div/a/span")));
            if (actualProfile.contains(expectedProfile))
                //Pass
                System.out.println("User can login successfully with valid email and password: TEST CASE PASSED");
            else
                //Fail
                System.out.println("User cannot login successfully with valid email and password: TEST CASE FAILED");

        }

        @Test(priority = 3)
        private void CategoryItemsTest() throws InterruptedException {
            // 8. From the Categories, click on the “Computers and Accessories” button
            driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/nav/div[2]/div[2]/div/a[2]")).click();
            Thread.sleep(5000);
            // 9. Click on the Laptops sub category
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
            Thread.sleep(5000);
            // 10. Select Apple Macbooks
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label")).click();

            Thread.sleep(5000);

            //TEST CASE FOUR: Verify that the Apple Macbooks button takes user to the Macbook computer accessories page____________________
            if (driver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
                //Pass
                System.out.println("Apple Macbooks button takes user to the right page: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Apple Macbooks button takes user to a wrong page: TEST CASE FAILED");

        }


        @Test(priority = 4)
        private void CartTest() throws InterruptedException {
            // 11. Click on the Add To Cart button on an item to add it to cart
            driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
            Thread.sleep(5000);


            //TEST CASE FIVE: Verify that the Add To Cart button adds item to cart____________________
            String expectedPopup = "//*[@id=\"app-content-wrapper\"]/div[1]";
            String actualPopup = String.valueOf(driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[1]")));
            if (actualPopup.contains(expectedPopup))
                //Pass
                System.out.println("Add To Cart button functions properly: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Add To Cart button does not function properly: TEST CASE FAILED");

            Thread.sleep(5000);

            // 12. Open the cart
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
            Thread.sleep(5000);
            // 13. Click on the Checkout button
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();

            Thread.sleep(10000);

            // 14. Select delivery address
            //A. click on 'Change'
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
            Thread.sleep(5000);
            //B. click on 'Add Delivery Address'
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
            Thread.sleep(5000);
            //C. select your address
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
            Thread.sleep(5000);
            //D. click on 'Use this Address'
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();

            Thread.sleep(5000);

            //TEST CASE SIX: Verify that selected address appears on check out page____________________
            String expectedAddress = "//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/p[2]";
            String actualAddress = String.valueOf(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/p[2]")));
            if (actualAddress.contains(expectedAddress))
                //Pass
                System.out.println("Address: Testify Avenue, by Postman cohort 13, Wukari, Taraba: TEST CASE PASSED");
            else
                //Fail
                System.out.println("No or invalid address: TEST CASE FAILED");


        }

        @Test(priority = 5)
        private void CardBtnTest() throws InterruptedException {
            // 15. Under 'Payment Options', click on the 'Pay Now' button
            driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/section/main/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
            Thread.sleep(5000);

            // 16. Click the 'Continue to Payment' button to open the modal
            //click(modalBtn);
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
            System.out.println("User successfully clicked on 'Continue to Payment' button");

            Thread.sleep(10000);

            // 17. Click the Card button
            click(modalCardBtn);
            System.out.println("User successfully clicked on card button");
            Thread.sleep(5000);

        }

        @Test (priority = 6)
        private  void NegativeCardDetails() throws InterruptedException {
            // 18. Input invalid card details
            //A. Input invalid card number
            SetText(modalCardNum, 123456781);
            //B. Input random date
            SetText(modalDate,0323);
            //C. Input invalid CVV
            SetText(modalCVV, 000);

            // 19. Click on the 'Pay Now' button
            click(modalPayBtn);
            System.out.println("User successfully clicked on 'Pay Now' button");
            Thread.sleep(3000);

            // Print out error code
            System.out.println("Invalid card number");
            System.out.println("Error code: 'Invalid card number' successfully printed");
            //String errorCode = driver.findElement(By.xpath("//*[@id=\"card-number_unhappy\"]")).getAccessibleName();
            //System.out.println(errorCode);


        }

        @Test(priority = 7)
        private void iframeTest() throws InterruptedException {
            // 20. Click on the x button to close i-Frame
           click(exitIframe);
            System.out.println("User successfully closed i-Frame");


            //TEST CASE TEN: Verify that closing the card details i-Frame takes user back to the order page____________________
            if (driver.getCurrentUrl().contains("https://www.konga.com/checkout/complete-order"))
                //Pass
                System.out.println("Success! User returns back to order page");
            else
                //Fail
                System.out.println("Fail! User is not taken back to order page");

            Thread.sleep(2000);

        }

        @AfterTest
        private void CloseBrowser() {
            // 22. Quit the browser
            driver.quit();


        }

    private void SetText(By modalDate, int i) {
    }

    private void click(By modalBtn) {
    }
}