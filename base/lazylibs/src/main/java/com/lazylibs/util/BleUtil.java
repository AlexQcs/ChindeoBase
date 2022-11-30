package com.lazylibs.util;

import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.le.ScanResult;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.callback.BleScanAndConnectCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import com.clj.fastble.exception.BleException;
import com.clj.fastble.scan.BleScanRuleConfig;

import java.util.List;

public class BleUtil {
    //设备是否开启蓝牙
    public static boolean isEnableBluetooth() {
        return BleManager.getInstance().isBlueEnable();
    }

    //设备开启蓝牙
    public static void enableBluetooth() {
        BleManager.getInstance().enableBluetooth();
    }

    //设备关闭蓝牙
    public static void disableBluetooth() {
        BleManager.getInstance().disableBluetooth();
    }

    //初始化
    //只需初始化一次，在使用库中的方法前调用，并非必须在Application中调用。

    public static void init(Application application) {
        BleManager.getInstance().init(application);

        BleManager.getInstance()
                .enableLog(true)
                .setReConnectCount(1, 5000)
                .setSplitWriteNum(20)
                .setConnectOverTime(10000)
                .setOperateTimeout(5000);

//        配置日志
//        默认打开库中的运行日志，如果不喜欢可以关闭
//
//        BleManager enableLog(boolean enable)

//        配置重连
//        设置连接时重连次数和重连间隔（毫秒），默认为0次不重连
//
//        BleManager setReConnectCount(int count, long interval)

//        配置分包发送
//        设置分包发送的时候，每一包的数据长度，默认20个字节
//
//        BleManager setSplitWriteNum(int num)

//        配置连接超时
//        设置连接超时时间（毫秒），默认10秒
//
//        BleManager setConnectOverTime(long time)

//        配置操作超时
//        设置readRssi、setMtu、write、read、notify、indicate的超时时间（毫秒），默认5秒
//
//        BleManager setConnectOverTime(long time)
    }


    //初始扫描规则
//        Tips：
//        - 在扫描设备之前，可以配置扫描规则，筛选出与程序匹配的设备
//                - 不配置的话均为默认参数
//                - 在2.1.2版本及之前，必须先配置过滤规则再扫描；在2.1.3版本之后可以无需配置，开启默认过滤规则的扫描。
    public static void initScanRule() {
        //`void initScanRule(BleScanRuleConfig scanRuleConfig)`

        BleScanRuleConfig scanRuleConfig = new BleScanRuleConfig.Builder()
//                .setServiceUuids(serviceUuids)      // 只扫描指定的服务的设备，可选
                // .setDeviceName(true, names)         // 只扫描指定广播名的设备，可选
//                 .setDeviceMac(mac)                  // 只扫描指定mac的设备，可选
                // .setAutoConnect(isAutoConnect)      // 连接时的autoConnect参数，可选，默认false
                .setScanTimeOut(10000)              // 扫描超时时间，可选，默认10秒；小于等于0表示不限制扫描时间
                .build();
        BleManager.getInstance().initScanRule(scanRuleConfig);


    }


    //    Tips:
//            - 扫描及过滤过程是在工作线程中进行，所以不会影响主线程的UI操作，最终每一个回调结果都会回到主线程。
    public static void scan(BleScanCallback callback) {

        BleManager.getInstance().scan(new BleScanCallback() {
            @Override
            public void onScanStarted(boolean success) {
                // 开始扫描（主线程）
                if (callback != null) {
                    callback.onScanStarted(success);
                }
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                // 扫描到一个符合扫描规则的BLE设备（主线程）
                if (callback != null) {
                    callback.onScanning(bleDevice);
                }
            }

            @Override
            public void onScanFinished(List<BleDevice> scanResultList) {
                // 扫描结束，列出所有扫描到的符合扫描规则的BLE设备（主线程）
                if (callback != null) {
                    callback.onScanFinished(scanResultList);
                }
            }
        });
    }

//    通过扫描到的BleDevice对象进行连接。
//    Tips:
//            - 在某些型号手机上，connectGatt必须在主线程才能有效。非常建议把连接过程放在主线程。
//            - 连接失败后重连：框架中包含连接失败后的重连机制，可以配置重连次数和时间间隔。当然也可以自行在`onConnectFail`回调方法中延时调用`connect`方法。
//            - 连接断开后重连：可以在`onDisConnected`回调方法中再次调用`connect`方法。
//            - 为保证重连成功率，建议断开后间隔一段时间之后进行重连。
//            - 某些机型上连接失败后会短暂地无法扫描到设备，可以通过设备对象或设备mac直连，而不经过扫描。

