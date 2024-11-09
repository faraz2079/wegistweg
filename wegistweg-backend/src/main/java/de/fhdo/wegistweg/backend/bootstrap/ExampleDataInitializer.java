package de.fhdo.wegistweg.backend.bootstrap;

import de.fhdo.wegistweg.backend.domain.Product;
import de.fhdo.wegistweg.backend.domain.User;
import de.fhdo.wegistweg.backend.repository.ProductDisplaySettingsRepository;
import de.fhdo.wegistweg.backend.repository.ProductInteractionRepository;
import de.fhdo.wegistweg.backend.repository.ProductRepository;
import de.fhdo.wegistweg.backend.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ExampleDataInitializer implements CommandLineRunner {

    private static Log log = LogFactory.getLog(ExampleDataInitializer.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductDisplaySettingsRepository productDisplaySettingsRepository;
    private final ProductInteractionRepository productInteractionRepository;

    private final Random random;

    public ExampleDataInitializer(UserRepository userRepository, ProductRepository productRepository, ProductDisplaySettingsRepository productDisplaySettingsRepository, ProductInteractionRepository productInteractionRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productDisplaySettingsRepository = productDisplaySettingsRepository;
        this.productInteractionRepository = productInteractionRepository;

        this.random = new Random();
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Initializing Example Data");
        initUsers();
        initProducts();
    }

    private void initUsers() {
        for (int i = 0; i < 10; i++) {
            userRepository.save(new User());
        }
    }

    private void initProducts() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setStock(random.nextInt(0,100));
            product.setPrice(getRandomPrice());

            productRepository.save(product);
        }
    }

    /**
     * @return a double with two decimal places representing a price in Euros.
     */
    private double getRandomPrice() {
        double euro = random.nextInt(0,100);
        double cents = random.nextInt(0, 100);

        return euro + (cents / 100);
    }
}
