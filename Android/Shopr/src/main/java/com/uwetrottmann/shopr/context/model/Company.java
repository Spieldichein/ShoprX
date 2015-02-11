package com.uwetrottmann.shopr.context.model;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yannick on 11.02.15.
 *
 * This class stores the current temperature within the given context scenario.
 */
public enum Company {
    ALONE(R.string.alone),
    WITH_FAMILY(R.string.withFamily),
    WITH_FRIEND(R.string.withAFriend);

    private int mCompanyType;

    private static Map<String, Company> sAvailableCompany = new HashMap<String, Company>();

    Company(int company) {
         mCompanyType = company;
    }

    public String descriptor(){
        return ShoprApp.getContext().getString(mCompanyType);
    }

    public static Company getCompany(String description){
        if (sAvailableCompany.size() == 0){
            for (Company company : Company.values()){
                sAvailableCompany.put(ShoprApp.getContext().getString(company.mCompanyType), company);
            }
        }

        return sAvailableCompany.get(description);
    }

    @Override
    public String toString(){
        return ShoprApp.getContext().getString(mCompanyType);
    }
}
