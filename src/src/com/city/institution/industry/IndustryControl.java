package com.city.institution.industry;

import com.city.IUpdateParameters;
import com.city.institution.InstitutionControl;

public class IndustryControl extends InstitutionControl {
    int occupationPercentage;
    int moneyGenerated;
    int approvalInfluence;

    public IndustryControl(){
        super();
        occupationPercentage = 100;
        moneyGenerated = 5;
        approvalInfluence = 15;
    }

    public int getParameter(char type) {
        int parameter = 0;
        if(type == 'o')
            parameter = occupationPercentage;

        return parameter;
    }

    public void limitOccupation(int percentage) {
        getCityParameters().updateApproval((int)(((double)approvalInfluence/100)*(double)(percentage-occupationPercentage)));
        occupationPercentage = percentage;
    }

    public void connect(IUpdateParameters cityParameters){
        setCityParameters(cityParameters);
    }

    public int getOccupationPercentage(){
        return occupationPercentage;
    }

    public void update(){
        getCityParameters().updateMoney((int)(moneyGenerated*((double)occupationPercentage/100)));
    }
}