    public static BluetoothGatt connect(BleDevice bleDevice, BleGattCallback callback) {

        return BleManager.getInstance().connect(bleDevice, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                // 开始连接
                if (callback != null) {
                    callback.onStartConnect();
                }
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                // 连接失败
                if (callback != null) {
                    callback.onConnectFail(bleDevice, exception);
                }
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接成功，BleDevice即为所连接的BLE设备
                if (callback != null) {
                    callback.onConnectSuccess(bleDevice, gatt, status);
                }
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接中断，isActiveDisConnected表示是否是主动调用了断开连接方法
                if (callback != null) {
                    callback.onDisConnected(isActiveDisConnected, bleDevice, gatt, status);
                }
            }
        });
    }


    //通过已知设备Mac直接
//    Tips:
//            - 此方法可以不经过扫描，尝试直接连接周围复合该Mac的BLE设备。
//            - 在很多使用场景，我建议APP保存用户惯用设备的Mac，然后使用该方法进行连接可以大大提高连接效率。
    public static BluetoothGatt connect(String mac, BleGattCallback callback) {

        return BleManager.getInstance().connect(mac, new BleGattCallback() {
            @Override
            public void onStartConnect() {
                // 开始连接
                if (callback != null) {
                    callback.onStartConnect();
                }
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                // 连接失败
                if (callback != null) {
                    callback.onConnectFail(bleDevice, exception);
                }
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接成功，BleDevice即为所连接的BLE设备
                if (callback != null) {
                    callback.onConnectSuccess(bleDevice, gatt, status);
                }
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接中断，isActiveDisConnected表示是否是主动调用了断开连接方法
                if (callback != null) {
                    callback.onDisConnected(isActiveDisConnected, bleDevice, gatt, status);
                }
            }
        });
    }

    //    扫描到首个符合扫描规则的设备后，便停止扫描，然后连接该设备。
//    Tips:
//            - 扫描及过滤过程是在工作线程中进行，所以不会影响主线程的UI操作，但每一个回调结果都会回到主线程。 连接操作会在主线中进行。
    public static void scanAndConnect(BleScanAndConnectCallback callback) {

        BleManager.getInstance().scanAndConnect(new BleScanAndConnectCallback() {
            @Override
            public void onScanStarted(boolean success) {
                // 开始扫描（主线程）
                if (callback != null) {
                    callback.onScanStarted(success);
                }
            }

            @Override
            public void onScanning(BleDevice bleDevice) {
                if (callback != null) {
                    callback.onScanning(bleDevice);
                }
            }

            @Override
            public void onScanFinished(BleDevice scanResult) {
                // 扫描结束，结果即为扫描到的第一个符合扫描规则的BLE设备，如果为空表示未搜索到（主线程）
                if (callback != null) {
                    callback.onScanFinished(scanResult);
                }
            }

            @Override
            public void onStartConnect() {
                // 开始连接（主线程）
                if (callback != null) {
                    callback.onStartConnect();
                }
            }

            @Override
            public void onConnectFail(BleDevice bleDevice, BleException exception) {
                // 连接失败（主线程）
                if (callback != null) {
                    callback.onConnectFail(bleDevice, exception);
                }
            }

            @Override
            public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt gatt, int status) {
                // 连接成功，BleDevice即为所连接的BLE设备（主线程）
                if (callback != null) {
                    callback.onConnectSuccess(bleDevice, gatt, status);
                }
            }

            @Override
            public void onDisConnected(boolean isActiveDisConnected, BleDevice device, BluetoothGatt gatt, int status) {
                // 连接断开，isActiveDisConnected是主动断开还是被动断开（主线程）
                if (callback != null) {
                    callback.onDisConnected(isActiveDisConnected, device, gatt, status);
                }
            }
        });
    }

    //    扫描过程中，中止扫描操作
