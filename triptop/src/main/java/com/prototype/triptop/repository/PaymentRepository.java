package com.prototype.triptop.repository;

import com.prototype.triptop.domain.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

//TODO: should be "extends"?
public class PaymentRepository implements CrudRepository<Payment, Integer> {

    @Override
    public <S extends Payment> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Payment> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Payment> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Payment> findAll() {
        return null;
    }

    @Override
    public Iterable<Payment> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Payment entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Payment> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
