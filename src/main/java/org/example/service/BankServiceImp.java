package org.example.service;

import org.example.entity.BankItem;
import org.example.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImp implements BankService
{

    @Autowired
    BankRepository bankRepository;

    @Override
    public List<BankItem> getAllBankItems() {
        return bankRepository.findAll();
    }

    @Override
    public BankItem getByItemId(Long itemId) {
        return bankRepository.findById(itemId).orElse(null);
    }

    @Override
    public ResponseEntity<String> addBank(BankItem bankItem) {
        bankRepository.save(bankItem);
        bankItem.getCreateDate();
        return ResponseEntity.status(HttpStatus.OK).body("Successfully added");
    }

    @Override
    public void updateBank(BankItem bankItem) {
        bankRepository.save(bankItem);
        bankItem.getModDate();

    }

    @Override
    public void deleteBank(Long itemId ,BankItem bankItem) {
        bankRepository.deleteById(itemId);
        bankItem.getDeleteDate();


    }
}
