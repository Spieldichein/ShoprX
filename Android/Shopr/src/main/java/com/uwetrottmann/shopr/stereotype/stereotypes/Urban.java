package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

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
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.allegraK), 2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				8);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				4);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 2);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 7);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				8);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 2);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 5);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 2);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 5);

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