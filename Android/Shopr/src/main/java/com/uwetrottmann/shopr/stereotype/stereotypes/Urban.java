package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Label;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

/**
 * Also known as the style of the street and thereby referred to as street fashion.
 * Fashion style that changes and represents the feel of the moment and time. It was born
 * out of hip hop roots in the 1990s and is generally accessible to the common person. Typical
 * are denim details, jeans, simple t-shirts and sneakers. The style often contradicts big brands
 * with inexpensive items. Fairly closely related at the moment to the indie/hipster style.
 */
public class Urban extends AbstractStereotype {

    public Urban() {
        this.stereotype = Stereotype.URBAN;

        buildAgeMap();

        buildJobMap();

        buildMusicMap();

        buildBrandMap();

        buildAttributeMap();
    }

    private void buildBrandMap() {
        this.brandProbabilityMap = new HashMap<Label.Value, Integer>();
        brandProbabilityMap.put(Label.Value.ADIDAS, 7);
        brandProbabilityMap.put(Label.Value.ALLEGRA_K, 2);
        brandProbabilityMap.put(Label.Value.BOOM_BAP, 8);
        brandProbabilityMap.put(Label.Value.HUGO_BOSS, 2);
        brandProbabilityMap.put(Label.Value.BRAX, 4);
        brandProbabilityMap.put(Label.Value.BYE_BYE_KITTY, 2);
        brandProbabilityMap.put(Label.Value.CARHARTT, 8);
        brandProbabilityMap.put(Label.Value.CHANEL, 2);
        brandProbabilityMap.put(Label.Value.CONVERSE, 8);
        brandProbabilityMap.put(Label.Value.CUPCAKECULT, 2);
        brandProbabilityMap.put(Label.Value.DC, 8);
        brandProbabilityMap.put(Label.Value.DENIM, 8);
        brandProbabilityMap.put(Label.Value.DICKIES, 9);
        brandProbabilityMap.put(Label.Value.DIESEL, 6);
        brandProbabilityMap.put(Label.Value.C_DIOR, 2);
        brandProbabilityMap.put(Label.Value.ESPRIT, 7);
        brandProbabilityMap.put(Label.Value.ETNIES, 8);
        brandProbabilityMap.put(Label.Value.FJÄLLRÄVEN, 5);
        brandProbabilityMap.put(Label.Value.FOREVER_21, 6);
        brandProbabilityMap.put(Label.Value.G_STAR, 7);
        brandProbabilityMap.put(Label.Value.GUCCI, 2);
        brandProbabilityMap.put(Label.Value.H_N_M, 6);
        brandProbabilityMap.put(Label.Value.HRLONDON, 2);
        brandProbabilityMap.put(Label.Value.HELLBUNNY, 2);
        brandProbabilityMap.put(Label.Value.INNOCENT, 1);
        brandProbabilityMap.put(Label.Value.J_CREW, 4);
        brandProbabilityMap.put(Label.Value.LACOSTE, 5);
        brandProbabilityMap.put(Label.Value.LEVI_S, 7);
        brandProbabilityMap.put(Label.Value.LIVINGDEADSOULS, 2);
        brandProbabilityMap.put(Label.Value.LOUIS_VUITTON, 2);
        brandProbabilityMap.put(Label.Value.LRG, 9);
        brandProbabilityMap.put(Label.Value.MAZINE, 9);
        brandProbabilityMap.put(Label.Value.NEW_YORKER, 7);
        brandProbabilityMap.put(Label.Value.NIKE, 7);
        brandProbabilityMap.put(Label.Value.PEPE, 7);
        brandProbabilityMap.put(Label.Value.PRADA, 2);
        brandProbabilityMap.put(Label.Value.RALPH_LAUREN, 2);
        brandProbabilityMap.put(Label.Value.REEBOK, 7);
        brandProbabilityMap.put(Label.Value.S_OLIVER, 6);
        brandProbabilityMap.put(Label.Value.SCOTCH_N_SODA, 7);
        brandProbabilityMap.put(Label.Value.SPIRAL, 2);
        brandProbabilityMap.put(Label.Value.SUPERDRY, 5);
        brandProbabilityMap.put(Label.Value.SUPERTRASH, 4);
        brandProbabilityMap.put(Label.Value.SWEET_PANTS, 6);
        brandProbabilityMap.put(Label.Value.SWING, 2);
        brandProbabilityMap.put(Label.Value.TEDDY_SMITH, 8);
        brandProbabilityMap.put(Label.Value.TIGER_OF_SWEDEN, 3);
        brandProbabilityMap.put(Label.Value.TOM_TAILOR, 5);
        brandProbabilityMap.put(Label.Value.TOMMY_HILFIGER, 5);
        brandProbabilityMap.put(Label.Value.TWINTIP, 6);
        brandProbabilityMap.put(Label.Value.URBAN_CLASSICS, 7);
        brandProbabilityMap.put(Label.Value.VANS, 8);
        brandProbabilityMap.put(Label.Value.VERO_MODA, 4);
        brandProbabilityMap.put(Label.Value.VERSACE, 2);
        brandProbabilityMap.put(Label.Value.VILA, 5);
        brandProbabilityMap.put(Label.Value.VOSSEN, 5);
        brandProbabilityMap.put(Label.Value.WRANGLER, 4);
        brandProbabilityMap.put(Label.Value.YOUR_TURN, 7);
        brandProbabilityMap.put(Label.Value.ZALANDO, 4);
    }

