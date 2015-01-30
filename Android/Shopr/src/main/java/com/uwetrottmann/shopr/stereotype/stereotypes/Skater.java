package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

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
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.allegraK), 2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				7);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 1);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 6);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				9);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 1);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 5);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 2);
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
				ShoprApp.getContext().getString(R.string.classic), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 1);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 4);

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
