package ui.ft.ccit.faculty.transaksi.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ui.ft.ccit.faculty.transaksi.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
