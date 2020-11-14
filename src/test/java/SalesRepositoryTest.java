import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.education.entity.Product;
import ru.education.entity.SalesJpa;
import ru.education.jpa.SalesJpaRepository;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SalesRepositoryTest {

    @Autowired
    private SalesJpaRepository salesJpaRepository;

    @Test
    public void findAllSalesTest() {
        List<SalesJpa> salesJpaList = salesJpaRepository.findAll();
        Assert.assertNotNull(salesJpaList);
    }

    @Test
    public void delSalesTest() {
        salesJpaRepository.deleteById(1);

        Optional<SalesJpa> salesJpa = salesJpaRepository.findById(1);
        Assert.assertEquals(Optional.empty(), salesJpa);
    }

    @Test
    public void addSalesTest() {
        Product product = new Product();
        product.setId(2);
        SalesJpa sales = new SalesJpa(0, product, 99, 999);
        salesJpaRepository.save(sales);

        SalesJpa sales1 = salesJpaRepository.findById(7).get();
        Assert.assertNotNull(sales1);
    }
}
