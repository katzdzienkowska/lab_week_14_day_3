package com.codeclan.example.WhiskyTracker;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WhiskyTrackerApplicationTests {

	@Autowired
	WhiskyRepository whiskyRepository;

	@Autowired
	DistilleryRepository distilleryRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createDistilleryAndWhiskyThenSave() {
		Distillery lochend = new Distillery("Lochend", "Edinburgh");
		distilleryRepository.save(lochend);
		Whisky oskar = new Whisky("Oskar", 2021, 1, lochend);
		whiskyRepository.save(oskar);
	}

	@Test
	public void canFindWhiskiesByYear() {
		List<Whisky> foundWhiskies = whiskyRepository.findByYear(2018);
		System.out.println(foundWhiskies.size());
		assertEquals(6, foundWhiskies.size());
	}

	@Test
	public void canFindDistilleriesByRegion() {
		List<Distillery> foundDistilleries = distilleryRepository.findByRegionIgnoreCase("Speyside");
		System.out.println(foundDistilleries.size());
		assertEquals(3, foundDistilleries.size());
	}

	@Test
	public void canFindWhiskyFromDistilleryByAge() {
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryIdAndAge(7L, 12);
		System.out.println(foundWhiskies.size());
		assertEquals(1, foundWhiskies.size());
	}

	@Test
	public void canFindAllWhiskiesByDistilleryRegion() {
		List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegionIgnoreCase("Lowland");
		System.out.println(foundWhiskies.size());
		assertEquals(4, foundWhiskies.size());
	}

	@Test
	public void canFindDistilleriesByWhiskyAge12() {
		List<Distillery> foundDistilleries = distilleryRepository.findByWhiskiesAgeEquals(12);
		System.out.println(foundDistilleries.size());
		assertEquals(6, foundDistilleries.size());
	}

}
