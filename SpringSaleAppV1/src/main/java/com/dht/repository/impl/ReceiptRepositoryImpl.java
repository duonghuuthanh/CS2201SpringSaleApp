
import com.dht.pojo.Cart;
import com.dht.pojo.OrderDetail;
import com.dht.pojo.SaleOrder;
import com.dht.repository.ProductRepository;
import com.dht.repository.ReceiptRepository;
import com.dht.repository.UserRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ReceiptRepositoryImpl implements ReceiptRepository {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addReceipt(List<Cart> carts) {
        Session s = this.factory.getObject().getCurrentSession();
        SaleOrder order = new SaleOrder();
        order.setCreatedDate(new Date());

        order.setUserId(this.userRepo.getUserByUsername("dhthanh"));
        s.persist(order);

        for (var cart : carts) {
            OrderDetail d = new OrderDetail();
            d.setProductId(this.productRepo.getProductById(cart.getId()));
            d.setQuantity(cart.getQuantity());
            d.setUnitPrice(cart.getPrice());

            s.persist(d);
        }
    }

}
