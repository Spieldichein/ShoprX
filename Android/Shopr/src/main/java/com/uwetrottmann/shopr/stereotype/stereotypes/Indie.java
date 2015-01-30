package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
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
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.allegraK), 6);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 2);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				5);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 2);
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
