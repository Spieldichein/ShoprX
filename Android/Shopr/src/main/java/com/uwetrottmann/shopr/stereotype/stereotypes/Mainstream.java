package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

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
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 7);
		brandProbabilityMap.put(
                ShoprApp.getContext().getString(R.string.allegraK), 3);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 3);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				6);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				7);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 6);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 5);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.nike),
				7);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.pepe),
				8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.prada),
				5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ralphlauren), 6);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.reebok), 6);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.sOliver), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.scotchsoda), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.spiral), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.superdry), 7);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 8);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				5);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 4);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 6);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 2);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 5);

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