    private void buildAttributeMap() {
        this.attributeProbabilityMap = new HashMap<String, Integer>();
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.acryl), 2);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.athletic), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.baggy), 7);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.blue), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.brown), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.cremeColored), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.triangle), 4);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.dark), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.emo), 1);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.tight), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.yellow), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.girly), 2);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.grey), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.green), 4);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.light), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.lightblue), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.hoody), 8);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.classic), 2);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.curt), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.leather), 1);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.lilac), 4);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.logo), 7);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.girl), 3);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.swatch), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.navy), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.neon), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.original), 7);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.pink), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.plush), 1);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.retro), 7);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.romantic), 2);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.red), 4);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.ribbon), 2);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.cord), 1);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.sport), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.sporty), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.black), 6);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.saying), 7);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.street), 9);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.stripes), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.used), 8);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.vintage), 8);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.white), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.gold), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.silver), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.petrol), 4);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.olive), 5);

    }

    private void buildMusicMap() {
        this.musicTaste = new HashMap<Music, Integer>();
        musicTaste.put(Music.ELECTRONIC, 6);
        musicTaste.put(Music.POP, 7);
        musicTaste.put(Music.ROCK, 5);
        musicTaste.put(Music.CLASSIC, 2);
        musicTaste.put(Music.JAZZ, 1);
        musicTaste.put(Music.DNB, 6);
        musicTaste.put(Music.HIP_HOP, 8);
        musicTaste.put(Music.FOLK, 4);
        musicTaste.put(Music.INDIE, 5);
    }

    private void buildJobMap() {
        this.jobs = new HashMap<Job, Integer>();
        jobs.put(Job.PUPIL, 5);
        jobs.put(Job.STUDENT, 7);
        jobs.put(Job.MANAGER, 2);
        jobs.put(Job.SALESPERSON, 5);
        jobs.put(Job.CASHIER, 5);
        jobs.put(Job.COOK, 4);
        jobs.put(Job.WAITER, 6);
        jobs.put(Job.NURSE, 6);
        jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 5);
        jobs.put(Job.CARPENTER, 4);
        jobs.put(Job.SECRETARY, 3);
        jobs.put(Job.ASSISTANT, 3);
        jobs.put(Job.LAWYER, 2);
        jobs.put(Job.PROGRAMMER, 6);
        jobs.put(Job.ATHLETE, 5);
        jobs.put(Job.RESEARCHER, 4);
        jobs.put(Job.UNEMPLOYED, 7);
        jobs.put(Job.OTHER, 0);
    }

    private void buildAgeMap() {
        this.ageRange = new HashMap<AgeRange, Integer>();
        ageRange.put(AgeRange.CHILD, 1);
        ageRange.put(AgeRange.TEENAGER, 7);
        ageRange.put(AgeRange.YOUNG_ADULT, 9);
        ageRange.put(AgeRange.ADULT, 5);
        ageRange.put(AgeRange.OLDER_ADULT, 2);
        ageRange.put(AgeRange.SENIOR, 1);
    }
}
