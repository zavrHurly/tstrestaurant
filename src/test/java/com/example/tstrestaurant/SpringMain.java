package com.example.tstrestaurant;

import com.example.tstrestaurant.controllers.dishes.AdminDishController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
public class SpringMain {

    @Autowired
    private AdminDishController adc;

    @Autowired
    private MockMvc mvc;

    @Test
    public void test() throws Exception {
        assertThat(adc).isNull();
    }



}
