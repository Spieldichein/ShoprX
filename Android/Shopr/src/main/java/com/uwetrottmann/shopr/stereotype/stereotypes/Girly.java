package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.algorithm.model.Label;
import com.uwetrottmann.shopr.algorithm.model.Sex;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

public class Girly extends AbstractStereotype {

	public Girly() {
		this.stereotype = Stereotype.GIRLY;
		this.sex = Sex.Value.FEMALE;
		buildAgeMap();
		buildJobMap();
		buildMusicMap();
		buildBrandMap();
		buildAttributeMap();
	}

	private void buildBrandMap() {
		this.brandProbabilityMap = new HashMap<Label.Value, Integer>();
		brandProbabilityMap.put(Label.Value.ADIDAS, 4);
		brandProbabilityMap.put(Label.Value.ALLEGRA_K, 9);
		brandProbabilityMap.put(Label.Value.BOOM_BAP, 6);
		brandProbabilityMap.put(Label.Value.HUGO_BOSS, 2);
		brandProbabilityMap.put(Label.Value.BRAX, 2);
		brandProbabilityMap.put(Label.Value.BYE_BYE_KITTY, 4);
		brandProbabilityMap.put(Label.Value.CARHARTT, 4);
		brandProbabilityMap.put(Label.Value.CHANEL, 5);
		brandProbabilityMap.put(Label.Value.CONVERSE, 3);
		brandProbabilityMap.put(Label.Value.CUPCAKECULT, 6);
		brandProbabilityMap.put(Label.Value.DC, 2);
		brandProbabilityMap.put(Label.Value.DENIM, 4);
		brandProbabilityMap.put(Label.Value.DICKIES_DOUBLE, 3);
		brandProbabilityMap.put(Label.Value.DIESEL, 2);
		brandProbabilityMap.put(Label.Value.C_DIOR, 5);
		brandProbabilityMap.put(Label.Value.ESPRIT, 5);
		brandProbabilityMap.put(Label.Value.ETNIES, 3);
		brandProbabilityMap.put(Label.Value.FJÄLLRÄVEN, 4);
		brandProbabilityMap.put(Label.Value.FOREVER_21, 8);
		brandProbabilityMap.put(Label.Value.G_STAR, 5);
		brandProbabilityMap.put(Label.Value.GUCCI, 6);
		brandProbabilityMap.put(Label.Value.H_N_M, 6);
		brandProbabilityMap.put(Label.Value.HRLONDON, 2);
		brandProbabilityMap.put(Label.Value.HELLBUNNY, 4);
		brandProbabilityMap.put(Label.Value.INNOCENT, 2);
		brandProbabilityMap.put(Label.Value.J_CREW, 2);
		brandProbabilityMap.put(Label.Value.LACOSTE, 3);
		brandProbabilityMap.put(Label.Value.LEVI_S, 2);
		brandProbabilityMap.put(Label.Value.LIVINGDEADSOULS, 2);
		brandProbabilityMap.put(Label.Value.LOUIS_VUITTON, 5);
		brandProbabilityMap.put(Label.Value.LRG, 3);
		brandProbabilityMap.put(Label.Value.MAZINE, 2);
		brandProbabilityMap.put(Label.Value.NEW_YORKER, 6);
		brandProbabilityMap.put(Label.Value.NIKE, 4);
		brandProbabilityMap.put(Label.Value.PEPE, 4);
		brandProbabilityMap.put(Label.Value.PRADA, 6);
		brandProbabilityMap.put(Label.Value.RALPH_LAUREN, 5);
		brandProbabilityMap.put(Label.Value.REEBOK, 3);
		brandProbabilityMap.put(Label.Value.S_OLIVER, 5);
		brandProbabilityMap.put(Label.Value.SCOTCH_N_SODA, 6);
		brandProbabilityMap.put(Label.Value.SPIRAL, 2);
		brandProbabilityMap.put(Label.Value.SUPERDRY, 3);
		brandProbabilityMap.put(Label.Value.TOM_TAILOR, 3);
		brandProbabilityMap.put(Label.Value.TOMMY_HILFIGER, 3);
		brandProbabilityMap.put(Label.Value.VANS, 2);
		brandProbabilityMap.put(Label.Value.VERSACE, 5);
	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 3);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 5);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 7);

	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 4);
		musicTaste.put(Music.POP, 6);
		musicTaste.put(Music.ROCK, 2);
		musicTaste.put(Music.CLASSIC, 0);
		musicTaste.put(Music.JAZZ, 0);
		musicTaste.put(Music.DNB, 1);
		musicTaste.put(Music.HIP_HOP, 1);
		musicTaste.put(Music.FOLK, 6);
		musicTaste.put(Music.INDIE, 7);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 7);
		jobs.put(Job.STUDENT, 6);
		jobs.put(Job.MANAGER, 0);
		jobs.put(Job.SALESPERSON, 2);
		jobs.put(Job.CASHIER, 3);
		jobs.put(Job.COOK, 2);
		jobs.put(Job.WAITER, 3);
		jobs.put(Job.NURSE, 5);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 3);
		jobs.put(Job.CARPENTER, 0);
		jobs.put(Job.SECRETARY, 3);
		jobs.put(Job.ASSISTANT, 5);
		jobs.put(Job.LAWYER, 1);
		jobs.put(Job.PROGRAMMER, 1);
		jobs.put(Job.ATHLETE, 1);
		jobs.put(Job.RESEARCHER, 2);
        jobs.put(Job.UNEMPLOYED, 3);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 8);
		ageRange.put(AgeRange.TEENAGER, 8);
		ageRange.put(AgeRange.YOUNG_ADULT, 7);
		ageRange.put(AgeRange.ADULT, 4);
		ageRange.put(AgeRange.OLDER_ADULT, 2);
		ageRange.put(AgeRange.SENIOR, 0);
	}
}
