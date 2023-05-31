import core.MainFrame;
import core.RulesService;
import models.Basket;
import models.ItemCity;
import models.Receipt;
import models.products.Product;
import models.products.TaxType;
import org.kie.api.KieServices;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            new MainFrame();

            RulesService.getKsession().insert(Basket.getBasket());
            Basket basket = Basket.getBasket();
            basket.promotion();

            RulesService.getKsession().insert(basket);
            RulesService.getKsession().fireAllRules();
            RulesService.getKsession().dispose();
            RulesService.initializeEngine(KieServices.Factory.get());
            RulesService.getKsession().insert(basket);
            RulesService.getKsession().fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
