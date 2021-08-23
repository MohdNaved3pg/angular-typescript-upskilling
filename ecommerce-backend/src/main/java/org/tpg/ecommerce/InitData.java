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
		category = categoryRepository.save(new Category("Mobiles"));
		for (int i = 21; i <= 38; i++) {
			productRepository.save(new Product(i, "Mobile-" + i
					+ " (Aqua Green, 4GB RAM, 64GB Storage) -Amoled Dot Display | 48MP Sony Sensor IMX582 | Snapdragon 678 Processor ",
					"\n" + "\n"
							+ "    Display: FHD+ (1080x2400) AMOLED Dot display; 16.33 centimeters (6.43 inch); 20:9 aspect ratio\n"
							+ "    Camera: 48 MP Quad Rear camera with 8MP Ultra-wide, 2MP Macro and Portrait lens| 13 MP Front camera\n"
							+ "    Battery: 5000 mAh large battery with 33W fast charger in-box and Type-C connectivity\n"
							+ "    Processor: Qualcomm Snapdragon 678 with Kryo 460 Octa-core; 11nm process; Up to 2.2GHz clock speed\n"
							+ "    Memory, Storage & SIM: 4GB RAM | 64GB UFS 2.2 storage expandable up to 512GB with dedicated SD card slot | Dual SIM (nano+nano) dual standby (4G+4G)\n",
					10, 999, category));
		}
	}

}