//    Tips:
//            - 调用该方法后，如果当前还处在扫描状态，会立即结束，并回调`onScanFinished`方法。
    public static void cancelScan() {
        if (BleManager.getInstance().getScanSate()== BleScanState.STATE_SCANNING)
        BleManager.getInstance().cancelScan();
    }

    //订阅通知notify

    public static void notify(
            BleDevice bleDevice,
            String uuid_service,
            String uuid_characteristic_notify,
            BleNotifyCallback callback) {
//        `void notify(BleDevice bleDevice,
//                        String uuid_service,
//                        String uuid_notify,
//                        BleNotifyCallback callback)`
//        `void notify(BleDevice bleDevice,
//                        String uuid_service,
//                        String uuid_notify,
//                boolean useCharacteristicDescriptor,
//                BleNotifyCallback callback)`


        BleManager.getInstance().notify(
                bleDevice,
                uuid_service,
                uuid_characteristic_notify,
                new BleNotifyCallback() {
                    @Override
                    public void onNotifySuccess() {
                        // 打开通知操作成功
                        if (callback!=null){
                            callback.onNotifySuccess();
                        }
                    }

                    @Override
                    public void onNotifyFailure(BleException exception) {
                        // 打开通知操作失败
                        if (callback!=null){
                            callback.onNotifyFailure(exception);
                        }
                    }

                    @Override
                    public void onCharacteristicChanged(byte[] data) {
                        // 打开通知后，设备发过来的数据将在这里出现
                        if (callback!=null){
                            callback.onCharacteristicChanged(data);
                        }
                    }
                });
    }

    //    取消订阅通知notify，并移除数据接收的回调监听
    public static boolean stopNotify(BleDevice bleDevice,
                                     String uuid_service,
                                     String uuid_characteristic_notify) {
//            boolean stopNotify (BleDevice bleDevice,
//                String uuid_service,
//                String uuid_notify,
//        boolean useCharacteristicDescriptor)

        return BleManager.getInstance().stopNotify(bleDevice, uuid_service, uuid_characteristic_notify);
    }

    //订阅通知indicate
    public static void indicate(BleDevice bleDevice,
                                String uuid_service,
                                String uuid_characteristic_indicate,
                                BleIndicateCallback callback) {


//`void indicate(BleDevice bleDevice,
//                String uuid_service,
//                String uuid_indicate,
//                BleIndicateCallback callback)`
//`void indicate(BleDevice bleDevice,
//                String uuid_service,
//                String uuid_indicate,
//        boolean useCharacteristicDescriptor,
//        BleIndicateCallback callback)`

        BleManager.getInstance().indicate(
                bleDevice,
                uuid_service,
                uuid_characteristic_indicate,
                new BleIndicateCallback() {
                    @Override
                    public void onIndicateSuccess() {
                        // 打开通知操作成功
                        if (callback!=null){
                            callback.onIndicateSuccess();
                        }
                    }

                    @Override
                    public void onIndicateFailure(BleException exception) {
                        // 打开通知操作失败
                        if (callback!=null){
                            callback.onIndicateFailure(exception);
                        }
                    }

                    @Override
                    public void onCharacteristicChanged(byte[] data) {
                        // 打开通知后，设备发过来的数据将在这里出现
                        if (callback!=null){
                            callback.onCharacteristicChanged(data);
                        }
                    }
                });

    }
//    取消订阅通知indicate，并移除数据接收的回调监听

    public static void stopIndicate(BleDevice bleDevice,
                                    String uuid_service,
                                    String uuid_characteristic_indicate) {
//`boolean stopIndicate(BleDevice bleDevice,
//                String uuid_service,
//                String uuid_indicate)`
//`boolean stopIndicate(BleDevice bleDevice,
//                String uuid_service,
//                String uuid_indicate,
//        boolean useCharacteristicDescriptor)`

        BleManager.getInstance().stopIndicate(bleDevice, uuid_service, uuid_characteristic_indicate);
    }

    //    写
//    Tips:
//            - 在没有扩大MTU及扩大MTU无效的情况下，当遇到超过20字节的长数据需要发送的时候，需要进行分包。参数`boolean split`表示是否使用分包发送；无`boolean split`参数的`write`方法默认对超过20字节的数据进行分包发送。
//            - 关于`onWriteSuccess`回调方法: `current`表示当前发送第几包数据，`total`表示本次总共多少包数据，`justWrite`表示刚刚发送成功的数据包。
//            - 对于分包发送的辅助策略，可以选择发送上一包数据成功之后发送下一包，或直接发送下一包，参数`sendNextWhenLastSuccess`表示是否待收到`onWriteSuccess`之后再进行下一包的发送。默认true。
//            - 参数`intervalBetweenTwoPackage`表示延时多长时间发送下一包，单位ms，默认0。
    public static void write(BleDevice bleDevice,
                             String uuid_service,
                             String uuid_characteristic_write,
                             byte[] data,
                             BleWriteCallback callback) {
//            `void write(BleDevice bleDevice,
//                        String uuid_service,
//                        String uuid_write,
//                        byte[] data,
//                        boolean split,
//                        BleWriteCallback callback)`
//            `void write(BleDevice bleDevice,
//                        String uuid_service,
//                        String uuid_write,
//                        byte[] data,
//                        boolean split,
//                        boolean sendNextWhenLastSuccess,
//                        long intervalBetweenTwoPackage,
//                        BleWriteCallback callback)`

        BleManager.getInstance().write(
                bleDevice,
                uuid_service,
                uuid_characteristic_write,
                data,
                new BleWriteCallback() {
                    @Override
                    public void onWriteSuccess(int current, int total, byte[] justWrite) {
                        // 发送数据到设备成功
                    }

                    @Override
                    public void onWriteFailure(BleException exception) {
                        // 发送数据到设备失败
                    }
                });
    }


    //    读
    public static void read(BleDevice bleDevice,
                            String uuid_service,
                            String uuid_characteristic_read,
                            BleReadCallback callback) {

        BleManager.getInstance().read(
                bleDevice,
                uuid_service,
                uuid_characteristic_read,
                new BleReadCallback() {
                    @Override
                    public void onReadSuccess(byte[] data) {
                        // 读特征值数据成功
                        if (callback!=null){
                            callback.onReadSuccess(data);
                        }
                    }

                    @Override
                    public void onReadFailure(BleException exception) {
                        // 读特征值数据失败
                        if (callback!=null){
                            callback.onReadFailure(exception);
                        }
                    }
                });
    }

    //    获取设备的信号强度Rssi
//    Tips：
//
//    获取设备的信号强度，需要在设备连接之后进行。
//    某些设备可能无法读取Rssi，不会回调onRssiSuccess(),而会因为超时而回调onRssiFailure()。

    public static void readRssi(BleDevice bleDevice, BleRssiCallback callback) {

        BleManager.getInstance().readRssi(
                bleDevice,
                new BleRssiCallback() {

                    @Override
                    public void onRssiFailure(BleException exception) {
                        // 读取设备的信号强度失败
                    }

                    @Override
                    public void onRssiSuccess(int rssi) {
                        // 读取设备的信号强度成功
                    }
                });
    }

    //设置最大传输单元MTU
//    Tips：
//
//    设置MTU，需要在设备连接之后进行操作。
//    默认每一个BLE设备都必须支持的MTU为23。
//    MTU为23，表示最多可以发送20个字节的数据。
//    在Android 低版本(API-17 到 API-20)上，没有这个限制。所以只有在API21以上的设备，才会有拓展MTU这个需求。
//    该方法的参数mtu，最小设置为23，最大设置为512。
//    并不是每台设备都支持拓展MTU，需要通讯双方都支持才行，也就是说，需要设备硬件也支持拓展MTU该方法才会起效果。调用该方法后，可以通过onMtuChanged(int mtu)查看最终设置完后，设备的最大传输单元被拓展到多少。如果设备不支持，可能无论设置多少，最终的mtu还是23。

    public static void setMtu(BleDevice bleDevice, int mtu, BleMtuChangedCallback callback) {

        BleManager.getInstance().

                setMtu(bleDevice, mtu, new BleMtuChangedCallback() {
                    @Override
                    public void onSetMTUFailure(BleException exception) {
                        // 设置MTU失败
                    }

                    @Override
                    public void onMtuChanged(int mtu) {
                        // 设置MTU成功，并获得当前设备传输支持的MTU值
                    }
                });
    }

    //设置连接的优先级
