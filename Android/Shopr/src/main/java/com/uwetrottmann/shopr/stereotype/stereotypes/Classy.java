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
 * The Classy Stereotype.
 */
public class Classy extends AbstractStereotype {

    public Classy() {
        this.stereotype = Stereotype.CLASSY;
        buildAgeMap();
        buildJobMap();
        buildMusicMap();
        buildBrandMap();
        buildAttributeMap();
    }

    private void buildAttributeMap() {
        this.attributeProbabilityMap = new HashMap<String, Integer>();
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.acryl), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.athletic), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.baggy), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.blue), 7);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.brown), 7);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.cremeColored), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.triangle), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.dark), 7);
        attributeProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.emo), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.tight), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.yellow), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.girly), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.grey), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.green), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.light), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.lightblue), 8);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.hoody), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.classic), 9);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.curt), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.leather), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.purple), 4);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.logo), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.girl), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.swatch), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.navy), 6);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.neon), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.original), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.pink), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.plush), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.retro), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.romantic), 3);
        attributeProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.red), 5);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.ribbon), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.cord), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.sport), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.sporty), 2);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.black), 8);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.saying), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.street), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.street2), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.stripes), 3);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.used), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.vintage), 1);
        attributeProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.white), 8);

    }

    private void buildBrandMap() {
        this.brandProbabilityMap = new HashMap<String, Integer>();
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.adidas), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.allegraK), 4);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.boomBap), 2);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
                9);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
                9);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.byebyekitty), 2);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.carhartt), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.chanel), 8);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.converse), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.cupcakecult), 1);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 1);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
                2);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.dickies), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.diesel), 4);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
                8);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.esprit), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.etnies), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.fjallraven), 3);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.forever21), 3);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
                5);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
                8);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 4);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.hrlondon), 2);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.hellbunny), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.innocent), 1);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
                9);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.lacoste), 9);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
                5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.livingdeadsouls), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.louisvitton), 9);
        brandProbabilityMap
                .put(ShoprApp.getContext().getString(R.string.lrg), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.mazine), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.newyorker), 3);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
                2);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
                5);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
                9);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.ralphlauren), 8);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.reebok), 2);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.sOliver), 6);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.scotchsoda), 4);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.spiral), 1);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.superdry), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.tomtailor), 5);
        brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.tommyhilfiger), 6);
        brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
                2);
        brandProbabilityMap.put(ShoprApp.getContext()
                .getString(R.string.versace), 9);
    }

    private void buildMusicMap() {
        this.musicTaste = new HashMap<Music, Integer>();
        musicTaste.put(Music.ELECTRONIC, 0);
        musicTaste.put(Music.POP, 2);
        musicTaste.put(Music.ROCK, 2);
        musicTaste.put(Music.CLASSIC, 8);
        musicTaste.put(Music.JAZZ, 8);
        musicTaste.put(Music.DNB, 1);
        musicTaste.put(Music.HIP_HOP, 1);
        musicTaste.put(Music.FOLK, 6);
        musicTaste.put(Music.INDIE, 2);
    }

    private void buildJobMap() {
        this.jobs = new HashMap<Job, Integer>();
        jobs.put(Job.PUPIL, 0);
        jobs.put(Job.STUDENT, 2);
        jobs.put(Job.MANAGER, 4);
        jobs.put(Job.SALESPERSON, 3);
        jobs.put(Job.CASHIER, 2);
        jobs.put(Job.COOK, 3);
        jobs.put(Job.WAITER, 4);
        jobs.put(Job.NURSE, 3);
        jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 3);
        jobs.put(Job.CARPENTER, 2);
        jobs.put(Job.SECRETARY, 6);
        jobs.put(Job.ASSISTANT, 6);
        jobs.put(Job.LAWYER, 8);
        jobs.put(Job.PROGRAMMER, 3);
        jobs.put(Job.ATHLETE, 2);
        jobs.put(Job.RESEARCHER, 7);
        jobs.put(Job.OTHER, 0);
    }

    private void buildAgeMap() {
        this.ageRange = new HashMap<AgeRange, Integer>();
        ageRange.put(AgeRange.CHILD, 0);
        ageRange.put(AgeRange.TEENAGER, 0);
        ageRange.put(AgeRange.YOUNG_ADULT, 4);
        ageRange.put(AgeRange.ADULT, 4);
        ageRange.put(AgeRange.OLDER_ADULT, 7);
        ageRange.put(AgeRange.SENIOR, 9);
    }
}
