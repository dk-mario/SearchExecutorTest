package ru.sberbank.jd.jdprofessionalsservice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.sberbank.jd.jdprofessionalsservice.controller.OrderController;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * unit-тесты.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class JDprofessionalsserviceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    OrderController orderController;

    /**
     * Bean OrderController создаётся при старте приложения
     */
    @Test
    public void someBeanLoads() {
        assertThat(orderController,is(not(nullValue())));
    }

}
