package de.tum.in.schlichter.shoprx.stereotype.dto;

import java.util.Set;

import de.tum.in.schlichter.shoprx.algorithm.model.Sex;
import de.tum.in.schlichter.shoprx.stereotype.user.Job;
import de.tum.in.schlichter.shoprx.stereotype.user.Music;

/**
 * Created by Yannick on 29.01.15.
 *
 * A Form which is used to transfer data from several parts of the app to others.
 */
public class StereotypeForm {

    public StereotypeForm(int age, Sex.Value sex, Job job, Set<Music> musicTaste){
        this.age = age;
        this.sex = sex;
        this.job = job;
        this.musicTaste = musicTaste;
        this.valid = false;
    }

    private boolean valid;

    private int age;

    private Sex.Value sex;

    private Job job;

    private Set<Music> musicTaste;

    public int getAge() {
        return age;
    }

    public Sex.Value getSex() {
        return sex;
    }

    public Job getJob() {
        return job;
    }

    public Set<Music> getMusicTaste() {
        return musicTaste;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("StereotypeForm:\n");
        builder.append("age = ").append(age);
        builder.append(", sex = ").append(sex.toString());
        builder.append(", job: ").append(job.toString());
        builder.append("Music: ");
        for (Music m : musicTaste){
            builder.append(m.toString()).append(", ");
        }
        return builder.toString().substring(0, builder.toString().length() - 1).concat(".");
    }
}
