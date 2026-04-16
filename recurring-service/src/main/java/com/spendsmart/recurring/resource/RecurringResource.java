package com.spendsmart.recurring.resource;



import com.spendsmart.recurring.entity.RecurringTransaction;
import com.spendsmart.recurring.service.RecurringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recurring")
@RequiredArgsConstructor
public class RecurringResource {

    private final RecurringService service;

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RecurringTransaction rt) {
        return ResponseEntity.ok(service.addRecurring(rt));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<RecurringTransaction>> getByUser(@PathVariable int id) {
        return ResponseEntity.ok(service.getByUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/active/{userId}")
    public ResponseEntity<?> active(@PathVariable int userId) {
        return ResponseEntity.ok(service.getActiveRecurring(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody RecurringTransaction rt) {
        return ResponseEntity.ok(service.updateRecurring(id, rt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        service.deleteRecurring(id);
        return ResponseEntity.ok("Deleted");
    }
}