//    Tips:
//    设置连接的优先级，一般用于高速传输大量数据的时候可以进行设置。 Must be one of{@link BluetoothGatt#CONNECTION_PRIORITY_BALANCED}, {@link BluetoothGatt#CONNECTION_PRIORITY_HIGH} or {@link BluetoothGatt#CONNECTION_PRIORITY_LOW_POWER}.
    public static boolean requestConnectionPriority(BleDevice bleDevice, int connectionPriority) {

        return BleManager.getInstance().requestConnectionPriority(bleDevice, connectionPriority);
    }

    //断开某个设备
    public static void disconnect(BleDevice bleDevice) {

        BleManager.getInstance().disconnect(bleDevice);
    }

    //断开所有设备
    public static void disconnectAllDevice() {

        BleManager.getInstance().disconnectAllDevice();
    }

    //退出使用，清理资源
    public static void destroy() {

        BleManager.getInstance().destroy();
    }


    //BLE设备对象，作为本框架中的扫描、连接、操作的最小单元对象。
//        `String getName()` 蓝牙广播名
//
//        `String getMac()` 蓝牙Mac地址
//
//        `byte[] getScanRecord()` 广播数据
//
//        `int getRssi()` 初始信号强度

    //自行构建BleDevice
    public static BleDevice convertBleDevice(BluetoothDevice bluetoothDevice) {

        //通过BluetoothDevice对象构建
        return BleManager.getInstance().convertBleDevice(bluetoothDevice);
    }

    //    对于BLE设备扫描，官方API上提供了很多种方法，功能丰富，包括过滤规则、后台扫描等情况。FastBle框架中默认使用的是API21以下的兼容性扫描方式，建议有其他特殊需求开发者可以根据官方提供的[其他方法](https://developer.android.com/reference/android/bluetooth/le/BluetoothLeScanner.html)自定义扫描流程。然后利用FastBle框架中的方法对扫描到的设备进行连接等后续操作。
//
//    Tips：
//            - 构建完成的BleDevice对象依然是未连接状态，如需操作，先进行连接。
    public static BleDevice convertBleDevice(ScanResult scanResult) {
        //通过ScanResult对象构建
        return BleManager.getInstance().convertBleDevice(scanResult);
    }


    //获取所有已连接设备
    public static List<BleDevice> getAllConnectedDevice() {

        return BleManager.getInstance().getAllConnectedDevice();
    }

    //获取某个已连接设备的BluetoothGatt
    public static BluetoothGatt getBluetoothGatt(BleDevice bleDevice) {
        return BleManager.getInstance().getBluetoothGatt(bleDevice);
    }
    //获取某个已连接设备的所有Service

    public static List<BluetoothGattService> getBluetoothGattServices(BleDevice bleDevice) {
        return BleManager.getInstance().getBluetoothGattServices(bleDevice);
    }

    //获取某个Service的所有Characteristic
    public static List<BluetoothGattCharacteristic> getBluetoothGattCharacteristics(BluetoothGattService service) {
        return BleManager.getInstance().getBluetoothGattCharacteristics(service);
    }

    //判断某个设备是否已连接
    public static boolean isConnected(BleDevice bleDevice) {

        return BleManager.getInstance().isConnected(bleDevice);
    }

    public static boolean isConnected(String mac) {

        return BleManager.getInstance().isConnected(mac);
    }

    //判断某个设备的当前连接状态
    public static int getConnectState(BleDevice bleDevice) {

        return BleManager.getInstance().getConnectState(bleDevice);
    }


}
