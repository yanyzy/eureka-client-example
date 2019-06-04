package top.zhyee;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.internal.util.Archaius1Utils;
import java.lang.reflect.Field;

/**
 * @author zhyee
 */
public class EurekaRegister{
    public void register() throws NoSuchFieldException, IllegalAccessException {
        //通过反射获取到该字段
        Class aClass = MyDataCenterInstanceConfig.class.getSuperclass();
        Field configInstance = aClass.getDeclaredField("configInstance");
        configInstance.setAccessible(true);

        //修改该字段的值
        MyDataCenterInstanceConfig instanceConfig = new MyDataCenterInstanceConfig();
        DynamicPropertyFactory propertyFactory = Archaius1Utils.initConfig("test");
        configInstance.set(instanceConfig, propertyFactory);

        DiscoveryManager.getInstance().initComponent(
            instanceConfig,
            new DefaultEurekaClientConfig());
        ApplicationInfoManager.getInstance().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
    }
}
