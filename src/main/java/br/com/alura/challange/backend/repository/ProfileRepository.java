package br.com.alura.challange.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.challange.backend.domain.Profile;
import br.com.alura.challange.backend.domain.User;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Optional<User> findByName(String name);
}
