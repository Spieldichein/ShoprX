package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

public class Emo extends AbstractStereotype {

	public Emo() {
		this.stereotype = Stereotype.EMO;
		buildAgeMap();
		buildJobMap();
		buildMusicMap();
		buildBrandMap();
		buildAttributeMap();
	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 9);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 10);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 7);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 4);
	}

	private void buildBrandMap() {
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.allegraK), 6);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 1);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				3);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 1);
	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 1);
		musicTaste.put(Music.POP, 6);
		musicTaste.put(Music.ROCK, 7);
		musicTaste.put(Music.CLASSIC, 1);
		musicTaste.put(Music.JAZZ, 0);
		musicTaste.put(Music.DNB, 3);
		musicTaste.put(Music.HIP_HOP, 0);
		musicTaste.put(Music.FOLK, 2);
		musicTaste.put(Music.INDIE, 6);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 8);
		jobs.put(Job.STUDENT, 6);
		jobs.put(Job.MANAGER, 1);
		jobs.put(Job.SALESPERSON, 5);
		jobs.put(Job.CASHIER, 6);
		jobs.put(Job.COOK, 4);
		jobs.put(Job.WAITER, 6);
		jobs.put(Job.NURSE, 7);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 5);
		jobs.put(Job.CARPENTER, 2);
		jobs.put(Job.SECRETARY, 2);
		jobs.put(Job.ASSISTANT, 4);
		jobs.put(Job.LAWYER, 1);
		jobs.put(Job.PROGRAMMER, 7);
		jobs.put(Job.ATHLETE, 0);
		jobs.put(Job.RESEARCHER, 4);
        jobs.put(Job.UNEMPLOYED, 5);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 0);
		ageRange.put(AgeRange.TEENAGER, 10);
		ageRange.put(AgeRange.YOUNG_ADULT, 7);
		ageRange.put(AgeRange.ADULT, 3);
		ageRange.put(AgeRange.OLDER_ADULT, 1);
		ageRange.put(AgeRange.SENIOR, 0);
	}
}
