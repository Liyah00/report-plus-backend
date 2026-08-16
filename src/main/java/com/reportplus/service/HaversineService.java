package com.reportplus.service;

import com.reportplus.model.Organization;
import com.reportplus.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaversineService {

    private final OrganizationRepository organizationRepository;

    public HaversineService(
            OrganizationRepository organizationRepository
    ) {
        this.organizationRepository = organizationRepository;
    }

    // Earth Radius in KM
    private static final double EARTH_RADIUS = 6371;

    // Calculate distance between two GPS points
    public double calculateDistance(
            double lat1,
            double lon1,
            double lat2,
            double lon2
    ) {

        double dLat = Math.toRadians(lat2 - lat1);

        double dLon = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS * c;

    }


// FIND NEAREST POLICE STATION
public Organization findNearestPoliceStation(
        double latitude,
        double longitude
) {

    List<Organization> policeStations =
            organizationRepository.findByOrganizationTypeAndStatus(
                    "POLICE",
                    "ACTIVE"
            );

    if (policeStations.isEmpty()) {

        throw new RuntimeException(
                "No active police station found."
        );

    }

    Organization nearestStation = null;

    double shortestDistance = Double.MAX_VALUE;

    for (Organization station : policeStations) {

        if (station.getLatitude() == null ||
                station.getLongitude() == null) {

            continue;

        }

        double distance = calculateDistance(

                latitude,

                longitude,

                station.getLatitude(),

                station.getLongitude()

        );

        if (distance < shortestDistance) {

            shortestDistance = distance;

            nearestStation = station;

        }

    }

    return nearestStation;

}


}