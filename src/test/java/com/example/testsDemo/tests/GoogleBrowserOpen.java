package com.example.testsDemo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleBrowserOpen {
	public static void main(String[] args) throws InterruptedException{

		System.out.println("Execution after setting ChromeDriver path in System setProperty method");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Virge\\Desktop\\Woman go Tech\\Dishaga kohtumised\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://google.com");
		Thread.sleep(5000);
		driver.quit();
		System.out.println("Execution complete");

	}
}
