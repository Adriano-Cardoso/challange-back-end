package br.com.alura.challange.backend.feature;

import java.util.Optional;

import br.com.alura.challange.backend.domain.Profile;

public class ProfileScenarioFactory {
	
	public static final Object PROFILE = loadProfile();
	public static final Optional<Profile> PROFILE_OPTIONAL = loadProfileOptional();

	private static Profile loadProfile() {
		return new Profile(1L, "test");
	}

	private static Optional<Profile> loadProfileOptional() {
		return Optional.of(loadProfile());
	}

}
