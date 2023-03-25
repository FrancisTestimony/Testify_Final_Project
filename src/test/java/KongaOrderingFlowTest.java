import org.apache.tika.extractor.DocumentSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
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


        //Set variable strings
        private By modalBtn = By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button");
        private By modalCardBtn = By.xpath("//*[@id=\"channel-template\"]/div[2]/div/div[2]/button");
        private By modalCardNum = By.id("card-number");
        private By modalDate = By.id("expiry");
        private By modalCVV = By.id("cvv");
        private By modalPayBtn = By.id("validateCardForm");
        private By exitIframe = By.xpath("/html/body/section/section/section/div[2]/div[1]/aside");

        //import the selenium WebDriver
        private WebDriver driver;

        @BeforeTest
        private void SetUp() {

            //Locate where your Chrome Driver is
            System.setProperty("webdriver.chrome.driver", "Driver/chromedriver.exe");
            System.out.println("Chrome driver located successfully"); //comment

        }

        @Test(priority = 0)
        private void UrlTest() throws InterruptedException {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            // 1. Open the Chrome Browser to begin tests
            driver = new ChromeDriver(options);
            System.out.println("Chrome browser launched successfully"); //comment


            // 2. Input the URL https://www.konga.com/ and visit the Konga page
            driver.get("https://www.konga.com/");
            System.out.println("Driver has visited the Konga webpage successfully"); //comment

//FIRST TEST CASE:
// Verify that the Url https://www.konga.com/ leads to the Konga page
            if (driver.getCurrentUrl().contains("https://www.konga.com"))
                //Pass
                System.out.println("Url leads to the Konga webpage: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Url does not lead to the Konga webpage: TEST CASE FAILED");

            // 3. Maximize the browser to get better visibility of the webpage
            driver.manage().window().maximize();

            Thread.sleep(3000);

        }

        @Test(priority = 1)
        private void LoginPageTest() throws InterruptedException {
            // 4. Click the Login/Signup button to signin to your already existing Konga account
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/div[4]/a")).click();
            System.out.println("User has successfully accessed the login page"); //comment

            Thread.sleep(5000);

//SECOND TEST CASE:
// Verify that the Login/Signup button leads user to the Login page
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
            System.out.println("Valid email address logged in successfully"); //comment

            // 6. Input valid password
            driver.findElement(By.id("password")).sendKeys("Password@01");
            System.out.println("Valid password logged in successfully"); //comment

            // 7. Click on the Login button to login
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[4]/section/section/aside/div[2]/div/form/div[3]/button")).click();
            System.out.println("User has successfully logged in to their account"); //comment

            Thread.sleep(5000);


//THIRD TEST CASE:
// Verify that user can Login successfully into their Konga account with their valid email and password
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
            // 8. From the Categories section, click on the “Computers and Accessories” sub-category button
            driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/nav/div[2]/div[2]/div/a[2]")).click();
            System.out.println("User has accessed Computer and accessories sub category"); //comment
            Thread.sleep(5000);

            // 9. From the dropdown menu that shows up, click on the 'Laptops' sub category
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/label/span")).click();
            System.out.println("User has accessed the Laptops sub category"); //comment
            Thread.sleep(5000);

            // 10. Select 'Apple Macbooks' option
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/section[3]/section/div/section/div[2]/div[2]/ul/li[3]/a/ul/li[1]/a/label")).click();
            System.out.println("User has accessed the Apple Macbook section"); //comment
            Thread.sleep(5000);

//FOURTH TEST CASE:
// Verify that the Apple Macbooks button takes user to the Macbook computer accessories page
            if (driver.getCurrentUrl().contains("https://www.konga.com/category/accessories-computing-5227"))
                //Pass
                System.out.println("Apple Macbooks button takes user to the right page: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Apple Macbooks button takes user to a wrong page: TEST CASE FAILED");

        }


        @Test(priority = 4)
        private void CartTest() throws InterruptedException {
            // 11. Click on the 'Add To Cart' button on an item to add it to cart
            driver.findElement(By.xpath("/html/body/div[1]/div/section/div[3]/section/main/section[3]/section/section/section/section/ul/li[1]/div/div/div[2]/form/div[3]/button")).click();
            System.out.println("Item added to cart successfully"); //comment
            Thread.sleep(5000);


//FIFTH TEST CASE:
// Verify that the Add To Cart button adds item to the cart
            String expectedPopup = "//*[@id=\"app-content-wrapper\"]/div[1]";
            String actualPopup = String.valueOf(driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[1]")));
            if (actualPopup.contains(expectedPopup))
                //Pass
                System.out.println("Add To Cart button functions properly: TEST CASE PASSED");
            else
                //Fail
                System.out.println("Add To Cart button does not function properly: TEST CASE FAILED");

            Thread.sleep(5000);

            // 12. Open the cart to proceed to the payment section
            driver.findElement(By.xpath("//*[@id=\"nav-bar-fix\"]/div[1]/div/div/a[2]")).click();
            System.out.println("User opened cart successfully"); //comment
            Thread.sleep(5000);

            // 13. Click on the Checkout button
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[3]/section/section/aside/div[3]/div/div[2]/button")).click();
            System.out.println("User checked out successfully"); //comment
            Thread.sleep(10000);

            // 14. Select delivery address as already created in the prerequisite stage
            //A. click on 'Change' (To open up the list of recently used addresses)
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[1]/div[2]/div/button")).click();
            Thread.sleep(5000);

            //B. click on 'Add Delivery Address' (To select your preferred address)
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[1]/div/div/div[2]/div[1]/div[2]/div[1]/div/button")).click();
            System.out.println("Delivery address seen successfully"); //comment
            Thread.sleep(5000);

            //C. Click on your preferred address to select it
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[2]/div/div/div[2]/div/form/button")).click();
            System.out.println("Delivery address selected successfully"); //comment
            Thread.sleep(5000);

            //D. click on 'Use this Address' to proceed to the checkout page
            driver.findElement(By.xpath("//*[@id=\"app-content-wrapper\"]/div[2]/section/section/aside/div[3]/div/div/div/a")).click();
            System.out.println("User successfully arrived checkout page"); //comment
            Thread.sleep(5000);

//SIXTH TEST CASE:
// Verify that selected address appears on check out page
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
        private void iFrameModalTest() throws InterruptedException {
            // 15. Under 'Payment Options', click on the 'Pay Now' button
            driver.findElement(By.xpath("/html/body/div[1]/div/section/div[2]/section/main/div/form/div/div[1]/section[2]/div/div[2]/div[1]/div[1]/span/input")).click();
            System.out.println("User clicked on 'Pay Now' button"); //comment
            Thread.sleep(5000);

            // 16. Click the 'Continue to Payment' button to open the payment options modal
            driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/form/div/div[1]/section[2]/div/div[2]/div[3]/div[2]/div/button")).click();
            System.out.println("User successfully clicked on 'Continue to Payment' button"); //comment
            Thread.sleep(10000);

            // 17. Open the iFrame/modal to interact with the elements in the frame
            //A. Set your variables into various strings
            String iFrame = "kpg-frame-component";   //Set the iFrame ID value to be = iFrame
            String cardPaymentBtn = "//*[@id=\"channel-template\"]/div[2]/div/div[2]/button";   //Set the card payment button xpath to be = cardPaymentBtn
            Duration timeout = Duration.ofSeconds(50);   //Assign driver sleep to the string 'timeout'

            //B. Give the iFrame modal time to fully load
            WebDriverWait wait = new WebDriverWait(driver, timeout);

            //C. Locate the i-Frame modal
            WebElement modalIFrame = driver.findElement(By.id(iFrame));
            System.out.println("i-Frame modal found"); //comment

            //D. Switch to the i-Frame modal to allow ChromeDriver interact with the elements in the frame
            driver.switchTo().frame(modalIFrame);
            System.out.println("Driver switched to the iFrame modal successfully"); //comment

//SEVENTH TEST CASE:
// Verify that the 'Continue to Payment' button opens a modal screen requesting user to choose a payment method
            String expectedInfo = "SELECT PAYMENT METHOD";
            String actualInfo = driver.findElement(By.xpath("//*[@id=\"channel-template\"]/div[1]/span")).getText();
            System.out.println(actualInfo);
            if (actualInfo.contains(expectedInfo))
                //Pass
                System.out.println("SELECT PAYMENT METHOD: Bank Transfer or Card: TEST CASE PASSED");
            else
                //Fail
                System.out.println("No popup screen: TEST CASE FAILED");

            Thread.sleep(2000);

            // 18. Click on the 'Card' button in the modal
            //A. Find the card payment button and assign it to the string 'CardOption'
            WebElement CardOption  = driver.findElement(By.xpath(cardPaymentBtn));

            //B. click on the card button
            CardOption.click();
            System.out.println("User clicked on card button"); //comment
            Thread.sleep(5000);

//EIGHTH TEST CASE:
// Verify that clicking on the 'Card' button brings a popup screen requesting user to input card details
            String expectedScreen = "/html/body/section/section/section/div[2]/div[3]/div/div/span[2]";
            String actualScreen = String.valueOf(driver.findElement(By.xpath("/html/body/section/section/section/div[2]/div[3]/div/div/span[2]")));
            if (actualScreen.contains(expectedScreen))
                //Pass
                System.out.println("ENTER CARD DETAILS: Card Number__ Date__ CVV__: TEST CASE PASSED");
            else
                //Fail
                System.out.println("No popup screen: TEST CASE FAILED");

        }

        @Test (priority = 6)
        private  void NegativeCardDetails() throws InterruptedException {
        // 19. Input invalid card details in the 'ENTER CARD DETAILS' modal
        //A. Input invalid card number in the 'Card Number' field
        driver.findElement(By.id("card-number")).sendKeys("123456781");
        System.out.println("Invalid card details inputted successfully"); //comment

        //B. Input random date in the 'Date' field
        driver.findElement(By.id("expiry")).sendKeys("0323");
        System.out.println("Random date inputted successfully"); //comment

        //C. Input invalid CVV in the 'CVV' field
        driver.findElement(By.id("cvv")).sendKeys("000");
        System.out.println("User inputted an invalid CVV"); //comment

        Thread.sleep(5000);

        // 20. Click on the 'Pay Now' button to validate your card
        click(modalPayBtn);
        System.out.println("User successfully clicked on 'Pay Now' button"); //comment
        Thread.sleep(3000);

        // 21. Print out error code
        String errorCode = driver.findElement(By.id("card-number_unhappy")).getText();
        System.out.println(errorCode);

        System.out.println("Error code printed successfully"); //comment


    }
/***
            //___________________________________
            //wait for the Debit card inputs to be available before filling it with fake card details
            //we use the id = card-number to locate it
            //we use the timeout above and the WebDriver wait method
            String cardNumberId = "card-number";
            WebElement cardNoInputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(cardNumberId)));

            //once it is available, the other inputs too wil be available
            //use fake debit card no
            String fakeCardNo = "123456781";
            cardNoInputBox.sendKeys(fakeCardNo);

            //we use the id of the expiry input called expiry
            String fakeExpiryDate ="0323";
            driver.findElement(By.id("expiry")).sendKeys(fakeExpiryDate);

            //we do same to Cvv, we use the id which is cvv
            String fakeCvv = "673";
            driver.findElement(By.id("cvv")).sendKeys(fakeCvv);

            //we do same for card pin, we use the id which is card-pin-new
            String fakePin = "000";
            driver.findElement(By.id("card-pin-new")).sendKeys(fakePin);clear
            String expectedErrorMessageId = "card-number_unhappy";
            String expectedErrorMessage = "Invalid card number";
            //compare the messages expected and the actual message to see if the test passed or failed
            if(driver.findElement(By.id(expectedErrorMessageId)).getText().contains(expectedErrorMessage)){
                System.out.println("Test Passed Successfully!!");
            }else{
                System.out.println("Test Failed!!");
            }

            //------------------------------------
            click(modalCardBtn);
            System.out.println("User successfully clicked on card button");
            Thread.sleep(5000);

          */


        @Test(priority = 7)
        private void iframeTest() throws InterruptedException {
            // 20. Click on the x button to close i-Frame
           click(exitIframe);
            System.out.println("User successfully closed i-Frame"); //comment


//NINTH TEST CASE:
// Verify that closing the card details i-Frame takes user back to the order page
            if (driver.getCurrentUrl().contains("https://www.konga.com/checkout/complete-order"))
                //Pass
                System.out.println("User is successfully taken back to order page: TEST CASE PASSED");
            else
                //Fail
                System.out.println("User is not taken back to the order page: TEST CASE FAILED");

            Thread.sleep(10000);

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