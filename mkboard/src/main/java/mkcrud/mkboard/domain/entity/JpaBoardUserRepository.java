package mkcrud.mkboard.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBoardUserRepository extends JpaRepository<BoardUser, Long> {
}
