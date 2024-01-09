package com.example.dailyshop.controller.RestController;

import com.example.dailyshop.model.entity.Photo;
import com.example.dailyshop.model.entity.Product;
import com.example.dailyshop.service.webservice.PhotoService;
import com.example.dailyshop.service.webservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/getProductBySupplier/{id}")
    //lấy ra toàn bộ sản phẩm theo nhà cung cấp
    public ResponseEntity<List<Product>> findProductBySupplier(@PathVariable Long id) {
        List<Product> productsList = productService.findProductBySupplierId(id);
        if (productsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productsList, HttpStatus.OK);
        }
    }


    @GetMapping("/getAllProduct")
    //lấy ra list danh sách sản phẩm
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = productService.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @PostMapping("/createProduct")
    //thêm mới một sản phẩm
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productNew = productService.save(product);
        return new ResponseEntity<>(productNew, HttpStatus.OK);
    }

    @PutMapping("/editProduct/{id}")
    //Sửa thông tin sản phẩm
    public ResponseEntity<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setProductID(id);
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    //Xóa một sản phẩm
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/searchProduct")
    //Tìm kiếm sản phẩm theo tên gần đúng.
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String name) {
        List<Product> listProduct = productService.findProductByproductNameContaining(name);
        if (listProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(listProduct, HttpStatus.OK);
        }
    }


    @GetMapping("/view/{id}")
    //Tìm kiếm thông tin một sản phẩm
    public ResponseEntity<Optional<Product>> viewProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/findProductById/{id}")
    public ResponseEntity<Optional<Product>> findProductById(@PathVariable Long id){
       Optional<Product> product = productService.findById(id);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

}
