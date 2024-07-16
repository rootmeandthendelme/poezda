package ru.tututu.trains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tututu.trains.entity.Platform;
import ru.tututu.trains.repo.PlatformRepo;

import java.sql.SQLException;
import java.util.List;

@Service
public class PlatformService {
    @Autowired
    private final PlatformRepo platformRepo;

    public PlatformService(PlatformRepo platformRepo) {
        this.platformRepo = platformRepo;
    }

    public List<Platform> getPlatformsArrayByLocalityName(String localityName) throws SQLException {
        return platformRepo.getAllPlatformsByLocalityName(localityName);
    }

    public Platform getPlatformById(int id) throws SQLException {
        return platformRepo.getPlatformById(id).orElseThrow();
    }
}
