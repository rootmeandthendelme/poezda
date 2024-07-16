package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Platform;
import ru.tututu.trains.repo.PlatformRepo;

import java.sql.SQLException;

@Service
public class PlatformService {
    @Autowired
    private final PlatformRepo platformRepo;

    public PlatformService(PlatformRepo platformRepo) {
        this.platformRepo = platformRepo;
    }

    public Object[] getPlatformsArrayByLocalityName(String localityName) throws SQLException {
        return platformRepo.getAllPlatformsByLocalityName(localityName)
                .stream()
                .map(Platform::getId)
                .toArray(Object[]::new);
    }

    public Platform getPlatformById(int id) throws SQLException {
        return platformRepo.getPlatformById(id).orElseThrow();
    }
}
