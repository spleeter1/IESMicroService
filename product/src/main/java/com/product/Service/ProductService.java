package com.product.Service;

import com.product.DTO.ProductReserveBatchRequest;
import com.product.DTO.ProductReserveRequest;
import com.product.Model.Product;
import com.product.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    @Transactional
    public void reserveProductBatch (ProductReserveBatchRequest request){
        for(ProductReserveRequest item : request.getItems()){
            Product product = productRepository.findById(item.getProductId()).orElseThrow();
            if(product.getQuantity() < item.getQuantity()){
                throw new RuntimeException("Not enough stock: "+ product.getName() );
            }
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }
    }

    @Transactional
    public void handleImportOrder(ProductReserveBatchRequest request) {
        for (ProductReserveRequest item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId()).orElseThrow();
            product.setQuantity(product.getQuantity() + item.getQuantity());
        }
    }
}