package gs.gs.component;

import gs.gs.model.Product;
import gs.gs.model.ProductVariant;
import gs.gs.model.StockHandler;
import gs.gs.model.User;
import gs.gs.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    StockHandlerRepo stockHandlerRepo;
    @Autowired
    ProductVariantRepo productVariantRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    UserRepo userRepo;

    public DataLoader() {
    }

        @Override
        public void run (ApplicationArguments args) throws Exception {

            StockHandler goldenShoes = new StockHandler("Golden Shoes");
            stockHandlerRepo.save(goldenShoes);

            Product adidasTrainer = new Product("ADIDAS", "HAMBURG", "men", "https://d2ob0iztsaxy5v.cloudfront.net/product/340085/3400852050_zm.jpg", 60.00);
            productRepo.save(adidasTrainer);
            Product docMartenBoots = new Product("DR MARTENS", "VOSS QUAD FLUFFY", "women", "https://d2ob0iztsaxy5v.cloudfront.net/product/175236/1752367020_zm.jpg", 109.00);
            productRepo.save(docMartenBoots);
            Product birkenstockSandal = new Product("BIRKENSTOCK", "ARIZONA", "men", "https://d2ob0iztsaxy5v.cloudfront.net/product/330393/3303937060_zm.jpg", 60.00);
            productRepo.save(birkenstockSandal);
            Product clarksShoe = new Product("CLARKS", "WALLABEE", "men", "https://d2ob0iztsaxy5v.cloudfront.net/product/312099/3120996120_zm.jpg", 125.00);
            productRepo.save(clarksShoe);
            Product converseHiTop = new Product("CONVERSE", "ALL STAR HI TOP", "men", "https://d2ob0iztsaxy5v.cloudfront.net/product/340140/3401401070_zm.jpg", 57.00);
            productRepo.save(converseHiTop);
            Product ckBoot = new Product("CALVIN KLEIN", "PLATFORM BOOT", "women", "https://d2ob0iztsaxy5v.cloudfront.net/product/144253/1442537060_zm.jpg", 140.00);
            productRepo.save(ckBoot);
            Product sandal = new Product("HAVAIANAS", "SLIM", "women", "https://d2ob0iztsaxy5v.cloudfront.net/product/176172/1761722060_zm.jpg", 25.00);
            productRepo.save(sandal);
            Product heel = new Product("D AND G", "STILETTO", "women", "https://d2ob0iztsaxy5v.cloudfront.net/product/111222/1112222560_zm.jpg", 300.00);
            productRepo.save(heel);


            ProductVariant item14 = new ProductVariant(5,1, "yellow", sandal, goldenShoes);
            productVariantRepo.save(item14);
            ProductVariant item15 = new ProductVariant(3,1, "black", ckBoot, goldenShoes);
            productVariantRepo.save(item15);
            ProductVariant item16 = new ProductVariant(3,1, "grey", converseHiTop, goldenShoes);
            productVariantRepo.save(item16);
            ProductVariant item17 = new ProductVariant(5,2, "red", heel, goldenShoes);
            productVariantRepo.save(item17);
            ProductVariant item18 = new ProductVariant(6,2, "brown", birkenstockSandal, goldenShoes);
            productVariantRepo.save(item18);
            ProductVariant item13 = new ProductVariant(5, 1,"grey", clarksShoe, goldenShoes);
            productVariantRepo.save(item13);
            ProductVariant item1 = new ProductVariant(7,2, "black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item1);
            goldenShoes.addStock(item1);
            ProductVariant item19 = new ProductVariant(7, 2,"black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item19);
            goldenShoes.addStock(item19);
            ProductVariant item20 = new ProductVariant(7,2, "black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item20);
            goldenShoes.addStock(item20);
            ProductVariant item2 = new ProductVariant(6,2, "black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item2);
            goldenShoes.addStock(item2);
            ProductVariant item3 = new ProductVariant(5,1,"black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item3);
            goldenShoes.addStock(item3);
            ProductVariant item4 = new ProductVariant(4, 3, "black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item4);
            goldenShoes.addStock(item4);
            ProductVariant item5 = new ProductVariant(4,2,  "black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item5);
            goldenShoes.addStock(item5);
            ProductVariant item6 = new ProductVariant(7,1, "black", docMartenBoots, goldenShoes);
            productVariantRepo.save(item6);
            goldenShoes.addStock(item6);

            ProductVariant item7 = new ProductVariant(3,2, "yellow", adidasTrainer, goldenShoes);
            productVariantRepo.save(item7);
            goldenShoes.addStock(item7);
            ProductVariant item8 = new ProductVariant(6,2, "yellow", adidasTrainer, goldenShoes);
            productVariantRepo.save(item8);
            goldenShoes.addStock(item8);
            ProductVariant item9 = new ProductVariant(5,1, "yellow", adidasTrainer, goldenShoes);
            productVariantRepo.save(item9);
            goldenShoes.addStock(item9);
            ProductVariant item10 = new ProductVariant(5,2, "yellow", adidasTrainer, goldenShoes);
            productVariantRepo.save(item10);
            goldenShoes.addStock(item10);
            ProductVariant item11 = new ProductVariant(3, 1,"blue", adidasTrainer, goldenShoes);
            productVariantRepo.save(item11);
            goldenShoes.addStock(item11);
            ProductVariant item12 = new ProductVariant(7,2, "blue", adidasTrainer, goldenShoes);
            productVariantRepo.save(item12);
            goldenShoes.addStock(item12);

            //password is user123
            User user1 = new User("user123","$2a$10$FOpyzUjfMc8kxWUxrrRQyOc7GiOyKNM4ci0I08GdS4/lRrqqrRh6e","user123","user123","user123@gmail.com" );
            userRepo.save(user1);

           /* ArrayList<ProductVariant> orderList1 = new ArrayList<>();
            orderList1.add(item2);
            orderList1.add(item12);
            Order order1 = goldenShoes.makeOrder(user1, orderList1);
            order1.setOrderDate(LocalDate.parse("2022-03-09"));
            order1.setEtaDelivery(LocalDate.parse("2022-08-12"));
            order1.setDispatchedStatus(true);
            order1.setDeliveredStatus(true);
            orderRepo.save(order1);


            ArrayList<ProductVariant> orderList2 = new ArrayList<>();
            orderList2.add(item3);
            Order order2 = goldenShoes.makeOrder(user2, orderList2);
            order2.setOrderDate(LocalDate.parse("2022-03-07"));
            order2.setEtaDelivery((LocalDate.parse("2022-03-10")));
            order2.setDispatchedStatus(true);
            orderRepo.save(order2);

            productVariantRepo.save(item2);
            productVariantRepo.save(item12);
            productVariantRepo.save(item3);

*/
        }
    }