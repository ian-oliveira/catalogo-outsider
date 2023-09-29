package com.outsider.catalogo.repository;

import com.outsider.catalogo.domain.produto.Cor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CorRepository extends JpaRepository<Cor, Long> {

}
