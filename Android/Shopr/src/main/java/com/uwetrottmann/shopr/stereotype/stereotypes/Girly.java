package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
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
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.allegraK), 9);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 5);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 3);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 5);
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
