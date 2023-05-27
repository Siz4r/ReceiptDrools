import core.MainFrame;
import core.RulesService;
import models.Basket;
import models.ItemCity;
import models.products.Milk;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            new MainFrame();

            ItemCity item1 = new ItemCity();
            item1.setPurchaseCity(ItemCity.City.PUNE);
            item1.setTypeofItem(ItemCity.Type.MEDICINES);
            item1.setSellPrice(new BigDecimal(10));
            RulesService.getKsession().insert(item1);

            ItemCity item2 = new ItemCity();
            item2.setPurchaseCity(ItemCity.City.PUNE);
            item2.setTypeofItem(ItemCity.Type.GROCERIES);
            item2.setSellPrice(new BigDecimal(10));
            RulesService.getKsession().insert(item2);

            ItemCity item3 = new ItemCity();
            item3.setPurchaseCity(ItemCity.City.NAGPUR);
            item3.setTypeofItem(ItemCity.Type.MEDICINES);
            item3.setSellPrice(new BigDecimal(10));
            RulesService.getKsession().insert(item3);

            ItemCity item4 = new ItemCity();
            item4.setPurchaseCity(ItemCity.City.NAGPUR);
            item4.setTypeofItem(ItemCity.Type.GROCERIES);
            item4.setSellPrice(new BigDecimal(10));
            RulesService.getKsession().insert(item4);

            RulesService.getKsession().insert(Basket.getBasket());
            RulesService.getKsession().insert(new Milk("Mleko"));

            RulesService.getKsession().fireAllRules();

            System.out.println(item1.getPurchaseCity().toString() + " "
                    + item1.getLocalTax());

            System.out.println(item2.getPurchaseCity().toString() + " "
                    + item2.getLocalTax());

            System.out.println(item3.getPurchaseCity().toString() + " "
                    + item3.getLocalTax());

            System.out.println(item4.getPurchaseCity().toString() + " "
                    + item4.getLocalTax());

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
