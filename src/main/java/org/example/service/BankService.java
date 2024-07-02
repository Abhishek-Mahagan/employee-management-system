package org.example.service;

import org.example.entity.BankItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankService
{
    public List<BankItem> getAllBankItems();
    public BankItem getByItemId(Long itemId);
    public ResponseEntity<String> addBank(BankItem bankItem);
    public void updateBank(BankItem bankItem);
    public void deleteBank(Long itemId,BankItem bankItem);


}
