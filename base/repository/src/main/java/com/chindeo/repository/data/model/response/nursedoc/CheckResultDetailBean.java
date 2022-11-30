package com.chindeo.repository.data.model.response.nursedoc;


import java.util.List;
//检验结果详情
public class CheckResultDetailBean {
    private CheckDetailSpecimenBean specimen;
    private List<CheckDetailSpecimenResultBean> specimenResult;


    public CheckDetailSpecimenBean getSpecimen() {
        return specimen;
    }

    public void setSpecimen(CheckDetailSpecimenBean specimen) {
        this.specimen = specimen;
    }

    public List<CheckDetailSpecimenResultBean> getSpecimenResult() {
        return specimenResult;
    }

    public void setSpecimenResult(List<CheckDetailSpecimenResultBean> specimenResult) {
        this.specimenResult = specimenResult;
    }
}
