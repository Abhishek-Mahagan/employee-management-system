    package org.example.controller;

    import org.example.entity.BankItem;
    import org.example.entity.LoginSession;
    import org.example.entity.User;
    import org.example.service.BankService;
    import org.example.service.LoginSessionService;
    import org.example.service.UserService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/BankItems")
    public class BankItemController {

        @Autowired
        private BankService bankService;

        @Autowired
        private UserService userService;

        @Autowired
        private LoginSessionService loginSessionService;

        @GetMapping("/getAllItems")
        public List<BankItem> getAllItems() {
            return bankService.getAllBankItems();
        }

        @GetMapping("/getItemById/{id}")
        public ResponseEntity<BankItem> getItemById(@PathVariable Long id) {
            Optional<BankItem> item = Optional.ofNullable(bankService.getByItemId(id));
            return item.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

//        @PostMapping("/addBankItem")
//        public ResponseEntity<String> addBankItem(@RequestHeader("id") Long id, @RequestBody BankItem bankItem) {
//            Optional<User> user = Optional.ofNullable(userService.getUserById(id));
//            Optional<BankItem> bankItem1= Optional.ofNullable(bankService.getByItemId(id));
//            if (user.isPresent()) {
//                // bankItem.setUser(user.get()); // Uncomment if BankItem has a User field
//                /*bankService.addBank(bankItem);
//                return ResponseEntity.status(HttpStatus.CREATED).body("BankItem successfully added");*/
//                if(bankItem1.isPresent())
//                {
//                    return ResponseEntity.status(HttpStatus.CREATED).body("BankItem is present already");
//
//                }
//                else {
//                    bankService.addBank(bankItem);
//                    return ResponseEntity.status(HttpStatus.CREATED).body("BankItem successfully added");
//                }
//            }
//            else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
//            }
//        }


        @PostMapping("/addBankItem")
        public ResponseEntity<String> addBankItem(@RequestHeader("sessionId") Long sessionId, @RequestBody BankItem bankItem) {
            LoginSession loginSession = loginSessionService.findById(sessionId);
            BankItem ExistingbankItem=bankService.getByItemId(loginSession.getUserId());
            if (loginSession != null) {
                Optional<User> user = Optional.ofNullable(userService.getUserById(loginSession.getUserId()));
                if (user.isPresent()) {
                   if(ExistingbankItem!=null)
                   {
                       return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("BankItem is Already present");
                   }
                   else {
                       // bankItem.setUser(user.get()); // Uncomment if BankItem has a User field
                       bankService.addBank(bankItem);
                       return ResponseEntity.status(HttpStatus.CREATED).body("BankItem successfully added");
                   }
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid session");
            }
        }




        @PutMapping("/updateBankItem/{id}")
        public ResponseEntity<String> updateBankItem(@PathVariable Long id, @RequestBody BankItem bankItem) {
            Optional<BankItem> existingItem = Optional.ofNullable(bankService.getByItemId(id));
            if (existingItem.isPresent()) {
                // bankItem.setItemId(id);
                bankService.updateBank(bankItem);
                return ResponseEntity.ok("BankItem successfully updated");
            } else {
                return ResponseEntity.notFound().build();
            }
        }


        @DeleteMapping("/deleteBankItem/{id}")
        public ResponseEntity<String> deleteBankItem(@PathVariable Long id) {
            Optional<BankItem> existingItem = Optional.ofNullable(bankService.getByItemId(id));
            if (existingItem.isPresent()) {
                bankService.deleteBank(id, existingItem.get());
                return ResponseEntity.ok("BankItem successfully deleted");
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }