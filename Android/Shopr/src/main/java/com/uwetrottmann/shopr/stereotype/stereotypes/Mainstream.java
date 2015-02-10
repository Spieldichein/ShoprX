package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Label;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

/**
 * The style of the average person. Influenced by various other current fashion
 * styles, but in a less extroverted version. Clothes are typically in a price range that can
 * be afforded by most people and main brands are H&M, C&A, Esprit, GStar, NewYorker,
 * s.Oliver, Tom Tailor or Tommy Hilfiger.
 */
public class Mainstream extends AbstractStereotype {

	public Mainstream() {
		this.stereotype = Stereotype.MAINSTREAM;

		buildAgeMap();

		buildJobMap();

		buildMusicMap();

		buildBrandMap();

		buildAttributeMap();
	}

	private void buildBrandMap() {
		this.brandProbabilityMap = new HashMap<Label.Value, Integer>();
		brandProbabilityMap.put(Label.Value.ADIDAS, 7);
		brandProbabilityMap.put(Label.Value.ALLEGRA_K, 3);
		brandProbabilityMap.put(Label.Value.BOOM_BAP, 4);
		brandProbabilityMap.put(Label.Value.HUGO_BOSS, 6);
		brandProbabilityMap.put(Label.Value.BRAX, 7);
		brandProbabilityMap.put(Label.Value.BYE_BYE_KITTY, 3);
		brandProbabilityMap.put(Label.Value.CARHARTT, 5);
		brandProbabilityMap.put(Label.Value.CHANEL, 5);
		brandProbabilityMap.put(Label.Value.CONVERSE, 5);
		brandProbabilityMap.put(Label.Value.CUPCAKECULT, 4);
		brandProbabilityMap.put(Label.Value.DC, 4);
		brandProbabilityMap.put(Label.Value.DENIM, 6);
		brandProbabilityMap.put(Label.Value.DICKIES, 5);
		brandProbabilityMap.put(Label.Value.DIESEL, 8);
		brandProbabilityMap.put(Label.Value.C_DIOR, 5);
		brandProbabilityMap.put(Label.Value.ESPRIT, 8);
		brandProbabilityMap.put(Label.Value.ETNIES, 5);
		brandProbabilityMap.put(Label.Value.FJÄLLRÄVEN, 7);
		brandProbabilityMap.put(Label.Value.FOREVER_21, 6);
		brandProbabilityMap.put(Label.Value.G_STAR, 8);
		brandProbabilityMap.put(Label.Value.GUCCI, 5);
		brandProbabilityMap.put(Label.Value.H_N_M, 8);
		brandProbabilityMap.put(Label.Value.HRLONDON, 2);
		brandProbabilityMap.put(Label.Value.HELLBUNNY, 2);
		brandProbabilityMap.put(Label.Value.INNOCENT, 1);
		brandProbabilityMap.put(Label.Value.J_CREW, 7);
		brandProbabilityMap.put(Label.Value.LACOSTE, 6);
		brandProbabilityMap.put(Label.Value.LEVI_S, 8);
		brandProbabilityMap.put(Label.Value.LIVINGDEADSOULS, 1);
		brandProbabilityMap.put(Label.Value.LOUIS_VUITTON, 5);
		brandProbabilityMap.put(Label.Value.LRG, 4);
		brandProbabilityMap.put(Label.Value.MAZINE, 5);
		brandProbabilityMap.put(Label.Value.NEW_YORKER, 8);
		brandProbabilityMap.put(Label.Value.NIKE, 7);
		brandProbabilityMap.put(Label.Value.PEPE, 8);
		brandProbabilityMap.put(Label.Value.PRADA, 5);
		brandProbabilityMap.put(Label.Value.RALPH_LAUREN, 6);
		brandProbabilityMap.put(Label.Value.REEBOK, 6);
		brandProbabilityMap.put(Label.Value.S_OLIVER, 8);
		brandProbabilityMap.put(Label.Value.SCOTCH_N_SODA, 8);
		brandProbabilityMap.put(Label.Value.SPIRAL, 1);
		brandProbabilityMap.put(Label.Value.SUPERDRY, 7);
		brandProbabilityMap.put(Label.Value.TOM_TAILOR, 8);
		brandProbabilityMap.put(Label.Value.TOMMY_HILFIGER, 8);
		brandProbabilityMap.put(Label.Value.VANS, 5);
		brandProbabilityMap.put(Label.Value.VERSACE, 4);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.acryl), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.athletic), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.baggy), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.blue), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.brown), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.triangle), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.dark), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.emo), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.tight), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.yellow), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.girly), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.grey), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.green), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.light), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.lightblue), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.hoody), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.classic), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.curt), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.leather), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.lilac), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.logo), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.girl), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.swatch), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.navy), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.neon), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.original), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.pink), 3);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.retro), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.romantic), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.red), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.ribbon), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.cord), 2);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.sport), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.sporty), 4);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.black), 7);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.saying), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.street), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.used), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.vintage), 6);
		attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.white), 5);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.gold), 3);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.silver), 3);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.petrol), 7);
        attributeProbabilityMap.put(ShoprApp.getContext().getString(R.string.olive), 5);

	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 4);
		musicTaste.put(Music.POP, 8);
		musicTaste.put(Music.ROCK, 7);
		musicTaste.put(Music.CLASSIC, 3);
		musicTaste.put(Music.JAZZ, 2);
		musicTaste.put(Music.DNB, 2);
		musicTaste.put(Music.HIP_HOP, 2);
		musicTaste.put(Music.FOLK, 3);
		musicTaste.put(Music.INDIE, 4);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 4);
		jobs.put(Job.STUDENT, 5);
		jobs.put(Job.MANAGER, 7);
		jobs.put(Job.SALESPERSON, 4);
		jobs.put(Job.CASHIER, 5);
		jobs.put(Job.COOK, 5);
		jobs.put(Job.WAITER, 4);
		jobs.put(Job.NURSE, 4);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 5);
		jobs.put(Job.CARPENTER, 5);
		jobs.put(Job.SECRETARY, 7);
		jobs.put(Job.ASSISTANT, 7);
		jobs.put(Job.LAWYER, 8);
		jobs.put(Job.PROGRAMMER, 4);
		jobs.put(Job.ATHLETE, 6);
		jobs.put(Job.RESEARCHER, 7);
        jobs.put(Job.UNEMPLOYED, 6);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 2);
		ageRange.put(AgeRange.TEENAGER, 3);
		ageRange.put(AgeRange.YOUNG_ADULT, 4);
		ageRange.put(AgeRange.ADULT, 7);
		ageRange.put(AgeRange.OLDER_ADULT, 7);
		ageRange.put(AgeRange.SENIOR, 5);
	}
}
