package com.example.mqtt;

import com.example.entity.Facility;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Arrays;
import java.util.Map;

@Slf4j
public class Callback implements MqttCallback {

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * MQTT 断开连接会执行此方法
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("断开了MQTT连接 ：{}", throwable.getMessage());
        log.error(throwable.getMessage(), throwable);
    }

    /**
     * publish发布成功后会执行到这里
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqtt) {
        log.info("发布消息成功");
    }

    /**
     * subscribe订阅后得到的消息会执行到这里
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //  TODO    此处可以将订阅得到的消息进行业务处理、数据存储
        log.info("收到来自 " + topic + " 的消息：{}", new String(message.getPayload()));

        //解析json，获取设备信息
        Facility facility = objectMapper.readValue(message.getPayload(), Facility.class);

        //判断设备o不ok
        String errorMessage = "存在设备信息异常，请注意查看";
        if (!facility.isOK()){
            System.out.println(errorMessage);
        }
    }
}