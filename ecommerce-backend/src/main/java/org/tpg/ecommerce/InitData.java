package org.tpg.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tpg.ecommerce.model.Category;
import org.tpg.ecommerce.model.Product;
import org.tpg.ecommerce.repository.CategoryRepository;
import org.tpg.ecommerce.repository.ProductRepository;

@Service
public class InitData {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@EventListener(ApplicationReadyEvent.class)
	public void loadData() {
		Category category = categoryRepository.save(new Category("Headphones"));
		for (int i = 1; i <= 20; i++) {
			productRepository.save(new Product(i,
					"Headphone-" + i + " with Immersive Audio, Lightweight Ergonomic Design, Cosy Padded Earcups",
					"Battery: Rockerz 370 offers a playback time of up to 12 hours. Playback Time : 12 hours, Charging Time : 2-3 hours, Standby Time : 180 hours\n"
							+ "Bluetooth: It has Bluetooth v5.0 with a range of 10m and is compatible with Android & iOS\n"
							+ "ANC: NA\n" + "No. of Mic: 1\n"
							+ "Other Inclusions: Micro USB Charging Cable, Warranty Card, User Manual\n"
							+ "Lightweight Ergonomic Design with Easy Controls\n" + "Instant Voice Assistant\n"
							+ "Drivers: 40mm Drivers with boAt Signature Sound\n" + "Cozy Padded Earcups.\n"
							+ "Rockerz 370 come equipped with latest Bluetooth v5.0 for instant wireless connectivity\n"
							+ "The powerful 300mAh battery provides up to 8 Hours of audio bliss\n"
							+ "40mm Dynamic Drivers supply immersive High Definition sound\n"
							+ "The headset has padded earcups for a comfortable experience\n"
							+ "The headphone has been ergonomically and aesthetically designed for a seamless experience\n"
							+ "One can connect to Rockerz 370 via dual modes for connectivity in the form of Bluetooth and AUX\n"
							+ "1 year warranty from the date of purchase.",
					10, 999, category));
		}
	}

}
