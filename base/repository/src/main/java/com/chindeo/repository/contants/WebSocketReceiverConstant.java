package com.chindeo.repository.contants;


//以WebSocket插件的调用者为视角
public interface WebSocketReceiverConstant {
    String WEB_SOCKET_APK_PACKAGE_NAME = "com.chindeo.websocket.app";
    String WEB_SOCKET_APK_SCHEME = "websocket";

    /*------------------向调用者发去的动作---------------*/
    //告诉调用者 WebSocket的状态
    String WEB_SOCKET_ACTION_RESPONSE_STATUS = "web_socket_action_response_status";
    //告诉调用者 WebSocket的现在所连接的参数
    String WEB_SOCKET_ACTION_RESPONSE_PARAM = "web_socket_action_response_param";
    //告诉调用者 WebSocket收到的数据
    String WEB_SOCKET_ACTION_RESPONSE_MESSAGE = "web_socket_action_response_message";
    //mqtt接收数据源
    String MQTT_MESSAGE_RESOURCE = "mqtt_message_source";


    /*------------------收到调用者发来的动作---------------*/
    //WebSocket收到调用者 关闭Ui的动作
    String WEB_SOCKET_ACTION_SEND_CLOSE = "web_socket_send_close";
    //WebSocket收到调用者 查状态的动作
    String WEB_SOCKET_ACTION_SEND_STATUS = "web_socket_send_status";
    //WebSocket收到调用者 发来的数据消息，再发给服务端  带TOPIC
    String WEB_SOCKET_ACTION_SEND_DATA_TOPIC = "web_socket_send_data_topic";
    //WebSocket收到调用者 发来的数据消息，再发给服务端  带TOPIC
    String WEB_SOCKET_ACTION_SEND_DATA_TOPIC_IMS = "web_socket_send_data_topic_ims";
    //WebSocket收到调用者 发来的数据消息，再发给服务端
    String WEB_SOCKET_ACTION_SEND_DATA = "web_socket_send_data";
    //WebSocket收到调用者 设置参数的动作
    String WEB_SOCKET_ACTION_SEND_PARAM = "web_socket_send_param";
    //WebSocket收到调用者 设置连接的动作
    String WEB_SOCKET_ACTION_SEND_CONNECT = "web_socket_send_connect";
    //WebSocket收到调用者 设置断开连接的动作
    String WEB_SOCKET_ACTION_SEND_DISCONNECT = "web_socket_send_disconnect";
    //mqtt 收到调用者 订阅主题
    String MQTT_ACTION_SUBSCRIBE_TOPICS = "mqtt_action_subscribe_topics";


    //数据的key
    String WEB_SOCKET_PARAM_KEY_MESSAGE = "message";
    String WEB_SOCKET_PARAM_KEY_STATUS = "status";
    String WEB_SOCKET_PARAM_KEY_HOST = "host";
    String WEB_SOCKET_PARAM_KEY_PARAM = "param";
    //发布消息的主题、获取订阅的主题
    String WEB_SOCKET_PARAM_KEY_TOPIC = "topic";
    String WEB_SOCKET_PARAM_KEY_DATA = "data";
    //MQTT策略
    String WEB_SOCKET_PARAM_KEY_QOS = "qos";

}
