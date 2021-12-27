package com.example.transactionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
public class TransactionController {
    @Resource
    private TransactionInfoAdapter transactionInfoAdapter;

    @Autowired
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello Transactions";
    }

    @GetMapping("/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.findAllTransactions();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable(name = "id") String id) {
        return transactionService.getTransactionById(id);
    }
    @GetMapping("/all/{userId}")
    public List<Transaction> getTransactionsByUserId(
            @PathVariable Integer userId,
            @RequestParam(defaultValue = "3") Integer count) {
        return transactionService.getTransactionsByUserId(userId, count);
    }

    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transactionRequest) throws Exception {
        Transaction transaction = new Transaction();
        ObjectMapper mapper = new ObjectMapper();

        String transactionInfo = transactionInfoAdapter.getTransactionInfo();
        Map<String, String> map = mapper.readValue(transactionInfo, Map.class);
        String id = map.get("id");
        LocalDateTime localDateTime = LocalDateTime.parse(map.get("dateOfExecution"));
        transactionService.createTransaction(id, localDateTime, transactionRequest);
        return transaction;
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable(name = "id")String id, @RequestBody Transaction transactionRequest) {
        return transactionService.updateTransaction(id, transactionRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable(name = "id") String id) {
        transactionService.deleteTransaction(id);
    }


}
