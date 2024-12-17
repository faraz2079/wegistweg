package de.fhdo.wegistweg.bootstrap;

import de.fhdo.wegistweg.entity.Product;
import de.fhdo.wegistweg.entity.ProductInteraction;
import de.fhdo.wegistweg.entity.ProductInteractionType;
import de.fhdo.wegistweg.entity.User;
import de.fhdo.wegistweg.repository.ProductDisplaySettingsRepository;
import de.fhdo.wegistweg.repository.ProductInteractionRepository;
import de.fhdo.wegistweg.repository.ProductRepository;
import de.fhdo.wegistweg.repository.UserRepository;
import de.fhdo.wegistweg.service.PasswordEncoder;
import de.fhdo.wegistweg.service.ProductInteractionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
public class ExampleDataInitializer implements CommandLineRunner {

    private static Log log = LogFactory.getLog(ExampleDataInitializer.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductDisplaySettingsRepository productDisplaySettingsRepository;
    private final ProductInteractionRepository productInteractionRepository;
    private final ProductInteractionService productInteractionService;
    private final PasswordEncoder passwordEncoder;

    private final Random random;

    public ExampleDataInitializer(UserRepository userRepository, ProductRepository productRepository, ProductDisplaySettingsRepository productDisplaySettingsRepository, ProductInteractionRepository productInteractionRepository, ProductInteractionService productInteractionService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productDisplaySettingsRepository = productDisplaySettingsRepository;
        this.productInteractionRepository = productInteractionRepository;
        this.productInteractionService = productInteractionService;
        this.passwordEncoder = passwordEncoder;

        this.random = new Random();
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Initializing Example Data");
        initUsers();
        initProducts();
        initProductInteractions();
    }

    private void initUsers() {
        User customer = new User();
        customer.setUsername("customer");
        customer.setPassword(passwordEncoder.encode("password"));
        customer.setEmail("customer@example.com");
        customer.setRole("ROLE_CUSTOMER");
        userRepository.save(customer);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setEmail("admin@example.com");
        admin.setRole("ROLE_ADMIN");
        userRepository.save(admin);
    }

    private void initProducts() {
        String[] productNamesOne = {"Silk", "Wool", "Cotton", "Polyester", "Recycled Polyester",
                "Black", "White", "Grey", "Red", "Green", "Blue", "Yellow"};
        String[] productNamesTwo = {"Sweater", "Trousers", "Coat", "Jacket", "T-Shirt", "Hat",
                "Tablecloth", "Carpet", "Blanket", "Curtains", "Towel", "Bag"};

        for (int i = 0; i < 50; i++) {
            Product product = new Product();
            product.setName(
                    productNamesOne[random.nextInt(productNamesOne.length)] +
                    " " +
                    productNamesTwo[random.nextInt(productNamesTwo.length)]
            );
            product.setStock(random.nextInt(0,100));
            product.setPrice(getRandomPrice());

            productRepository.save(product);
        }
    }

    private void initProductInteractions() {
        final int numberOfInteractions = 200;

        final LocalDateTime timestamp = LocalDateTime.now().minusSeconds(numberOfInteractions);

        final List<User> users = userRepository.findAll();
        final List<Product> products = productRepository.findAll();
        ProductInteractionType[] productInteractionTypes = ProductInteractionType.values();


        for (int i = 0; i < numberOfInteractions; i++) {
            ProductInteraction productInteraction = new ProductInteraction();
            productInteraction.setUser(users.get(random.nextInt(users.size())));
            productInteraction.setProduct(products.get(random.nextInt(products.size())));
            productInteraction.setTimestamp(timestamp.plusSeconds(numberOfInteractions));
            productInteraction.setInteractionType(productInteractionTypes[random.nextInt(productInteractionTypes.length)]);

            productInteractionService.addProductInteraction(productInteraction);
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
