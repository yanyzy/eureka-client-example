package top.zhyee;

import org.junit.Test;

/**
 * @author zhyee
 */
public class EurekaRegisterTest {

    private EurekaRegister eurekaRegister = new EurekaRegister();

    @Test
    public void register() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        eurekaRegister.register();
        Thread.sleep(400000);
    }
}