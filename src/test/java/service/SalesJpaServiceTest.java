package service;

import config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.education.entity.Product;
import ru.education.entity.SalesJpa;
import ru.education.exceptions.EntityHasDetailsException;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.exceptions.EntityNotFoundException;
import ru.education.service.SalesJpaService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class SalesJpaServiceTest {

    @Autowired
    private SalesJpaService salesJpaService;

    //findAll
    @Test
    public void findAll_Test() {
        List<SalesJpa> salesJpaList = salesJpaService.findAll();
        Assert.assertEquals(6, salesJpaList.size());
    }

    //findById
    @Test(expected = EntityIllegalArgumentException.class)
    public void findById_NullId_Test() {
        salesJpaService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findById_NotParsedId_Test() {
        salesJpaService.findById(".dh21");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_NotFoundSale_Test() {
        salesJpaService.findById(10);
    }

    @Test
    public void findById_Test() {
        SalesJpa salesJpa = salesJpaService.findById(5);
        Assert.assertNotNull(salesJpa);
    }

    //create
    @Test(expected = EntityIllegalArgumentException.class)
    public void create_NullSale_Test() {
        salesJpaService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void create_NullProduct_Test() {
        SalesJpa salesJpa = new SalesJpa();
        salesJpa.setPrice(999);
        salesJpa.setCount(10);
        salesJpaService.create(salesJpa);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void create_NullProductId_Test() {
        SalesJpa salesJpa = new SalesJpa();
        salesJpa.setPrice(999);
        salesJpa.setCount(10);
        Product product = new Product();
        salesJpa.setProduct(product);
        salesJpaService.create(salesJpa);
    }

    @Test(expected = EntityNotFoundException.class)
    public void create_ProductNotExist_Test() {
        SalesJpa salesJpa = new SalesJpa();
        salesJpa.setPrice(999);
        salesJpa.setCount(10);
        Product product = new Product();
        product.setId(10);
        salesJpa.setProduct(product);
        salesJpaService.create(salesJpa);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void create_NullCount_Test() {
        SalesJpa salesJpa = new SalesJpa();
        salesJpa.setPrice(999);
        Product product = new Product();
        product.setId(1);
        salesJpa.setProduct(product);
        salesJpaService.create(salesJpa);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void create_NullPrice_Test() {
        SalesJpa salesJpa = new SalesJpa();
        salesJpa.setCount(10);
        Product product = new Product();
        product.setId(1);
        salesJpa.setProduct(product);
        salesJpaService.create(salesJpa);
    }

    @Test
    public void create_Test() {
        SalesJpa salesJpa = new SalesJpa();
        salesJpa.setCount(10);
        salesJpa.setPrice(999);
        Product product = new Product();
        product.setId(1);
        salesJpa.setProduct(product);
        salesJpaService.create(salesJpa);

        SalesJpa salesJpa1 = salesJpaService.findById(7);
        Assert.assertNotNull(salesJpa1);
    }

    //delete
    @Test(expected = EntityNotFoundException.class)
    public void delete_Test() {
        salesJpaService.delete(2);
        salesJpaService.findById(2);
    }
}
