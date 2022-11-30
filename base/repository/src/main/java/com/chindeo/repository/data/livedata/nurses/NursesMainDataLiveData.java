package com.chindeo.repository.data.livedata.nurses;


import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.fastjson.JSON;
import com.chindeo.repository.data.livedata.DeviceInfoLiveData;
import com.chindeo.repository.data.model.nurses.BedListBean;
import com.chindeo.repository.data.model.nurses.ListDeviceBean;
import com.chindeo.repository.data.model.nurses.MessageUnReadBean;
import com.chindeo.repository.data.model.nurses.PatientBean;
import com.chindeo.repository.data.model.params.bed.BedListParams;
import com.chindeo.repository.data.model.response.device.MqttConfigBean;
import com.chindeo.repository.data.model.response.nurses.RingtonesAccountsBean;
import com.chindeo.repository.mmkv.impl.AppModuleCache;
import com.chindeo.repository.resources.BedRepository;
import com.chindeo.repository.resources.NursesRepository;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;

public enum NursesMainDataLiveData {

    INSTANCE;

    private MutableLiveData<ListDeviceBean> liveData;
    private DisposableSingleObserver<ListDeviceBean> dataDis;

    NursesMainDataLiveData() {
    }

    public MutableLiveData<ListDeviceBean> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<ListDeviceBean>() {
                @Nullable
                @Override
                public ListDeviceBean getValue() {
                    return super.getValue() == null ? new ListDeviceBean() : super.getValue();
                }

                @Override
                public void postValue(ListDeviceBean value) {
                    super.postValue(value);
                }
            };
        }
        return liveData;
    }

    public void init() {
        if (dataDis != null) {
            return;
        }
        BedRepository repository = BedRepository.getInstance();
        dataDis = repository.getListDevice(new BedListParams(DeviceInfoLiveData.getDeviceId(), 0))
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        dataDis = null;
                    }
                })
                .compose(new ResultFlowableTransformer<>())
                .singleOrError()
                .subscribeWith(new DisposableSingleObserver<ListDeviceBean>() {
                    @Override
                    public void onSuccess(@NonNull ListDeviceBean listDeviceBean) {
                        if (listDeviceBean != null && listDeviceBean.bedList != null) {
                            for (BedListBean bedListBean : listDeviceBean.bedList) {
                                if (bedListBean.patient != null) {
                                    bedListBean.patient.bedCode = bedListBean.code;
                                }
                            }
                        }
                        getLiveData().postValue(listDeviceBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getLiveData().postValue(null);
                        e.printStackTrace();
                    }
                });
    }

    private DisposableSingleObserver<List<RingtonesAccountsBean>> ringDis;
    //获取铃声列表
    public void getBedRingtones(){
        if (ringDis != null) {
            return;
        }
        NursesRepository repository = NursesRepository.getInstance();//RepositoryComponent.INSTANCE
        ringDis = repository.getBedRingtones()
                .doFinally(() -> ringDis = null)
                .compose(new ResultFlowableTransformer<>())
                .singleOrError()
                .subscribeWith(new DisposableSingleObserver<List<RingtonesAccountsBean>>() {
                    @Override
                    public void onSuccess(@NonNull List<RingtonesAccountsBean> listDeviceBean) {
                        Log.d("TAG", "获取铃声列表-----------" + JSON.toJSONString(listDeviceBean));
                        AppModuleCache.cacheRingtones(JSON.toJSONString(listDeviceBean));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * 获取病人和空床的列表
     *
     * @return
     */
    public static List<PatientBean> getPatientAndEmptyBedList() {
        List<PatientBean> list = new ArrayList<>();
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        List<MessageUnReadBean> unReadList = MessageUnReadLiveData.list(value.loc.code);
        if (value != null) {
            for (BedListBean bedListBean : value.bedList) {
                if (bedListBean.patient != null) {
                    bedListBean.patient.messageUnRead = false;
                    for (MessageUnReadBean messageUnReadBean : unReadList) {
                        if (bedListBean.patient.admNo.equals(messageUnReadBean.admNo)) {
                            bedListBean.patient.messageUnRead = true;
                        }
                    }
                    bedListBean.patient.bedCode = bedListBean.code;
                    list.add(bedListBean.patient);
                } else {
                    list.add(PatientBean.empty(bedListBean.id, bedListBean.code));
                }
            }
        }
        return list;
    }

    /**
     * 获取病人列表
     *
     * @return
     */
    public static List<PatientBean> getPatientList() {
        List<PatientBean> list = new ArrayList<>();
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value != null) {
            List<MessageUnReadBean> unReadList = MessageUnReadLiveData.list(value.loc.code);
            for (BedListBean bedListBean : value.bedList) {
                if (bedListBean.patient != null) {
                    bedListBean.patient.messageUnRead = false;
                    for (MessageUnReadBean messageUnReadBean : unReadList) {
                        if (bedListBean.patient.admNo.equals(messageUnReadBean.admNo)) {
                            bedListBean.patient.messageUnRead = true;
                        }
                    }
                    bedListBean.patient.bedCode = bedListBean.code;
                    list.add(bedListBean.patient);
                }
            }
        }
        return list;
    }

    /**
     * 获取病人
     *
     * @return
     */
    public static PatientBean getPatient(String admNo) {
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value != null && value.bedList != null) {
            for (BedListBean bedListBean : value.bedList) {
                if (bedListBean.patient != null) {
                    if (admNo.equals(bedListBean.patient.admNo)) {
                        bedListBean.patient.bedCode = bedListBean.code;
                        return bedListBean.patient;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取病人
     *
     * @return
     */
    public static PatientBean getPatientByPhoneNum(@NotNull String phoneNum) {
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value != null && value.bedList != null) {
            for (BedListBean bedListBean : value.bedList) {
                if (bedListBean.patient != null) {
                    if (phoneNum.equals(bedListBean.deviceUser.phoneNumber)) {
                        bedListBean.patient.bedCode = bedListBean.code;
                        return bedListBean.patient;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取床信息
     *
     * @return
     */
    public static BedListBean getBedInfo(String bedCode) {
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value != null) {
            for (BedListBean bedListBean : value.bedList) {
                if (bedCode.equals(bedListBean.code)) {
                    return bedListBean;
                }
            }
        }
        return null;
    }


    /**
     * 获取病人床位信息
     *
     * @return
     */
    public static BedListBean getBedByPatient(PatientBean patientBean) {
        List<PatientBean> list = new ArrayList<>();
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value != null) {
            for (BedListBean bedListBean : value.bedList) {
                if (bedListBean.patient != null) {
                    if (patientBean.id.equals(bedListBean.patient.id)) {
                        return bedListBean;
                    }
                }
            }
        }
        return null;
    }


    public static void updateBedPatientStatus(BedListBean bean) {
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value != null) {
            for (BedListBean bedListBean : value.bedList) {
                if (bedListBean.equals(bean)) {
                    bedListBean = bean;
                    NursesMainDataLiveData.INSTANCE.getLiveData().postValue(value);
                    return;
                }
            }
        }
    }

    public static String getLocCode() {
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value.loc != null) {
            return value.loc.code;
        }
        return null;
    }

    public static String getMqttLocCode() {
        MqttConfigBean mqttConfigBean = AppModuleCache.getMqttConfigBean();
        if (mqttConfigBean != null && !TextUtils.isEmpty(mqttConfigBean.simulation)) {
            return mqttConfigBean.simulation;
        }
        ListDeviceBean value = NursesMainDataLiveData.INSTANCE.getLiveData().getValue();
        if (value.loc != null) {
            return value.loc.code;
        }
        return null;
    }
}
