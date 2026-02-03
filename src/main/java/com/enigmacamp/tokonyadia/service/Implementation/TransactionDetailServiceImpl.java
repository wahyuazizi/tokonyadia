package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.dto.request.TransactionDetailRequest;
import com.enigmacamp.tokonyadia.dto.response.TransactionDetailResponse;
import com.enigmacamp.tokonyadia.dto.response.TransactionResponse;
import com.enigmacamp.tokonyadia.entity.Product;
import com.enigmacamp.tokonyadia.entity.Transaction;
import com.enigmacamp.tokonyadia.entity.TransactionDetail;
import com.enigmacamp.tokonyadia.exception.OutOfStockException;
import com.enigmacamp.tokonyadia.repository.TransactionDetailRepository;
import com.enigmacamp.tokonyadia.service.ProductService;
import com.enigmacamp.tokonyadia.service.TransactionDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

    private final TransactionDetailRepository transactionDetailRepository;
    private final ProductService productService;

    public TransactionDetailServiceImpl(TransactionDetailRepository transactionDetailRepository,  ProductServiceImpl productService) {
        this.transactionDetailRepository = transactionDetailRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public TransactionDetail saveTransactionDetail(TransactionDetail transactionDetailPayload) {
        Product product = productService.getProductById(transactionDetailPayload.getProduct().getId());

        if (product.getStock() < transactionDetailPayload.getQuantity()) {
            throw new OutOfStockException("Out of Stock");
        }

        transactionDetailPayload.setProduct(product);
        transactionDetailPayload.setQuantity(transactionDetailPayload.getQuantity());
        transactionDetailPayload.setPriceSell(product.getProductPrice());

        //kurangin stock sesuai quantity
        product.setStock(product.getStock() - transactionDetailPayload.getQuantity());
        return transactionDetailRepository.save(transactionDetailPayload);

    }

    @Override
    public List<TransactionDetail> saveAllTransactionDetails(List<TransactionDetail> transactionDetails) {
        return transactionDetailRepository.saveAll(transactionDetails);
    }

    @Override
    public TransactionDetail getTransactionDetailById(UUID id) {
        return transactionDetailRepository.findById(id).orElse(null);
    }

    @Override
    public List<TransactionDetailResponse> getAllTransactionDetails() {

        List<TransactionDetail> transactionDetailList = transactionDetailRepository.findAll();
        Double total = transactionDetailList.stream().mapToDouble(detail-> detail.getQuantity()* detail.getPriceSell()).sum();

        List<TransactionDetailResponse> listTrxDetail = transactionDetailList.stream()
                .map(
                        detail-> TransactionDetailResponse.builder()
                                .id(detail.getId())
                                .product(detail.getProduct())
                                .quantity(detail.getQuantity())
                                .priceSell(detail.getPriceSell())
                                .subTotal(detail.getPriceSell()* detail.getQuantity())
                                .totalPrice(total)
                                .build()
                ).toList();
        return listTrxDetail;
    }

    @Override
    @Transactional
    public void cancelTransactionDetail(UUID id) {
        TransactionDetail transactionDetail = getTransactionDetailById(id);
        transactionDetail.setDeleted(Boolean.TRUE);
        transactionDetail.setDeletedAt(LocalDateTime.now());
        transactionDetailRepository.save(transactionDetail);
    }
//
//    @Override
//    public TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail) {
//        getTransactionDetailById(transactionDetail.getId());
//        return saveTransactionDetail(transactionDetail);
//    }
}
