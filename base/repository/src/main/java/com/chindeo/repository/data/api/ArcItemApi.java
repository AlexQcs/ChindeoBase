package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.nurses.AudioRecordBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.arcitem.PatientImageCaseBean;
import com.chindeo.repository.data.model.response.arcitem.PatientInfo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ArcItemApi {


    @GET(ApiConstants.ARC_ITEM_PATIENT)
    Flowable<HttpResult<PatientInfo>> getPatientInfo(@Query("admId") String admId);

    @GET(ApiConstants.ARC_ITEM_PATIENT_CASE)
    Flowable<HttpResult<List<PatientImageCaseBean>>> getPatientImageCase(@Query("admId") String admId);

    @GET(ApiConstants.ARC_ITEM_PATIENT_CASE)
    Flowable<HttpResult<String>> getPatientHtmlCase(@Query("admNo") String admNo);

}
