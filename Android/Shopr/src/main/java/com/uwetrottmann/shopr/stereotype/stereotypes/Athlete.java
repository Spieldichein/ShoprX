package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

/**
 * Created by yannick on 29.01.15.
 *
 * The Athlete stereotype. The athlete really is a sportsman.
 */
public class Athlete extends AbstractStereotype {

    public Athlete() {
        this.stereotype = Stereotype.ATHLETE;
        buildAgeRangeMap();
        buildJobMap();
        buildMusicMap();
        buildBrandMap();
        buildAttributeMap();
    }

    private void buildAttributeMap() {
        this.attributeProbabilityMap = new HashMap<String, Integer>();
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.acryl), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.athletic), 9);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.baggy), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.blue), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.brown), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.cremeColored), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.triangle), 4);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.dark), 5);
        attributeProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.emo), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.tight), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.yellow), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.girly), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.grey), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.green), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.light), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.lightblue), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.hoody), 7);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.classic), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.curt), 7);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.leather), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.purple), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.logo), 7);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.girl), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.swatch), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.navy), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.neon), 4);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.original), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.pink), 4);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.plush), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.retro), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.romantic), 1);
        attributeProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.red), 4);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.ribbon), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.cord), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.sport), 9);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.sporty), 9);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.black), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.saying), 7);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.street), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.street2), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.stripes), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.used), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.vintage), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.white), 6);

    }

    private void buildBrandMap() {
        this.brandProbabilityMap = new HashMap<String, Integer>();
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.adidas), 9);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.allegraK), 2);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.boomBap), 5);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
                5);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
                5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.byebyekitty), 2);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.carhartt), 6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.chanel), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.converse), 7);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.cupcakecult), 2);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 7);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
                7);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.dickies), 7);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.diesel), 7);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
                2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.esprit), 6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.etnies), 7);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.fjallraven), 8);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.forever21), 4);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
                6);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
                3);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.hrlondon), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.hellbunny), 3);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.innocent), 3);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
                4);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.lacoste), 5);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
                6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.livingdeadsouls), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.louisvitton), 2);
        brandProbabilityMap
                .put(ShoprApp.getContext().getString(R.string.lrg), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.mazine), 6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.newyorker), 6);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
                9);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
                6);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
                2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.ralphlauren), 4);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.reebok), 9);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.sOliver), 6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.scotchsoda), 6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.spiral), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.superdry), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.tomtailor), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.tommyhilfiger), 5);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
                6);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.versace), 2);

    }

    private void buildMusicMap() {
        this.musicTaste = new HashMap<Music, Integer>();
        musicTaste.put(Music.ELECTRONIC, 3);
        musicTaste.put(Music.POP, 5);
        musicTaste.put(Music.ROCK, 6);
        musicTaste.put(Music.CLASSIC, 2);
        musicTaste.put(Music.JAZZ, 1);
        musicTaste.put(Music.DNB, 5);
        musicTaste.put(Music.HIP_HOP, 6);
        musicTaste.put(Music.FOLK, 3);
        musicTaste.put(Music.INDIE, 3);
    }

    private void buildJobMap() {
        this.jobs = new HashMap<Job, Integer>();
        jobs.put(Job.PUPIL, 6);
        jobs.put(Job.STUDENT, 7);
        jobs.put(Job.MANAGER, 3);
        jobs.put(Job.SALESPERSON, 5);
        jobs.put(Job.CASHIER, 3);
        jobs.put(Job.COOK, 4);
        jobs.put(Job.WAITER, 4);
        jobs.put(Job.NURSE, 3);
        jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 4);
        jobs.put(Job.CARPENTER, 7);
        jobs.put(Job.SECRETARY, 4);
        jobs.put(Job.ASSISTANT, 5);
        jobs.put(Job.LAWYER, 2);
        jobs.put(Job.PROGRAMMER, 4);
        jobs.put(Job.ATHLETE, 10);
        jobs.put(Job.RESEARCHER, 3);
        jobs.put(Job.UNEMPLOYED, 5);
        jobs.put(Job.OTHER, 0);
    }

    private void buildAgeRangeMap() {
        this.ageRange = new HashMap<AgeRange, Integer>();
        ageRange.put(AgeRange.CHILD, 1);
        ageRange.put(AgeRange.TEENAGER, 8);
        ageRange.put(AgeRange.YOUNG_ADULT, 7);
        ageRange.put(AgeRange.ADULT, 5);
        ageRange.put(AgeRange.OLDER_ADULT, 3);
        ageRange.put(AgeRange.SENIOR, 1);
    }
}
