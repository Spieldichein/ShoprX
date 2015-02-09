package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Label;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

public class Indie extends AbstractStereotype {

	public Indie() {
		this.stereotype = Stereotype.INDIE;

		buildAgeMap();

		buildJobMap();

		buildMusicMap();

		buildBrandMap();

		buildAttributeMap();
	}

	private void buildBrandMap() {
		this.brandProbabilityMap = new HashMap<Label.Value, Integer>();
		brandProbabilityMap.put(Label.Value.ADIDAS, 5);
		brandProbabilityMap.put(Label.Value.ALLEGRA_K, 6);
		brandProbabilityMap.put(Label.Value.BOOM_BAP, 9);
		brandProbabilityMap.put(Label.Value.HUGO_BOSS, 2);
		brandProbabilityMap.put(Label.Value.BRAX, 2);
		brandProbabilityMap.put(Label.Value.BYE_BYE_KITTY, 4);
		brandProbabilityMap.put(Label.Value.CARHARTT, 6);
		brandProbabilityMap.put(Label.Value.CHANEL, 3);
		brandProbabilityMap.put(Label.Value.CONVERSE, 7);
		brandProbabilityMap.put(Label.Value.CUPCAKECULT, 6);
		brandProbabilityMap.put(Label.Value.DC, 4);
		brandProbabilityMap.put(Label.Value.DENIM, 4);
		brandProbabilityMap.put(Label.Value.DICKIES_DOUBLE, 4);
		brandProbabilityMap.put(Label.Value.DIESEL, 3);
		brandProbabilityMap.put(Label.Value.C_DIOR, 2);
		brandProbabilityMap.put(Label.Value.ESPRIT, 6);
		brandProbabilityMap.put(Label.Value.ETNIES, 4);
		brandProbabilityMap.put(Label.Value.FJÄLLRÄVEN, 3);
		brandProbabilityMap.put(Label.Value.FOREVER_21, 9);
		brandProbabilityMap.put(Label.Value.G_STAR, 7);
		brandProbabilityMap.put(Label.Value.GUCCI, 2);
		brandProbabilityMap.put(Label.Value.H_N_M, 5);
		brandProbabilityMap.put(Label.Value.HRLONDON, 2);
		brandProbabilityMap.put(Label.Value.HELLBUNNY, 2);
		brandProbabilityMap.put(Label.Value.INNOCENT, 2);
		brandProbabilityMap.put(Label.Value.J_CREW, 4);
		brandProbabilityMap.put(Label.Value.LACOSTE, 3);
		brandProbabilityMap.put(Label.Value.LEVI_S, 6);
		brandProbabilityMap.put(Label.Value.LIVINGDEADSOULS, 2);
		brandProbabilityMap.put(Label.Value.LOUIS_VUITTON, 2);
		brandProbabilityMap.put(Label.Value.LRG, 4);
		brandProbabilityMap.put(Label.Value.MAZINE, 4);
		brandProbabilityMap.put(Label.Value.NEW_YORKER, 6);
		brandProbabilityMap.put(Label.Value.NIKE, 4);
		brandProbabilityMap.put(Label.Value.PEPE, 6);
		brandProbabilityMap.put(Label.Value.PRADA, 2);
		brandProbabilityMap.put(Label.Value.RALPH_LAUREN, 4);
		brandProbabilityMap.put(Label.Value.REEBOK, 4);
		brandProbabilityMap.put(Label.Value.S_OLIVER, 6);
		brandProbabilityMap.put(Label.Value.SCOTCH_N_SODA, 8);
		brandProbabilityMap.put(Label.Value.SPIRAL, 2);
		brandProbabilityMap.put(Label.Value.SUPERDRY, 5);
		brandProbabilityMap.put(Label.Value.TOM_TAILOR, 3);
		brandProbabilityMap.put(Label.Value.TOMMY_HILFIGER, 4);
		brandProbabilityMap.put(Label.Value.VANS, 5);
		brandProbabilityMap.put(Label.Value.VERSACE, 2);
	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 5);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 10);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 1);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 6);
	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 9);
		musicTaste.put(Music.POP, 4);
		musicTaste.put(Music.ROCK, 2);
		musicTaste.put(Music.CLASSIC, 0);
		musicTaste.put(Music.JAZZ, 0);
		musicTaste.put(Music.DNB, 4);
		musicTaste.put(Music.HIP_HOP, 4);
		musicTaste.put(Music.FOLK, 7);
		musicTaste.put(Music.INDIE, 9);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 7);
		jobs.put(Job.STUDENT, 10);
		jobs.put(Job.MANAGER, 2);
		jobs.put(Job.SALESPERSON, 5);
		jobs.put(Job.CASHIER, 5);
		jobs.put(Job.COOK, 4);
		jobs.put(Job.WAITER, 7);
		jobs.put(Job.NURSE, 7);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 5);
		jobs.put(Job.CARPENTER, 4);
		jobs.put(Job.SECRETARY, 3);
		jobs.put(Job.ASSISTANT, 6);
		jobs.put(Job.LAWYER, 3);
		jobs.put(Job.PROGRAMMER, 6);
		jobs.put(Job.ATHLETE, 2);
		jobs.put(Job.RESEARCHER, 4);
        jobs.put(Job.UNEMPLOYED, 5);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 2);
		ageRange.put(AgeRange.TEENAGER, 10);
		ageRange.put(AgeRange.YOUNG_ADULT, 8);
		ageRange.put(AgeRange.ADULT, 6);
		ageRange.put(AgeRange.OLDER_ADULT, 3);
		ageRange.put(AgeRange.SENIOR, 1);
	}
}
