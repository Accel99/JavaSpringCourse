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
import ru.education.exceptions.EntityHasDetailsException;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.exceptions.EntityNotFoundException;
import ru.education.service.ProductService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    //findAll
    @Test
    public void findAll_Test() {
        List<Product> products = productService.findAll();
        Assert.assertEquals(3, products.size());
    }

    //findById
    @Test(expected = EntityIllegalArgumentException.class)
    public void findById_NullId_Test() {
        productService.findById(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void findById_NotParsedId_Test() {
        productService.findById("4&&");
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_NotFoundProduct_Test() {
        productService.findById(10);
    }

    @Test
    public void findById_Test() {
        Product product = productService.findById(3);
        Assert.assertNotNull(product);
    }

    //create
    @Test(expected = EntityIllegalArgumentException.class)
    public void create_NullProduct_Test() {
        productService.create(null);
    }

    @Test(expected = EntityIllegalArgumentException.class)
    public void create_EmptyName_Test() {
        Product product = new Product();
        productService.create(product);
    }

    @Test
    public void create_Test() {
        Product product = new Product();
        product.setName("test_prod_4");
        productService.create(product);

        Product product1 = productService.findById(4);
        Assert.assertNotNull(product1);
    }

    //delete
    @Test(expected = EntityHasDetailsException.class)
    public void delete_HasDetails_Test() {
        productService.delete(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_Test() {
        productService.delete(2);
        productService.findById(2);
    }
}
