package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IRBNetHomePageObjects {
	
WebDriver driver = null;
	
	//defining locator(s)
	By login_Username = By.name("j_username");
	By login_Password = By.name("j_password");
	By login_button = By.name("login");
	
	//Shall move this to ''IRBNet_User_LandingPage'' class
	By logout_button = By.xpath("/html/body/table/tbody/tr[1]/td/map/area[2]");
	By welcomeMsg = By.xpath("//li[contains(.,'Welcome to IRBNetFatema Moutushi R')]");
	
	
	//Constructor
	public IRBNetHomePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	//Inserting login Username
	public void setLogin_Username(String text) {
		driver.findElement(login_Username).sendKeys(text);
	}
	
	
	//Inserting login Password
	public void setLogin_Password(String text) {
		driver.findElement(login_Password).sendKeys(text);
	}
	
	
	//Clicking on 'Login' button
	public void click_LoginButton() {
		driver.findElement(login_button).click();
	}
	
	
	//Shall move this to ''IRBNet_User_LandingPage'' class
	//Clicking on 'Logout' button
	public void click_LogoutButton() {
		driver.findElement(logout_button).click();
	}
	
	public String getWelcomeMsg(){

        return    driver.findElement(welcomeMsg).getText();

       }

}
