package com.uwetrottmann.shopr.stereotype.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.algorithm.model.Sex;
import com.uwetrottmann.shopr.stereotype.dto.StereotypeForm;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;
import com.uwetrottmann.shopr.stereotype.user.User;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yannick on 29.01.15.
 *
 * Is responsible for displaying the input parameters for the stereotype selection frames.
 */
public class StereotypeFormFragment extends Fragment{

    private OnStereotypeFormSubmit mCallback;

    public interface OnStereotypeFormSubmit {
        public void onSubmit(StereotypeForm stereotypeForm);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (!(getActivity() instanceof OnStereotypeFormSubmit)) {
            throw new RuntimeException(
                    "Expected calling activity of StereotypeFormFragment to implement"
                            + " interface OnStereotypeFormSubmit.");
        }
        mCallback = (OnStereotypeFormSubmit) getActivity();
        View view = inflater.inflate(R.layout.fragment_stereotype_form,
                container, false);
        Button submit = ((Button) view
                .findViewById(R.id.buttonStereotypeSubmit));

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(android.view.View v) {
                StereotypeForm form = processForm();
                if (form.isValid()) {
                    mCallback.onSubmit(form);
                }
            }

        });
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    private StereotypeForm processForm() {
        boolean valid = true;
        int age = 0;
        Job job;
        EditText ageText = (EditText) getActivity().findViewById(
                R.id.stereotypeAge);
        try {
            age = Integer.parseInt(ageText.getText().toString());
        } catch (NumberFormatException e) {
            ageText.setError("Please provide your age.");
            valid = false;
        }

        Set<Music> musicTaste = processMusicTaste();
        if (musicTaste.isEmpty()) {
            if (valid) {
                showAlertDialog();
                valid = false;
            }
        }

        Spinner jobSpinner = (Spinner) getActivity()
                .findViewById(R.id.spinner1);
        try {
            job = determineJobFromForm(jobSpinner.getSelectedItem()
                    .toString());
        } catch (IllegalArgumentException e) {
            Log.e(StereotypeForm.class.getName(),
                    "Illegal input to spinner for job in stereotypeForm: "
                            + jobSpinner.getSelectedItem().toString());
            throw e;
        }

        //Determine Sex
        Spinner sexSpinner = (Spinner) getActivity().findViewById(R.id.stereotypeSex);
        String selected = sexSpinner.getSelectedItem().toString();
        String compare = getResources().getString(R.string.male);

        Sex.Value sex;
        if (selected.equals(compare)){
            sex = Sex.Value.MALE;
        } else {
            sex = Sex.Value.FEMALE;
        }

        // Create a new user
        User.initializeUser(job, sex, musicTaste);

        StereotypeForm form = new StereotypeForm(age, sex, job, musicTaste);
        form.setValid(valid);
        return form;
    }

    /**
     * This method selects the adequate Job given the user's selection in the form.
     * @param value the selected job
     * @return the job which fits the type of the person in the backend.
     */
    private Job determineJobFromForm(String value){
        if (value.equals(getResources().getString(R.string.jobPupil)))
            return Job.PUPIL;
        else if (value.equals(getResources().getString(R.string.jobStudent)))
            return Job.STUDENT;
        else if (value.equals(getResources().getString(R.string.jobResearcher)))
            return Job.RESEARCHER;
        else if (value.equals(getResources().getString(R.string.jobManager)))
            return Job.MANAGER;
        else if (value.equals(getResources().getString(R.string.jobSalesperson)))
            return Job.SALESPERSON;
        else if (value.equals(getResources().getString(R.string.jobCashier)))
            return Job.CASHIER;
        else if (value.equals(getResources().getString(R.string.jobChef)))
            return Job.COOK;
        else if (value.equals(getResources().getString(R.string.jobWaiter)))
            return Job.WAITER;
        else if (value.equals(getResources().getString(R.string.jobNurse)))
            return Job.NURSE;
        else if (value.equals(getResources().getString(R.string.jobCustomerConsultant)))
            return Job.CUSTOMER_SERVICE_REPRESENTATIVE;
        else if (value.equals(getResources().getString(R.string.jobCraftsman)))
            return Job.CARPENTER;
        else if (value.equals(getResources().getString(R.string.jobSecretary)))
            return Job.SECRETARY;
        else if (value.equals(getResources().getString(R.string.jobAssistant)))
            return Job.ASSISTANT;
        else if (value.equals(getResources().getString(R.string.jobProgrammer)))
            return Job.PROGRAMMER;
        else if (value.equals(getResources().getString(R.string.jobAthlete)))
            return Job.ATHLETE;
        else if (value.equals(getResources().getString(R.string.jobUnemployed)))
            return Job.UNEMPLOYED;
        else if (value.equals(getResources().getString(R.string.jobLawyer)))
            return Job.LAWYER;
        else if (value.equals(getResources().getString(R.string.jobOthers)))
            return Job.OTHER;
        else
            throw new IllegalArgumentException("Unknown job: " + value + ".");

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please select at least one music taste")
                .setTitle("No Music Taste selected")
                .setPositiveButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private Set<Music> processMusicTaste() {
        Set<Music> musicTaste = new HashSet<Music>();
        CheckBox rock = (CheckBox) getActivity()
                .findViewById(R.id.checkBoxRock);
        if (rock.isChecked()) {
            musicTaste.add(Music.ROCK);
        }

        CheckBox electro = (CheckBox) getActivity().findViewById(
                R.id.checkBoxElectro);
        if (electro.isChecked()) {
            musicTaste.add(Music.ELECTRONIC);
        }

        CheckBox classic = (CheckBox) getActivity().findViewById(
                R.id.checkBoxClassic);
        if (classic.isChecked()) {
            musicTaste.add(Music.CLASSIC);
        }

        CheckBox hipHop = (CheckBox) getActivity().findViewById(
                R.id.checkBoxHipHop);
        if (hipHop.isChecked()) {
            musicTaste.add(Music.HIP_HOP);
        }

        CheckBox pop = (CheckBox) getActivity().findViewById(R.id.checkBoxPop);
        if (pop.isChecked()) {
            musicTaste.add(Music.POP);
        }

        CheckBox folk = (CheckBox) getActivity()
                .findViewById(R.id.checkBoxFolk);
        if (folk.isChecked()) {
            musicTaste.add(Music.FOLK);
        }

        CheckBox jazz = (CheckBox) getActivity()
                .findViewById(R.id.checkBoxJazz);
        if (jazz.isChecked()) {
            musicTaste.add(Music.JAZZ);
        }

        CheckBox indie = (CheckBox) getActivity().findViewById(
                R.id.checkBoxIndie);
        if (indie.isChecked()) {
            musicTaste.add(Music.INDIE);
        }

        CheckBox dnb = (CheckBox) getActivity().findViewById(R.id.checkBoxDnB);
        if (dnb.isChecked()) {
            musicTaste.add(Music.DNB);
        }
        return musicTaste;
    }
}
