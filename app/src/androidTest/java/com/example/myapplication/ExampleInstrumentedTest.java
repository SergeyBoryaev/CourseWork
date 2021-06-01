 package com.example.myapplication;

import android.content.Context;
import android.os.RemoteException;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private UiDevice device;

    @Before
    public void setUp() throws Exception {
        device = UiDevice.getInstance(getInstrumentation());
        try {
            device.pressRecentApps();
            UiObject app = new UiObject(new UiSelector().resourceId("com.google.android.apps.nexuslauncher:id/snapshot"));
            app.swipeUp(30);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        device.pressHome();
        device.wait(Until.hasObject(By.text("The Weather Channel")), 8000);
        UiObject2 weatherApp = device.findObject(By.text("The Weather Channel"));
        weatherApp.click();
    }

    @Test
    public void settingsButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/more_icon")), 8000);
        UiObject2 settingsButton = device.findObject(By.res("com.weather.Weather:id/more_icon"));
        settingsButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/item_settings")), 8000);
        UiObject settings2Button = new UiObject(new UiSelector().text("Settings"));
        assertTrue("Cannot find settings button.", settings2Button.exists());
    }

    @Test
    public void settingsClickButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/more_icon")), 8000);
        UiObject2 settingsButton = device.findObject(By.res("com.weather.Weather:id/more_icon"));
        settingsButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/item_settings")), 8000);
        UiObject settings2Button = new UiObject(new UiSelector().text("Settings"));
        try {
            settings2Button.clickAndWaitForNewWindow(2000);
            UiObject mainInterface = new UiObject(new UiSelector().className("android.widget.RelativeLayout"));
            assertTrue("Cannot open settings menu.", mainInterface.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void followOffButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/more_icon")), 8000);
        UiObject2 settingsButton = device.findObject(By.res("com.weather.Weather:id/more_icon"));
        settingsButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/item_settings")), 8000);
        UiObject settings2Button = new UiObject(new UiSelector().text("Settings"));
        try {
            settings2Button.clickAndWaitForNewWindow(2000);
            UiObject checkInterface = new UiObject(new UiSelector().resourceId("android:id/switch_widget"));
            boolean firstState = checkInterface.isChecked();
            checkInterface.click();
            boolean secondState = checkInterface.isChecked();
            assertTrue("Check interface correct menu.", firstState != secondState);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void followOffButtonWork() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/more_icon")), 8000);
        UiObject2 settingsButton = device.findObject(By.res("com.weather.Weather:id/more_icon"));
        settingsButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/item_settings")), 8000);
        UiObject settings2Button = new UiObject(new UiSelector().text("Settings"));
        try {
            settings2Button.clickAndWaitForNewWindow(2000);
            UiObject checkInterface = new UiObject(new UiSelector().resourceId("android:id/switch_widget"));
            if (checkInterface.isChecked())
                checkInterface.click();
            device.pressBack();
            device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
            UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
            searchButton.click();
            device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_list")), 8000);
            UiObject currentLocation = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/search_list").className("androidx.recyclerview.widget.RecyclerView"));
            currentLocation.click();
            UiObject button = new UiObject(new UiSelector().text("NO THANKS"));
            assertTrue("Check follow location off.", button.exists());
            button.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
        UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
        searchButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_text")), 8000);
        UiObject searchMenu = new UiObject(new UiSelector().text("Search City or Zip"));
        assertTrue("Cannot find search menu.", searchMenu.exists());
    }

    @Test
    public void searchLA() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
        UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
        searchButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_text")), 8000);
        UiObject searchMenu = new UiObject(new UiSelector().text("Search City or Zip"));
        try {
            searchMenu.setText("LA");
            device.wait(Until.hasObject(By.text("Los Angeles")), 8000);
            UiObject check = new UiObject(new UiSelector().text("Los Angeles"));
            assertTrue("Cannot find Los Angeles.", check.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void badSearch() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
        UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
        searchButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_text")), 8000);
        UiObject searchMenu = new UiObject(new UiSelector().text("Search City or Zip"));
        try {
            searchMenu.setText("asdasdf");
            device.wait(Until.hasObject(By.text("Cannot Find Location")), 8000);
            UiObject check = new UiObject(new UiSelector().text("Cannot Find Location"));
            assertTrue("Search 'asdasdf' has any results.", check.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openLAInterface() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
        UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
        searchButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_text")), 8000);
        UiObject searchMenu = new UiObject(new UiSelector().text("Search City or Zip"));
        try {
            searchMenu.setText("LA");
            device.wait(Until.hasObject(By.text("Los Angeles")), 8000);
            UiObject LA = new UiObject(new UiSelector().text("Los Angeles"));
            LA.click();
            device.wait(Until.hasObject(By.text("Los Angeles, CA")), 8000);
            UiObject check = new UiObject(new UiSelector().text("Los Angeles, CA"));
            assertTrue("Cannot open LA menu.", check.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveLAInterface() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
        try {
            UiObject NN = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/txt_location_name"));
            if (NN.getText().equals("Los Angeles, CA")) { // open NN menu
                UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
                searchButton.click();
                device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_text")), 8000);
                UiObject searchMenu = new UiObject(new UiSelector().text("Search City or Zip"));
                searchMenu.setText("Niz");
                device.wait(Until.hasObject(By.text("Nizhny Novgorod")), 8000);
                UiObject NN2 = new UiObject(new UiSelector().text("Nizhny Novgorod"));
                NN2.click();
            }
            // open LA menu
            UiObject2 searchButton = device.findObject(By.res("com.weather.Weather:id/search_icon"));
            searchButton.click();
            device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_text")), 8000);
            UiObject searchMenu = new UiObject(new UiSelector().text("Search City or Zip"));
            searchMenu.setText("LA");
            device.wait(Until.hasObject(By.text("Los Angeles")), 8000);
            UiObject LA = new UiObject(new UiSelector().text("Los Angeles"));
            LA.click();
            device.wait(Until.hasObject(By.text("Los Angeles, CA")), 8000);
            // close app
            device.pressRecentApps();
            UiObject app = new UiObject(new UiSelector().resourceId("com.google.android.apps.nexuslauncher:id/snapshot"));
            app.swipeUp(30);
            // open app
            device.pressHome();
            device.wait(Until.hasObject(By.text("The Weather Channel")), 8000);
            UiObject2 weatherApp = device.findObject(By.text("The Weather Channel"));
            weatherApp.click();
            //check if LA
            device.wait(Until.hasObject(By.res("com.weather.Weather:id/search_icon")), 8000);
            UiObject check = new UiObject(new UiSelector().text("Los Angeles, CA"));
            assertTrue("Cannot save Los Angeles interface after closing app.", check.exists());
        } catch (UiObjectNotFoundException | RemoteException e) {
            e.printStackTrace();
        }
    }


    public void notificationButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/notifications_icon")), 8000);
        UiObject2 notificationButton = device.findObject(By.res("com.weather.Weather:id/notifications_icon"));
        notificationButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/toolbar_title")), 8000);
        UiObject notificationMenu = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/my_alert_settings_tablayout"));
        assertTrue("Cannot open notification menu.", notificationMenu.exists());
    }

    @Test
    public void manageButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/notifications_icon")), 8000);
        UiObject2 notificationButton = device.findObject(By.res("com.weather.Weather:id/notifications_icon"));
        notificationButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/toolbar_title")), 8000);
        try {
            UiObject manageButton = new UiObject(new UiSelector().text("MANAGE"));
            manageButton.click();
            device.wait(Until.hasObject(By.text("Flu Risk")), 8000);
            UiObject check = new UiObject(new UiSelector().text("Flu Risk"));
            assertTrue("Cannot open manage menu.", check.exists());
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fluRiskButton() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/notifications_icon")), 8000);
        UiObject2 notificationButton = device.findObject(By.res("com.weather.Weather:id/notifications_icon"));
        notificationButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/toolbar_title")), 8000);
        try {
            UiObject manageButton = new UiObject(new UiSelector().text("MANAGE"));
            manageButton.click();
            device.wait(Until.hasObject(By.text("Flu Risk")), 8000);
            UiObject fluRisk = new UiObject(new UiSelector().text("Flu Risk"));
            fluRisk.click();
            device.wait(Until.hasObject(By.text("Off OFF")), 8000);
            UiObject off = new UiObject(new UiSelector().text("Off OFF"));
            off.click();
            device.wait(Until.hasObject(By.text("NO THANKS")), 8000);
            UiObject check = new UiObject(new UiSelector().text("NO THANKS"));
            assertTrue("Cannot open Flu Risk button.", check.exists());
            check.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hourlyButton() {
        device.wait(Until.hasObject(By.text("Hourly")), 8000);
        UiObject2 hourlyButton = device.findObject(By.text("Hourly"));
        hourlyButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/hourly_container")), 8000);
        UiObject hourlyMenu = new UiObject(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
        assertTrue("Cannot open hourly menu.", hourlyMenu.exists());
    }

    @Test
    public void anotherHourlyButton() {
        device.wait(Until.hasObject(By.text("Hourly")), 8000);
        UiObject2 hourlyButton = device.findObject(By.text("See Details"));
        hourlyButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/hourly_container")), 8000);
        UiObject hourlyMenu = new UiObject(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
        assertTrue("Cannot open hourly menu.", hourlyMenu.exists());
    }

    @Test
    public void extendHourlyButton() {
        device.wait(Until.hasObject(By.text("Hourly")), 8000);
        UiObject2 hourlyButton = device.findObject(By.text("Hourly"));
        hourlyButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/hourly_recycler_view")), 8000);
        UiObject mainInterface = new UiObject(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
        try {
            UiObject button = mainInterface.getChild(new UiSelector().index(1))
                    .getChild(new UiSelector().index(0))
                    .getChild(new UiSelector().index(0));
            button.click();
            UiObject checkInterface = mainInterface.getChild(new UiSelector().index(1)).getChild(new UiSelector().index(0));
            int test = checkInterface.getChildCount();
            assertTrue("Cannot close block hourly button.", checkInterface.getChildCount() == 2);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extendHourlyTwiceButton() {
        device.wait(Until.hasObject(By.text("Hourly")), 8000);
        UiObject2 hourlyButton = device.findObject(By.text("Hourly"));
        hourlyButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/hourly_recycler_view")), 8000);
        UiObject mainInterface = new UiObject(new UiSelector().className("androidx.recyclerview.widget.RecyclerView"));
        try {
            UiObject button = mainInterface.getChild(new UiSelector().index(1))
                    .getChild(new UiSelector().index(0))
                    .getChild(new UiSelector().index(0));
            button.click();
            UiObject checkInterface = mainInterface.getChild(new UiSelector().index(1)).getChild(new UiSelector().index(0));
            button.click();
            assertTrue("Cannot open block, after closing hourly button.", checkInterface.getChildCount() == 3);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dailyButton() {
        device.wait(Until.hasObject(By.text("Daily")), 8000);
        UiObject2 dailyButton = device.findObject(By.text("Daily"));
        dailyButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/day_part_container")), 8000);
        UiObject dailyMenu = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/day_part_container"));
        assertTrue("Cannot open daily menu.", dailyMenu.exists());
    }

    @Test
    public void radarButton() {
        device.wait(Until.hasObject(By.text("Radar")), 8000);
        UiObject2 radarButton = device.findObject(By.text("Radar"));
        radarButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/maps_main_layout")), 8000);
        UiObject radarMenu = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/maps_main_layout"));
        assertTrue("Cannot open radar button.", radarMenu.exists());
    }

    @Test
    public void videosButton() {
        device.wait(Until.hasObject(By.text("Videos")), 8000);
        UiObject2 videosButton = device.findObject(By.text("Videos"));
        videosButton.click();
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/video_fragment_container")), 8000);
        UiObject videosMenu = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/video_fragment_container"));
        assertTrue("Cannot open videos menu.", videosMenu.exists());
    }

    @Test
    public void randomClick() {
        device.wait(Until.hasObject(By.res("com.weather.Weather:id/current_conditions_temperature_feels_like")), 8000);
        UiObject conditionsButton = new UiObject(new UiSelector().resourceId("com.weather.Weather:id/current_conditions_temperature_feels_like"));
        try {
            assertFalse("Screen has been updated after random click.", conditionsButton.clickAndWaitForNewWindow());

        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rotationRight() {
        device.wait(Until.hasObject(By.text("Videos")), 8000);
        try {
            device.setOrientationRight();
            assertFalse("Screen has been updated after rotation on right side.", device.waitForWindowUpdate("com.weather.Weather", 10));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rotationLeft() {
        device.wait(Until.hasObject(By.text("Videos")), 8000);
        try {
            device.setOrientationLeft();
            assertFalse("Screen has been updated after rotation on left side.", device.waitForWindowUpdate("com.weather.Weather", 10));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
