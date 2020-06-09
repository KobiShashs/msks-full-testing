package com.shasha.msksfulltesting.web.client;

import com.shasha.msksfulltesting.domain.Webinar;
import com.shasha.msksfulltesting.web.model.WebinarDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.fail;

/**
 * Created by kobis on 10 Jun, 2020
 */
@RunWith(SpringRunner.class)
@JsonTest
public class ClientControllerTest {

    @Autowired
    private JacksonTester<String> test;

    @Test
    public void testWebinarJsonInfo() throws MalformedURLException {
        try {
            String toCheckAgainst = "OK";
            // fetch REAL webinar from DB as JSON through REST

            URL url = new URL("http://localhost:8080/api/v1/webinar/ping");
            String json = "";
            try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
                json = in.readLine();
                System.out.println("Got from server: " + json);
            } catch (Exception e) {
                fail("Failed connecting to REAL server");
            }
            // check structure & content
            test.toString().equals(toCheckAgainst);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
