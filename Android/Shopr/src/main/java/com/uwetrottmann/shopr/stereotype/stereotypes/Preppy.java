package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

public class Preppy extends AbstractStereotype {

	public Preppy() {
		this.stereotype = Stereotype.PREPPY;

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
				.getString(R.string.boomBap), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				9);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 9);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 5);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				3);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 9);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 4);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 1);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 6);
	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 2);
		musicTaste.put(Music.POP, 4);
		musicTaste.put(Music.ROCK, 4);
		musicTaste.put(Music.CLASSIC, 6);
		musicTaste.put(Music.JAZZ, 6);
		musicTaste.put(Music.DNB, 4);
		musicTaste.put(Music.HIP_HOP, 1);
		musicTaste.put(Music.FOLK, 6);
		musicTaste.put(Music.INDIE, 5);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 6);
		jobs.put(Job.STUDENT, 6);
		jobs.put(Job.MANAGER, 8);
		jobs.put(Job.SALESPERSON, 9);
		jobs.put(Job.CASHIER, 5);
		jobs.put(Job.COOK, 4);
		jobs.put(Job.WAITER, 3);
		jobs.put(Job.NURSE, 3);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 8);
		jobs.put(Job.CARPENTER, 3);
		jobs.put(Job.SECRETARY, 6);
		jobs.put(Job.ASSISTANT, 6);
		jobs.put(Job.LAWYER, 9);
		jobs.put(Job.PROGRAMMER, 2);
		jobs.put(Job.ATHLETE, 5);
		jobs.put(Job.RESEARCHER, 4);
        jobs.put(Job.UNEMPLOYED, 2);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 7);
		ageRange.put(AgeRange.TEENAGER, 5);
		ageRange.put(AgeRange.YOUNG_ADULT, 6);
		ageRange.put(AgeRange.ADULT, 8);
		ageRange.put(AgeRange.OLDER_ADULT, 7);
		ageRange.put(AgeRange.SENIOR, 4);
	}
}
