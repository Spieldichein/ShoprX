package com.uwetrottmann.shopr.stereotype.stereotypes;

import com.uwetrottmann.shopr.R;
import com.uwetrottmann.shopr.ShoprApp;
import com.uwetrottmann.shopr.stereotype.user.AgeRange;
import com.uwetrottmann.shopr.stereotype.user.Job;
import com.uwetrottmann.shopr.stereotype.user.Music;

import java.util.HashMap;

public class Gothic extends AbstractStereotype {

	public Gothic() {
		this.stereotype = Stereotype.GOTHIC;

		buildAgeMap();

		buildJobMap();

		buildMusicMap();

		buildBrandMap();

		buildAttributeMap();
	}

	private void buildBrandMap() {
		this.brandProbabilityMap = new HashMap<String, Integer>();
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.adidas), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.allegraK), 7);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.boomBap), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.boss),
				1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.brax),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.byebyekitty), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.ca), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.carhartt), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.chanel), 1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.converse), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cupcakecult), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dc), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.denim),
				2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.dickies), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.diesel), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.dior),
				1);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.esprit), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.etnies), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.fjallraven), 5);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.forever21), 5);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gstar),
				4);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.gucci),
				1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.hm), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hrlondon), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hellbunny), 8);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.innocent), 9);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.jcrew),
				1);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.lacoste), 1);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.levis),
				4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.livingdeadsouls), 9);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.louisvitton), 1);
		brandProbabilityMap
				.put(ShoprApp.getContext().getString(R.string.lrg), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.mazine), 2);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.newyorker), 3);
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
				ShoprApp.getContext().getString(R.string.superdry), 4);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tomtailor), 3);
		brandProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tommyhilfiger), 2);
		brandProbabilityMap.put(ShoprApp.getContext().getString(R.string.vans),
				2);
		brandProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.versace), 5);

	}

	private void buildAttributeMap() {
		this.attributeProbabilityMap = new HashMap<String, Integer>();
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.acryl), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.athletic), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.baggy), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.blue), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.brown), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cremeColored), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.triangle), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.dark), 9);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.emo), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.tight), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.yellow), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girly), 3);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.grey), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.green), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.light), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.lightblue), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.hoody), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.classic), 4);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.curt), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.leather), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.purple), 6);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.logo), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.girl), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.swatch), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.navy), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.neon), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.original), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.pink), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.plush), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.retro), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.romantic), 8);
		attributeProbabilityMap.put(ShoprApp.getContext()
				.getString(R.string.red), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.ribbon), 7);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.cord), 8);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sport), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.sporty), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.black), 9);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.saying), 2);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.street2), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.stripes), 5);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.used), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.vintage), 1);
		attributeProbabilityMap.put(
				ShoprApp.getContext().getString(R.string.white), 5);

	}

	private void buildMusicMap() {
		this.musicTaste = new HashMap<Music, Integer>();
		musicTaste.put(Music.ELECTRONIC, 1);
		musicTaste.put(Music.POP, 2);
		musicTaste.put(Music.ROCK, 7);
		musicTaste.put(Music.CLASSIC, 3);
		musicTaste.put(Music.JAZZ, 1);
		musicTaste.put(Music.DNB, 2);
		musicTaste.put(Music.HIP_HOP, 1);
		musicTaste.put(Music.FOLK, 2);
		musicTaste.put(Music.INDIE, 3);
	}

	private void buildJobMap() {
		this.jobs = new HashMap<Job, Integer>();
		jobs.put(Job.PUPIL, 7);
		jobs.put(Job.STUDENT, 7);
		jobs.put(Job.MANAGER, 0);
		jobs.put(Job.SALESPERSON, 5);
		jobs.put(Job.CASHIER, 6);
		jobs.put(Job.COOK, 4);
		jobs.put(Job.WAITER, 6);
		jobs.put(Job.NURSE, 6);
		jobs.put(Job.CUSTOMER_SERVICE_REPRESENTATIVE, 3);
		jobs.put(Job.CARPENTER, 5);
		jobs.put(Job.SECRETARY, 5);
		jobs.put(Job.ASSISTANT, 3);
		jobs.put(Job.LAWYER, 1);
		jobs.put(Job.PROGRAMMER, 7);
		jobs.put(Job.ATHLETE, 1);
		jobs.put(Job.RESEARCHER, 5);
        jobs.put(Job.UNEMPLOYED, 6);
		jobs.put(Job.OTHER, 0);
	}

	private void buildAgeMap() {
		this.ageRange = new HashMap<AgeRange, Integer>();
		ageRange.put(AgeRange.CHILD, 0);
		ageRange.put(AgeRange.TEENAGER, 7);
		ageRange.put(AgeRange.YOUNG_ADULT, 8);
		ageRange.put(AgeRange.ADULT, 6);
		ageRange.put(AgeRange.OLDER_ADULT, 4);
		ageRange.put(AgeRange.SENIOR, 1);
	}
}
