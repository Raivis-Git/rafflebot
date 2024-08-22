package com.alphabot.register.repository;

import com.alphabot.register.module.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends JpaRepository<Guild, Long> {

    boolean existsByGuildId(String guildId);

    Guild findFirstByGuildId(String guildId);

}
