package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Label;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

/**
 * Related to the skateboarding subculture. Closely related to the urban style. Clothes
 * typically include shirts in all colors with messages, baggy or nowadays often super tight
 * pants, as well as hoodies with big logos. Representative brands are DC, Etnies, Dickies,
 * Carhartt, Mazine or Vans.
 */
public class Skater extends AbstractStereotype {

	public Skater() {
		this.stereotype = Stereotype.SKATER;

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
		brandProbabilityMap.put(Label.Value.BOOM_BAP, 7);
		brandProbabilityMap.put(Label.Value.BRAX, 1);
		brandProbabilityMap.put(Label.Value.BYE_BYE_KITTY, 2);
		brandProbabilityMap.put(Label.Value.CARHARTT, 7);
		brandProbabilityMap.put(Label.Value.CHANEL, 2);
		brandProbabilityMap.put(Label.Value.CONVERSE, 8);
		brandProbabilityMap.put(Label.Value.CUPCAKECULT, 2);
		brandProbabilityMap.put(Label.Value.DC, 9);
		brandProbabilityMap.put(Label.Value.DENIM, 7);
		brandProbabilityMap.put(Label.Value.DICKIES, 9);
		brandProbabilityMap.put(Label.Value.DIESEL, 5);
		brandProbabilityMap.put(Label.Value.C_DIOR, 1);
		brandProbabilityMap.put(Label.Value.ESPRIT, 6);
		brandProbabilityMap.put(Label.Value.ETNIES, 9);
		brandProbabilityMap.put(Label.Value.FJÄLLRÄVEN, 5);
		brandProbabilityMap.put(Label.Value.FOREVER_21, 3);
		brandProbabilityMap.put(Label.Value.G_STAR, 5);
		brandProbabilityMap.put(Label.Value.GUCCI, 1);
		brandProbabilityMap.put(Label.Value.H_N_M, 4);
		brandProbabilityMap.put(Label.Value.HRLONDON, 1);
        brandProbabilityMap.put(Label.Value.HUGO_BOSS, 1);
        brandProbabilityMap.put(Label.Value.HELLBUNNY, 1);
		brandProbabilityMap.put(Label.Value.INNOCENT, 1);
		brandProbabilityMap.put(Label.Value.J_CREW, 2);
		brandProbabilityMap.put(Label.Value.LACOSTE, 2);
		brandProbabilityMap.put(Label.Value.LEVI_S, 5);
		brandProbabilityMap.put(Label.Value.LIVINGDEADSOULS, 1);
		brandProbabilityMap.put(Label.Value.LOUIS_VUITTON, 1);
		brandProbabilityMap.put(Label.Value.LRG, 7);
		brandProbabilityMap.put(Label.Value.MAZINE, 8);
		brandProbabilityMap.put(Label.Value.NEW_YORKER, 6);
		brandProbabilityMap.put(Label.Value.NIKE, 5);
		brandProbabilityMap.put(Label.Value.PEPE, 5);
		brandProbabilityMap.put(Label.Value.PRADA, 1);
		brandProbabilityMap.put(Label.Value.RALPH_LAUREN, 1);
		brandProbabilityMap.put(Label.Value.REEBOK, 6);
		brandProbabilityMap.put(Label.Value.S_OLIVER, 5);
		brandProbabilityMap.put(Label.Value.SCOTCH_N_SODA, 5);
		brandProbabilityMap.put(Label.Value.SPIRAL, 1);
		brandProbabilityMap.put(Label.Value.SUPERDRY, 3);
		brandProbabilityMap.put(Label.Value.TOM_TAILOR, 4);
		brandProbabilityMap.put(Label.Value.TOMMY_HILFIGER, 5);
		brandProbabilityMap.put(Label.Value.VANS, 9);
		brandProbabilityMap.put(Label.Value.VERSACE, 1);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.acryl), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.athletic), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.baggy), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.blue), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.brown), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.triangle), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.dark), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.emo), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.tight), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.yellow), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.girly), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.grey), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.green), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.light), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.lightblue), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.hoody), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.classic), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.leather), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.lilac), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.logo), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.girl), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.swatch), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.navy), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.neon), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.original), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.pink), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.retro), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.romantic), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.red), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.ribbon), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.cord), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.sport), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.sporty), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.black), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.saying), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.street), 8);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.used), 8);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.vintage), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.white), 4);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.gold), 1);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.silver), 1);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.petrol), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.olive), 5);

	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 3);
		musicTaste.put(Music.POP, 2);
		musicTaste.put(Music.ROCK, 6);
		musicTaste.put(Music.CLASSIC, 0);
		musicTaste.put(Music.JAZZ, 0);
		musicTaste.put(Music.DNB, 6);
		musicTaste.put(Music.HIP_HOP, 7);
		musicTaste.put(Music.FOLK, 3);
		musicTaste.put(Music.INDIE, 2);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 8);
		jobs.put(Job.STUDENT, 7);
		jobs.put(Job.MANAGER, 2);
		jobs.put(Job.SALESPERSON, 3);
		jobs.put(Job.CASHIER, 3);
		jobs.put(Job.COOK, 4);
		jobs.put(Job.WAITER, 4);
		jobs.put(Job.NURSE, 3);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 3);
		jobs.put(Job.CARPENTER, 2);
		jobs.put(Job.SECRETARY, 2);
		jobs.put(Job.ASSISTANT, 2);
		jobs.put(Job.LAWYER, 1);
		jobs.put(Job.PROGRAMMER, 6);
		jobs.put(Job.ATHLETE, 4);
		jobs.put(Job.RESEARCHER, 2);
        jobs.put(Job.UNEMPLOYED, 6);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 2);
		ageRange.put(AgeRange.TEENAGER, 8);
		ageRange.put(AgeRange.YOUNG_ADULT, 6);
		ageRange.put(AgeRange.ADULT, 3);
		ageRange.put(AgeRange.OLDER_ADULT, 1);
		ageRange.put(AgeRange.SENIOR, 0);
	}
}
